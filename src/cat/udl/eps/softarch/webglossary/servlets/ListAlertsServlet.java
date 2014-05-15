package cat.udl.eps.softarch.webglossary.servlets;

import cat.udl.eps.softarch.webglossary.model.Alert;
import cat.udl.eps.softarch.webglossary.model.GlossaryEntry;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Carlos on 15/05/2014.
 */
public class ListAlertsServlet extends HttpServlet {
    @Override
    public void listAlerts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Alert> alerts = Alert.getList();
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("<html><body>");
        for (Alert a: alerts) {
            out.println("<p>Identifier: "+a.getId()+"<br/>Date: "+a.getDate()+"</p>");
        }
        out.println("</body></html>");
    }
}
