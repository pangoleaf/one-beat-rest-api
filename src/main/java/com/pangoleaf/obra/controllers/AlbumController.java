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

import com.pangoleaf.obra.models.Album;
import com.pangoleaf.obra.services.AlbumService;
import com.pangoleaf.obra.utils.Utils;

@RestController
@RequestMapping(path = "/album")
public class AlbumController extends BaseController {
    @Autowired AlbumService service;
    
    @PostMapping
    public ResponseEntity<Album> createAlbum(@RequestBody Album album) {
        Album newAlbum = this.service.createAlbum(album);
        URI uri = Utils.getUri(this.mapping, Integer.toString(newAlbum.getId()));
        return ResponseEntity.created(uri).body(newAlbum);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Album> findAlbumById(@PathVariable("id") Integer id) {
        return ResponseEntity.of(this.service.getAlbum(id));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Album> updateAlbum(@RequestBody Album album, @PathVariable Integer id) {
        return ResponseEntity.ok().body(this.service.updateAlbum(album, id));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAlbum(@PathVariable Integer id) {
        return this.service.deleteAlbum(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
