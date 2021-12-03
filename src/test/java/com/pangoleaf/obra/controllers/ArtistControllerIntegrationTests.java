package com.pangoleaf.obra.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pangoleaf.obra.utils.Utils;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:test-schema.sql", "classpath:test-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class ArtistControllerIntegrationTests {
    @Autowired
    private MockMvc mvc;
    
    @Autowired
    private ObjectMapper mapper;

    @Test
    void createArtistTest() throws Exception {
        String artistJSON = Utils.getTestData("create-artist-req");
        String artistJSONResponse = Utils.getTestData("create-artist-res");
        
        RequestBuilder request = post("/artist").contentType(MediaType.APPLICATION_JSON).content(artistJSON);
        
        ResultMatcher status = status().isCreated();
        ResultMatcher content = content().json(artistJSONResponse);
        
        this.mvc.perform(request).andExpect(status).andExpect(content);
    }

    
    @Test
    void getArtistTest() throws Exception {
        String artistJSON = Utils.getTestData("get-artist");
        
        RequestBuilder request = get("/artist/5");
        
        ResultMatcher status = status().isOk();
        ResultMatcher content = content().json(artistJSON);
        
        this.mvc.perform(request).andExpect(status).andExpect(content);
    }
    
    @Test
    void updateAritstTest() throws Exception {
        String artistJSON = Utils.getTestData("put-artist");
        
        RequestBuilder request = put("/artist/5").contentType(MediaType.APPLICATION_JSON).content(artistJSON);
        
        ResultMatcher status = status().isOk();
        ResultMatcher content = content().json(artistJSON);
        
        this.mvc.perform(request).andExpect(status).andExpect(content);
    }
    
    @Test
    void deleteArtistTest() throws Exception {
        this.mvc.perform(delete("/artist/2")).andExpect(status().isNoContent());
    }
}
