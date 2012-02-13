package com.rolfbenz.lp.entity

import java.util.Date
import javax.persistence._

/**
 * Cutterteammitglied
 *
 * User: Speer, Rolf
 * Date: 03.03.2011
 */

@serializable
@Entity
class Cutterteammitglied {
  
  def this(sainame: String, team: Int, personalnummer: Int, vorname: String, nachname: String) = {
    this()
    this.sainame = sainame
    this.team = team
    this.personalnummer = personalnummer
    this.vorname = vorname
    this.nachname = nachname
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  var id: Long = _

  @Column(length=50)
  var sainame: String = _
  var team: Int = _
  var personalnummer: Int = _

  @Column(length=50)
  var vorname: String = _

  @Column(length=50)
  var nachname: String = _

  @Column(insertable = true, updatable = false)
  val erstelldatum = new Date()

  @Column(insertable = false, updatable = true)
  val aenderungsdatum = new Date()

  @Column(nullable = true)
  var cutterzuweisungs_id = 0L

  def getId = id
  def getSainame = sainame
  def getTeam = team
  def getPersonalnummer = personalnummer
  def getVorname = vorname
  def getNachname = nachname
  def getErstelldatum = erstelldatum
  def getAenderungsdatum = aenderungsdatum
  def getCutterzuweisungs_id = cutterzuweisungs_id

  def toXml = <teammitglied>
                <team>{team}</team>
                <personalnummer>{personalnummer}</personalnummer>
                <vorname>{vorname}</vorname>
                <nachname>{nachname}</nachname>
              </teammitglied>

}