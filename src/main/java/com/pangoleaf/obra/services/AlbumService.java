package com.pangoleaf.obra.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    
    List<String> allowedSearchFields = List.of(
            "name", "year", "length"
    );

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
    
    public List<Album> getAllAlbums(Map<String,String> params) {
        Map<String,String> filteredParams = 
                params.entrySet().stream()
                    .filter(e -> this.allowedSearchFields.contains(e.getKey()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        
        Album egAlbum = new ObjectMapper().convertValue(filteredParams, Album.class);
        ExampleMatcher matcher = ExampleMatcher.matchingAll();
        return this.repo.findAll(Example.of(egAlbum, matcher));
    }
    
    public Optional<Album> getAlbum(Integer id) {
        return this.repo.findById(id);
    }
    
    public Album updateAlbum(Album album, Integer id) {
        Album albumToUpdate = this.repo.findById(id).get()
                .setName(album.getName())
                .setYear(album.getYear())
                .setLength(album.getLength())
                .setTracks(album.getTracks());
        return this.repo.save(albumToUpdate);
    }
    
    public boolean deleteAlbum(Integer id) {
        try {
            this.repo.deleteById(id);
            return !this.repo.existsById(id);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
