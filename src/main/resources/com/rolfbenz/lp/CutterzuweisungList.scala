package com.rolfbenz.lp

import dao.CutterzuweisungDao
import entity.Cutterzuweisung
import javax.servlet.http.HttpServlet
import javax.servlet.http.{ HttpServletRequest => Request, HttpServletResponse => Response}
import java.util.ArrayList
import scala.collection.JavaConversions._

/**
 *  CutterzuweisungList
 *
 *  URL with all request parameters:
 *
 *  http://localhost:8080/CutterzuweisungList.do?team=3&vonstatus=30&bisstatus=40
 *
 *  request parameters: team, vonstatus, bisstatus
 *
 */

class CutterzuweisungList extends HttpServlet {

  override def doPost(request: Request, response: Response): Unit = {
    processRequest(request, response)
  }

  override def doGet(request: Request, response: Response): Unit = {
    processRequest(request, response)
  }

  def processRequest(request: Request, response: Response): Unit = {

    var team, vonstatus, bisstatus, meister = 0

    // Team
    try {team = request.getParameter("team").toInt
    } catch {
      case e => team=0
    }

    // von-Status
    try {vonstatus = request.getParameter("vonstatus").toInt
    } catch {
      case e => vonstatus=0
    }

    // bis-Status
    try {bisstatus = request.getParameter("bisstatus").toInt
    } catch {
      case e => bisstatus=99
    }

    // Meister
    try {meister = request.getParameter("meister").toInt
    } catch {
      case e => meister=0
    }


    // get Cutterzuweisungen
    val list = CutterzuweisungDao findAll

    // convert result list to a generic java.util.List with Cutterzuweisung as type
    // parameter to apply the filter functions with predicates
    var i = 0
    var cutterzuweisungList : java.util.List[Cutterzuweisung] = new ArrayList[Cutterzuweisung]();
    while (i < list.size) {
      cutterzuweisungList.add(list.get(i).asInstanceOf[Cutterzuweisung])
      i = i + 1
    }

    // predicate team
    if (team > 0) {
      cutterzuweisungList = cutterzuweisungList.filter(_.sainame.substring(0,1)==team.toString)
    }

    // predicate vonstatus
    if (vonstatus > 0) {
      cutterzuweisungList = cutterzuweisungList.filter(_.status >= vonstatus)
    }

    // predicate bisstatus
    if (bisstatus > 0) {
      cutterzuweisungList = cutterzuweisungList.filter(_.status <= bisstatus)
    }

    request.setAttribute("cutterzuweisungList", cutterzuweisungList)
    request.setAttribute("meister", meister)


    val view = request.getRequestDispatcher("CutterzuweisungList.jsp")

    view.forward(request, response);

  }

}