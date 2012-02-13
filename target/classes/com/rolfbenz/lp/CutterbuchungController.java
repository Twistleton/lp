package com.rolfbenz.lp;

import com.rolfbenz.lp.entity.*;
import com.rolfbenz.lp.jpa.JpaUtil;
import com.rolfbenz.lp.view.Cutterbuchungsmaske;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 *
 *  Java Version - not in use
 *
 *
 *
 */
public class CutterbuchungController extends HttpServlet {

    private static final String TEAM = "team";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


            System.out.println("CutterbuchungController ");

            String id = request.getParameter("id");

            System.out.println("ID ===> " + id);


        if (request.getParameter("berechnen") != null) {
            // Neuberechnung
//            neuBerechnung(request, response);
        } else if (request.getParameter("pruefen") != null) {

            // methode pruefen
            System.out.println("PRÜFEN");
            System.out.println("Grund ==> " + request.getParameter("grund"));
            String grund = request.getParameter("grund");

            if (grund.equals("0")) {
                System.out.println("KEIN GRUND AUSGEWAEHLT!");
//                sendMessage(request, response, new InputError("Es muss ein Prüfungsgrund ausgewählt werden!", "0"));
            } else                 {
                // Setzen Status

            }

        } else if (request.getParameter("freigeben") != null) {
            System.out.println("FREIGEBEN");
            // methode freigeben
        } else if (request.getParameter("refresh") != null) {
            //  loadCutterzuweisung(request, response);
//            sendMessage(request, response, new InputError("... Daten wurden erneut geladen ...", "0"));
        } else {
            // Daten neu laden
            loadCutterzuweisung(request, response);
        }

    }

    private void loadCutterzuweisung(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        System.out.println("loadCutterzuweisung");

//        Cutterteammitglied ct = new Cutterteammitglied();
//
//        em.persist(ct);


//        Cutterzuweisung cu = new Cutterzuweisung();
//
//        cu.setId(2L);
//
//        Cutterartikel ca = new Cutterartikel(38900, "LIN", 310);

//        em.persist(ca);

//        ca.setId(1);
//
//        cu.setCutterartikel(ca);
//
//        em.persist(cu);
//
//        em.find(Cutterzuweisung.class, 1L);

//        tx.commit();
//        em.close();
//
        System.out.println("end loadCutterzuweisung");

        Cutterbuchungsmaske maske = new Cutterbuchungsmaske();

        Cutterzuweisung cutterzuweisung = new Cutterzuweisung();

        maske.setCutterzuweisung(cutterzuweisung);

        System.out.println("CutterbuchungController - processRequest");

        HttpSession session = request.getSession();

        String team = (String) session.getAttribute(TEAM);

        // get Cutterzuweisung

        LinkedList auftragsliste = new LinkedList();

//        for (int i = 0; i < 10; i++) {
//            auftragsliste.add(new Cutterauftrag("012345689", 978845, "1124567", 5.72f, 5.52f));
//        }



        // Artikelinfo

//        Cutterartikel cutterartikel = new Cutterartikel(38900, "LIN", 310);
//
//        cutterzuweisung.setCutterartikel(cutterartikel);

        // Teaminfo


        request.setAttribute("team", new String("9912"));

        LinkedList teammitglieder = new LinkedList();

//        teammitglieder.add(new Cutterteammitglied(1L, 0122, "Hugo","Meyer"));
//        teammitglieder.add(new Cutterteammitglied(2L, 1456, "Oscar", "Müller"));

//        cutterzuweisung.setCutterteammitgliedList(teammitglieder);

        LinkedList hautliste = new LinkedList();

        for (int i = 0; i < 10; i++) {
            hautliste.add(new Cutterhaut(i, i, 54f, 5.32f, 0f, -90f, 0f));
        }


//        cutterzuweisung.setCutterhautList(hautliste);
//        cutterzuweisung.setCutterauftragList(auftragsliste);

        request.setAttribute("teammitglieder", teammitglieder);

        session.setAttribute("maske", maske);

        RequestDispatcher view = request.getRequestDispatcher("Cutterbuchung.jsp");

        view.forward(request, response);


    }

    private void neuBerechnung(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("NEUBERECHNUNG");

        Cutterbuchungsmaske maske = (Cutterbuchungsmaske) request.getSession().getAttribute("maske");

        List<InputError> errorlist = new ArrayList<InputError>();
        Map<String, Boolean> errorset = new HashMap<String, Boolean>();

        // :
        // TODO
        //
        // Eingabeprüfung
        //
        //  wenn okay - lesen Cutterzuweisung
        //              set-Methode aufrufen
        //              save
        //  sonst       Fehler -> Maske


        for (Cutterhaut haut : maske.getCutterzuweisung().getCutterhautList()) {


            String key = "abschreibung." + Integer.toString(haut.getHautnummer());

            Float abschreibung = checkFloat(request.getParameter(key));

            if (abschreibung == null) {
                errorlist.add(new InputError("Fehlerhafte Eingabe bei " + key, key));
                errorset.put(key, true);
            } else {
                haut.setAbschreibung(abschreibung);
            }

            key = "lieferantenmass." + Integer.toString(haut.getHautnummer());

            Float lieferantenmass = checkFloat(request.getParameter(key));

            if (lieferantenmass == null) {
                errorlist.add(new InputError("Fehlerhafte Eingabe bei " + key, key));
                errorset.put(key, true);
            } else {
                haut.setLieferantenmass(lieferantenmass);
            }

            // weitere Prüfungen


        }

        request.setAttribute("errorlist", errorlist);
        request.setAttribute("errorset", errorset);

        RequestDispatcher view = request.getRequestDispatcher("Cutterbuchung.jsp");
        view.forward(request, response);

    }

    //
    // Send der Cutterzuweisung
    //
    private void sendCutterzuweisung (HttpServletRequest request, HttpServletResponse response, InputError inputError) throws ServletException, IOException {

    }

    //
    // Senden einer Nachricht aus dem Objekt InputError
    //
    private void sendMessage(HttpServletRequest request, HttpServletResponse response, InputError inputError) throws ServletException, IOException {

        List<InputError> errorlist = new ArrayList<InputError>();
        Map<String, Boolean> errorset = new HashMap<String, Boolean>();

        errorlist.add(inputError);

        request.setAttribute("errorlist", errorlist);
        request.setAttribute("errorset", errorset);

        RequestDispatcher view = request.getRequestDispatcher("Cutterbuchung.jsp");
        view.forward(request, response);

    }

    private Float checkFloat(String inputString) {
        try {
            return Float.parseFloat(inputString);

        } catch (Exception e) {

            return null;
        }

        */
    }


 }



