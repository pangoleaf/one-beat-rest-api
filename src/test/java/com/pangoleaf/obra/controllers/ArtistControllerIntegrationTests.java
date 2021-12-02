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
    
//    insert into artist (id,name,country,start_year,end_year) values (5,'Godspeed You! Black Emperor','Canada',1994,null);
//    insert into album (id,name,year,length,artist_id) values (23,'Lift Your Skinny Fists Like Antennas to Heaven',2000,5244,5);
//    insert into track (id,track_number,name,length,album_id) values (245,1,'Storm',1352,23);
//    insert into track (id,track_number,name,length,album_id) values (246,2,'Static',1356,23);
//    insert into track (id,track_number,name,length,album_id) values (247,1,'Sleep',1398,23);
//    insert into track (id,track_number,name,length,album_id) values (248,2,'Like Antennas To Heaven…',1138,23);
//    insert into album (id,name,year,length,artist_id) values (24,'F♯ A♯ ∞',1997,3808,5);
//    insert into track (id,track_number,name,length,album_id) values (249,1,'The Dead Flag Blues',988,24);
//    insert into track (id,track_number,name,length,album_id) values (250,2,'East Hastings',1078,24);
//    insert into track (id,track_number,name,length,album_id) values (251,3,'Providence',1742,24);
//    insert into album (id,name,year,length,artist_id) values (25,'Slow Riot for New Zero Kanada',1998,0,5);
//    insert into track (id,track_number,name,length,album_id) values (252,1,'Moya',652,25);
//    insert into track (id,track_number,name,length,album_id) values (253,2,'Blaise Bailey Finnegan III',1065,25);
    
    @Test
    void getArtistTest() throws Exception {
        String artistJSON = this.mapper.writeValueAsString(
            Artist.builder()
            .id(5)
            .name("Godspeed You! Black Emperor")
            .country("Canada")
            .startYear(1990)
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
