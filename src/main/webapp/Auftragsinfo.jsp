<div class="area"><span class="areatitle">Auftragsinfo</span>
  <span class="areaheadline">&nbsp;&nbsp;&nbsp;Daten werden zur Materialbuchung verwendet; Basis der Istmenge ist daher das Lieferantenma&szlig;&nbsp;&nbsp;&nbsp;</span>
<div style="overflow:auto;height:222;">
    <table border="0">
        <tr>
            <td>Sammelauftragsnummer</td>
            <td>Auftragsnummer</td>
            <td>SAI-Nummer</td>
            <td>Sollmenge</td>
            <td>Istmenge</td>
        </tr>
    <c:forEach items="${maske.cutterzuweisung.cutterpraemieSet}" var="item">
        <tr>
            <td>${item.sammelauftragsnummer}</td>
            <td>${item.auftragsnummer}</td>
            <td>${item.sainame}</td>
            <td align="right"><fmt:formatNumber value="${item.sollmenge}" groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" /></td>
            <td align="right"><fmt:formatNumber value="${item.istmenge_maschinenmass}" groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" /></td>
        </tr>
    </c:forEach>
</table>
</div>
</div>
