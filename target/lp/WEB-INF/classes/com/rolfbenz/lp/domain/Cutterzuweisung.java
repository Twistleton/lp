package com.rolfbenz.lp.domain;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: U987
 * Date: 04.04.11
 * Time: 14:02
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class Cutterzuweisung {

    @Id
    private long id;

    @OneToMany (mappedBy = "cutterzuweisung")
    private List<Cutterhaut> cutterhautList;

    @OneToMany (mappedBy = "cutterzuweisung")
    private List<Cutterauftrag> cutterauftragList;

    @OneToMany (mappedBy = "cutterzuweisung")
    private List<Cutterteammitglied> cutterteammitgliedList;

    private Timestamp aenderungsdatum;

    private Timestamp erstelldatum;

    private String grund;

    private String info;

    private String kriterium;

    private int rejectedrows;

    private String sainame;

    private int status;

    private double sumabschreibung;

    private double sumsurfmes;

    private double sumsurfreste;

    private double sumsurfsup;

    private double verbrauchmes;

    private double verbrauchsup;

    @Embedded
    private Cutterartikel cutterartikel;

    public Cutterzuweisung() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getVerbrauchsup() {
        return verbrauchsup;
    }

    public void setVerbrauchsup(double verbrauchsup) {
        this.verbrauchsup = verbrauchsup;
    }

    public Timestamp getAenderungsdatum() {
        return aenderungsdatum;
    }

    public void setAenderungsdatum(Timestamp aenderungsdatum) {
        this.aenderungsdatum = aenderungsdatum;
    }

    public Timestamp getErstelldatum() {
        return erstelldatum;
    }

    public void setErstelldatum(Timestamp erstelldatum) {
        this.erstelldatum = erstelldatum;
    }

    public String getGrund() {
        return grund;
    }

    public void setGrund(String grund) {
        this.grund = grund;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getKriterium() {
        return kriterium;
    }

    public void setKriterium(String kriterium) {
        this.kriterium = kriterium;
    }

    public int getRejectedrows() {
        return rejectedrows;
    }

    public void setRejectedrows(int rejectedrows) {
        this.rejectedrows = rejectedrows;
    }

    public String getSainame() {
        return sainame;
    }

    public void setSainame(String sainame) {
        this.sainame = sainame;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getSumabschreibung() {
        return sumabschreibung;
    }

    public void setSumabschreibung(double sumabschreibung) {
        this.sumabschreibung = sumabschreibung;
    }

    public double getSumsurfmes() {
        return sumsurfmes;
    }

    public void setSumsurfmes(double sumsurfmes) {
        this.sumsurfmes = sumsurfmes;
    }

    public double getSumsurfreste() {
        return sumsurfreste;
    }

    public void setSumsurfreste(double sumsurfreste) {
        this.sumsurfreste = sumsurfreste;
    }

    public double getSumsurfsup() {
        return sumsurfsup;
    }

    public void setSumsurfsup(double sumsurfsup) {
        this.sumsurfsup = sumsurfsup;
    }

    public double getVerbrauchmes() {
        return verbrauchmes;
    }

    public void setVerbrauchmes(double verbrauchmes) {
        this.verbrauchmes = verbrauchmes;
    }

    public Cutterartikel getCutterartikel() {
        return cutterartikel;
    }

    public void setCutterartikel(Cutterartikel cutterartikel) {
        this.cutterartikel = cutterartikel;
    }

    public List<Cutterteammitglied> getCutterteammitgliedList() {
        return cutterteammitgliedList;
    }

    public void setCutterteammitgliedList(List<Cutterteammitglied> cutterteammitgliedList) {
        this.cutterteammitgliedList = cutterteammitgliedList;
    }

    public List<Cutterhaut> getCutterhautList() {
        return cutterhautList;
    }

    public void setCutterhautList(List<Cutterhaut> cutterhautList) {
        this.cutterhautList = cutterhautList;
    }

    public List<Cutterauftrag> getCutterauftragList() {
        return cutterauftragList;
    }

    public void setCutterauftragList(List<Cutterauftrag> cutterauftragList) {
        this.cutterauftragList = cutterauftragList;
    }

}
