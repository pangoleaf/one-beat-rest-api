package com.pangoleaf.obra.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pangoleaf.obra.models.Artist;
import com.pangoleaf.obra.repos.ArtistRepo;

@Service
public class ArtistService {
    @Autowired private ArtistRepo repo;
    
    @Autowired private AlbumService albumService;
    
    public Artist createArtist(Artist artist) {
        return this.repo.save(artist);
    }
    
    public Optional<Artist> getArtist(Integer id) {
        return this.repo.findById(id);
    }
}
