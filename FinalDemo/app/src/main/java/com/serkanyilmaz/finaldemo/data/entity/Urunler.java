package com.serkanyilmaz.finaldemo.data.entity;

import java.io.Serializable;

public class Urunler implements Serializable {
    private int id;
    private String ad;

    private String aciklama;
    private String resim;
    private int fiyat;

    public Urunler() {
    }

    public Urunler(int id, String ad, String aciklama, String resim, int fiyat) {
        this.id = id;
        this.ad = ad;
        this.aciklama = aciklama;
        this.resim = resim;
        this.fiyat = fiyat;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getResim() {
        return resim;
    }

    public void setResim(String resim) {
        this.resim = resim;
    }

    public int getFiyat() {
        return fiyat;
    }

    public void setFiyat(int fiyat) {
        this.fiyat = fiyat;
    }
}
