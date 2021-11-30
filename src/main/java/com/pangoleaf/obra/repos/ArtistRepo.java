package com.pangoleaf.obra.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pangoleaf.obra.models.Artist;

@Repository
public interface ArtistRepo extends JpaRepository<Artist, Integer> {
    
    Optional<Artist> findFirstByNameIgnoreCase(String name);
}
