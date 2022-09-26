package org.example;

import org.example.config.Configuration;
import org.example.config.ConfigurationManager;

public class HttpServer {
    public static void main(String[] args){
        System.out.println("server starting ...");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();
        System.out.println("port: " + conf.getPort());
        System.out.println("webroot: " + conf.getWebroot());

    }
}
