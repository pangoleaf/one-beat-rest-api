package com.pangoleaf.obra.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pangoleaf.obra.models.Album;
import com.pangoleaf.obra.repos.TrackRepo;

@Service
public class TrackService {
    @Autowired
    private TrackRepo repo;

    // CRUD

    public Album createTracksFromAlbum(Album album) {
        album.getTracks().forEach(t -> this.repo.save(t.setAlbum(album)));
        return album;
    }
}
