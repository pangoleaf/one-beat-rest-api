package com.pangoleaf.obra.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
}
