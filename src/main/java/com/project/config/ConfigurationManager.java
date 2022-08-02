package com.project.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.util.Json;

import java.io.*;
import java.util.Arrays;

public class ConfigurationManager {

    private static ConfigurationManager myConfigurationManager;
    private static Configuration myCurrentConfiguration;
    private static Metadata myCurrentMetadata;
    private static Order[] myCurrentOrder;
    private static String configurationFilePath;
    private static String metadataFilePath;
    private static String formFilePath;

    private ConfigurationManager() {}

    public static ConfigurationManager getInstance(){
        if (myConfigurationManager == null)
            myConfigurationManager = new ConfigurationManager();
        return myConfigurationManager;
    }

    public void setConfigurationPath(String filePath){
        configurationFilePath = filePath;
    }

    public void setMetadataPath(String filePath){
        metadataFilePath = filePath;
    }

    public void setFormPath(String filePath){
        formFilePath = filePath;
    }

    public void loadConfigurationFile(){
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(configurationFilePath);
        } catch (FileNotFoundException e) {
            throw new HttpConfigurationException(e);
        }
        StringBuffer sb = new StringBuffer();
        int i;
        try {
            while ((i = fileReader.read()) != -1) {
                sb.append((char) i);
            }
        } catch (IOException e){
            throw new HttpConfigurationException(e);
        }

        JsonNode conf = null;
        try {
            conf = Json.parse(sb.toString());
        } catch (IOException e) {
            throw new HttpConfigurationException("Error parsing the Configuration File", e);
        }
        try {
            myCurrentConfiguration = Json.fromJson(conf, Configuration.class);
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error parsing the Configuration File, internal", e);
        }
    }

    public void saveMetadataFile(InputStream input) throws IOException {
        FileWriter fileWriter = new FileWriter(metadataFilePath);
        StringBuilder stringBuilder = new StringBuilder();

        for (int ch; (ch = input.read()) != -1;){
            stringBuilder.append((char) ch);
        }
        JsonNode jsonNode = Json.parse(String.valueOf(stringBuilder));
        fileWriter.write(jsonNode.toString());
        fileWriter.close();
    }

    public void saveFormFile(InputStream input) throws IOException {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(formFilePath);
        } catch (FileNotFoundException e) {
            throw new HttpConfigurationException(e);
        }
        StringBuffer stringBuffer = new StringBuffer();
        int i;
        try {
            while ((i = fileReader.read()) != -1) {
                stringBuffer.append((char) i);
            }
        } catch (IOException e){
            throw new HttpConfigurationException(e);
        }

        JsonNode conf = null;
        try {
            conf = Json.parse(stringBuffer.toString());
        } catch (IOException e) {
            throw new HttpConfigurationException("Error parsing the Form File", e);
        }
        try {
            myCurrentOrder = Json.fromJson(conf, Order[].class);
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error parsing the Form File, internal", e);
        }
        fileReader.close();

        Order[] tmp = new Order[myCurrentOrder.length+1];

        for( int j = 0; j < myCurrentOrder.length; j++){
            tmp[j] = myCurrentOrder[j];
        }


        ObjectMapper objectMapper = new ObjectMapper();
        FileWriter fileWriter = new FileWriter(formFilePath);
        StringBuilder sb = new StringBuilder();

        for (int ch; (ch = input.read()) != -1;){
            sb.append((char) ch);
        }
        JsonNode jsonNode = Json.parse(String.valueOf(sb));
        tmp[tmp.length-1] = Json.fromJson(jsonNode, Order.class);
        fileWriter.write(objectMapper.writeValueAsString(tmp));
        fileWriter.close();
    }

    public void loadMetadataFile(){
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(metadataFilePath);
        } catch (FileNotFoundException e) {
            throw new HttpConfigurationException(e);
        }
        StringBuffer sb = new StringBuffer();
        int i;
        try {
            while ((i = fileReader.read()) != -1) {
                sb.append((char) i);
            }
        } catch (IOException e){
            throw new HttpConfigurationException(e);
        }

        JsonNode conf = null;
        try {
            conf = Json.parse(sb.toString());
        } catch (IOException e) {
            throw new HttpConfigurationException("Error parsing the Metadata File", e);
        }
        try {
            myCurrentMetadata = Json.fromJson(conf, Metadata.class);
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error parsing the Metadata File, internal", e);
        }
    }

    public Configuration getCurrentConfiguration() {
        if ( myCurrentConfiguration == null){
            throw new HttpConfigurationException("No current configuration!");
        }
        return myCurrentConfiguration;
    }

    public Metadata getCurrentMetadata() {
        if ( myCurrentMetadata == null){
            throw new HttpConfigurationException("No current metadata!");
        }
        return myCurrentMetadata;
    }
}
