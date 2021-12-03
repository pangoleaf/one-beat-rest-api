package com.pangoleaf.obra.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Set;

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
import com.pangoleaf.obra.models.Album;
import com.pangoleaf.obra.models.Artist;
import com.pangoleaf.obra.models.Track;

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
        String artistJSON = this.mapper.writeValueAsString(
            Artist.builder().name("Aphex Twin").country("United Kingdom").startYear(1985).build()
        );
        String artistJSONResponse = this.mapper.writeValueAsString(
            Artist.builder().id(13).name("Aphex Twin").country("United Kingdom").startYear(1985).build()
        );
        
        RequestBuilder request = post("/artist").contentType(MediaType.APPLICATION_JSON).content(artistJSON);
        
        ResultMatcher status = status().isCreated();
        ResultMatcher content = content().json(artistJSONResponse);
        
        this.mvc.perform(request).andExpect(status).andExpect(content);
    }

    
    @Test
    void getArtistTest() throws Exception {
        String artistJSON = this.mapper.writeValueAsString(
            Artist.builder()
            .id(5)
            .name("Godspeed You! Black Emperor")
            .country("Canada")
            .startYear(1990)
            .endYear(null)
            .albums(Set.of(
                Album.builder()
                .id(25)
                .name("Slow Riot for New Zero Kanada")
                .year(1998)
                .length(1716)
                .tracks(Set.of(
                        Track.builder()
                        .id(252)
                        .trackNumber(1)
                        .name("Moya")
                        .length(652)
                        .build(),
                        Track.builder()
                        .id(253)
                        .trackNumber(2)
                        .name("Blaise Bailey Finnegan III")
                        .length(1065)
                        .build()))
                .build()))
            .build()
        );
        
        RequestBuilder request = get("/artist/5");
        
        ResultMatcher status = status().isOk();
        ResultMatcher content = content().json(artistJSON);
        
        this.mvc.perform(request).andExpect(status).andExpect(content);
    }
}
