package com.rolfbenz.lp.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigInteger;

@javax.persistence.Table(name = "BDE_MADAT", schema = "PLRV11")
@Entity
public class BdeMadatEntity {
    private int maPnr;

    @javax.persistence.Column(name = "MA_PNR")
    @Id
    public int getMaPnr() {
        return maPnr;
    }

    public void setMaPnr(int maPnr) {
        this.maPnr = maPnr;
    }

    private String maNname;

    @javax.persistence.Column(name = "MA_NNAME")
    @Basic
    public String getMaNname() {
        return maNname;
    }

    public void setMaNname(String maNname) {
        this.maNname = maNname;
    }

    private String maVname;

    @javax.persistence.Column(name = "MA_VNAME")
    @Basic
    public String getMaVname() {
        return maVname;
    }

    public void setMaVname(String maVname) {
        this.maVname = maVname;
    }

    private int maKst;

    @javax.persistence.Column(name = "MA_KST")
    @Basic
    public int getMaKst() {
        return maKst;
    }

    public void setMaKst(int maKst) {
        this.maKst = maKst;
    }

    private int maTel;

    @javax.persistence.Column(name = "MA_TEL")
    @Basic
    public int getMaTel() {
        return maTel;
    }

    public void setMaTel(int maTel) {
        this.maTel = maTel;
    }

    private String maAp;

    @javax.persistence.Column(name = "MA_AP")
    @Basic
    public String getMaAp() {
        return maAp;
    }

    public void setMaAp(String maAp) {
        this.maAp = maAp;
    }

    private BigInteger maMaxAb;

    @javax.persistence.Column(name = "MA_MAX_AB")
    @Basic
    public BigInteger getMaMaxAb() {
        return maMaxAb;
    }

    public void setMaMaxAb(BigInteger maMaxAb) {
        this.maMaxAb = maMaxAb;
    }

    private String maAnmeld;

    @javax.persistence.Column(name = "MA_ANMELD")
    @Basic
    public String getMaAnmeld() {
        return maAnmeld;
    }

    public void setMaAnmeld(String maAnmeld) {
        this.maAnmeld = maAnmeld;
    }

    private BigInteger maRecht;

    @javax.persistence.Column(name = "MA_RECHT")
    @Basic
    public BigInteger getMaRecht() {
        return maRecht;
    }

    public void setMaRecht(BigInteger maRecht) {
        this.maRecht = maRecht;
    }

    private int maPin;

    @javax.persistence.Column(name = "MA_PIN")
    @Basic
    public int getMaPin() {
        return maPin;
    }

    public void setMaPin(int maPin) {
        this.maPin = maPin;
    }

    private String maProd;

    @javax.persistence.Column(name = "MA_PROD")
    @Basic
    public String getMaProd() {
        return maProd;
    }

    public void setMaProd(String maProd) {
        this.maProd = maProd;
    }

    private String maUser;

    @javax.persistence.Column(name = "MA_USER")
    @Basic
    public String getMaUser() {
        return maUser;
    }

    public void setMaUser(String maUser) {
        this.maUser = maUser;
    }

    private BigInteger maWerk;

    @javax.persistence.Column(name = "MA_WERK")
    @Basic
    public BigInteger getMaWerk() {
        return maWerk;
    }

    public void setMaWerk(BigInteger maWerk) {
        this.maWerk = maWerk;
    }

    private int maTeam;

    @javax.persistence.Column(name = "MA_TEAM")
    @Basic
    public int getMaTeam() {
        return maTeam;
    }

    public void setMaTeam(int maTeam) {
        this.maTeam = maTeam;
    }

    private String maTeamKz;

    @javax.persistence.Column(name = "MA_TEAM_KZ")
    @Basic
    public String getMaTeamKz() {
        return maTeamKz;
    }

    public void setMaTeamKz(String maTeamKz) {
        this.maTeamKz = maTeamKz;
    }

    private String maDrucker;

    @javax.persistence.Column(name = "MA_DRUCKER")
    @Basic
    public String getMaDrucker() {
        return maDrucker;
    }

    public void setMaDrucker(String maDrucker) {
        this.maDrucker = maDrucker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BdeMadatEntity that = (BdeMadatEntity) o;

        if (maKst != that.maKst) return false;
        if (maPin != that.maPin) return false;
        if (maPnr != that.maPnr) return false;
        if (maTeam != that.maTeam) return false;
        if (maTel != that.maTel) return false;
        if (maAnmeld != null ? !maAnmeld.equals(that.maAnmeld) : that.maAnmeld != null) return false;
        if (maAp != null ? !maAp.equals(that.maAp) : that.maAp != null) return false;
        if (maDrucker != null ? !maDrucker.equals(that.maDrucker) : that.maDrucker != null) return false;
        if (maMaxAb != null ? !maMaxAb.equals(that.maMaxAb) : that.maMaxAb != null) return false;
        if (maNname != null ? !maNname.equals(that.maNname) : that.maNname != null) return false;
        if (maProd != null ? !maProd.equals(that.maProd) : that.maProd != null) return false;
        if (maRecht != null ? !maRecht.equals(that.maRecht) : that.maRecht != null) return false;
        if (maTeamKz != null ? !maTeamKz.equals(that.maTeamKz) : that.maTeamKz != null) return false;
        if (maUser != null ? !maUser.equals(that.maUser) : that.maUser != null) return false;
        if (maVname != null ? !maVname.equals(that.maVname) : that.maVname != null) return false;
        if (maWerk != null ? !maWerk.equals(that.maWerk) : that.maWerk != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = maPnr;
        result = 31 * result + (maNname != null ? maNname.hashCode() : 0);
        result = 31 * result + (maVname != null ? maVname.hashCode() : 0);
        result = 31 * result + maKst;
        result = 31 * result + maTel;
        result = 31 * result + (maAp != null ? maAp.hashCode() : 0);
        result = 31 * result + (maMaxAb != null ? maMaxAb.hashCode() : 0);
        result = 31 * result + (maAnmeld != null ? maAnmeld.hashCode() : 0);
        result = 31 * result + (maRecht != null ? maRecht.hashCode() : 0);
        result = 31 * result + maPin;
        result = 31 * result + (maProd != null ? maProd.hashCode() : 0);
        result = 31 * result + (maUser != null ? maUser.hashCode() : 0);
        result = 31 * result + (maWerk != null ? maWerk.hashCode() : 0);
        result = 31 * result + maTeam;
        result = 31 * result + (maTeamKz != null ? maTeamKz.hashCode() : 0);
        result = 31 * result + (maDrucker != null ? maDrucker.hashCode() : 0);
        return result;
    }
}
