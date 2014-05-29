package cat.udl.eps.softarch.webglossary.servlets;

import cat.udl.eps.softarch.webglossary.model.Alert;
import cat.udl.eps.softarch.webglossary.model.Glossary;
import cat.udl.eps.softarch.webglossary.model.GlossaryEntry;
import cat.udl.eps.softarch.webglossary.model.Itinerary;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by albertbergespeiro on 26/05/14.
 */
public class ItinerariesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = UserServiceFactory.getUserService();
        User currentUser = userService.getCurrentUser();

        if (currentUser != null) {
            ArrayList<Itinerary> alerts = Itinerary.getStoredItineraries(currentUser.getEmail());

            request.setAttribute("alerts", alerts);
            request.getRequestDispatcher("itineriaries.jsp").forward(request, response);

        } else {
            response.sendRedirect(userService.createLoginURL(request.getRequestURI()));
        }


        /*request.setAttribute("alerts", alerts);
        request.getRequestDispatcher("list.jsp").forward(request, response);*/

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = UserServiceFactory.getUserService();
        User currentUser = userService.getCurrentUser();
        if (currentUser != null) {
            String road = request.getParameter("road");
            double start =  Double.parseDouble(request.getParameter("start"));
            double end =  Double.parseDouble(request.getParameter("end"));
            boolean enabled = Boolean.parseBoolean(request.getParameter("enabled"));

            Itinerary.addItinerary(new Itinerary(currentUser.getEmail(),start,end,enabled));

            response.setContentType("text/plain");
            response.getWriter().println(currentUser.getNickname()+" added a new entry.");
        } else {
            response.sendRedirect(userService.createLoginURL(request.getRequestURI()));
        }
    }

}
