package com.rolfbenz.lp

import dao.CutterzuweisungDao
import entity.com.rolfbenz.lp.entity.BdeMadat
import entity.{Cutterteammitglied, Cutterzuweisung}
import jpa.JpaUtil
import javax.persistence.{EntityManager, EntityTransaction}
import javax.servlet.http.HttpServlet
import javax.servlet.http.{ HttpServletRequest => Request, HttpServletResponse => Response}
import javax.servlet.RequestDispatcher
import view.Cutterbuchungsmaske
import java.util.{HashMap, ArrayList}
import _root_.scala.xml._
import _root_.scala.collection.JavaConversions._
import java.util.logging.Level
import java.util.logging.Logger

/**
 *
 *  lp - CutterbuchungMeisterController
 *
 *  User: Rolf Speer
 *  Date: 01.08.2011
 *
 */

class CutterbuchungMeisterController extends HttpServlet {

  override def doPost(request: Request, response: Response): Unit = {
    processRequest(request, response)
  }

  override def doGet(request: Request, response: Response): Unit = {
    processRequest(request, response)
  }

  def processRequest(request: Request, response: Response) {

    // starttime
    val stime = new java.util.Date()

    Logger.getAnonymousLogger().log(Level.INFO, "* start processRequest (CutterbuchungMeisterController) ----------------------------------------------" + stime)

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

    Logger.getAnonymousLogger().log(Level.INFO, "* id for processRequest (CutterbuchungMeisterController) =============================> " + id)

    if (request.getParameter("berechnen") != null) {
        neuberechnen(request, response)
        verteilenIstmenge()
        loadCutterzuweisung(request, response)
        sendMap(request, response, new InputError("(I) - Werte neu berechnet", "0"))
    } else if (request.getParameter("pruefen") != null) {
             if (request.getParameter("grund").equals("0")) {
                sendMap(request, response, new InputError("(A) - Bitte Prüfungsgrund ausgewählen", "0"))
             }  else {
                setStatus20(request, response)
                loadCutterzuweisung(request, response)
                sendMap(request, response, new InputError("(I) - Grund gesetzt", "0"))
      }
    }
    else if (request.getParameter("freigeben") != null) {
             if (checkStatus(request, response)) {
                setStatus40(request, response)
                loadCutterzuweisung(request, response)
                sendMap(request, response, new InputError("Cutterzuweisung freigegeben, Kontrollbeleg an Drucker ", "0"))
             } else                               {
                sendMap(request, response, new InputError("(A) - Status erlaubt keine Freigabe", "0"))
             }
    }
    else if (request.getParameter("loeschen") != null) {
             setStatus50(request, response)
             loadCutterzuweisung(request, response)
             sendMap(request, response, new InputError("Zuweisung gelöscht (Status 50)", "0"))
    }
    else if (request.getParameter("aendern") != null)  {
             setKommentare(request, response)
             loadCutterzuweisung(request, response)
             sendMap(request, response, new InputError("Kriterium / Info geändert", "0"))
    }
    else if (request.getParameter("zuruecksetzen") != null) {
                setStatus10(request, response)
                loadCutterzuweisung(request, response)
                sendMap(request, response, new InputError("Status wieder auf 10 zurückgesetzt", "0"))
    }
    else {   loadCutterzuweisung(request, response)
             sendMap(request, response, new InputError("Cutterzuweisung angezeigt", "0"))
    }

    Logger.getAnonymousLogger().log(Level.INFO, "* end  processRequest (CutterbuchungMeisterController) -----------------------------------------------")

    //
    // end of processRequest
    //
    // -----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // nested function of processRequest (scope)
    //

    //
    //  setzen Status 10 - Status zurückfahren
    //
    def setStatus10(request: Request, response: Response) {

      val cutterzuweisung = getCutterzuweisung(id)
      cutterzuweisung.status = 10
      CutterzuweisungDao update cutterzuweisung
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

    //
    //  setzen Status 40 - Buchung und Freigabe
    //
    def setStatus40(request: Request, response: Response) {

      val factory = JpaUtil.getEntityManagerFactory
      val manager = factory.createEntityManager
      val tx = manager.getTransaction
      tx.begin

      val cutterzuweisung = getCutterzuweisungWithoutEntityManager(id, manager)

      cutterzuweisung.status = 40  // Buchung und Freigabe

      // get Teammitglieder and get print for the team
      var query3 = manager.createQuery("select c from Cutterteammitglied c where cutterzuweisungs_id = :id", classOf[Cutterteammitglied])
      query3.setParameter("id", id)
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
      val outputXml = cutterzuweisung.toXml("Meister")

      // create xml file
      var filename = ""

      try {
            filename = cutterzuweisung.getFilename(teaminfo.getMaDrucker)
      } catch {
            case e => Logger.getAnonymousLogger().log(Level.SEVERE, "CutterbuchungController2 - XML-Dateiname konnte nicht erzeugt werden" + e)
                      filename="standardFileName"
      }

      try {
            XML.save(filename, outputXml, "UTF-8", true, null)
      } catch {
            case e => Logger.getAnonymousLogger().log(Level.SEVERE, "CutterbuchungController2 - Fehler aufgetreten beim Erstellung der XML-Datei" + e)
      }

      tx.commit
      manager.close

      CutterzuweisungDao update cutterzuweisung
    }

    def setStatus50(request: Request, response: Response) {

      val cutterzuweisung = getCutterzuweisung(id)
      cutterzuweisung.status = 50
      CutterzuweisungDao update cutterzuweisung

    }

    def setKommentare(request: Request, response: Response) {

      val cutterzuweisung = getCutterzuweisung(id)
      cutterzuweisung.kriterium = request.getParameter("kriterium")
      cutterzuweisung.info = request.getParameter("info")
      CutterzuweisungDao update cutterzuweisung
    }

    //
    //  setzen Grund
    //
    def setGrund(request: Request, response: Response) {

      val cutterzuweisung = getCutterzuweisung(id)
      cutterzuweisung.grund = request.getParameter("grund")
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

    var view: RequestDispatcher = request.getRequestDispatcher("CutterbuchungMeister.jsp")
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
        surfsupMap += (key.toLong -> value.toFloat)
      } catch {
        case e => Logger.getAnonymousLogger().log(Level.SEVERE, "CutterbuchungMeisterController - Fehler bei Lieferantenmass in Map setzen")
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
        abschreibungMap += (key.toLong -> value.toFloat)
      } catch {
        case e => Logger.getAnonymousLogger().log(Level.SEVERE, "CutterbuchungMeisterController - Fehler bei Abschreibung in Map setzen")
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
        case e => Logger.getAnonymousLogger().log(Level.SEVERE, "CutterbuchungMeisterController - Fehler bei Hautaktiv in Map setzen")
      }
    }

    val factory = JpaUtil.getEntityManagerFactory
    val manager = factory.createEntityManager
    val tx = manager.getTransaction
    tx.begin

    val cutterzuweisung = getCutterzuweisungWithoutEntityManager(id, manager)

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

    val cutterhautList = cutterzuweisung.getCutterhautSet.toList

    cutterzuweisung.sumAbschreibung = cutterhautList.map(_.abschreibung).sum

    tx.commit
    manager.close

    // update cutterzuweisung
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

//      val summmeSollmenge = cutterzuweisung.getSummeSollmenge.toFloat - cutterzuweisung.getSumSurfreste - cutterzuweisung.getSumAbschreibung
      val summmeSollmenge = cutterzuweisung.getSummeSollmenge.toFloat

      // Verteilung Istmenge (Auftragsinfo)
      for (cutterpraemie <- cutterzuweisung.cutterpraemieSet) {
        try {
          cutterpraemie.istmenge_maschinenmass = cutterzuweisung.getSummeIstmengeLiefermass.toFloat  * ( cutterpraemie.sollmenge / summmeSollmenge )
        }  catch {
          case e => cutterpraemie.istmenge_maschinenmass = 0
        }

      }

      tx.commit
      manager.close

      CutterzuweisungDao update cutterzuweisung
  }

    def getTeaminformation(team: Int, manager: EntityManager): BdeMadat = {
      var mitarbeiterList: java.util.List[BdeMadat] = null
      var query = manager.createQuery("select c from BdeMadat c where ma_pnr = :team", classOf[BdeMadat])
      query.setParameter("team", team)
      mitarbeiterList = query.getResultList
      mitarbeiterList.get(0).asInstanceOf[BdeMadat]
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

  } // end of processRequest - all functions are nested functions of processRequest (scope)

}