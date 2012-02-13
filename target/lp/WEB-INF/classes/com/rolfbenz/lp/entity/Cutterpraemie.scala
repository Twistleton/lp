package com.rolfbenz.lp.entity

import _root_.javax.persistence._
import _root_.java.util._

@serializable
@Entity
@SqlResultSetMapping(name="getRows",
  entities=Array(new EntityResult(entityClass=classOf[Cutterpraemie])))
class Cutterpraemie  {

  def this(cutterpraemie : Cutterpraemie) = {
    this()
    this.sainame                  = cutterpraemie.sainame
    this.sammelauftragsnummer     = cutterpraemie.sammelauftragsnummer
    this.auftragsnummer           = cutterpraemie.auftragsnummer
    this.bezugsmaterial           = cutterpraemie.bezugsmaterial
    this.sollmenge                = cutterpraemie.sollmenge
    this.nettomenge               = cutterpraemie.nettomenge
    this.istmenge_lieferantenmass = cutterpraemie.istmenge_lieferantenmass
    this.istmenge_maschinenmass   = cutterpraemie.istmenge_maschinenmass
    this.datum_weiterleitung      = cutterpraemie.datum_weiterleitung
    this.buchungsmonat            = currentMonth                              // MMYYYY
    this.vorgangsart              = cutterpraemie.vorgangsart
    this.verursacherschluessel    = cutterpraemie.verursacherschluessel
    this.programm                 = cutterpraemie.programm
    this.kennzeichen_praemie      = true
    this.lief_kkz                 = cutterpraemie.lief_kkz
    this.charg_nr                 = cutterpraemie.charg_nr
    this.zuschnittssystem         = cutterpraemie.zuschnittssystem
    this.zuschnittswerk           = cutterpraemie.zuschnittswerk
    this.lederklasse              = cutterpraemie.lederklasse
    this.status                   = cutterpraemie.status

  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  var id: Long = _

  @ManyToOne
  @JoinColumn(name = "cutterzuweisung", nullable = false)
  var cutterzuweisung : Cutterzuweisung = _

  var sainame : String = _

  var sammelauftragsnummer     : Long = _
  var auftragsnummer           : Int  = _
  var bezugsmaterial           : Long = _
  var sollmenge                : Float = _
  var nettomenge               : Float = _
  var istmenge_lieferantenmass : Float = _
  var istmenge_maschinenmass   : Float = _
  var datum_weiterleitung      : Date = _
  var buchungsmonat            : Int  = _      
  var vorgangsart              : Int  = _
  var verursacherschluessel    : Int  = _
  var programm                 : Int  = _
  var kennzeichen_praemie      : Boolean = _
  var lief_kkz                 : String  = _
  var charg_nr                 : Int = _
  var zuschnittssystem         : Int = _
  var zuschnittswerk           : Int = _
  var lederklasse              : Int = _
  var status                   : Int = _

  @Column(insertable = true, updatable = false)
  val erstelldatum = new Date()

  @Column(insertable = false, updatable = true)
  var aenderungsdatum = new Date()

  def setCutterzuweisung(cutterzuweisung : Cutterzuweisung) = {
    this.cutterzuweisung = cutterzuweisung
  }

  // current Month MMYYYY
  def currentMonth() : Int = {
    return (Calendar.getInstance.get(Calendar.MONTH) + 1) * 10000 +
            Calendar.getInstance.get(Calendar.YEAR)
  }

  def getId = id
  def getSainame = sainame
  def getSammelauftragsnummer = sammelauftragsnummer
  def getAuftragsnummer = auftragsnummer
  def getBezugsmaterial = bezugsmaterial
  def getSollmenge = sollmenge
  def getNettomenge = nettomenge
  def getIstmenge_lieferantenmass = istmenge_lieferantenmass
  def getIstmenge_maschinenmass   = istmenge_maschinenmass
  def getDatum_weiterleitung = datum_weiterleitung
  def getBuchungsmonat  = buchungsmonat
  def getVorgangsart = vorgangsart
  def getVerursacherschluessel = verursacherschluessel
  def getProgramm = programm
  def getKennzeichen_praemie = kennzeichen_praemie
  def getLief_kkz = lief_kkz
  def getCharg_nr = charg_nr
  def getZuschnittssystem = zuschnittssystem
  def getZuschnittswerk = zuschnittswerk
  def getLederklasse = lederklasse
  def getStatus = status

  def toXml = <cutterpraemie>
                <id>{id}</id>
                <sainame>{sainame}</sainame>
                <sammelauftragsnummer>{sammelauftragsnummer}</sammelauftragsnummer>
                <auftragsnummer>{auftragsnummer}</auftragsnummer>
                <bezugsmaterial>{bezugsmaterial}</bezugsmaterial>
                <sollmenge>{sollmenge}</sollmenge>
                <nettomenge>{nettomenge}</nettomenge>
                <istmenge_lieferantenmass>{istmenge_lieferantenmass}</istmenge_lieferantenmass>
                <istmenge_maschinenmass>{istmenge_maschinenmass}</istmenge_maschinenmass>
                <buchungsmonat>{buchungsmonat}</buchungsmonat>
                <vorgangsart>{vorgangsart}</vorgangsart>
                <verursacherschluessel>{verursacherschluessel}</verursacherschluessel>
                <programm>{programm}</programm>
                <kennzeichen_praemie>{kennzeichen_praemie}</kennzeichen_praemie>
                <lief_kkz>{lief_kkz}</lief_kkz>
                <charg_nr>{charg_nr}</charg_nr>
                <zuschnittssystem>{zuschnittssystem}</zuschnittssystem>
                <zuschnittswerk>{zuschnittswerk}</zuschnittswerk>
                <lederklasse>{lederklasse}</lederklasse>
                <status>{status}</status>
              </cutterpraemie>

}