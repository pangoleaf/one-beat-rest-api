package com.pangoleaf.obra.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pangoleaf.obra.models.Track;

@Repository
public interface TrackRepo extends JpaRepository<Track, Integer> {

}
