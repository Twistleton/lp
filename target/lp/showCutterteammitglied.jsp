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
<title>Anzeigen Cutterteammitglied</title>
<body>
    <table>
        <tr>
            <td>id</td>
            <td>sainame</td>
            <td>team</td>
            <td>vorname</td>
            <td>nachname</td>
            <td>erstelldatum</td>
            <td>aenderungsdatum</td>
            <td>cutterzuweisungs_id</td>
        </tr>
    <c:forEach items="${cutterteammitgliedList}" var="item">
        <tr>
          <td>${item.id}</td>
          <td>${item.sainame}</td>
          <td>${item.team}</td>
          <td>${item.vorname}</td>
          <td>${item.nachname}</td>
          <td>${item.erstelldatum}</td>
          <td>${item.aenderungsdatum}</td>
          <td>${item.cutterzuweisungs_id}</td>
        <tr>
    </c:forEach>
</table>
</body>
</html>
