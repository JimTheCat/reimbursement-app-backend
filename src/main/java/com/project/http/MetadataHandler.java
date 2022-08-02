package com.project.http;

import com.project.config.ConfigurationManager;
import com.project.config.Metadata;
import com.project.util.Json;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class MetadataHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        ConfigurationManager.getInstance().loadMetadataFile();
        Metadata metadata = ConfigurationManager.getInstance().getCurrentMetadata();

        if ("GET".equals(exchange.getRequestMethod())) {

            exchange.sendResponseHeaders(200, Json.toJson(metadata).toString().getBytes().length);
            OutputStream output = exchange.getResponseBody();
            output.write(Json.toJson(metadata).toString().getBytes());
            output.flush();
            exchange.close();
        }
        else if ("POST".equals(exchange.getRequestMethod())){
            try {
                ConfigurationManager.getInstance().saveMetadataFile(exchange.getRequestBody());
                exchange.sendResponseHeaders(200, 0);
            } catch (IOException e){
                exchange.sendResponseHeaders(400, -1);
                e.printStackTrace();
            }
            exchange.close();
        }
    }
}
