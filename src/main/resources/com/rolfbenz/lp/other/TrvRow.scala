package com.rolfbenz.lp.other

/**
 * TrvRow (row from the trv files, the db entity is com.rolfbenz.entity.Cutterrow)
 * 
 * User: Speer, Rolf
 * Date: 17.02.2011
 */

case class TrvRow(line: String) {
  val data = line.split("\t")

  val hbegdigit     = data(0)  // Beginn Digit
  val henddigit     = data(1)  // Ende Digit
  val hbegnest      = data(2)  // Beginn Nesting
  val hendnest      = data(3)  // Ende Nesting
  val hsendgpilot   = data(4)  // Uebergabe Cutter
  val operator      = data(5)  // Personal
  val hide          = data(6)  // Hautfolge
  val batch         = data(7)  // ?
  val leathertype   = data(8)  // Artikelnummer 5-stellig
  val supplier      = data(9)  // Lieferant
  val surfmes       = data(10).toFloat // Flaeche (Maschinenmass)
  val surfsup       = data(11).toFloat // Flaeche (Lieferantenmass)
  val nbpiecnest    = data(12) // Teile genestet
  val nbpiecleft    = data(13) // Teile noch zu nesten
  val surfnest      = data(14) // Flaeche genestet
  val surfleft      = data(15) // Flaeche noch uebrig
  val effic         = data(16) // Effizienz
  val surfdef1      = data(17) // Flaeche A2
  val surfdef2      = data(18) // Flaeche A3
  val surfdef3      = data(19) // Flaeche A4
  val surfdef4      = data(20) // Flaeche A5
  val surfdef5      = data(21) // Flaeche Loch
  val nbdef1        = data(22) // Anzahl A2
  val nbdef2        = data(23) // Anzahl A3
  val nbdef3        = data(24) // Anzahl A4
  val nbdef4        = data(25) // Anzahl A5
  val nbdef5        = data(26) // Anzahl Loch
  val surfunit      = data(27) // Anzahl Rest
  val nbrestes      = data(28) // Anteil
  val surfreste1    = data(29).toFloat // Lederreste  1 bis 10
  val surfreste2    = data(30).toFloat
  val surfreste3    = data(31).toFloat
  val surfreste4    = data(32).toFloat
  val surfreste5    = data(33).toFloat
  val surfreste6    = data(34).toFloat
  val surfreste7    = data(35).toFloat
  val surfreste8    = data(36).toFloat
  val surfreste9    = data(37).toFloat
  val surfreste10   = data(38).toFloat
  val brdname       = data(39) // SAI Name   // BRD
  var commentfield  = 0f // Kommentar / Abschreibung

  try {
    commentfield = data(40).toFloat // Abschreibung
  } catch {
    case e: NumberFormatException => commentfield = 0f
  }

  val hbeghide  = data(41)
  val hendhide  = data(42)
  val hendscan  = data(43)
  val hbegtrans = data(44)
  val hendtrans = data(45)
  val ficparam  = data(46)          // Parameter Einstellung
  val modele    = data(47)          // PRG Modell
  val nbpiecini = data(48).toInt    // Teile gesamt
  val surfini   = data(49).toFloat  // Flaeche gesamt
  var relevant  = true              // TrvRow relevant fuer Verbrauchsberechnung (intern)
  var hautrelevant = false          // TrvRow relevant fuer Haut (Rest in der letzten row)
}