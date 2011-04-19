<%--
  User: Speer, Rolf
  Date: 28.03.2011
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<style type="text/css">

    .title {
       text-align:center;
       font-family:monospace;
    }
    .hintergrund {
        background-color: #90ee90;
    }
    .area {
        color:black;
        border: 1px solid;
        padding: 10px
    }
    .areatitle {
        color:black;
        background-color:#90ee90;
        display: inline-block;
        position: relative;
        top: -20px;
        bottom: -20px;
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

<h1 class="title">Cutterbuchung</h1>
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
            <td>

                        <%@ include file="Hautinfo.jsp" %>
                        <input type="submit" name="berechnen" value="Neuberechnung">
                        <input type="submit" name="pruefen" value="Zur Prüfung">
                        <input type="submit" name="freigeben" value="Buchung und Freigabe">
                        <input type="submit" name="refresh" value="erneut laden">
            </td>
            <td style="vertical-align:top">
                <div class="area" style="height:222"><span class="areatitle">Prüfungsgrund</span>
                <select name="grund">
                    <option value="0">-</option>
                    <option value="1">aus der Prämie (AdP)</option>
                    <option value="2">Tk</option>
                    <option value="3">Cutterstörung</option>
                    <option value="4">Störung allgemein</option>
                </select>
                </div>
                </form>
            </td>
        </tr>
        <tr>
            <td>
                <%@ include file="Summeninfo.jsp" %>
            </td>
        </tr>
    </table>
   </div>
</body>
</html>