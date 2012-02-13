package com.rolfbenz.lp

import dao.CutterteammitgliedDao
import entity.{BdeMadatEntity, Cutterzuweisung, Cutterteammitglied}
import jpa.JpaUtil
import javax.persistence.{EntityManager, EntityTransaction}
import javax.servlet.http.HttpServlet
import javax.servlet.http.{ HttpServletRequest => Request, HttpServletResponse => Response}
import javax.servlet.RequestDispatcher
import java.util.logging.Level
import java.util.logging.Logger

/**
 *
 *  lp - TeamverwaltungController
 *
 *  User: Rolf Speer
 *  Date: 01.08.2011
 *
 */

class TeamverwaltungController extends HttpServlet {

  override def doPost(request: Request, response: Response): Unit = {
    processRequest(request, response)
  }

  override def doGet(request: Request, response: Response):  Unit = {
    processRequest(request, response)
  }

  def processRequest(request: Request, response: Response) {

    var cutterzuweisungList : java.util.List[Cutterteammitglied] = null

    var em : EntityManager = JpaUtil.getEntityManagerFactory().createEntityManager()
    var tx : EntityTransaction = em.getTransaction

    var id : Long = 0L

    try{
      id = request.getParameter("id").toLong
    }  catch {
      case e => id = -1
    }


    tx.begin

    var query = em.createQuery("select c from Cutterteammitglied c where c.cutterzuweisungs_id = :id ", classOf[Cutterteammitglied])
    query.setParameter("id", id)
    cutterzuweisungList = query.getResultList

    var queryResultList : java.util.List[Cutterteammitglied] = null

    if (request.getParameter("delete") != null) {
              Logger.getAnonymousLogger().log(Level.INFO,  "TeamverwaltungController - delete ")
              val query2 = em.createQuery("select c from Cutterteammitglied c where c.id = :id")
              query2.setParameter("id", request.getParameter("cutterteammitgliedid").toString.toLong)
              val teammitglied = query2.getResultList.get(0).asInstanceOf[Cutterteammitglied]
              em.remove(teammitglied)
              request.setAttribute("message", "Mitarbeiter gelöscht")
    } else if (request.getParameter("insert") != null) {
               Logger.getAnonymousLogger().log(Level.INFO, "TeamverwaltungController - insert new Cutterteammitglied ==> " + request.getParameter("pnr"))
               val pnr = request.getParameter("pnr")
               var query = em.createQuery("select a from BdeMadatEntity a where maPnr = :pnr", classOf[BdeMadatEntity])
               query.setParameter("pnr",pnr.toInt)
               val resultList = query.getResultList

               if (resultList.isEmpty) {
                   request.setAttribute("message", "Personnummer nicht gefunden!")
               } else {
                   val mitarbeiter = resultList.get(0).asInstanceOf[BdeMadatEntity]
                   var session = request.getSession()
                   val id2 = session.getAttribute("id")
                   val team = session.getAttribute("team")
                   val sainame = session.getAttribute("sainame")
                   val neuzugang = new Cutterteammitglied()
                   neuzugang.nachname = mitarbeiter.getMaNname
                   neuzugang.vorname = mitarbeiter.getMaVname
                   neuzugang.cutterzuweisungs_id = id2.toString.toLong
                   neuzugang.personalnummer = mitarbeiter.getMaPnr.toInt
                   neuzugang.team = 991 + team.toString.toInt
                   neuzugang.sainame = sainame.toString
                   CutterteammitgliedDao save neuzugang
                   request.setAttribute("message", "Mitarbeiter hinzugefügt")
               }
    } else {
//              var em : EntityManager = JpaUtil.getEntityManagerFactory().createEntityManager
//              var tx : EntityTransaction = em.getTransaction
//              tx.begin

              var query = em.createQuery("select c from Cutterteammitglied c where c.cutterzuweisungs_id = :id", classOf[Cutterteammitglied])
              query.setParameter("id", id)
              queryResultList = query.getResultList
              // Element fuer Neuanlage Cutterteammitglied
              queryResultList.add(new Cutterteammitglied)
//              tx.commit
    }

    request.setAttribute("cutterteamList", queryResultList)

    em.flush
    tx.commit

    var view: RequestDispatcher = request.getRequestDispatcher("Teamverwaltung.jsp")
    view.forward(request, response)

    def addCutterteammitglied(request: Request, response: Response) {

      val pnr = request.getParameter("pnr")

      // new Cutterteammitglied()
    }

    def showTeammitglieder() {

              var query = em.createQuery("select c from Cutterteammitglied c where c.cutterzuweisungs_id = :id", classOf[Cutterteammitglied])
              query.setParameter("id", id)
              queryResultList = query.getResultList
              // Element fuer Neuanlage Cutterteammitglied
              queryResultList.add(new Cutterteammitglied)

    }


  }

}