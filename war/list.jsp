<%@ page import="cat.udl.eps.softarch.webglossary.model.Alert" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Stored Alerts</title>
</head>
<body>
<TABLE BORDER="1">
    <TR>
        <TH>ID</TH>
        <TH>Cause</TH>
        <TH>Date</TH>
        <TH>Description</TH>
        <TH>Description type</TH>
        <TH>End</TH>
        <TH>Road</TH>
        <TH>Start</TH>
        <TH>Towards</TH>
        <TH>Direction</TH>
    </TR>
<%
    ArrayList<Alert> alerts = (ArrayList<Alert>) request.getAttribute("alerts");
    for (Alert al: alerts) {
        String color = "#d3d3d3";
        if (al.isNew()) color ="#7fff00";
    %>
            <TR bgcolor= <%=color %>>
                <TD> <%= al.getId() %> </TD>
                <TD> <%= al.getCause() %> </TD>
                <TD> <%= al.getDate() %> </TD>
                <TD> <%= al.getDescription() %> </TD>
                <TD> <%= al.getDescription_type() %> </TD>
                <TD> <%= al.getEnd() %> </TD>
                <TD> <%= al.getRoad() %> </TD>
                <TD> <%= al.getStart() %> </TD>
                <TD> <%= al.getTowards() %> </TD>
                <TD> <%= al.getDirection() %> </TD>
            </TR>
    <%  } %>
</TABLE>
</body>
</html>