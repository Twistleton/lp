package com.rolfbenz.lp

import entity.Cutterteammitglied
import jpa.JpaUtil
import javax.persistence.{EntityManager, EntityTransaction}
import javax.servlet.http.HttpServlet
import javax.servlet.http.{ HttpServletRequest => Request, HttpServletResponse => Response}
import javax.servlet.RequestDispatcher
import java.util.logging.Level
import java.util.logging.Logger

/**
 *
 *  lp - ShowCutterteammitglied
 *
 *  User: Rolf Speer
 *  Date: 01.08.2011
 *
 */

class ShowCutterteammitglied extends HttpServlet {

  override def doPost(request: Request, response: Response): Unit = {
    processRequest(request, response)
  }

  override def doGet(request: Request, response: Response): Unit = {
    processRequest(request, response)
  }

  def processRequest(request: Request, response: Response) {

    Logger.getAnonymousLogger().log(Level.INFO, "--- ShowCutterteamitglied - processRequest ---")

    var cutterteammitgliedList : java.util.List[Cutterteammitglied] = null

    var em : EntityManager = JpaUtil.getEntityManagerFactory().createEntityManager()
    var tx : EntityTransaction = em.getTransaction
    val id : Long = request.getParameter("id").toLong

    tx.begin
    var query = em.createQuery("select c from Cutterteammitglied c where cutterzuweisungs_id = :id", classOf[Cutterteammitglied])
    query.setParameter("id", id)

    cutterteammitgliedList = query.getResultList

    request.setAttribute("cutterteammitgliedList", cutterteammitgliedList)
    tx.commit

    var view: RequestDispatcher = request.getRequestDispatcher("showCutterteammitglied.jsp")
    view.forward(request, response)

  }

}