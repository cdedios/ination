<%@ page import="cat.udl.eps.softarch.webglossary.model.Alert" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Stored Alerts</title>
</head>
<body>
<%
    ArrayList<Alert> alerts = (ArrayList<Alert>) request.getAttribute("alerts");
    for (Alert ge: alerts) { %>
        <p>Alert: <%=ge.getId()%> <br/>Description: <%=ge.getDescription()%> </p>
<%  } %>
</body>
</html>