package com.pangoleaf.obra.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pangoleaf.obra.services.ArtistService;

@RestController
@RequestMapping(path = "/artist")
public class ArtistController extends BaseController{
    ArtistService service;
    

}
