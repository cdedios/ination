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

public class BrowseGlossaryServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String letterParam = request.getParameter("letter");
        char letter = 'a';
        if (letterParam!=null && letterParam.length()>0)
            letter = letterParam.charAt(0);

        ArrayList<GlossaryEntry> terms = Glossary.browseLetter(letter);

        request.setAttribute("terms", terms);
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }
}
