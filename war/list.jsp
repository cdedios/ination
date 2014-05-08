<%@ page import="cat.udl.eps.softarch.webglossary.model.GlossaryEntry" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Web Glossary</title>
</head>
<body>
<%
    ArrayList<GlossaryEntry> terms = (ArrayList<GlossaryEntry>) request.getAttribute("terms");
    for (GlossaryEntry ge: terms) { %>
        <p>Term: <%=ge.getTerm()%> <br/>Description: <%=ge.getDescription()%> </p>
<%  } %>
</body>
</html>