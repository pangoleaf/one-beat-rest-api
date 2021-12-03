package com.pangoleaf.obra.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@AllArgsConstructor @RequiredArgsConstructor
@Getter @Setter @Accessors @Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Track {  // implements IReadableTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="album_id", referencedColumnName="id")
//    @JsonBackReference
    private Album album;
    
    private Integer trackNumber;
    private String name;
    private Integer length;
//    @Transient
//    private String lengthStr;
}
