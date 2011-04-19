package com.rolfbenz.lp.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by IntelliJ IDEA.
 * User: U987
 * Date: 04.04.11
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class Cutterauftrag {

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Id
    private long id;
    private String sammelauftragsnummer;
    private int auftragsnummer;
    private String sainame;
    private float sollmenge;
    private float istmenge;

    @ManyToOne
    private Cutterzuweisung cutterzuweisung;

    public Cutterauftrag() {
    }

    public Cutterzuweisung getCutterzuweisung() {
        return cutterzuweisung;
    }

    public void setCutterzuweisung(Cutterzuweisung cutterzuweisung) {
        this.cutterzuweisung = cutterzuweisung;
    }

    public Cutterauftrag(String sammelauftragsnummer, int auftragsnummer, String sainame, float sollmenge, float istmenge) {
        this.sammelauftragsnummer = sammelauftragsnummer;
        this.auftragsnummer = auftragsnummer;
        this.sainame = sainame;
        this.sollmenge = sollmenge;
        this.istmenge = istmenge;
    }

    public String getSammelauftragsnummer() {

        return sammelauftragsnummer;
    }

    public void setSammelauftragsnummer(String sammelauftragsnummer) {
        this.sammelauftragsnummer = sammelauftragsnummer;
    }

    public int getAuftragsnummer() {
        return auftragsnummer;
    }

    public void setAuftragsnummer(int auftragsnummer) {
        this.auftragsnummer = auftragsnummer;
    }

    public String getSainame() {
        return sainame;
    }

    public void setSainame(String sainame) {
        this.sainame = sainame;
    }

    public float getSollmenge() {
        return sollmenge;
    }

    public void setSollmenge(float sollmenge) {
        this.sollmenge = sollmenge;
    }

    public float getIstmenge() {
        return istmenge;
    }

    public void setIstmenge(float istmenge) {
        this.istmenge = istmenge;
    }
}
