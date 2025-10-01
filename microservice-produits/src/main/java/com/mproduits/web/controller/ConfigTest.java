package com.mproduits.web.controller;

import com.mproduits.config.GlobalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ConfigTest {

    /**
     * Cette annotation permet d'exploiter les données de configuration
     * Elle est néanmois redondante, on préferera passer la classe de config comme GlobalConfig ci dessous
     */
    @Value("${global.params.a}")
    private int p1;
    @Value("${global.params.b}")
    private int p2;

    /**
     * Permet de récuperer toutes les configuration dans une seule propriété
     */
    @Autowired
    private GlobalConfig globalConfig;

    @GetMapping("/config")
    Map<String, Integer> getParam1() {
        return Map.of("p1", p1, "p2", p2);
    }

    @GetMapping("/global-config")
    GlobalConfig getGlobalConfig() {
        return globalConfig;
    }
}
