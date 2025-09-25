# Managing micro services

## Spring Cloud Gateway

![spring_gateway_1.png](./ressources/images/spring_gateway_1.png)

Le rôle de la gateway est de diriger une requête vers le bon microservice.
La route de la requête est caratérisé par :

1. l'URI
2. Le prédicat (une condition à laquelle doit répondre la requête)
3. Les filtres (module le contenu de la requête)

| Fonction                         | Description                                                                 |
|----------------------------------|-----------------------------------------------------------------------------|
| **Routage dynamique**            | Redirige les requêtes selon le chemin, les headers, les paramètres         |
| **Authentification centralisée** | Vérifie les JWT ou tokens OAuth2 avant de transmettre la requête           |
| **Rate limiting**                | Limite le nombre de requêtes par IP ou utilisateur                         |
| **Circuit breaker**              | Coupe les appels vers un service défaillant (via Resilience4j ou Hystrix) |
| **Transformation des requêtes**  | Modifie les headers, le corps ou les paramètres avant routage              |
| **Logging & monitoring**         | Centralise les logs et les métriques des appels                            |

## 
