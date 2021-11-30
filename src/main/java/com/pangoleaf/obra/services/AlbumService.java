package com.pangoleaf.obra.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pangoleaf.obra.models.Album;
import com.pangoleaf.obra.models.Artist;
import com.pangoleaf.obra.repos.AlbumRepo;
import com.pangoleaf.obra.repos.ArtistRepo;

@Service
public class AlbumService {
    @Autowired
    private AlbumRepo repo;
    @Autowired
    private ArtistRepo artistRepo;

    // CRUD

    public Album createAlbum(Album newAlbum) {
        Artist artist;
        if (newAlbum.getArtist().getId() != null) 
            artist = this.artistRepo.findById(newAlbum.getArtist().getId()).orElseThrow();
        else if (newAlbum.getArtist().getName() != null) 
            artist = this.artistRepo.findFirstByNameIgnoreCase(newAlbum.getArtist().getName()).get();
        else
            artist = new Artist();
        
        newAlbum.setArtist(artist);
        return this.repo.save(newAlbum);
    }

}
