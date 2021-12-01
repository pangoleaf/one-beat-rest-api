package com.pangoleaf.obra.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pangoleaf.obra.models.Artist;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
// @Sql(scripts = {"classpath:test-schema.sql", "classpath:test-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class ArtistControllerIntegrationTests {
    @Autowired
    private MockMvc mvc;
    
    @Autowired
    private ObjectMapper mapper;

    @Test
    void createArtistTest() throws Exception {
        String artistJSON = this.mapper.writeValueAsString(
            Artist.builder().name("Opeth").country("Sweden").startYear(1990).build()
        );
        String artistJSONResponse = this.mapper.writeValueAsString(
            Artist.builder().id(1).name("Opeth").country("Sweden").startYear(1990).build()
        );
        
        RequestBuilder request = post("/artist").contentType(MediaType.APPLICATION_JSON).content(artistJSON);
        
        ResultMatcher status = status().isCreated();
        ResultMatcher content = content().json(artistJSONResponse);
        
        this.mvc.perform(request).andExpect(status).andExpect(content);
    }
}
