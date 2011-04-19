<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<div class="area"><span class="areatitle">Hautinfo</span>
<c:if test="${not empty errorlist}" >
    <span style="color:red;font-size:20px;font-weight:bold;font-family:monospace;">
        <c:forEach items="${errorlist}" var="error">
            ${error.message}<br>
        </c:forEach>
    </span>
</c:if>
<div style="overflow:auto;height:222;">
    <table border="0">
        <tr>
            <td>Hautnummer</td>
            <td>Liefermass</td>
            <td>Masch.-maß</td>
            <td>Abschreibung</td>
            <td>Abweichung</td>
        </tr>
    <c:forEach items="${maske.cutterzuweisung.cutterhautList}" var="item">
        <c:set var="key1" value="lieferantenmass.${item.hautnummer}" />
        <c:set var="key2" value="abschreibung.${item.hautnummer}" />
        <tr>
            <td>${item.hautnummer}</td>
            <td><input type="text" name="${key1}" style="${empty errorset[key1]?'':'background-color:red'}" value="${item.lieferantenmass}"/></td>
            <td>${item.maschinen_original}</td>
            <td><input type="text" name="${key2}" style="${empty errorset[key2]?'':'background-color:red'}" value="${item.abschreibung}"/></td>
            <td>${item.abweichung}</td>
        </tr>
    </c:forEach>
</table>
</div>
</div>