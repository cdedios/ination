package cat.udl.eps.softarch.webglossary.servlets;


import cat.udl.eps.softarch.webglossary.model.Itinerary;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by albertbergespeiro on 26/05/14.
 */
public class ItinerariesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = UserServiceFactory.getUserService();
        User currentUser = userService.getCurrentUser();

        if (currentUser != null) {
            ArrayList<Itinerary> itineraries = Itinerary.getStoredItinerariesByOwner(currentUser.getEmail());

                if(!itineraries.isEmpty())
                    request.setAttribute("itineraries", itineraries);


                request.getRequestDispatcher("itinerary.jsp").forward(request, response);

        } else {
            response.sendRedirect(userService.createLoginURL(request.getRequestURI()));
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = UserServiceFactory.getUserService();
        User currentUser = userService.getCurrentUser();
        if (currentUser != null) {
            String userPath = request.getServletPath();

            ArrayList<Itinerary> itineraries = Itinerary.getStoredItinerariesByOwner(currentUser.getEmail());
            String action = request.getParameter("action");

            if(action.equals("update") || action.equals("delete")){
                float idIt = Float.parseFloat(request.getParameter("idIt"));
                Itinerary clicked = null;
                System.out.println("Rebem el id"+idIt);
                for(Itinerary it: itineraries){
                    if(it.getKey().getId()==idIt){
                        System.out.println("entrem i e trobem");
                        clicked = it;
                    }
                }
                if (action.equals("update")){
                    Itinerary.changeEnabled(clicked);
                    System.out.println("Update del "+clicked.getKey().getId());
                }
                if (action.equals("delete")){
                    Itinerary.removeItinerary(clicked);
                    System.out.println("Delete del "+clicked.getKey().getId());
                }

            }else{

                String road = request.getParameter("road");
                double start =  Double.parseDouble(request.getParameter("start"));
                double end =  Double.parseDouble(request.getParameter("end"));
                boolean enabled = Boolean.parseBoolean(request.getParameter("enabled"));

                Itinerary.addItinerary(new Itinerary(currentUser.getEmail(),road,start,end,enabled));
            }
            response.sendRedirect("/itineraries");
        } else {
            response.sendRedirect(userService.createLoginURL(request.getRequestURI()));
        }
    }

}
