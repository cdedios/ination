package cat.udl.eps.softarch.webglossary.servlets;

import cat.udl.eps.softarch.webglossary.model.Alert;
import cat.udl.eps.softarch.webglossary.model.GlossaryEntry;
import cat.udl.eps.softarch.webglossary.utils.XQueryHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.xquery.XQException;
//import javax.servlet.http.
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Carlos on 15/05/2014.
 */
public class ListAlertsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Alert> alerts = Alert.getStoredAlerts();

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("<html><body>");
        for (Alert a: alerts) {
            out.println("<p>Identifier: "+a.getId()+"<br/>Date: "+a.getDate()+"</p>");
        }
        out.println("</body></html>");
    }
}
