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
import com.pangoleaf.obra.utils.IReadableTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Accessors @Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Album implements IReadableTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name="artist_id", referencedColumnName="id")
    @JsonBackReference
    private Artist artist;
    
    private String name;
    private Integer year;
    private Integer length;
    
    @OneToMany(mappedBy="album")
    @Builder.Default
//    @JsonManagedReference(value="tracks")
    private Set<Track> tracks = new HashSet<>();
    

}
