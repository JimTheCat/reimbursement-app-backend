package com.project.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationManagerTest {

    private String expectedValue = "{\"allowanceRate\":15.0,\"mileageRate\":0.3,\"maxReimbursement\":1500.0,\"receipts\":{\"Taxi\":10.0,\"Hotel\":200.0,\"Air ticket\":30.0,\"Train\":40.0,\"Uber\":150.0}}";

    @BeforeAll
    private static void beforeClass() {
        ConfigurationManager.getInstance().setMetadataPath("src/test/resources/test.json");
    }

    @Test
    void loadMetadataFile() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ConfigurationManager.getInstance().loadMetadataFile();
        Metadata metadata = ConfigurationManager.getInstance().getCurrentMetadata();
        assertEquals(expectedValue, objectMapper.writeValueAsString(metadata));
    }
}