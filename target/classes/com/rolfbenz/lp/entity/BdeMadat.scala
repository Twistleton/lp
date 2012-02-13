package com.rolfbenz.lp.entity
package com.rolfbenz.lp.entity

import javax.persistence.Basic
import javax.persistence.Entity
import javax.persistence.Id
import java.math.BigInteger

@javax.persistence.Table(name = "BDE_MADAT", schema = "PLRV11")
@Entity class BdeMadat {
  @javax.persistence.Column(name = "MA_PNR")
  @Id def getMaPnr: Int = {
    return maPnr
  }

  def setMaPnr(maPnr: Int): Unit = {
    this.maPnr = maPnr
  }

  @javax.persistence.Column(name = "MA_NNAME")
  @Basic def getMaNname: String = {
    return maNname
  }

  def setMaNname(maNname: String): Unit = {
    this.maNname = maNname
  }

  @javax.persistence.Column(name = "MA_VNAME")
  @Basic def getMaVname: String = {
    return maVname
  }

  def setMaVname(maVname: String): Unit = {
    this.maVname = maVname
  }

  @javax.persistence.Column(name = "MA_KST")
  @Basic def getMaKst: Int = {
    return maKst
  }

  def setMaKst(maKst: Int): Unit = {
    this.maKst = maKst
  }

  @javax.persistence.Column(name = "MA_TEL")
  @Basic def getMaTel: Int = {
    return maTel
  }

  def setMaTel(maTel: Int): Unit = {
    this.maTel = maTel
  }

  @javax.persistence.Column(name = "MA_AP")
  @Basic def getMaAp: String = {
    return maAp
  }

  def setMaAp(maAp: String): Unit = {
    this.maAp = maAp
  }

  @javax.persistence.Column(name = "MA_MAX_AB")
  @Basic def getMaMaxAb: BigInteger = {
    return maMaxAb
  }

  def setMaMaxAb(maMaxAb: BigInteger): Unit = {
    this.maMaxAb = maMaxAb
  }

  @javax.persistence.Column(name = "MA_ANMELD")
  @Basic def getMaAnmeld: String = {
    return maAnmeld
  }

  def setMaAnmeld(maAnmeld: String): Unit = {
    this.maAnmeld = maAnmeld
  }

  @javax.persistence.Column(name = "MA_RECHT")
  @Basic def getMaRecht: BigInteger = {
    return maRecht
  }

  def setMaRecht(maRecht: BigInteger): Unit = {
    this.maRecht = maRecht
  }

  @javax.persistence.Column(name = "MA_PIN")
  @Basic def getMaPin: Int = {
    return maPin
  }

  def setMaPin(maPin: Int): Unit = {
    this.maPin = maPin
  }

  @javax.persistence.Column(name = "MA_PROD")
  @Basic def getMaProd: String = {
    return maProd
  }

  def setMaProd(maProd: String): Unit = {
    this.maProd = maProd
  }

  @javax.persistence.Column(name = "MA_USER")
  @Basic def getMaUser: String = {
    return maUser
  }

  def setMaUser(maUser: String): Unit = {
    this.maUser = maUser
  }

  @javax.persistence.Column(name = "MA_WERK")
  @Basic def getMaWerk: BigInteger = {
    return maWerk
  }

  def setMaWerk(maWerk: BigInteger): Unit = {
    this.maWerk = maWerk
  }

  @javax.persistence.Column(name = "MA_TEAM")
  @Basic def getMaTeam: Int = {
    return maTeam
  }

  def setMaTeam(maTeam: Int): Unit = {
    this.maTeam = maTeam
  }

  @javax.persistence.Column(name = "MA_TEAM_KZ")
  @Basic def getMaTeamKz: String = {
    return maTeamKz
  }

  def setMaTeamKz(maTeamKz: String): Unit = {
    this.maTeamKz = maTeamKz
  }

  @javax.persistence.Column(name = "MA_DRUCKER")
  @Basic def getMaDrucker: String = {
    return maDrucker
  }

  def setMaDrucker(maDrucker: String): Unit = {
    this.maDrucker = maDrucker
  }

  private var maPnr: Int = 0
  private var maNname: String = null
  private var maVname: String = null
  private var maKst: Int = 0
  private var maTel: Int = 0
  private var maAp: String = null
  private var maMaxAb: BigInteger = null
  private var maAnmeld: String = null
  private var maRecht: BigInteger = null
  private var maPin: Int = 0
  private var maProd: String = null
  private var maUser: String = null
  private var maWerk: BigInteger = null
  private var maTeam: Int = 0
  private var maTeamKz: String = null
  private var maDrucker: String = null
}

