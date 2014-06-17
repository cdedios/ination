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


<body>
<h1>Add itinerary</h1>
<form action="/itineraries" method="post">
    <div>Road: <input type="text" name="road" value=""/></div>
    <div>Start: <input type="text" name="start" value=""/></div>
    <div>End: <input type="text" name="end" value=""/></div>
    <div>Enabled: <input type="checkbox" name="enabled" value="true"/></div>
    <div>
        <input type="hidden" name="action" value="add">
        <input type="submit" value="Add"/>
    </div>
</form>

<br>
<br>
<br>
<%
    if(request.getAttribute("itineraries") == null){
        %><h1>No Itineraries saved yet!</h1><%
    }else{
        ArrayList<Itinerary> itineraries = (ArrayList<Itinerary>) request.getAttribute("itineraries");
        %> <h1>Stored itineraries</h1>
        <TABLE BORDER="1">
            <TR>
                <TH>Owner</TH>
                <TH>Road</TH>
                <TH>Start</TH>
                <TH>End</TH>
                <TH>Enabled</TH>
            </TR>
            <%for (Itinerary it: itineraries) {
                //long id = it.getKey().getId();
                String color = "#d3d3d3",
                        button = "Enable",
                        id = String.valueOf(it.getKey().getId()),
                        url1 = "/itineraries/"+id+"/change",
                        url2 = "/itineraries/"+id+"/delete";


                if (it.isEnabled()){
                    color ="#7fff00";
                    button ="Disenable";
                }
            %>
                    <TD> <%= it.getOwner() %> </TD>
                    <TD> <%= it.getRoad() %> </TD>
                    <TD> <%= it.getStart() %> </TD>
                    <TD> <%= it.getEnd() %> </TD>
                    <TD> <%= it.isEnabled() %> </TD>
                    <TD> <form action="/itineraries" method="post">
                        <div>
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="idIt" value="<%=it.getKey().getId()%>"/>
                            <input type="submit" value="Delete"/>
                        </div>
                    </form></TD>
                     <TD> <form action="/itineraries" method="post">
                        <div>
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="idIt" value="<%=it.getKey().getId()%>"/>
                            <input type="submit" value="<%=button%>"/>
                        </div>
                    </form></TD>

                </TR>
        <%}
    }%>

</TABLE>
</body>
</html>