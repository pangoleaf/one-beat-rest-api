package com.pangoleaf.obra.utils;

import java.net.URI;
import java.util.Arrays;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class Utils {
    public static URI getUri(String path, String id) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().replacePath(path + "/" + id).build().toUri();
    }
    
    public static URI getUri(String path) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().replacePath(path).build().toUri();
    }
    
    public static Integer runTimeStrToSeconds(String runTimeStr) {
        return Arrays.stream(runTimeStr.split(":")).mapToInt(Integer::parseInt).reduce((a,b) -> a*60+b).orElseThrow();
    }
}
