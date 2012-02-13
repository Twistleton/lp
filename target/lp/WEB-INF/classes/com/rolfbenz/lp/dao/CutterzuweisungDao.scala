package com.rolfbenz.lp.dao

/**
 * CutterzuweisungDao (Data Access Object)
 *
 * User: Speer, Rolf
 * Date: 28.02.2011
 */

import _root_.com.rolfbenz.lp.entity.Cutterzuweisung
import _root_.com.rolfbenz.lp.jpa.JpaUtil

object CutterzuweisungDao extends GenericDao[Cutterzuweisung]  {

//  def findBySainame(sainame : String) = {
//    val factory = JpaUtil.getEntityManagerFactory
//    val manager = factory.createEntityManager
//    val tx = manager.getTransaction
//    tx.begin
//    // JPQL
//    val query = manager.createQuery("select c from Cutterzuweisung c where c.sainame = :sainame")
//    query.setParameter("sainame", sainame)
//    val queryResultList = query.getResultList
//    // convert Java list to scala list
//    val queryScalaResultList = scala.collection.JavaConversions.asScalaBuffer(queryResultList)
//    queryScalaResultList
//
//  }

    // remove all rows for the giving sainame
  def removeAllCutterrows(sainame : String) = {
    val factory = JpaUtil.getEntityManagerFactory
    val manager = factory.createEntityManager
    val tx = manager.getTransaction
    tx.begin
    // JPQL bulk delete
    val delete = manager.createQuery("DELETE FROM Cutterrow c             " +
                                     " WHERE c.sainame = :sainame         ")
    delete.setParameter("sainame", sainame)
    delete.executeUpdate
    tx.commit
  }

    // remove all rows for the giving sainame
  def removeAllCutterhaeute(sainame : String) = {
    val factory = JpaUtil.getEntityManagerFactory
    val manager = factory.createEntityManager
    val tx = manager.getTransaction
    tx.begin
    // JPQL bulk delete
    val delete = manager.createQuery("DELETE FROM Cutterhaut c            " +
                                     " WHERE c.sainame = :sainame         ")
    delete.setParameter("sainame", sainame)
    delete.executeUpdate
    tx.commit
  }

    def findAll() = {

      val factory = JpaUtil.getEntityManagerFactory
      val manager = factory.createEntityManager
      val tx = manager.getTransaction
      tx.begin
//    val allList = List()
//      val query = manager.createQuery("select a from Cutterzuweisung a where a.status <= 10 order by a.erstelldatum, a.aenderungsdatum ")
      val query = manager.createQuery("select a from Cutterzuweisung a order by a.erstelldatum desc, a.aenderungsdatum desc ")
      val allList = query.getResultList

      tx.commit
      manager.close
      allList
  }


}