package com.example.uts.bokingfutsal.domain;

/**
 * Created by cymas on 29/10/15.
 */
public class Booking {
    private String id;
    private String nama;
    private String mulaijam;
    private String waktu;
    private String berapajam;
    private String lapangan;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getMulaijam() {
        return mulaijam;
    }

    public void setMulaijam(String mulaijam) {
        this.mulaijam = mulaijam;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getBerapajam() {
        return berapajam;
    }

    public void setBerapajam(String berapajam) {
        this.berapajam = berapajam;
    }

    public String getLapangan() {
        return lapangan;
    }

    public void setLapangan(String lapangan) {
        this.lapangan = lapangan;
    }
}
