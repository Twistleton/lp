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
<title>Anzeigen Cutterhäute</title>
<body>
    <table>
        <tr>
            <td>Id</td>
            <td>sainame</td>
            <td>hide</td>
            <td>surfmes</td>
            <td>surfmes_original</td>
            <td>surfsup</td>
            <td>surfsup_original</td>
            <td>verbrauch</td>
            <td>verbrauch_original</td>
        </tr>
    <c:forEach items="${cutterhautSet}" var="item">
        <tr>
          <td>${item.id}</td>
          <td>${item.sainame}</td>
          <td>${item.hide}</td>
          <td>${item.surfmes}</td>
          <td>${item.surfmes_original}</td>
          <td>${item.surfsup}</td>
          <td>${item.surfsup_original}</td>
          <td>${item.verbrauch}</td>
          <td>${item.verbrauch_original}</td>
        <tr>
    </c:forEach>
</table>
</body>
</html>
