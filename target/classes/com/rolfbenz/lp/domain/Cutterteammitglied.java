package com.rolfbenz.lp.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * User: Speer, Rolf
 * Date: 29.03.2011
 */

@Entity
public class Cutterteammitglied {

    @Id
    private long id;
    private int personalnummer;
    private String vorname;
    private String nachname;

    @ManyToOne
    private Cutterzuweisung cutterzuweisung;

    public Cutterteammitglied() {

    }

    public Cutterteammitglied(long id, int personalnummer, String vorname, String nachname) {

        this.id = id;
        this.personalnummer = personalnummer;
        this.vorname = vorname;
        this.nachname = nachname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPersonalnummer() {
        return personalnummer;
    }

    public void setPersonalnummer(int personalnummer) {
        this.personalnummer = personalnummer;
    }

    public Cutterzuweisung getCutterzuweisung() {
        return cutterzuweisung;
    }

    public void setCutterzuweisung(Cutterzuweisung cutterzuweisung) {
        this.cutterzuweisung = cutterzuweisung;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }
}


