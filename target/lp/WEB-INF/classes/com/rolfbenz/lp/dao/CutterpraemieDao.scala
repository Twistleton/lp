package com.rolfbenz.lp.dao

import com.rolfbenz.lp.jpa.JpaUtil
import com.rolfbenz.lp.entity.{Cutterzuweisung, Cutterpraemie}

/**
 *
 * CutterpraemieDao (Data Access Object)
 *
 * User: Speer, Rolf 
 * Date: 09.05.2011
 */

object CutterpraemieDao extends GenericDao[Cutterpraemie] {

  def getCutterpraemien(saiNameWithWildcard: String, cutterzuweisung : Cutterzuweisung)  = {

   val factory = JpaUtil.getEntityManagerFactory
   val manager = factory.createEntityManager
   val tx = manager.getTransaction
   tx.begin

   // build sql command 
   val select = <s>SELECT rownum                        AS id
                        , ZUS_SAMMEL_KOPF.SAK_SANR      AS sammelauftragsnummer
                        , PLR_AUFTR_KOPF.AKO_ABNR       AS auftragsnummer
                        , ZUS_KOMMISSION.KOM_ART_NR     AS bezugsmaterial
                        , ZUS_KOMMISSION.KOM_QM_BRUTTO  AS sollmenge
                        , ZUS_KOMMISSION.KOM_QM_NETTO   AS nettomenge
                        , 0                             AS istmenge_lieferantenmass
                        , 0                             AS istmenge_maschinenmass
                        , null                          AS erstelldatum
                        , null                          AS datum_weiterleitung
                        , 0                             AS buchungsmonat
                        , 0                             AS vorgangsart
                        , 0                             AS verursacherschluessel
                        , ZUS_KOMMISSION.KOM_PROG       AS programm
                        , 0                             AS kennzeichen_praemie
                        , ZUS_SAMMEL_KOPF.SAK_LIEF_KKZ  AS lief_kkz
                        , ZUS_SAMMEL_KOPF.SAK_CHARG_NR  AS charg_nr
                        , ZUS_KOMMISSION.KOM_ZS_SYS     AS zuschnittssystem
                        , 0                             AS zuschnittswerk
                        , PLR_AUFTR_KOPF.AKO_LEDER_KL   AS lederklasse
                        , 0                             AS status
                        , PLR_AUFTR_KOPF.AKO_ABNR       AS auftragsnummer
                        , null                          AS erstelldatum
                        , null                          AS aenderungsdatum
                        , null                          AS cutterzuweisung
                        , null                          AS sainame
                     FROM PLRV11.ZUS_SAMMEL_KOPF
                        , PLRV11.PLR_AUFTR_KOPF
                        , PLRV11.ZUS_KOMMISSION
                        , PLRV11.PLR_AUFTR_STATUS
                    WHERE ZUS_SAMMEL_KOPF.SAK_SANR = ZUS_KOMMISSION.KOM_SAMMEL_ABNR
                      AND ZUS_KOMMISSION.KOM_ABNR =  PLR_AUFTR_KOPF.AKO_ABNR
                      AND PLR_AUFTR_STATUS.AS_ABNR = PLR_AUFTR_KOPF.AKO_ABNR
                      AND PLR_AUFTR_STATUS.AS_AEND_IX = PLR_AUFTR_KOPF.AKO_AEND_IX
                      AND PLR_AUFTR_STATUS.as_status != 99
                      AND ZUS_SAMMEL_KOPF.SAK_SANR IN
                         (SELECT SAI_SANR
                            FROM PLRV11.SAI_TABLE1
                           WHERE SAI_SANR = ZUS_SAMMEL_KOPF.SAK_SANR
                             AND SAI_NAME LIKE '{saiNameWithWildcard}') </s>.text

    
    // result set for the entity Cutterpraemie 
    val query = manager.createNativeQuery(select, "getRows")

    // return java.util.ArrayList
    query.getResultList()

  }

}