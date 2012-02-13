package com.rolfbenz.lp.dao

import _root_.com.rolfbenz.lp.jpa.JpaUtil

/**
 * GenericDao (generisches Data Acess Object)
 *
 * User: Speer, Rolf
 * Date: 01.03.2011
 */

trait GenericDao[T] {

  def save(entity : T) = {
    val factory = JpaUtil.getEntityManagerFactory
    val manager = factory.createEntityManager
    val tx = manager.getTransaction
    tx.begin
    manager.persist(entity)
    tx.commit
    manager.close

  }

  def update(entity : T) = {
    val factory = JpaUtil.getEntityManagerFactory
    val manager = factory.createEntityManager
    val tx = manager.getTransaction
    tx.begin
    manager.merge(entity)
    tx.commit
    manager.close
  }

  def delete(entity: T) = {
    val factory = JpaUtil.getEntityManagerFactory
    val manager = factory.createEntityManager
    val tx = manager.getTransaction
    tx.begin
    manager.remove(entity)
    tx.commit
    manager.close
  }

//  def findAll() : List[T] = {
//
//    val factory = JpaUtil.getEntityManagerFactory
//    val manager = factory.createEntityManager
//    val tx = manager.getTransaction
//    tx.begin
//    val allList = List()
////    val allList = manager.find("select a from " + classOf[T])
//    tx.commit
//    manager.close
//    allList
//  }


}