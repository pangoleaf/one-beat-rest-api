package com.pangoleaf.obra.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(unique=true)
    @NonNull private String name;
    
    @NonNull private String country;
    @NonNull private Integer startYear;
    @NonNull private Integer endYear;
    
    @OneToMany(mappedBy="artist")
    @Builder.Default
    private List<Album> listAlbums = new ArrayList<>();

}
