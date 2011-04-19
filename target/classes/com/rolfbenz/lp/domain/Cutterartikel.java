package com.rolfbenz.lp.domain;

import javax.persistence.*;

/**
 * Cutterartikel embbeded in Cutterzuweisung
 * User: U987
 * Date: 04.04.11
 */

@Embeddable
public class Cutterartikel {

    @Column(name="artikel_artikelnummer")
    private int artikelnummer;

    @Column(name="artikel_lieferant", length = 50)
    private String lieferant;

    @Column(name="artikel_charge")
    private int charge;

    public int getArtikelnummer() {
        return artikelnummer;
    }

    public void setArtikelnummer(int artikelnummer) {
        this.artikelnummer = artikelnummer;
    }

    public String getLieferant() {
        return lieferant;
    }

    public void setLieferant(String lieferant) {
        this.lieferant = lieferant;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public Cutterartikel(int artikelnummer, String lieferant, int charge) {
        this.artikelnummer = artikelnummer;
        this.lieferant = lieferant;
        this.charge = charge;
    }
}
