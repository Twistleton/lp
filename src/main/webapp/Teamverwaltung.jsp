<%--
  Created by IntelliJ IDEA.
  User: U987
  Date: 07.04.11
  Time: 13:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Teamverwaltung</title></head>
<body bgcolor="#add8e6">
<h1 align="center">Teamverwaltung</h1>
<table border="2" align="center">
         <tr>
          <th>Id</th>
          <th>Personalnummer</th>
          <th>Vorname</th>
          <th>Nachname</th>
          <th>Aktion</th>
        </tr>
    <c:forEach items="${cutterteamList}" var="item" varStatus="stat">
        <form action="Teamverwaltung.do" method="POST">
            <tr>
                <td>${item.id}</td>
                <c:if test="${not stat.last}">
                   <td>${item.personalnummer}</td>
                </c:if>
                <c:if test="${stat.last}">
                   <td><input type="text" name="pnr" value="${item.personalnummer}" maxlength="4"/></td>
                </c:if>
                <td>${item.vorname}</td>
                <td>${item.nachname}</td>
                <c:if test="${not stat.last}">
                    <td><input type="submit" name="delete" value="Löschen"></td>
                </c:if>
                <c:if test="${stat.last}">
                   <td><input type="submit" name="insert" value="Einfügen"></td>
                </c:if>
                <input type="hidden" name="id" value="1">
                <input type="hidden" name="cutterteammitgliedid" value="${item.id}">
                <input type="hidden" name="personalnummer" value="${item.personalnummer}">
            </tr>
        </form>
    </c:forEach>
</table>
<p align="center">${requestScope.message}</p>
<p align="center"><a href="javascript:window.close()"><img border="0" src="window-close02.gif" width="115" height="21"></a></p>
</body>
<%--<input type="submit" name="insert" value="Einfügen">--%>
</html>