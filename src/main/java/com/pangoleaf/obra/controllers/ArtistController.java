package com.pangoleaf.obra.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    
    @GetMapping("/{id}")
    public ResponseEntity<Artist> findArtistById(@PathVariable("id") Integer id) {
        return ResponseEntity.of(this.service.getArtist(id));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Artist> updateArtist(@RequestBody Artist artist, @PathVariable Integer id) {
        return ResponseEntity.ok().body(this.service.updateArtist(artist, id));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArtist(@PathVariable Integer id) {
        return this.service.deleteArtist(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
