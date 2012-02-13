package com.rolfbenz.lp.entity

import _root_.java.util.Date
import _root_.javax.persistence._
import _root_.com.rolfbenz.lp.other.TrvRow

/**
 *
 * Cutterrow (rows from the trv files)
 *
 * User: Speer, Rolf
 * Date: 07.03.2011
 */

@serializable
@Entity
class Cutterrow {

  def this(name: String, row: TrvRow) = {
    this ()
    this.sainame = name
    this.hbegdigit = row.hbegdigit
    this.henddigit = row.henddigit
    this.hbegnest = row.hbegnest
    this.hendnest = row.hendnest
    this.hsendgpilot = row.hsendgpilot
    this.operator = row.operator
    this.hide = row.hide
    this.batch = row.batch
    this.leathertype = row.leathertype
    this.supplier = row.supplier
    this.surfmes = row.surfmes
    this.surfsup = row.surfsup
    this.nbpiecnest = row.nbpiecnest
    this.nbpiecleft = row.nbpiecleft
    this.surfnest = row.surfnest
    this.surfleft = row.surfleft
    this.effic = row.effic
    this.surfdef1 = row.surfdef1
    this.surfdef2 = row.surfdef2
    this.surfdef3 = row.surfdef3
    this.surfdef4 = row.surfdef4
    this.surfdef5 = row.surfdef5
    this.nbdef1 = row.nbdef1
    this.nbdef2 = row.nbdef2
    this.nbdef3 = row.nbdef3
    this.nbdef4 = row.nbdef4
    this.nbdef5 = row.nbdef5
    this.surfunit = row.surfunit
    this.nbrestes = row.nbrestes
    this.surfreste1 = row.surfreste1
    this.surfreste2 = row.surfreste2
    this.surfreste3 = row.surfreste3
    this.surfreste4 = row.surfreste4
    this.surfreste5 = row.surfreste5
    this.surfreste6 = row.surfreste6
    this.surfreste7 = row.surfreste7
    this.surfreste8 = row.surfreste8
    this.surfreste9 = row.surfreste9
    this.surfreste10 = row.surfreste10
    this.brdname = row.brdname
    this.commentfield = row.commentfield
    this.hbeghide = row.hbeghide
    this.hendhide = row.hendhide
    this.hendscan = row.hendscan
    this.hbegtrans = row.hbegtrans
    this.hendtrans = row.hendtrans
    this.ficparam = row.ficparam
    this.modele = row.modele
    this.nbpiecini = row.nbpiecini
    this.surfini = row.surfini
    this.relevant = row.relevant
    this.hautrelevant = row.hautrelevant
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  var id: Long = _

  var sainame: String = _
  var hbegdigit: String = _
  var henddigit: String = _
  var hbegnest: String = _
  var hendnest: String = _
  var hsendgpilot: String = _
  var operator: String = _
  var hide: String = _
  var batch: String = _
  var leathertype: String = _
  var supplier: String = _
  var surfmes: Float = _
  var surfsup: Float = _
  var nbpiecnest: String = _
  var nbpiecleft: String = _
  var surfnest: String = _
  var surfleft: String = _
  var effic: String = _
  var surfdef1: String = _
  var surfdef2: String = _
  var surfdef3: String = _
  var surfdef4: String = _
  var surfdef5: String = _
  var nbdef1: String = _
  var nbdef2: String = _
  var nbdef3: String = _
  var nbdef4: String = _
  var nbdef5: String = _
  var surfunit: String = _
  var nbrestes: String = _
  var surfreste1: Float = _
  var surfreste2: Float = _
  var surfreste3: Float = _
  var surfreste4: Float = _
  var surfreste5: Float = _
  var surfreste6: Float = _
  var surfreste7: Float = _
  var surfreste8: Float = _
  var surfreste9: Float = _
  var surfreste10: Float = _
  var brdname: String = _
  var commentfield: Float = _
  var hbeghide: String = _
  var hendhide: String = _
  var hendscan: String = _
  var hbegtrans: String = _
  var hendtrans: String = _
  var ficparam: String = _
  var modele: String = _
  var nbpiecini: Int = _
  var surfini: Float = _
  var relevant: Boolean = _
  var hautrelevant : Boolean = _

  @Column(insertable = true, updatable = false)
  val erstelldatum = new Date()

  @Column(insertable = false, updatable = true)
  val aenderungsdatum = new Date()

  @ManyToOne
  @JoinColumn(name = "cutterzuweisung", nullable = false)
  var cutterzuweisung : Cutterzuweisung = _

  def setCutterzuweisung(cutterzuweisung : Cutterzuweisung) = {
    this.cutterzuweisung = cutterzuweisung
  }

  // getter for EL (JSP)

  def getSainame = sainame
  def getHbegdigit = hbegdigit
  def getHenddigit = henddigit
  def getHbegnest = hbegnest
  def getHendnest = hendnest
  def getHsendgpilot = hsendgpilot
  def getOperator = operator
  def getHide = hide
  def getBatch = batch
  def getLeathertype = leathertype
  def getSupplier = supplier
  def getSurfmes = surfmes
  def getSurfsup = surfsup
  def getNbpiecnest = nbpiecnest
  def getNbpiecleft = nbpiecleft
  def getSurfnest = surfnest
  def getSurfleft = surfleft
  def getEffic = effic
  def getSurfdef1 = surfdef1
  def getSurfdef2 = surfdef2
  def getSurfdef3 = surfdef3
  def getSurfdef4 = surfdef4
  def getSurfdef5 = surfdef5
  def getNbdef1 = nbdef1
  def getNbdef2 = nbdef2
  def getNbdef3 = nbdef3
  def getNbdef4 = nbdef4
  def getNbdef5 = nbdef5
  def getSurfunit = surfunit
  def getNbrestes = nbrestes
  def getSurfreste1 = surfreste1
  def getSurfreste2 = surfreste2
  def getSurfreste3 = surfreste3
  def getSurfreste4 = surfreste4
  def getSurfreste5 = surfreste5
  def getBrdname = brdname
  def getCommentfield = commentfield
  def getHbeghide = hbeghide
  def getHendhide = hendhide
  def getHendscan = hendscan
  def getHbegtrans = hbegtrans
  def getHendtrans = hendtrans
  def getFicparam = ficparam
  def getModele = modele
  def getNbpiecini = nbpiecini
  def getSurfini = surfini
  def getRelevant = relevant
  def getHautrelevant = hautrelevant
  def getErstelldatum = erstelldatum

}