package com.mcommandes.web.controller;

import com.mcommandes.clients.ProductRestClient;
import com.mcommandes.dao.CommandesDao;
import com.mcommandes.dto.CommandeDTO;
import com.mcommandes.dto.ProductDTO;
import com.mcommandes.model.Commande;
import com.mcommandes.model.Product;
import com.mcommandes.web.exceptions.CommandeNotFoundException;
import com.mcommandes.web.exceptions.ImpossibleAjouterCommandeException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CommandeController {

    @Autowired
    CommandesDao commandesDao;
    ProductRestClient productRestClient;

    public CommandeController(CommandesDao commandesDao, ProductRestClient productRestClient) {
        this.commandesDao = commandesDao;
        this.productRestClient = productRestClient;
    }

    @PostMapping(value = "/commandes")
    public ResponseEntity<CommandeDTO> ajouterCommande(@RequestBody Commande commande) {
        Product product = productRestClient.findProductById(commande.getProductId());

        Commande nouvelleCommande = commandesDao.save(commande);

        if(nouvelleCommande == null) throw new ImpossibleAjouterCommandeException("Impossible d'ajouter cette commande");

        CommandeDTO commandeDto = new CommandeDTO();
        BeanUtils.copyProperties(nouvelleCommande, commandeDto);

        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);

        commandeDto.setProduct(productDTO);

        return new ResponseEntity<CommandeDTO>(commandeDto, HttpStatus.CREATED);
    }

    @GetMapping(value = "/commandes/{id}")
    public Optional<CommandeDTO> recupererUneCommande(@PathVariable int id){

        Optional<Commande> commande = commandesDao.findById(id);

        if(!commande.isPresent()) throw new CommandeNotFoundException("Cette commande n'existe pas");

        Product product = productRestClient.findProductById(commande.get().getProductId());

        CommandeDTO commandeDto = new CommandeDTO();
        BeanUtils.copyProperties(commande, commandeDto);

        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);

        commandeDto.setProduct(productDTO);

        return Optional.of(commandeDto);
    }
}
