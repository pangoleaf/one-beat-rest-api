package com.pangoleaf.obra.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Album {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name="artist_id", referencedColumnName="id")
    private Artist artist;
    
    private String name;
    private Integer year;
    private Integer runTime;
    
    @OneToMany(mappedBy="album")
    @Builder.Default
    private List<Track> listTracks = new ArrayList<>();
    
    public String runTimeReadable () {
        return Math.floor(this.runTime / 60) + ":" + this.runTime % 60;
    }

}
