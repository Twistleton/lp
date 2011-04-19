package com.rolfbenz.lp

import domain.{Cutterzuweisung, Cutterteammitglied}
import jpa.JpaUtil
import javax.persistence.{EntityManager, EntityTransaction}
import javax.servlet.http.HttpServlet
import javax.servlet.http.{ HttpServletRequest => Request, HttpServletResponse => Response}
import javax.servlet.RequestDispatcher

/**
 * Created by IntelliJ IDEA.
 * User: U987
 * Date: 07.04.11
 */

class TeamverwaltungController extends HttpServlet {


  override def doPost(request: Request, response: Response): Unit = {

    System.out.println("doPost")
    processRequest(request, response)
  }

  override def doGet(request: Request, response: Response): Unit = {

    System.out.println("doGet")
    processRequest(request, response)

  }

  def processRequest(request: Request, response: Response) {

    var cutterzuweisungList : java.util.List[Cutterzuweisung] = null

    var em : EntityManager = JpaUtil.getEntityManagerFactory().createEntityManager()
    var tx : EntityTransaction = em.getTransaction
//    var id : Long = (Long) request.getParameter("id").toLong
    var id : Long = 1


    tx.begin

    var query = em.createQuery("select c from Cutterzuweisung c where c.id = :id ", classOf[Cutterzuweisung])
    query.setParameter("id", id)
    cutterzuweisungList = query.getResultList



    var queryResultList : java.util.List[Cutterteammitglied] = null

    if (request.getParameter("delete") != null) {
              System.out.println("delete Funktion")
              System.out.println(request.getParameter("personalnummer"))
              // Eingabe prüfen
              // wenn okay save
              // sonst Fehler rausschicken
    } else {
//              var em : EntityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
//              var tx : EntityTransaction = em.getTransaction();
//              tx.begin();
//              var query = em.createQuery("select c from Cutterteammitglied c where c.id > :id", classOf[Cutterteammitglied])
//              query.setParameter("id", 0L)
//              queryResultList = query.getResultList
//              tx.commit
    }


    System.out.println("processRequest")


    request.setAttribute("cutterteamList", queryResultList)

    System.out.println("==>" + queryResultList
    )

    tx.commit

    var view: RequestDispatcher = request.getRequestDispatcher("Teamverwaltung.jsp")
    view.forward(request, response)


  }

}