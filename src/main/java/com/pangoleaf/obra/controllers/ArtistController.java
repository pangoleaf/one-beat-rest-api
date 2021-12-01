package com.pangoleaf.obra.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pangoleaf.obra.models.Artist;
import com.pangoleaf.obra.services.ArtistService;
import com.pangoleaf.obra.utils.Utils;

@RestController
@RequestMapping(path = "/artist")
public class ArtistController extends BaseController {
    @Autowired ArtistService service;
    
    @PostMapping
    public ResponseEntity<Artist> createArtist(@RequestBody Artist artist) {
        Artist newArtist = this.service.createArtist(artist);
        URI uri = Utils.getUri(this.mapping, Integer.toString(newArtist.getId()));
        return ResponseEntity.created(uri).body(newArtist);
    }
}
