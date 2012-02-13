<div class="area"><span class="areatitle">Artikel</span>
    <table border="0">
        <c:forEach items="${maske.cutterzuweisung.cutterpraemieSet}" var="element" varStatus="status">
            <tr>
            <c:if test="${status.first}">
                <tr>
                    <td>Material :</td>
                    <td>${element.bezugsmaterial}</td>
                </tr>
                <tr>
                    <td>Lieferant :</td>
                    <td>${element.lief_kkz}</td>
                </tr>
                <tr>
                    <td>Charge :</td>
                    <td>${element.charg_nr}</td>
                </tr>
            </c:if>
            </tr>
        </c:forEach>
    </table>
    <br>
</div>
