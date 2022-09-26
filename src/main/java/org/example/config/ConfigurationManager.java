package org.example.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.example.util.Json;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ConfigurationManager {
    private static ConfigurationManager configurationManager;
    private static Configuration currentConfiguration;

    public static ConfigurationManager getInstance(){
        if(configurationManager==null)
            configurationManager = new ConfigurationManager();
        return configurationManager;
    }

    public void loadConfigurationFile(String filePath) {
        //FileReader fileReader = new FileReader(filePath);
        StringBuffer sb = new StringBuffer();
        Path path = Paths.get(filePath);
        List<String> lines = null;
        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            throw new HttpConfigurationException(e);
        }
        for (String s: lines) {
            sb.append(s);
        }
        JsonNode conf = null;
        try {
            conf = Json.parse(sb.toString());
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error parsing the Configuration File.", e);
        }
        try {
            currentConfiguration = Json.fromJson(conf,Configuration.class);
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error parsing the Configuration File internal.", e);
        }
    }



    public Configuration getCurrentConfiguration(){
        if(currentConfiguration==null)
            throw new HttpConfigurationException("No current configuration.");
        return currentConfiguration;

    }
}
