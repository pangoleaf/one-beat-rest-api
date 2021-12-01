package com.pangoleaf.obra.utils;

import java.net.URI;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class Utils {
    public static URI getUri(String path, String id) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().replacePath(path + "/" + id).build().toUri();
    }
    
    public static URI getUri(String path) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().replacePath(path).build().toUri();
    }
}
