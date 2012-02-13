package com.rolfbenz.lp.dao

import _root_.com.rolfbenz.lp.jpa.JpaUtil
import _root_.com.rolfbenz.lp.entity.Cutterteammitglied

/**
 * CutterteammitgliedDao (Data Access Object)
 *
 * User: Speer, Rolf
 * Date: 28.02.2011
 */


object CutterteammitgliedDao extends GenericDao[Cutterteammitglied] {

  def getTeammitglieder(sainame : String) = {

    val factory = JpaUtil.getEntityManagerFactory
    val manager = factory.createEntityManager
    val tx = manager.getTransaction
    tx.begin
    // JPQL
    val query = manager.createQuery("select c from Cutterteammitglied c where c.sainame = :sainame", classOf[Cutterteammitglied])
    query.setParameter("sainame", sainame)
    query.getResultList
  }

  def getTeammitgliedById(id : Long) = {

    val factory = JpaUtil.getEntityManagerFactory
    val manager = factory.createEntityManager
    val tx = manager.getTransaction
    tx.begin
    // JPQL
    val query = manager.createQuery("select c from Cutterteammitglied c where c.id = :id")
    query.setParameter("id", id)
    query.getResultList.get(0).asInstanceOf[Cutterteammitglied]
  }

  def setCutterzuweisungsId(sainame: String, id : Long) = {

    val factory = JpaUtil.getEntityManagerFactory
    val manager = factory.createEntityManager
    val tx = manager.getTransaction
    tx.begin
    // JPQL bulk update
    val update = manager.createQuery("UPDATE Cutterteammitglied c         " +
                                     "   SET c.cutterzuweisungs_id =  :id " +
                                     " WHERE sainame = :sainame ")
    update.setParameter("sainame", sainame)
    update.setParameter("id", id)
    update.executeUpdate
    tx.commit
  }

}