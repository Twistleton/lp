package com.rolfbenz.lp.entity

import java.util.Date
import javax.persistence._
import _root_.com.rolfbenz.lp.other.TrvRow

/**
 *
 * Cutterhaut (rows from the trv files)
 *
 * User: Speer, Rolf
 * Date: 07.03.2011
 */

@serializable
@Entity
class Cutterhaut  {

  def this(sainame: String, row: TrvRow, sumSurfreste : Double) = {
    this()
    this.sainame = sainame
    this.hide = row.hide

    this.surfmes = row.surfmes               // Maschinenmass
    this.surfmes_original = row.surfmes      // Maschinenmass

    this.surfsup = row.surfsup               // Lieferantenmass
    this.surfsup_original = row.surfsup      // Lieferantenmass

    this.verbrauch  = BigDecimal((row.surfmes - sumSurfreste.toFloat - row.commentfield).toString).setScale(4, BigDecimal.RoundingMode.CEILING).toFloat
    this.verbrauch_original = this.verbrauch
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  var id: Long = _

  @ManyToOne
  @JoinColumn(name = "cutterzuweisung", nullable = false)
  var cutterzuweisung : Cutterzuweisung = _  

  var sainame : String = _ 

  var hide                     : String = _
  var surfsup                  : Float = _
  var surfsup_original         : Float = _
  var surfmes                  : Float = _
  var surfmes_original         : Float = _
  var abschreibung             : Float = _
  var abschreibung_original    : Float = _
  var verbrauch                : Float = _ 
  var verbrauch_original       : Float = _
  @Column(name = "haut_aktiv")
  var hautaktiv                : Boolean = _
  var status                   : Int = _ 
//  var hautgrund                : String = _

  @Column(insertable = true, updatable = false)
  val erstelldatum = new Date()

  @Column(insertable = false, updatable = true)
  val aenderungsdatum = new Date()

  def setCutterzuweisung(cutterzuweisung : Cutterzuweisung) = {
    this.cutterzuweisung = cutterzuweisung
  }

  def getId = id
  def getSainame = sainame
  def getHide = hide
  def getSurfmes = surfmes
  def getSurfmes_original = surfmes
  def getSurfsup = surfsup
  def getSurfsup_original = surfsup
  def getVerbrauch  = verbrauch
  def getVerbrauch_original = verbrauch_original
  def getAbschreibung = abschreibung
  def getHautaktiv = hautaktiv
//  def getHautgrund = hautgrund

  def toXml = {

              var abweichung = 0.00
              if (this.surfsup > 0) {
                abweichung = 100*this.surfmes/this.surfsup-100
              } else                {
                abweichung =   0
              }

              <cutterhaut>
                <id>{id}</id>
                <sainame>{sainame}</sainame>
                <hide>{hide}</hide>
                <surfmes>{"%8.2f".format(surfmes).trim}</surfmes>
                <surfmes_original>{"%8.2f".format(surfmes_original).trim}</surfmes_original>
                <surfsup>{"%8.2f".format(surfsup).trim}</surfsup>
                <surfsup_original>{"%8.2f".format(surfmes_original).trim}</surfsup_original>
                <verbrauch>{"%8.2f".format(verbrauch).trim}</verbrauch>
                <verbrauch_original>{"%8.2f".format(verbrauch_original).trim}</verbrauch_original>
                <abschreibung>{"%8.2f".format(abschreibung).trim}</abschreibung>
                <hautaktiv>{hautaktiv}</hautaktiv>
                <abweichung>{"%8.2f".format(abweichung).trim}</abweichung>
              </cutterhaut>

  }

}