package com.wira_fkom.crud_firebase;

import java.io.Serializable;

public class Dosen implements Serializable {

    private String nik;

    private
    String nama;

    private String ja;

    private
    String key;

    public Dosen() {
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJa() {
        return ja;
    }

    public void setJa(String ja) {
        this.ja = ja;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String toString() {
        return " " + nik + "" +
                "" + nama + "" +
                "" + ja;
    }

    public Dosen(String nk, String nm, String j) {
        nik = nk;
        nama = nm;
        ja = j
        ;
    }
}