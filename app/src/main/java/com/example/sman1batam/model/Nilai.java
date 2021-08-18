package com.example.sman1batam.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Nilai implements Serializable {
    public int id;
    public int nilai;
    public Mapel mapel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNilai() {
        return nilai;
    }

    public void setNilai(int nilai) {
        this.nilai = nilai;
    }

    public Mapel getMapel() {
        return mapel;
    }

    public void setMapel(Mapel mapel) {
        this.mapel = mapel;
    }


}