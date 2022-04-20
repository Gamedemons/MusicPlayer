package com.manthanrajoria.musicplayer;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Playlist {
    private String name;
    private ArrayList<String> songs = new ArrayList<>();

    public Playlist(String name){
        this.name = name;
    }

    public String toString(){

        return name + " : "  ;
    }
}
