package com.pangoleaf.obra.services;

import java.util.Optional;

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

    public Album createAlbum(Album album) {
        Artist artist = new Artist();  // throw exception instead
        if (album.getArtist() != null) {
            if (album.getArtist().getId() != null) 
                artist = this.artistRepo.findById(album.getArtist().getId()).orElseThrow();
            else if (album.getArtist().getName() != null) 
                artist = this.artistRepo.findFirstByNameIgnoreCase(album.getArtist().getName()).orElseThrow();
        }
        return this.repo.save(album.setArtist(artist));
    }
    
    public Optional<Album> getAlbum(Integer id) {
        return this.repo.findById(id);
    }

}
