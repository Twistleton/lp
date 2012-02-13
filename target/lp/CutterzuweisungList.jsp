<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <style type="text/css">
        body  {margin-left:40px; margin-right:40px; margin-top:20px; margin-bottom:100px; }
        table {margin-left:15px; border-collapse:collapse; width:100%; }
        table, th, td { border:solid 1px black; }
        .oddRow { background-color:#90ee90; }
        .evenRow { background-color:#f0ffff; }
    </style>
    <script type="text/javascript">
    function FensterCutterrowOeffnen(Adresse) {

        MeinCutterrowFenster = window.open(Adresse, "Cutterrow", "scrollbars=yes, width=1200, height=500, left=200, top=100");
        setTimeout("MeinCutterrowFenster.focus()", 300);
        MeinCutterrowFenster.focus();
    }
    function FensterCutterteammitgliedOeffnen(Adresse) {

        MeinCutterteammitgliedFenster = window.open(Adresse, "Cuttermitglied", "scrollbars=yes, width=1200, height=500, left=210, top=110");
        setTimeout("MeinCutterteammitgliedFenster.focus()",300);
    }
    function FensterCutterpraemieOeffnen(Adresse) {

        MeinCutterpraemieFenster = window.open(Adresse, "Cutterpraemie", "scrollbars=yes, width=1200, height=500, left=220, top=120");
        setTimeout("MeinCutterpraemieFenster.focus()",300);
    }
    function FensterCutterhautOeffnen(Adresse) {

        MeinCutterhautFenster = window.open(Adresse, "Cutterhaut", "scrollbars=yes, width=1200, height=500, left=230, top=130");
        setTimeout("MeinCutterhautFenster.focus();",300);
    }
</script>
</head>
<title>Cutterzuweisungsübersicht</title>
<body>
    <img src="green_cutterzuweisung.jpg" width="100%" height="150">
    <p align="left">
        <a href="http://rbsv0007.huels-group.net:8080/rb/zusbemeldung_5/cutSelbstZuw.jsp">Cutterselbstzuweisung</a>
    </p>
    <p align="right">
        Angemeldet als Meister: ${requestScope.meister}
    </p>
    <table>
        <tr>
            <td>Id</td>
            <td>SAI-Name</td>
            <td>Erstelldatum</td>
            <td>Änderungsdatum</td>
            <td>sumSurfmes</td>
            <td>sumSurfsup</td>
            <td>sumSurfreste</td>
            <td>sumAbschreibung</td>
            <td>verbrauchMes</td>
            <td>verbrauchSup</td>
            <td>Status</td>
            <td>Grund</td>
            <td>Kriterium</td>
            <td>Info</td>
            <td>Rows</td>
            <td>Team</td>
            <td>Prämie</td>
            <td>Haut</td>
        </tr>
    <c:forEach items="${cutterzuweisungList}" var="item" varStatus="stat">
        <tr class="${(stat.index % 2) == 0 ? "evenRow" : "oddRow"}">
          <c:choose>
            <c:when test="${requestScope.meister =='1'}">
               <td><a href="CutterbuchungMeister.do?id=${item.id}">${item.id}</a></td>
            </c:when>
            <c:otherwise>
               <td><a href="Cutterbuchung.do?id=${item.id}">${item.id}</a></td>
            </c:otherwise>
          </c:choose>
          <td>${item.sainame}</td>
          <td><fmt:formatDate   value="${item.erstelldatum}" pattern="dd.MM.yyyy HH:mm:ss" /></td>
          <td align="left"><c:out value="${fn:substring(item.aenderungsdatum,0,19)}"/></td>
          <td align="right"><fmt:formatNumber value="${item.sumSurfmes}"   groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" /></td>
          <td align="right"><fmt:formatNumber value="${item.sumSurfsup}"   groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" /></td>
          <td align="right"><fmt:formatNumber value="${item.sumSurfreste}" groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" /></td>
          <td align="right"><fmt:formatNumber value="${item.sumAbschreibung}" groupingUsed="false" minFractionDigits="1" maxFractionDigits="1"/></td>
          <td align="right"><fmt:formatNumber value="${item.verbrauchMes}" groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" /></td>
          <td align="right"><fmt:formatNumber value="${item.verbrauchSup}" groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" /></td>
          <td align="center">${item.status}</td>
          <td>${item.grund}</td>
          <td>${item.kriterium}</td>
          <td>${itme.info}</td>
          <td><a href="showCutterrow.do?id=${item.id}" onclick="FensterCutterrowOeffnen(this.href); return false">*</a></td>
          <td><a href="showCutterteammitglied.do?id=${item.id}" onclick="FensterCutterteammitgliedOeffnen(this.href); return false">*</a></td>
          <td><a href="showCutterpraemie.do?id=${item.id}" onclick="FensterCutterpraemieOeffnen(this.href); return false">*</a></td>
          <td><a href="showCutterhaut.do?id=${item.id}" onclick="FensterCutterhautOeffnen(this.href); return false">*</a></td>
        <tr>
    </c:forEach>
</table>
</body>
</html>
