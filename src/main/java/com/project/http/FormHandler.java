package com.project.http;

import com.project.config.ConfigurationManager;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class FormHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        if ("GET".equals(exchange.getRequestMethod())) {
            //TODO: Add possibility to get information about claims
        }
        else if ("POST".equals(exchange.getRequestMethod())) {
            try {
                ConfigurationManager.getInstance().saveFormFile(exchange.getRequestBody());
                exchange.sendResponseHeaders(200, 0);
            } catch (IOException e){
                exchange.sendResponseHeaders(400, -1);
                e.printStackTrace();
            }
            exchange.close();
        }
    }
}
