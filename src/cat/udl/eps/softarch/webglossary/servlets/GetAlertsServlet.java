package cat.udl.eps.softarch.webglossary.servlets;

import cat.udl.eps.softarch.webglossary.model.Alert;
import cat.udl.eps.softarch.webglossary.model.GlossaryEntry;
import cat.udl.eps.softarch.webglossary.persistence.EMF;
import cat.udl.eps.softarch.webglossary.utils.XQueryHelper;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.xquery.XQException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by carlos on 5/18/14.
 */
public class GetAlertsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            XQueryHelper helper = new XQueryHelper();
            ArrayList<Alert> newAlerts = helper.getGencatAlerts();
            ArrayList<Alert> oldAlerts = Alert.getStoredAlerts();
            ArrayList<Alert> finalAlerts = updateGencatAlerts(oldAlerts,newAlerts);

            request.setAttribute("alerts", finalAlerts);
            request.getRequestDispatcher("list.jsp").forward(request, response);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (XQException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Alert> updateGencatAlerts(ArrayList<Alert> oldAlerts, ArrayList<Alert> newAlerts) {
        ArrayList<Alert> finalAlerts = new ArrayList<Alert>();
        allOld(oldAlerts);
        for( Alert oldAlert: oldAlerts){
            if(!newAlerts.contains(oldAlert)){ // alerta que ja no es alerta
                Alert.removeAlert(oldAlert);
                finalAlerts.remove(oldAlert);
            }
        }
        for(Alert newAlert: newAlerts){
            if(!oldAlerts.contains(newAlert)){// alerta nova
                Alert.addAlert(newAlert);
                finalAlerts.add(newAlert);
            }
        }
        return finalAlerts;
    }

    private void allOld(ArrayList<Alert> alerts){
        for (Alert alert: alerts){
            alert.notNew();
        }
    }


}
