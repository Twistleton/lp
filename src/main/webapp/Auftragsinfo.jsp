<div class="area"><span class="areatitle">Auftragsinfo</span>
<div style="overflow:auto;height:222;">
    <table border="0">
        <tr>
            <td>Sammelauftragsnummer</td>
            <td>Auftragsnummer</td>
            <td>SAI-Nummer</td>
            <td>Sollmenge</td>
            <td>Istmenge</td>
        </tr>
    <c:forEach items="${maske.cutterzuweisung.cutterauftragList}" var="item">
        <tr>
            <td>${item.sammelauftragsnummer}</td>
            <td>${item.auftragsnummer}</td>
            <td>${item.sainame}</td>
            <td>${item.sollmenge}</td>
            <td>${item.istmenge}</td>
        </tr>
    </c:forEach>
</table>
</div>
</div>
