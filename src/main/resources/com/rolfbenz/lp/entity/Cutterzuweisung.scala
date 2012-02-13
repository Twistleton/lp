package com.rolfbenz.lp.entity

import _root_.com.rolfbenz.lp.dao.CutterteammitgliedDao
import javax.persistence._
import scala.collection.JavaConversions._
import java.text.SimpleDateFormat
import java.util.Date

/**
 * Cutterzuweisung
 *
 * User: Speer, Rolf
 * Date: 21.02.2011
 */

@serializable
@Entity
class Cutterzuweisung {

  def this(name: String, sumSurfmes: Double, sumSurfsup : Double, summereste: Double, summeabschreibung: Double, rejectedRows: Int) = {
    this()
    this.sainame = name
    this.sumSurfmes = BigDecimal(sumSurfmes.toString).setScale(2, BigDecimal.RoundingMode.CEILING).toFloat
    this.sumSurfsup = BigDecimal(sumSurfsup.toString).setScale(2, BigDecimal.RoundingMode.CEILING).toFloat
    this.sumSurfreste = BigDecimal(summereste.toString).setScale(2, BigDecimal.RoundingMode.CEILING).toFloat
    this.sumAbschreibung = BigDecimal(summeabschreibung.toString).setScale(2, BigDecimal.RoundingMode.CEILING).toFloat
    this.rejectedRows = rejectedRows
    this.verbrauchMes = BigDecimal((sumSurfmes - summereste - summeabschreibung).toString).setScale(4, BigDecimal.RoundingMode.CEILING).toFloat
    this.verbrauchSup = BigDecimal((sumSurfsup - summereste - summeabschreibung).toString).setScale(4, BigDecimal.RoundingMode.CEILING).toFloat
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  var id: Long = 0

  @OneToMany(mappedBy="cutterzuweisung", cascade = Array(CascadeType.ALL))
  @OrderBy("hide")
  var cutterhautSet : java.util.Set[Cutterhaut] = new java.util.HashSet[Cutterhaut]

  @OneToMany(mappedBy="cutterzuweisung", cascade = Array(CascadeType.ALL))
  @OrderBy("hide")
  var cutterrowSet : java.util.Set[Cutterrow] = new java.util.HashSet[Cutterrow]

  @OneToMany(mappedBy="cutterzuweisung", cascade = Array(CascadeType.ALL))
  @OrderBy("id")
  var cutterpraemieSet : java.util.Set[Cutterpraemie] = new java.util.HashSet[Cutterpraemie]

  @Column(length = 25, unique = true, nullable = false)
  var sainame: String = _

  @Column(scale = 2)
  var sumSurfmes: Float = _

  @Column(scale = 2)
  var sumSurfsup: Float = _

  @Column(scale = 2)
  var sumSurfreste: Float = _

  @Column(scale = 2)
  var sumAbschreibung: Float = _
  var rejectedRows: Int = _

  @Column(scale = 2)
  var verbrauchMes: Float = _
                                                                    
  @Column(scale = 2)
  var verbrauchSup: Float = _

  @Column(scale = 2)
  var sumRecut: Float = _

  @Column(insertable = true, updatable = false)
  val erstelldatum = new Date()

  @Column(insertable = false, updatable = true)
  val aenderungsdatum = new Date()

  var status = 5
  var grund : String = _
  var kriterium : String = _
  var info : String = _

  override def toString = "Cutterzuweisung : " + id + " " + sainame

  def addCutterhaut(cutterhaut : Cutterhaut) = {
    this.cutterhautSet.add(cutterhaut)
    cutterhaut.setCutterzuweisung(this)
  }

  def addCutterrow(cutterrow : Cutterrow) = {
    this.cutterrowSet.add(cutterrow)
    cutterrow.setCutterzuweisung(this)
  }

  def addCutterpraemie(cutterpraemie : Cutterpraemie) = {
    this.cutterpraemieSet.add(cutterpraemie)
    cutterpraemie.setCutterzuweisung(this)
  }

  // getters for use in the JSPs

  def getId = id
  def getSainame = sainame
  def getErstelldatum = erstelldatum
  def getAenderungsdatum = aenderungsdatum
  def getSumSurfmes = sumSurfmes
  def getSumSurfsup = sumSurfsup
  def getSumSurfreste = sumSurfreste
  def getSumAbschreibung = sumAbschreibung
  def getSumRecut     = sumRecut
  def getVerbrauchMes = verbrauchMes
  def getVerbrauchSup = verbrauchSup
  def getStatus = status
  def getGrund = grund
  def getKriterium = kriterium
  def getInfo = info

  def getCutterrowSet = cutterrowSet
  def getCutterhautSet = cutterhautSet
  def getCutterpraemieSet = cutterpraemieSet

  def setVerbrauchMes() = {
    this.verbrauchMes = sumSurfmes - sumSurfreste - sumAbschreibung
  }

  def setVerbrauchSup() = {
    this.verbrauchSup = sumSurfsup - sumSurfreste - sumAbschreibung
  }

  def getSummeSollmenge() : Double   = {
        val cutterpraemienList = cutterpraemieSet.toList
        cutterpraemienList.map(_.sollmenge).sum
  }

  def getSummeIstmenge() : Double = {
      // convert to scala.collection.Set

      if (cutterhautSet.isEmpty) {
        return 0.00
      }

      val cutterhautList = cutterhautSet.filter(_.hautaktiv == false).toList

      var summeIstmenge : Double = 0.00
      var summeAbschreibung : Double = 0.00
      var summeRest : Double = 0.00

      try{
         summeIstmenge = cutterhautList.map(_.surfmes).sum
         summeAbschreibung = cutterhautList.map(_.abschreibung).sum
      } catch {
        case e => summeIstmenge = 0
                  summeAbschreibung = 0
      }
      summeIstmenge - summeAbschreibung
  }

   def getSummeIstmengeLiefermass() : Double = {
      // convert to scala.collection.Set
      // TODO - hautaktiv in nichtrelevant umbenennen
      val cutterhautList = cutterhautSet.filter(_.hautaktiv == false).toList
     // :TODO abschreibung abziehen
      cutterhautList.map(_.surfsup).sum - sumSurfreste - sumAbschreibung
  }

  def getSummeAbschreibung() : Double = {

      val cutterhautList = cutterhautSet.toList
      cutterhautList.map(_.abschreibung).sum
  }

  // filename : yyyyMMddHHmmssSSS.cpd
  //            20110809191700000.cpd
  //
  def getFilename(teamPrinter: String) = {
               // get path from the configuration file
               val xmlConfig = scala.xml.XML.loadFile("/etc/lp.conf")
               val path = xmlConfig \ "@path"
               // generate the filename
               val filename  = new java.text.SimpleDateFormat("yyyyMMddHHmmssSSS").format(new java.util.Date)
               // select the file extension
               val extension: String = teamPrinter match {
                    case "rbcutter01" => ".CPD1"  // Team 1 + 2
                    case "rbcutter02" => ".CPD2"  // Team 3 + 4
                    case "rbit01"     => ".CPD3"  // IT
                    case _            => ".CPD3"  // otherwise set CPD3 for printer rbit01 (IT, Nagold)
               }
               path + filename + extension
  }

  def toXml(buchungDurch: String) = {

               var verschnitt = 0.00

               if (this.getSummeSollmenge>0) {
                 verschnitt = 100 / this.getSummeSollmenge * (this.getSummeIstmenge - this.getSumSurfreste - this.sumRecut) - 100
               } else {
                 verschnitt = 0
               }

               <cutterzuweisung>
               <!-- Cutterzuweisung-Freigabe -->
               <treffer>cutterzuweisung</treffer>
               <id>{id}</id>
               <buchungDurch>{buchungDurch}</buchungDurch>
               <sainame>{sainame}</sainame>
               <erstelldatum>{formatDate(erstelldatum)}</erstelldatum>
               <aenderungsdatum>{formatDate(aenderungsdatum)}</aenderungsdatum>
               <sumSurfmes>{"%8.2f".format(sumSurfmes).trim}</sumSurfmes>
               <sumSurfsup>{"%8.2f".format(sumSurfsup).trim}</sumSurfsup>
               <sumSurfreste>{"%8.2f".format(sumSurfreste).trim}</sumSurfreste>
               <sumAbschreibung>{"%8.2f".format(sumAbschreibung).trim}</sumAbschreibung>
               <verbrauchMes>{"%8.2f".format(verbrauchMes).trim}</verbrauchMes>
               <verbrauchSup>{"%8.2f".format(verbrauchSup).trim}</verbrauchSup>
               <status>{status}</status>
               <grund>{grund}</grund>
               <kriterium>{kriterium}</kriterium>
               <info>{info}</info>
               <sollmenge>{"%8.2f".format(getSummeSollmenge).trim}</sollmenge>
               <summereste>{"%8.2f".format(getSumSurfreste).trim}</summereste>
               <istmenge>{"%8.2f".format(getSummeIstmenge - getSumSurfreste - getSumRecut).trim}</istmenge>
               <verschnitt>{"%8.2f".format(verschnitt).trim}</verschnitt>
               <cutterhaeute>
               { cutterhautSet.toList.sortBy(_.hide).map(_.toXml) }
               </cutterhaeute>
               <cutterpraemien>
               { cutterpraemieSet.map(_.toXml) }
               </cutterpraemien>
               <teammitglieder>
               { getTeammitglieder(sainame) }
               </teammitglieder>
              </cutterzuweisung>
  }

  def getTeammitglieder(sainame: String) = {
    val teammitgliederList = CutterteammitgliedDao.getTeammitglieder(sainame)
    teammitgliederList.map(_.toXml)

  }

  def formatDate(date: Date) = {
    val sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss")
    try {
        sdf.format(date)
    } catch {
      case e => ""
    }
  }


}