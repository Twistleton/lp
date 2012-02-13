<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Cutterzuweisungsmenü (Stand 07.02.2012)</title>
    <style type="text/css">
        body   {font-family:monospace; margin-left:50px; margin-top:50px; margin-right:50px; }
        h2     {font-family:monospace; }
        h2.red {font-family:monospace; color:red; }
        h3.red {font-family:monospace; color:red; }
        h3     {font-family:monospace; }
        .content {
            color:black;
            background-color:#fffff0;
        }
    </style>
</head>
<body div class="content">
<h1 align="center">Automatische Prämiendatenermittlung - Mitarbeitersicht</h1>
<br>
<p>
<h2 class="red">Cutterzuweisungs-Postfach</h2>
</p>
<p>
 <a href="CutterzuweisungList.do?team=1&bisstatus=10">Cutterzuweisungen für Team 1 mit Status kleiner 20</a><br><br>
 <a href="CutterzuweisungList.do?team=2&bisstatus=10">Cutterzuweisungen für Team 2 mit Status kleiner 20</a><br><br>
 <a href="CutterzuweisungList.do?team=3&bisstatus=10">Cutterzuweisungen für Team 3 mit Status kleiner 20</a><br><br>
 <a href="CutterzuweisungList.do?team=4&bisstatus=10">Cutterzuweisungen für Team 4 mit Status kleiner 20</a><br><br>
</p>
<hr>
<p>
<h3>Cutterzuweisungsübersichten pro Team</h3>
 <a href="CutterzuweisungList.do?team=1">Cutterzuweisungsübersicht für Team 1</a><br>
 <a href="CutterzuweisungList.do?team=2">Cutterzuweisungsübersicht für Team 2</a><br>
 <a href="CutterzuweisungList.do?team=3">Cutterzuweisungsübersicht für Team 3</a><br>
 <a href="CutterzuweisungList.do?team=4">Cutterzuweisungsübersicht für Team 4</a><br>
</p>
<hr>
<p>
<h3>Cutterzuweisungsübersicht gesamt</h3>
 <a href="CutterzuweisungList.do">Cutterzuweisungsübersicht gesamt</a>
</p>
<hr>
<p>
 <h3>Verwaltungsbereich Meister</h3>
    <a href="/lp/meisterbereich/index.jsp">Verwaltung Meister</a><br>
</p>
</body>
</html>