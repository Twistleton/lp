<%--
  Created by IntelliJ IDEA.
  User: U987
  Date: 07.04.11
  Time: 13:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Teamverwaltung</title></head>
<body>Teamverwaltung</body>

<table border="2">
    <c:forEach items="${cutterteamList}" var="item">
        <form action="Teamverwaltung.do" method="POST">
            <tr>
                <td>${item.personalnummer}</td>
                <td>${item.vorname}</td>
                <td>${item.nachname}</td>
                <input type="hidden" name="id" value="1">
                <input type="hidden" name="personalnummer" value="${item.personalnummer}">
                <td><input type="submit" name="delete" value="Löschen"></td>
            </tr>
        </form>
    </c:forEach>
</table>
<input type="submit" name="save" value="Speichern">
<input type="submit" name="cancel" value="abbrechen">
</html>