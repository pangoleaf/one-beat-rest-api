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
import org.springframework.test.context.TestPropertySource;
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
@TestPropertySource(locations="classpath:application-test.properties")
@Sql(scripts = {"classpath:test-schema.sql", "classpath:test-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class AlbumControllerIntegrationTests {
    @Autowired
    private MockMvc mvc;
    
    @Autowired
    private ObjectMapper mapper;

    @Test
    void createAlbumTest() throws Exception {
        String albumJSON = Utils.getTestData("create-album-req");
        String albumJSONResponse = Utils.getTestData("create-album-res");
        
        RequestBuilder request = post("/album").contentType(MediaType.APPLICATION_JSON).content(albumJSON);
        
        ResultMatcher status = status().isCreated();
        ResultMatcher content = content().json(albumJSONResponse, false);
        
        this.mvc.perform(request).andExpect(status).andExpect(content);
    }
    
    @Test
    void getAlbumTest() throws Exception {
        String albumJSON = Utils.getTestData("get-album");
                
        RequestBuilder request = get("/album/25");
        
        ResultMatcher status = status().isOk();
        ResultMatcher content = content().json(albumJSON);
        
        this.mvc.perform(request).andExpect(status).andExpect(content);
    }
    
    @Test
    void getAllAlbumsTest() throws Exception {
        String allArtistsJSON = Utils.getTestData("get-all-albums");
        
        RequestBuilder request = get("/album");
        
        ResultMatcher status = status().isOk();
        ResultMatcher content = content().json(allArtistsJSON);
        
        this.mvc.perform(request).andExpect(status).andExpect(content);
    }
    
    @Test
    void updateArtistTest() throws Exception {
        String artistJSON = Utils.getTestData("put-album");
        
        RequestBuilder request = put("/album/25").contentType(MediaType.APPLICATION_JSON).content(artistJSON);
        
        ResultMatcher status = status().isOk();
        ResultMatcher content = content().json(artistJSON);
        
        this.mvc.perform(request).andExpect(status).andExpect(content);
    }
    
    @Test
    void deleteAlbumTest() throws Exception {
        this.mvc.perform(delete("/album/32")).andExpect(status().isNoContent());
    }
}
