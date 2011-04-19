<div class="area"><span class="areatitle">Teaminfo</span>
    <p>Team: ${team}
    <table border="0">
        <c:forEach items="${maske.cutterzuweisung.cutterteammitgliedList}" var="item">
            <tr>
                <td><c:out value="${item.personalnummer}"/></td>
                <td><c:out value="${item.vorname}"/></td>
                <td><c:out value="${item.nachname}"/></td>
            </tr>
        </c:forEach>
    </table>
    <a href="/Teamverwaltung.do" onclick="FensterOeffnen(this.href); return false">Teamverwaltung</a>
</div>




