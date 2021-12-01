package com.pangoleaf.obra.utils;

public interface ITimeInSeconds {
    Integer getLength ();

    default String getlengthStr () {
        return Math.floor(this.getLength() / 60) + ":" + this.getLength() % 60;
    }
}
