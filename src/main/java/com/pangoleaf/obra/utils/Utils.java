package com.pangoleaf.obra.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class Utils {
    public static URI getUri(String path, String id) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().replacePath(path + "/" + id).build().toUri();
    }
    
    public static URI getUri(String path) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().replacePath(path).build().toUri();
    }
    
    public static String getTestData (String name) {
        String fileContents = null;
        try {
            fileContents = Files.readString(Paths.get(ClassLoader.getSystemResource("test-cases/" + name + ".json").toURI()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return fileContents;
    }
}
