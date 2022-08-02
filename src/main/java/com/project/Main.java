package com.project;

import com.project.config.Configuration;
import com.project.config.ConfigurationManager;
import com.project.http.FormHandler;
import com.project.http.MetadataHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Server is starting!");

        ConfigurationManager.getInstance().setConfigurationPath("src/main/resources/http.json");
        ConfigurationManager.getInstance().setMetadataPath("src/main/resources/metadata.json");
        ConfigurationManager.getInstance().setFormPath("src/main/resources/order.json");
        ConfigurationManager.getInstance().loadConfigurationFile();
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        System.out.println("Using Port: " + conf.getPort());

        HttpServer server = HttpServer.create(new InetSocketAddress(conf.getPort()), 0);
        server.createContext("/api/metadata", new MetadataHandler());
        server.createContext("/api/form", new FormHandler());
        server.setExecutor(null);
        server.start();
    }
}