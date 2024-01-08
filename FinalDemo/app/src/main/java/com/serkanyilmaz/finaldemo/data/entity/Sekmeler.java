package com.serkanyilmaz.finaldemo.data.entity;

import java.io.Serializable;

public class Sekmeler implements Serializable {
    private String sekmeAd;
    private String sekmeResim;

    public Sekmeler() {
    }

    public Sekmeler(String sekmeAd, String sekmeResim) {
        this.sekmeAd = sekmeAd;
        this.sekmeResim = sekmeResim;
    }

    public String getSekmeAd() {
        return sekmeAd;
    }

    public void setSekmeAd(String sekmeAd) {
        this.sekmeAd = sekmeAd;
    }

    public String getSekmeResim() {
        return sekmeResim;
    }

    public void setSekmeResim(String sekmeResim) {
        this.sekmeResim = sekmeResim;
    }
}
