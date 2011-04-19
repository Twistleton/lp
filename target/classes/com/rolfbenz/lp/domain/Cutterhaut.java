package com.rolfbenz.lp.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by IntelliJ IDEA.
 * User: U987
 * Date: 04.04.11
 * Time: 11:17
 * To change this template use File | Settings | File Templates.
 */

@Entity

public class Cutterhaut {

    @Id
    private long id;
    private int   hautnummer;
    private float lieferantenmass;
    private float maschinen_original;
    private float abschreibung;
    private float abschreibung_original;
    @ManyToOne
    private Cutterzuweisung cutterzuweisung;


    public Cutterhaut() {
    }

    public Cutterzuweisung getCutterzuweisung() {
        return cutterzuweisung;
    }

    public void setCutterzuweisung(Cutterzuweisung cutterzuweisung) {
        this.cutterzuweisung = cutterzuweisung;
    }



    public float getAbweichung() {
        return abweichung;
    }

    public void setAbweichung(float abweichung) {
        this.abweichung = abweichung;
    }

    private float abweichung;

    public Cutterhaut(long id, int hautnummer, float lieferantenmass, float maschinen_original, float abschreibung, float abschreibung_original, float abweichung) {
        this.id = id;
        this.hautnummer = hautnummer;
        this.lieferantenmass = lieferantenmass;
        this.maschinen_original = maschinen_original;
        this.abschreibung = abschreibung;
        this.abschreibung_original = abschreibung_original;
        this.abweichung = abweichung;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getHautnummer() {
        return hautnummer;
    }

    public String getHautnummerToString() {
        return Integer.toString(hautnummer);
    }

    public void setHautnummer(int hautnummer) {
        this.hautnummer = hautnummer;
    }

    public float getLieferantenmass() {
        return lieferantenmass;
    }

    public void setLieferantenmass(float lieferantenmass) {
        this.lieferantenmass = lieferantenmass;
    }

    public float getMaschinen_original() {
        return maschinen_original;
    }

    public void setMaschinen_original(float maschinen_original) {
        this.maschinen_original = maschinen_original;
    }

    public float getAbschreibung() {
        return abschreibung;
    }

    public void setAbschreibung(float abschreibung) {
        this.abschreibung = abschreibung;
    }

    public float getAbschreibung_original() {
        return abschreibung_original;
    }

    public void setAbschreibung_original(float abschreibung_original) {
        this.abschreibung_original = abschreibung_original;
    }

    public Cutterhaut(long id, float abschreibung, float abschreibung_original, String sainame) {

        this.id = id;
        this.abschreibung = abschreibung;
        this.abschreibung_original = abschreibung_original;
    }



}
