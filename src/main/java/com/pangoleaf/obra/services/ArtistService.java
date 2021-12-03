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
import com.pangoleaf.obra.models.Artist;
import com.pangoleaf.obra.repos.ArtistRepo;

@Service
public class ArtistService {
    @Autowired private ArtistRepo repo;
    
    @Autowired private AlbumService albumService;
    
    List<String> allowedSearchFields = List.of(
            "name", "country", "startYear", "endYear"
    );
    
    public Artist createArtist(Artist artist) {
        return this.repo.save(artist);
    }
    
    public List<Artist> getAllArtists(Map<String,String> params) {
        Map<String,String> filteredParams = 
                params.entrySet().stream()
                    .filter(e -> this.allowedSearchFields.contains(e.getKey()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        
        Artist egArtist = new ObjectMapper().convertValue(filteredParams, Artist.class);
        ExampleMatcher matcher = ExampleMatcher.matchingAll();
        return this.repo.findAll(Example.of(egArtist, matcher));
    }
  
    public Optional<Artist> getArtist(Integer id) {
        return this.repo.findById(id);
    }
    
    public Artist updateArtist(Artist artist, Integer id) {
        Artist artistToUpdate = this.repo.findById(id).get()
                .setName(artist.getName())
                .setCountry(artist.getCountry())
                .setStartYear(artist.getStartYear())
                .setEndYear(artist.getEndYear());
        return this.repo.save(artistToUpdate);
    }
    
    public boolean deleteArtist(Integer id) {
        try {
            this.repo.deleteById(id);
            return !this.repo.existsById(id);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
