package com.pangoleaf.obra.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pangoleaf.obra.models.Artist;
import com.pangoleaf.obra.repos.ArtistRepo;

@Service
public class ArtistService {
    @Autowired private ArtistRepo repo;
    
    @Autowired private AlbumService albumService;
    
    public Artist createArtist(Artist artist) {
        Artist newArtist = this.repo.save(artist);
        newArtist.getAlbums().forEach(a -> albumService.createAlbum(a.setArtist(artist)));
        return newArtist;
    }
}
