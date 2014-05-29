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
    <title>Itineraries</title>
</head>

<h1>Add itinerary</h1>
<form action="/" method="post">
    <div>Road: <input type="text" name="road" value=""/></div>
    <div>Start: <input type="text" name="start" value=""/></div>
    <div>End: <input type="text" name="end" value=""/></div>
    <div>Enabled: <input type="checkbox" name="enabled" value=""/></div>
    <div><input type="submit" value="Add"/></div>
</form>

<br>
<br>
<br>

<h1>Stored itineraries</h1>
<body>
<TABLE BORDER="1">
    <TR>
        <TH>Owner</TH>
        <TH>Road</TH>
        <TH>Start</TH>
        <TH>End</TH>
        <TH>Enabled</TH>
    </TR>
    <%
        ArrayList<Itinerary> itineraries = (ArrayList<Itinerary>) request.getAttribute("itineraries");
        if(itineraries.isEmpty())
        {
            %><h1>Empty list</h1><%
        }else{
            for (Itinerary it: itineraries) {
                String color = "#d3d3d3";
                if (it.isEnabled()) color ="#7fff00";
            %>
                <TR bgcolor= <%=color %>>
                    <TD> <%= it.getOwner() %> </TD>
                    <TD> <%= it.getRoad() %> </TD>
                    <TD> <%= it.getStart() %> </TD>
                    <TD> <%= it.getEnd() %> </TD>
                    <TD> <%= it.isEnabled() %> </TD>
                </TR>
    <%  }
        }%>

</TABLE>
</body>
</html>
