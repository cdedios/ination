package cat.udl.eps.softarch.webglossary.servlets;

import cat.udl.eps.softarch.webglossary.model.Alert;
import cat.udl.eps.softarch.webglossary.persistence.EMF;
import cat.udl.eps.softarch.webglossary.utils.XQueryHelper;

import javax.persistence.EntityManager;
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
/*
        EntityManager em = EMF.get().createEntityManager();
        EntityTransaction txn = em.getTransaction();
        try {
            txn.begin();
            em.persist(ge);
            txn.commit(); }
        catch (Exception e) {
            log.warning(e.getMessage()); }
        finally {
            if (txn.isActive()) txn.rollback();
            em.close(); }
            */
        try {
            XQueryHelper helper = new XQueryHelper();
            ArrayList<Alert> newAlerts = helper.getGencatAlerts();
            ArrayList<Alert> oldAlerts = new ArrayList<Alert>();
            oldAlerts = getStoredAlerts();
            updateGencatAlerts(oldAlerts,newAlerts);



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

    private void updateGencatAlerts(ArrayList<Alert> oldAlerts, ArrayList<Alert> newAlerts) {
        for( Alert oldAlert: oldAlerts){
            if(!newAlerts.contains(oldAlert)){ // alerta que ja no es alerta

            }
        }
    }

    public static ArrayList<Alert> getStoredAlerts() {
        ArrayList<Alert> alerts = new ArrayList<Alert>();
        EntityManager em = EMF.get().createEntityManager();
        try {
            Query q = em.createQuery("SELECT * FROM Alert ");
            alerts = new ArrayList<Alert>(q.getResultList());
        } finally {
            em.close();
        }
        return alerts;
    }

}
