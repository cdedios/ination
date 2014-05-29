<%--
  Created by IntelliJ IDEA.
  User: albertbergespeiro
  Date: 29/05/14
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="cat.udl.eps.softarch.webglossary.model.Itinerary" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Stored Alerts</title>
</head>
<body>
<TABLE BORDER="1">
    <TR>
        <TH>Owner</TH>
        <TH>Start</TH>
        <TH>End</TH>
        <TH>Enabled</TH>
    </TR>
    <%
        ArrayList<Itinerary> itineraries = (ArrayList<Itinerary>) request.getAttribute("itineraries");
        for (Itinerary it: itineraries) {
            String color = "#d3d3d3";
            if (it.isEnabled()) color ="#7fff00";
    %>
    <TR bgcolor= <%=color %>>
        <TD> <%= it.getOwner() %> </TD>
        <TD> <%= it.getStart() %> </TD>
        <TD> <%= it.getEnd() %> </TD>
        <TD> <%= it.isEnabled() %> </TD>
    </TR>
    <%  } %>
</TABLE>
</body>
</html>
