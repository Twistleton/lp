<div class="area"><span class="areatitle">Teaminfo</span>
    <b>Team: ${team}</b>
    <table border="0">
        <c:forEach items="${maske.cutterteammitgliedList}" var="item">
            <tr>
                <td><c:out value="${item.personalnummer}"/></td>
                <td><c:out value="${item.vorname}"/></td>
                <td><c:out value="${item.nachname}"/></td>
            </tr>
        </c:forEach>
    </table>
    <p align="center">
    <a href="Teamverwaltung.do?id=${maske.cutterteammitgliedList[0].cutterzuweisungs_id}" onclick="FensterOeffnen(this.href); return false">Teamverwaltung</a>
    </p><p>
</div>




