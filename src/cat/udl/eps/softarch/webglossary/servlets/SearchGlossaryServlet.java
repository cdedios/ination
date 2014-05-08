package cat.udl.eps.softarch.webglossary.servlets;

import cat.udl.eps.softarch.webglossary.model.Glossary;
import cat.udl.eps.softarch.webglossary.model.GlossaryEntry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * @author http://rhizomik.net/~roberto/
 */

public class SearchGlossaryServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<GlossaryEntry> entries = Glossary.browseLetter('*');
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("<html><body>");
        for (GlossaryEntry ge: entries) {
            out.println("<p>Term: "+ge.getTerm()+"<br/>Description: "+ge.getDescription()+"</p>");
        }
        out.println("</body></html>");
    }
}
