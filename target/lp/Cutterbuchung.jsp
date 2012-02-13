<%--
  User: Speer, Rolf
  Date: 28.03.2011
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style type="text/css">

    .title {
       text-align:center;
       font-family:monospace;
    }
    .hintergrund {
        /*background-color: #57E964;*/
        background-color: #1CDB2C;
    }
    .area {
        color:black;
        border: 1px solid;
        padding: 10px;
        font-family: monospace;
    }

    .areaheadline {
        text-align:center;
        font-size: 11;
        border-left-width: 1px;
        border-left-style: solid;
        border-top-width: 1px;
        border-top-style: solid;
        border-bottom-width: 3px;
        border-bottom-style: solid;
        border-right-width: 3px;
        border-right-style: solid;
        border-color: red;
    }

    .areatitle {
        font-family:monospace;
        color:#fffafa;
        background-color:black;
        display: inline-block;
        position: relative;
        top: -20px;
        bottom: -20px;
    }
    .summen {
        font-family:monospace;
        color: black;
        font-size: 28;
        font-weight : bold;
        padding: 10px;

    }

</style>
<script type="text/javascript">
    function FensterOeffnen(Adresse) {

        MeinFenster = window.open(Adresse, "Teamverwaltung", "scrollbars=no, width=700, height=500, left=400, top=300");
        MeinFenster.focus();
    }

</script>


<html>
<head><title>Cutterbuchung</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
</head>
<body>

<h1 class="title">Cutterbuchung #${sessionScope.id} für Team ${team} - Status ${cutterzuweisungsstatus}</h1>
<div class="hintergrund">
    <table border="1" width="100%" height="100px">
        <tr>
            <td width="86%" rowspan="2">
                <%@ include file="Auftragsinfo.jsp" %>
            </td>
            <td>
                <%@ include file="Artikelinfo.jsp" %>
            </td>
        </tr>
        <tr>
            <td>
                <%@ include file="Teaminfo.jsp" %>
            </td>
        </tr>
        <tr>
            <form action="Cutterbuchung.do" method="POST">
            <td align="left">
               <%@ include file="Hautinfo.jsp" %>
               <p align="center">
               <input type="submit" name="berechnen" value="Neuberechnung">
               <input type="submit" name="pruefen" value="Weiterleitung an Meister">
               <input type="submit" name="freigeben" value="Buchung und Freigabe">
               <input type="submit" name="refresh" value="erneut laden">
               <%--<a href="/rb/zusbemeldung_5/cutSelbstZuw.jsp">Cutterselbstzuweisung</a>--%>
               <a href="http://rbsv0007.huels-group.net:8080/rb/zusbemeldung_5/cutSelbstZuw.jsp">Zuweisung</a>
               <a href="/lp/CutterzuweisungList.do?team=${team}&bisstatus=10">Übersicht</a>
               &nbsp;&nbsp;Kontrollbeleg  an &nbsp;
               <select name="drucker">
                    <option value="0">Standarddrucker</option>
                    <option ${sessionScope.drucker == 1 ? 'selected' : '' } value="1">rbcutter01</option>
                    <option ${sessionScope.drucker == 2 ? 'selected' : '' } value="2">rbcutter02</option>
                    <option ${sessionScope.drucker == 3 ? 'selected' : '' } value="3">rbit01</option>
                </select>&nbsp;senden<br><br>
            </td>
            <td style="vertical-align:top">
                <div class="area" style="height:222"><span class="areatitle">Prüfungsgrund</span>
                <select name="grund">
                    <option value="0">-</option>
                    <option ${maske.cutterzuweisung.grund == 1 ? 'selected' : '' } value="1">aus der Prämie (AdP)</option>
                    <option ${maske.cutterzuweisung.grund == 2 ? 'selected' : '' } value="2">Tk</option>
                    <option ${maske.cutterzuweisung.grund == 3 ? 'selected' : '' } value="3">Cutterstörung</option>
                    <option ${maske.cutterzuweisung.grund == 4 ? 'selected' : '' } value="4">Störung allgemein</option>
                    <option ${maske.cutterzuweisung.grund == 5 ? 'selected' : '' } value="5">Rest aus Recut</option>
                </select>
                </div>
                </form>
            </td>
        </tr>
        <tr>
            <td>
                <%@ include file="Summeninfo.jsp" %>
            </td>
            <td>
            </td>
        </tr>
    </table>
   </div>
</body>
</html>