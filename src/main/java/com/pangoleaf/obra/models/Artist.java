package com.pangoleaf.obra.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity
@AllArgsConstructor @RequiredArgsConstructor
@Getter @Setter @Accessors @Builder @ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(unique=true)
    private String name;
    
    private String country;
    private Integer startYear;
    private Integer endYear;
    
    @OneToMany(mappedBy="artist", cascade=CascadeType.ALL)
    @Builder.Default
//    @JsonManagedReference(value="albums")
    private Set<Album> albums = new HashSet<>();
    
}
