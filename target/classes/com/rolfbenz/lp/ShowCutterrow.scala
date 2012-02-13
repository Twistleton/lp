package com.rolfbenz.lp

import entity.{Cutterzuweisung, Cutterrow}
import jpa.JpaUtil
import javax.persistence.{EntityManager, EntityTransaction}
import javax.servlet.http.HttpServlet
import javax.servlet.http.{ HttpServletRequest => Request, HttpServletResponse => Response}
import javax.servlet.RequestDispatcher
import java.util.logging.Level
import java.util.logging.Logger

/**
 *
 *  lp - ShowCutterrow
 *
 *  User: Rolf Speer
 *  Date: 01.08.2011
 *
 */

class ShowCutterrow extends HttpServlet {

  override def doPost(request: Request, response: Response): Unit = {
    processRequest(request, response)
  }

  override def doGet(request: Request, response: Response): Unit = {
    processRequest(request, response)
  }

  def processRequest(request: Request, response: Response) {

    Logger.getAnonymousLogger().log(Level.INFO, "--- ShowCutterrow - processRequest ---")

    var cutterzuweisungList : java.util.List[Cutterzuweisung] = null

    var em : EntityManager = JpaUtil.getEntityManagerFactory().createEntityManager()
    var tx : EntityTransaction = em.getTransaction
    val id : Long = request.getParameter("id").toLong

    tx.begin
    var query = em.createQuery("select c from Cutterzuweisung c where id = :id", classOf[Cutterzuweisung])
    query.setParameter("id", id)
    cutterzuweisungList = query.getResultList
    val cutterrowSet : java.util.Set[Cutterrow] = cutterzuweisungList.get(0).getCutterrowSet
    request.setAttribute("cutterrowSet", cutterrowSet)
    tx.commit

    var view: RequestDispatcher = request.getRequestDispatcher("showCutterrow.jsp")
    view.forward(request, response)

  }

}