package com.mproduits.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Cette classe permet de récupérer les données de configuration,
 * Qui sera exposée à travers le microservice
 */
@ConfigurationProperties(prefix = "global.params")
public class GlobalConfig {
    private int a;
    private int b;

    public GlobalConfig(int p1, int p2) {
        this.a = p1;
        this.b = p2;
    }

    public GlobalConfig() {}

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}
