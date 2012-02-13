package com.rolfbenz.lp

import dao.CutterzuweisungDao
import entity.com.rolfbenz.lp.entity.BdeMadat
import entity.{Cutterteammitglied, Cutterhaut, Cutterzuweisung}
import jpa.JpaUtil
import javax.persistence.{EntityManager, EntityTransaction}
import javax.servlet.http.HttpServlet
import javax.servlet.http.{ HttpServletRequest => Request, HttpServletResponse => Response}
import javax.servlet.RequestDispatcher
import view.Cutterbuchungsmaske
import java.util.{HashMap, ArrayList}
import java.util.logging.Level
import java.util.logging.Logger
import _root_.scala.xml._
import _root_.scala.collection.JavaConversions._
//import com.rolfbenz.lp.InputError

/**
 *  lp - CutterbuchungController2 - Mitarbeitermaske
 *
 *  User: Rolf Speer
 *  Date: 01.08.2011
 *
 */

class CutterbuchungController2 extends HttpServlet {

  override def doPost(request: Request, response: Response): Unit = {
    processRequest(request, response)
  }

  override def doGet(request: Request, response: Response): Unit = {
    processRequest(request, response)
  }

  def processRequest(request: Request, response: Response) {

    // starttime
    val stime = new java.util.Date()

    Logger.getAnonymousLogger().log(Level.INFO, "* start processRequest (CutterbuchungController2) --------------------------------------------------" + stime)

    val maxAbweichung = 10.00

    // error list
    var errorlist: java.util.List[InputError] = new ArrayList[InputError]
    var errorset: java.util.Map[String, Boolean] = new HashMap[String, Boolean]

    // get id (from request, if not set get the id from session)
    var session = request.getSession()
    var id : Long = 0L
    try {
      id  = request.getParameter("id").toLong
    } catch {
      case e => id = session.getAttribute("id").toString.toLong
    }

    Logger.getAnonymousLogger().log(Level.INFO, "* id for processRequest (CutterbuchungController2) =======================================================> " + id)

    if (request.getParameter("berechnen") != null) {
      Logger.getAnonymousLogger().log(Level.INFO, "* processRequest/CutterbuchungController2/berechnen ------------------------------------------------" + stime)
      neuberechnen(request, response)
      verteilenIstmenge()
      loadCutterzuweisung(request, response)
      sendMap(request, response, new InputError("(I) - Werte neu berechnet", "0"))
    } else if (request.getParameter("pruefen") != null) {
      Logger.getAnonymousLogger().log(Level.INFO, "* processRequest/CutterbuchungController2/prüfen ------------------------------------------------" + stime)
      if (checkAbweichung(request, response)) {
        if (request.getParameter("grund").equals("0")) {
          sendMap(request, response, new InputError("(A) - Bitte Prüfungsgrund ausgewählen", "0"))
        } else {
          setStatus20(request, response)
          neuberechnen(request, response)
          loadCutterzuweisung(request, response)
          sendMap(request, response, new InputError("(I) - Grund gesetzt", "0"))
        }
      } else {
        sendMap(request, response, new InputError("Maximalwert für die Abweichung von " + maxAbweichung + "% überschritten, keine Weiterleitung möglich", "0"))
      }
    }
    else if (request.getParameter("freigeben") != null) {
      Logger.getAnonymousLogger().log(Level.INFO, "* processRequest/CutterbuchungController2/freigeben ------------------------------------------------" + stime)
      if (checkStatus(request, response)) {
        if (checkAbweichung(request, response)) {
          if (checkFreigabe(request, response)) {
            setStatus40(request, response)
            loadCutterzuweisung(request, response)
            sendMap(request, response, new InputError("Cutterzuweisung freigegeben, Kontrollbeleg an Drucker ", "0"))
          } else {
            sendMap(request, response, new InputError("(A) - Freigabe nicht möglich, da Häute deaktiviert sind; Haut aktivieren & Neuberechnung durchführen", "0"))
          }
        } else {
          sendMap(request, response, new InputError("(A) - Abweichung über dem Maximalwert von " + maxAbweichung + "%, Freigabe nicht möglich", "0"))
        }
      } else {
        sendMap(request, response, new InputError("(A) - Status erlaubt keine Freigabe", "0"))
      }
    }
    else if (request.getParameter("refresh") != null) {
      Logger.getAnonymousLogger().log(Level.INFO, "* processRequest/CutterbuchungController2/refresh --------------------------------------------------" + stime)
      loadCutterzuweisung(request, response)
      sendMap(request, response, new InputError("Daten nachgeladen", "0"))
    }
    else {
      Logger.getAnonymousLogger().log(Level.INFO, "* processRequest/CutterbuchungController2/else-branch-----------------------------------------------" + stime)
      loadCutterzuweisung(request, response)
      neuberechnen(request, response)
      verteilenIstmenge()
      setStatus10(request, response)
      loadCutterzuweisung(request, response)
      sendMap(request, response, new InputError("Cutterzuweisung angezeigt", "0"))
    }

    Logger.getAnonymousLogger().log(Level.INFO, "* end  processRequest (CutterbuchungController2) ---------------------------------------------------" + stime)

    //
    // end of processRequest
    //
    // -----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // nested function of processRequest (scope)
    //

    //
    // setzen Status 10 - Cutterzuweisung zur Buchung aufgerufen
    //
    def setStatus10(request: Request, response: Response) {

      val cutterzuweisung = getCutterzuweisung(id)

      // Status 5 auf Status 10 setzen
      if (cutterzuweisung.status < 10) {
        cutterzuweisung.status = 10
        CutterzuweisungDao update cutterzuweisung
      }

    }

    //
    //  setzen Status 20 - Prüfung und Weiterleitung an Meister
    //
    def setStatus20(request: Request, response: Response) {

      val cutterzuweisung = getCutterzuweisung(id)

      cutterzuweisung.status = 20  // Weiterleitung an Meister
      cutterzuweisung.grund = request.getParameter("grund")

      CutterzuweisungDao update cutterzuweisung
    }

    def checkStatus(request: Request, response: Response) : Boolean = {
      val factory = JpaUtil.getEntityManagerFactory
      val manager = factory.createEntityManager
      val tx = manager.getTransaction
      tx.begin

      val cutterzuweisung = getCutterzuweisungWithoutEntityManager(id, manager)
      val result = cutterzuweisung.status < 40

      tx.commit
      manager.close
      result
    }

    def checkFreigabe(request: Request, response: Response) : Boolean = {

      val factory = JpaUtil.getEntityManagerFactory
      val manager = factory.createEntityManager
      val tx = manager.getTransaction
      tx.begin

      val cutterzuweisung = getCutterzuweisungWithoutEntityManager(id, manager)
      val result1 = cutterzuweisung.getCutterhautSet.forall(_.hautaktiv == false)

      val query4 = manager.createQuery("select c from Cutterteammitglied c where cutterzuweisungs_id = :cutterzuweisungs_id", classOf[Cutterteammitglied])
      query4.setParameter("cutterzuweisungs_id", id)

      val result2 = if (query4.getResultList.size()>0) true else false

      tx.commit
      manager.close
      result1 && result2
    }

    def checkAbweichung(request: Request, response: Response) : Boolean = {

      val factory = JpaUtil.getEntityManagerFactory
      val manager = factory.createEntityManager
      val tx = manager.getTransaction
      tx.begin

      val cutterzuweisung = getCutterzuweisungWithoutEntityManager(id, manager)
      val result = cutterzuweisung.getCutterhautSet.map((h: Cutterhaut) => 100 * h.surfmes / h.surfsup - 100).forall( _.abs < maxAbweichung)

      tx.commit
      manager.close
      result
    }

    //
    //  setzen Status 40 - Buchung und Freigabe
    //
    def setStatus40(request: Request, response: Response) {

      val factory = JpaUtil.getEntityManagerFactory
      val manager = factory.createEntityManager
      val tx = manager.getTransaction
      tx.begin

      val cutterzuweisung = getCutterzuweisungWithoutEntityManager(id, manager)
      cutterzuweisung.getCutterhautSet.map(_.hautaktiv == true)
      cutterzuweisung.status = 40  // Buchung und Freigabe
      cutterzuweisung.setVerbrauchMes() // aktualisieren verbrauchMes
      cutterzuweisung.setVerbrauchSup() // aktualisieren verbrauchSup

      // get Teammitglieder and get print for the team
      var query3 = manager.createQuery("select c from Cutterteammitglied c where cutterzuweisungs_id = :cutterzuweisungs_id", classOf[Cutterteammitglied])
      query3.setParameter("cutterzuweisungs_id", id)
      val teammitgliederList = query3.getResultList
      var teaminfo : BdeMadat = null

      if (teammitgliederList.isEmpty) {
           // keine Teamangaben gefunden
      } else                          {
           teaminfo = getTeaminformation(teammitgliederList.get(0).team, manager)
      }

      var printer = ""

      // if no printer selected use the printer from the team info
      if (request.getParameter("drucker").equals("0"))    {
          printer = teaminfo.getMaDrucker
      } else                                              {
          printer = request.getParameter("drucker") match {
             case "1" => "rbcutter01"
             case "2" => "rbcutter02"
             case "3" => "rbit01"
          }
      }

      // show selected printer on screen
      request.setAttribute("message", printer + " gesendet")

      // generate xml
      val outputXml = cutterzuweisung.toXml("Team")

      // create xml file
      var filename = ""

      try {
            filename = cutterzuweisung.getFilename(printer)
      } catch {
            case e => Logger.getAnonymousLogger().log(Level.SEVERE, "CutterbuchungController2 - XML-Dateiname konnte nicht erzeugt werden" + e)
                      filename="standardFileName"
      }

      try {
            XML.save(filename, outputXml, "UTF-8", true, null)
      } catch {
            case e => Logger.getAnonymousLogger().log(Level.SEVERE, "CutterbuchungController2 - Fehler aufgetreten beim Erstellung der XML-Datei" + e)
      }

//      try {
//            var p : Process = Runtime.getRuntime.exec("lpr -Prbit01 " + filename)
//      } catch {
//            case e => Logger.getAnonymousLogger().log(Level.SEVERE, "CutterbuchungController2 - Fehler aufgetreten beim Drucken der XML-Datei" + e)
//      }

      tx.commit
      manager.close

      CutterzuweisungDao update cutterzuweisung
    }

    //
    // Cutterzuweisung + Cutterteammitglied laden, Maske aufbereiten
    //
    def loadCutterzuweisung(request: Request, response: Response)  =  {

      var cutterzuweisungList : java.util.List[Cutterzuweisung] = null
      var cutterteammitgliedList : java.util.List[Cutterteammitglied] = null

      val factory = JpaUtil.getEntityManagerFactory
      val manager = factory.createEntityManager
      val tx = manager.getTransaction
      tx.begin

      var query = manager.createQuery("select c from Cutterzuweisung c where id = :id", classOf[Cutterzuweisung])
      query.setParameter("id", id)
      cutterzuweisungList = query.getResultList

      var query2 = manager.createQuery("select c from Cutterteammitglied c where cutterzuweisungs_id = :id", classOf[Cutterteammitglied])
      query2.setParameter("id", id)
      cutterteammitgliedList = query2.getResultList

      if (cutterteammitgliedList.isEmpty) {
        request.setAttribute("message", " - Teammitglieder fehlen - Bitte zuerst Teammitglieder anlegen!")
      }

      var maske: Cutterbuchungsmaske = new Cutterbuchungsmaske
      var cutterzuweisung: Cutterzuweisung = new Cutterzuweisung
      cutterzuweisung = cutterzuweisungList.get(0)

      maske.setCutterzuweisung(cutterzuweisung)
      maske.setCutterteammitgliedList(cutterteammitgliedList)

      maske.setCutterzuweisung(cutterzuweisung)

      session.setAttribute("maske", maske)
      session.setAttribute("id", id)
      session.setAttribute("team", cutterzuweisung.getSainame.substring(0,1))
      session.setAttribute("sainame", cutterzuweisung.getSainame)

      val sollmenge =  cutterzuweisung.getSummeSollmenge
      val sumReste  =  cutterzuweisung.getSumSurfreste
      val sumRecut  =  cutterzuweisung.getSumRecut
      val istmenge  =  cutterzuweisung.getSummeIstmenge - sumReste - sumRecut

      session.setAttribute("sollmenge", sollmenge)
      session.setAttribute("istmenge", istmenge)
      session.setAttribute("sumReste", sumReste)
      session.setAttribute("sumRecut", sumRecut)

      session.setAttribute("cutterzuweisungsstatus", cutterzuweisung.getStatus)

      tx.commit
      manager.close

  }

  //
  // send Map incl. error msg
  //
  def sendMap(request: Request, response: Response, inputError: InputError): Unit = {

    errorlist.add(inputError)

    request.setAttribute("errorlist", errorlist)
    request.setAttribute("errorset", errorset)

    var view: RequestDispatcher = request.getRequestDispatcher("Cutterbuchung.jsp")
    view.forward(request, response)
  }

  //
  // Neuberechnung für die Werteingabefelder
  //
  def neuberechnen(request: Request, response: Response): Unit = {

    var surfsupMap = Map[Long, Float]().withDefaultValue(-1f)
    var abschreibungMap = Map[Long, Float]().withDefaultValue(-1f)
    var hautaktivMap = Map[Long, String]().withDefaultValue("")

    // Liefermass-Felder (surfsup) in Map setzen
    for (item <- request.getParameterMap.toMap if item._1.startsWith("surfsup")) {
      // get id
      val key = item._1.drop(item._1.indexOf('.')+1)
      // get input value
      val value = request.getParameter(item._1)
      // set valid fields into the map
      try{
        surfsupMap += (key.toLong -> value.replace(',', '.').toFloat)
      } catch {
        case e => Logger.getAnonymousLogger().log(Level.SEVERE, "CutterbuchungController2 - Fehler bei Lieferantenmass in Map setzen")
      }
    }

    // Abschreibung-Felder in Map setzen
    for (item <- request.getParameterMap.toMap if item._1.startsWith("abschreibung")) {
      // get id
      val key = item._1.drop(item._1.indexOf('.')+1)
      // get input value
      val value = request.getParameter(item._1)
      // set valid fields into the map
      try{
        abschreibungMap += (key.toLong -> value.replace(',', '.').toFloat)
      } catch {
        case e => Logger.getAnonymousLogger().log(Level.SEVERE, "CutterbuchungController2 - Fehler bei Abschreibung in Map setzen")
      }
    }

    // Haut-aktiv in Map setzen
    for (item <- request.getParameterMap.toMap if item._1.startsWith("haut")) {
      // get id
      val key = item._1.drop(item._1.indexOf('.')+1)
      // get input value
      val value = request.getParameter(item._1)
      // set valid fields into the map
      try{
        hautaktivMap += (key.toLong -> value)
      } catch {
        case e => Logger.getAnonymousLogger().log(Level.SEVERE, "CutterbuchungController2 - Fehler bei Hautaktiv in Map setzen")
      }
    }

    val factory = JpaUtil.getEntityManagerFactory
    val manager = factory.createEntityManager
    val tx = manager.getTransaction
    tx.begin

    val cutterzuweisung = getCutterzuweisungWithoutEntityManager(id, manager)

//    cutterzuweisung.getCutterhautSet.foreach(println)

    // set input values
    for (haut <- cutterzuweisung.getCutterhautSet) {
      //
      if (surfsupMap.get(haut.id).get >= 0)        {
        haut.surfsup = surfsupMap.get(haut.id).get
      }
      //
      if (abschreibungMap.get(haut.id).get >= 0)   {
        haut.abschreibung = abschreibungMap.get(haut.id).get
      }

      if (hautaktivMap.get(haut.id).get > "")       {
        haut.hautaktiv = true
      } else                                          {
        haut.hautaktiv = false
      }

    }

    if (cutterzuweisung.getCutterhautSet.isEmpty) {
        cutterzuweisung.sumAbschreibung = 0
    }  else {
         val cutterhautList = cutterzuweisung.getCutterhautSet.toList
         cutterzuweisung.sumAbschreibung = cutterhautList.map(_.abschreibung).sum
         //
         cutterzuweisung.sumSurfmes = cutterhautList.map(_.surfmes).sum
         cutterzuweisung.sumSurfsup = cutterhautList.map(_.surfsup).sum
         // update cutterzuweisung
    }

    cutterzuweisung.setVerbrauchMes() // aktualisieren verbrauchMes
    cutterzuweisung.setVerbrauchSup() // aktualisieren verbrauchSup

    tx.commit
    manager.close

    CutterzuweisungDao update cutterzuweisung

  } // end of neuberechnen

  //
  // get Cutterzuweisung
  //
  def getCutterzuweisung(id : Long) : Cutterzuweisung = {

    val factory = JpaUtil.getEntityManagerFactory
    val manager = factory.createEntityManager
    val tx = manager.getTransaction
    tx.begin

    var cutterzuweisungList : java.util.List[Cutterzuweisung] = null

    var query = manager.createQuery("select c from Cutterzuweisung c where id = :id", classOf[Cutterzuweisung])
    query.setParameter("id", id)
    cutterzuweisungList = query.getResultList

    tx.commit
    manager.close

    cutterzuweisungList.get(0).asInstanceOf[Cutterzuweisung]
  }

  def getCutterzuweisungWithoutEntityManager(id: Long, manager: EntityManager) : Cutterzuweisung = {

    var cutterzuweisungList : java.util.List[Cutterzuweisung] = null
    var query = manager.createQuery("select c from Cutterzuweisung c where id = :id", classOf[Cutterzuweisung])
    query.setParameter("id", id)
    cutterzuweisungList = query.getResultList
    cutterzuweisungList.get(0).asInstanceOf[Cutterzuweisung]
  }


  def verteilenIstmenge() = {

      val factory = JpaUtil.getEntityManagerFactory
      val manager = factory.createEntityManager
      val tx = manager.getTransaction
      tx.begin

      val cutterzuweisung = getCutterzuweisungWithoutEntityManager(id, manager)

      val summmeSollmenge = cutterzuweisung.getSummeSollmenge.toFloat

      Logger.getAnonymousLogger().log(Level.SEVERE, "*** summeSollmenge ==>" + summmeSollmenge)

      // Verteilung Istmenge (Auftragsinfo)
      for (cutterpraemie <- cutterzuweisung.cutterpraemieSet) {
        try {
            Logger.getAnonymousLogger().log(Level.SEVERE, "*** IST-Menge Lieferantenmass ==>" + cutterzuweisung.getSummeIstmengeLiefermass())
//          cutterpraemie.istmenge_maschinenmass = (cutterzuweisung.getSummeIstmengeLiefermass.toFloat - cutterzuweisung.getSumSurfreste - cutterzuweisung.getSumAbschreibung) * ( cutterpraemie.sollmenge / summmeSollmenge )
            cutterpraemie.istmenge_maschinenmass = cutterzuweisung.getSummeIstmengeLiefermass.toFloat  * ( cutterpraemie.sollmenge / summmeSollmenge )
            Logger.getAnonymousLogger().log(Level.SEVERE, "*** Cutterprämie ===============>" + cutterpraemie.istmenge_maschinenmass)
        }  catch {
          case e => cutterpraemie.istmenge_maschinenmass = 0
        }

      }

      // TODO Mengen aktualisieren
      cutterzuweisung.setVerbrauchMes() // aktualisieren verbrauchMes
      cutterzuweisung.setVerbrauchSup() // aktualisieren verbrauchSup

      tx.commit
      manager.close

      CutterzuweisungDao update cutterzuweisung

  }

  def getTeaminformation(team: Int, manager: EntityManager) : BdeMadat = {
    var mitarbeiterList : java.util.List[BdeMadat] = null
    var query = manager.createQuery("select c from BdeMadat c where ma_pnr = :team", classOf[BdeMadat])
    query.setParameter("team", team)
    mitarbeiterList = query.getResultList
    mitarbeiterList.get(0).asInstanceOf[BdeMadat]
  }

  } // end of processRequest - all functions are nested functions of processRequest (scope)

}