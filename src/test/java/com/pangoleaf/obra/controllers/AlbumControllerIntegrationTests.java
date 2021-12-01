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
import com.pangoleaf.obra.models.Album;
import com.pangoleaf.obra.models.Artist;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
// @Sql(scripts = {"classpath:test-schema.sql", "classpath:test-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class AlbumControllerIntegrationTests {
    @Autowired
    private MockMvc mvc;
    
    @Autowired
    private ObjectMapper mapper;

    @Test
    void createAlbumTest() throws Exception {
        Artist artist = new Artist(); // add album to existing artist from dummy data... implement dummy data
        String albumJSON = this.mapper.writeValueAsString(
                Album.builder()
                    .artist(artist)
                    .name("Opeth")
                    .name("Ghost Reveries")
                    .year(2005)
                    .runTime(4003)
                    .build()
        );
        String albumJSONResponse = this.mapper.writeValueAsString(
                Album.builder()
                .id(1)
                .artist(artist)
                .name("Opeth")
                .name("Ghost Reveries")
                .year(2005)
                .runTime(4003)
                .build()
        );
        
        RequestBuilder request = post("/album").contentType(MediaType.APPLICATION_JSON).content(albumJSON);
        
        ResultMatcher status = status().isCreated();
        ResultMatcher content = content().json(albumJSONResponse);
        
        this.mvc.perform(request).andExpect(status).andExpect(content);
    }
}
