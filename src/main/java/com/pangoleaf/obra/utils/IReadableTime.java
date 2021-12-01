package com.pangoleaf.obra.utils;

import java.util.Arrays;

public interface IReadableTime {
    Integer getLength ();

    default String getLengthStr () {
        return Integer.valueOf((int) Math.floor(this.getLength() / 60)) + ":" + this.getLength() % 60;
    }
    
    static Integer lengthStrToInt (String lengthStr) {
        return lengthStr.matches("(?:(?:([0-9]+):)?([0-5]?\\d):)?([0-5]?\\d)" )
            ? Arrays.stream(lengthStr.split(":")).mapToInt(Integer::parseInt).reduce(0, (a, b) -> a * 60 + b)
            : null;
    }
}
