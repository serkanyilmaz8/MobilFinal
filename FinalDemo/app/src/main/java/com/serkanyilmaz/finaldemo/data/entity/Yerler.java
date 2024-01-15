package com.serkanyilmaz.finaldemo.data.entity;

import java.io.Serializable;

public class Yerler implements Serializable {
    private int id;
    private String ad;

    private String aciklama;
    private String resim;

    public Yerler() {
    }

    public Yerler(int id, String ad, String aciklama, String resim) {
        this.id = id;
        this.ad = ad;
        this.aciklama = aciklama;
        this.resim = resim;
    }

    public int getId() {
        return id;
    }

    public String getAd() {
        return ad;
    }

    public String getAciklama() {
        return aciklama;
    }

    public String getResim() {
        return resim;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public void setResim(String resim) {
        this.resim = resim;
    }
}
