<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<html>
<head>
    <style type="text/css">
        body  {margin-left:40px; margin-right:40px; margin-top:20px; margin-bottom:100px; }
        table {margin-left:15px; border-collapse:collapse; width:100%; }
        table, th, td { border:solid 1px black; }
    </style>
</head>
<title>Anzeigen Cutterprämie</title>
<body>
    <h1>Anzeigen Cutterprämie</h1>
    <table>
        <tr>
            <td>id</td>
            <td>sainame</td>
            <td>sammelauftragsnummer</td>
            <td>auftragsnummer</td>
            <td>bezugsmaterial</td>
            <td>sollmenge</td>
            <td>nettomenge</td>
            <td>istmenge_lieferantenmass</td>
            <td>istmenge_maschinenmass</td>
            <td>datum_weiterleitung</td>
            <td>buchungsmonat</td>
            <td>vorgangsart</td>
            <td>verursacherschluessel</td>
            <td>programm</td>
            <td>kennzeichen_praemie</td>
            <td>lief_kkz</td>
            <td>charg_nr</td>
            <td>zuschnittssystem</td>
            <td>zuschnittswerk</td>
            <td>lederklasse</td>
            <td>status</td>
        </tr>
    <c:forEach items="${cutterpraemieSet}" var="item">
        <tr>
          <td>${item.id}</td>
          <td>${item.sainame}</td>
          <td>${item.sammelauftragsnummer}</td>
          <td>${item.auftragsnummer}</td>
          <td>${item.bezugsmaterial}</td>
          <td>${item.sollmenge}</td>
          <td>${item.nettomenge}</td>
          <td>${item.istmenge_lieferantenmass}</td>
          <td>${item.istmenge_maschinenmass}</td>
          <td>${item.datum_weiterleitung}</td>
          <td>${item.buchungsmonat}</td>
          <td>${item.vorgangsart}</td>
          <td>${item.verursacherschluessel}</td>
          <td>${item.programm}</td>
          <td>${item.kennzeichen_praemie}</td>
          <td>${item.lief_kkz}</td>
          <td>${item.charg_nr}</td>
          <td>${item.zuschnittssystem}</td>
          <td>${item.zuschnittswerk}</td>
          <td>${item.lederklasse}</td>
          <td>${item.status}</td>
        <tr>
    </c:forEach>
</table>
</body>
</html>
