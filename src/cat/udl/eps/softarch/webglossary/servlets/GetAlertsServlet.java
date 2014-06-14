package cat.udl.eps.softarch.webglossary.servlets;

import cat.udl.eps.softarch.webglossary.model.Alert;
import cat.udl.eps.softarch.webglossary.model.GlossaryEntry;
import cat.udl.eps.softarch.webglossary.model.Itinerary;
import cat.udl.eps.softarch.webglossary.model.User;
import cat.udl.eps.softarch.webglossary.persistence.EMF;
import cat.udl.eps.softarch.webglossary.utils.XQueryHelper;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.xquery.XQException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Properties;

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
        ArrayList<Alert> finalAlerts = new ArrayList<Alert>(oldAlerts);
        oldAlerts = allOld(oldAlerts);
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
                sendAdvice(newAlert);
            }
        }
        return finalAlerts;
    }



    private ArrayList<Alert> allOld(ArrayList<Alert> alerts){
        for (Alert alert: alerts){
            alert.notNew();
        }
        return alerts;
    }

    private void sendAdvice(Alert alert){
        ArrayList<Itinerary> allItineraries = Itinerary.getAllItineraries();
        for(Itinerary iti: allItineraries){
            if(iti.getRoad()==alert.getRoad()){
                sendMail(iti.getOwner(), alert);
            }
        }
    }

    private void sendMail(String email, Alert alert){
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        String msgBody = " Hello "+ email +" \n " +
                "The alert on the road:  "+alert.getRoad()+" has been activated" +
                "the cause its:"+ alert.getDescription()+ " starting at" +alert.getStart()+" and" +
                "finishing at: " +alert.getEnd();


        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("admin@example.com", "Example.com Admin"));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(email, "Hi " + email + " !"));
            msg.setSubject("One of your alerts has been activated!");
            msg.setText(msgBody);
            Transport.send(msg);

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


}
