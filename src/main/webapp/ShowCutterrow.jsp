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
<title>Anzeigen Cutterrows</title>
<body>
    <table>
        <tr>
            <td>brdname</td>
            <td>comment</td>
            <td>Erstelldatum</td>
            <td>hide</td>
            <td>leathertype</td>
            <td>modele</td>
            <td>sainame</td>
            <td>supplier</td>
            <td>surfmes</td>
            <td>surfreste1</td>
            <td>surfreste2</td>
            <td>surfreste3</td>
            <td>relevant</td>
        </tr>
    <c:forEach items="${cutterrowSet}" var="item">
        <tr>
          <td>${item.brdname}</td>
          <td>${item.commentfield}</td>
          <td>${item.erstelldatum}</td>
          <td>${item.hide}</td>
          <td>${item.leathertype}</td>
          <td>${item.modele}</td>
          <td>${item.sainame}</td>
          <td>${item.supplier}</td>
          <td>${item.surfmes}</td>
          <td>${item.surfreste1}</td>
          <td>${item.surfreste2}</td>
          <td>${item.surfreste3}</td>
          <td>${item.relevant}</td>
        <tr>
    </c:forEach>
</table>
</body>
</html>
