<div class="area"><span class="areatitle">Summen</span>
    <span class="areaheadline">&nbsp;&nbsp;&nbsp;Daten werden zur Pr&auml;mienermittlung verwendet; Basis der Istmenge ist daher das Maschinenma&szlig;&nbsp;&nbsp;&nbsp;</span>
    <table border="0" class="summen" width="70%" align="center">
        <tr>
            <td>Sollmenge</td>
            <td>Istmenge</td>
            <td>Verschnitt</td>
            <td>Recutsumme</td>
            <td>Reste</td>
        </tr>
        <tr>
            <td><fmt:formatNumber value="${sollmenge}" groupingUsed="false" minFractionDigits="2" maxFractionDigits="2"  /></td>
            <td><fmt:formatNumber value="${istmenge}"  groupingUsed="false" minFractionDigits="2" maxFractionDigits="2"  /></td>
            <c:choose>
              <c:when test="${sollmenge > 0}">
                <td><fmt:formatNumber value="${100/sollmenge*istmenge-100}" minFractionDigits="2"  maxFractionDigits="2" />%</td>
               </c:when>
              <c:otherwise>
                <td>-.--%</td>
              </c:otherwise>
            </c:choose>
            <td><fmt:formatNumber value="${sumRecut}"  groupingUsed="false" minFractionDigits="2" maxFractionDigits="2"  /></td>
            <td><fmt:formatNumber value="${sumReste}"  groupingUsed="false" minFractionDigits="2" maxFractionDigits="2"  /></td>
        </tr>
    </table>
</div>