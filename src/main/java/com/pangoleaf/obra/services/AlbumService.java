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
    @Autowired
    private TrackService trackService;

    // CRUD

    public Album createAlbum(Album album) {
        Artist artist;
        if (album.getArtist().getId() != null) 
            artist = this.artistRepo.findById(album.getArtist().getId()).orElseThrow();
        else if (album.getArtist().getName() != null) 
            artist = this.artistRepo.findFirstByNameIgnoreCase(album.getArtist().getName()).get();
        else
            artist = new Artist();
        
        return trackService.createTracksFromAlbum(this.repo.save(album.setArtist(artist)));
    }

}
