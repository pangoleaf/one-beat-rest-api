package com.pangoleaf.obra.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pangoleaf.obra.models.Album;

@Repository
public interface AlbumRepo extends JpaRepository<Album, Integer> {

}
