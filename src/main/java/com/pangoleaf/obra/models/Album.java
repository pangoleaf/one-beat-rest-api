package com.pangoleaf.obra.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor @RequiredArgsConstructor
@Getter @Setter @Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name="artist_id", referencedColumnName="id")
    @JsonBackReference
    private Artist artist;
    
//    private Integer artistId;
    private String name;
    private Integer year;
    private Integer runTime;
    
    @OneToMany(mappedBy="album")
    @Builder.Default
//    @JsonManagedReference(value="tracks")
    private Set<Track> tracks = new HashSet<>();
    
    public String runTimeReadable () {
        return Math.floor(this.runTime / 60) + ":" + this.runTime % 60;
    }

}
