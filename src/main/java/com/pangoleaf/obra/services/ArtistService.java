package com.pangoleaf.obra.services;

import org.springframework.stereotype.Service;

import com.pangoleaf.obra.models.Artist;
import com.pangoleaf.obra.repos.ArtistRepo;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArtistService {
    @NonNull private ArtistRepo repo;
    
    public Artist createArtist(Artist artist) {
        return this.repo.save(artist);
    }
}
