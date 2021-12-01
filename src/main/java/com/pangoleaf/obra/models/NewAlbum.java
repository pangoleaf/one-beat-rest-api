package com.pangoleaf.obra.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.Getter;

@Getter @Data
public class NewAlbum {
    private String name;
    private Integer year;
    private Integer runTime;
    private List<Track> tracks = new ArrayList<>();
    
    private String artistName;
    private Integer artistId;
    private String runTimeStr;
}
