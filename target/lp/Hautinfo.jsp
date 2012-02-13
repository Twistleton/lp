<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<div class="area"><span class="areatitle">Hautinfo</span>
<c:if test="${not empty errorlist}" >
    <span style="color:red;font-size:20px;font-weight:bold;font-family:monospace;">
        <c:forEach items="${errorlist}" var="error">
            ${error.message}
        </c:forEach>
    </span>
</c:if>
    <span style="color:red;font-size:20px;font-weight:bold;font-family:monospace;">
    ${requestScope.message}
    </span>
<div style="overflow:auto;height:222;">
    <table border="0">
        <tr>
            <td>Hautnummer</td>
            <td>Liefermass</td>
            <td>Masch.-maß</td>
            <td>Abschreibung</td>
            <td>Abweichung</td>
            <td>Haut deaktivieren</td>
            <td>Bemerkung</td>
            <td>Grund für das Deaktivieren der Haut</td>
        </tr>
    <c:forEach items="${maske.cutterzuweisung.cutterhautSet}" var="item" varStatus="stat">
        <c:set var="key1" value="surfsup.${item.id}" />
        <c:set var="key2" value="abschreibung.${item.id}" />
        <c:set var="key3" value="haut.${item.id}" />
        <%--<c:set var="aktiv" value="${item.hautaktiv}" />--%>
        <tr>
            <td>${stat.index+1}</td>
            <td><input type="text" name="${key1}" style="${empty errorset[key1]?'':'background-color:red'}" value="${item.surfsup}"/></td>
            <td align="right"><fmt:formatNumber value="${item.surfmes}" minFractionDigits="2" maxFractionDigits="2" /></td>
            <td><input type="text" name="${key2}" style="${empty errorset[key2]?'':'background-color:red'}" value="${item.abschreibung}"/></td>
            <c:choose>
              <c:when test="${item.surfsup > 0}">
                <td align="right"><font style="font-weight:bold" color="${(100*item.surfmes/item.surfsup-100) > 10.00 || (100*item.surfmes/item.surfsup-100) < -10  ? 'red' : 'black'}" ><fmt:formatNumber value="${100*item.surfmes/item.surfsup-100}" minFractionDigits="2"  maxFractionDigits="2" />%</font></td>
               </c:when>
              <c:otherwise>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-.--%</td>
              </c:otherwise>
            </c:choose>
            <td><input type="checkbox" name="${key3}" value="1" ${item.hautaktiv ? 'checked' : '' } /></td>
            <td>${item.hautaktiv ? 'Haut soll entfallen' : 'Haut relevant'}</td>
            <td>
            </td>
        </tr>
    </c:forEach>
</table>
</div>
</div>