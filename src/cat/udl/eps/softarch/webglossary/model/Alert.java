package cat.udl.eps.softarch.webglossary.model;

import cat.udl.eps.softarch.webglossary.persistence.EMF;
import com.google.appengine.api.datastore.Key;

import javax.persistence.*;
import java.util.ArrayList;


/**
 * Created by carlos on 5/8/14.
 **/

@Entity
public class Alert{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Key key;

    private int id;
    private String road;
    private double start;
    private double end;
    private String cause;
    private String towards;
    private String date;
    private String direction;
    private String description;
    private String description_type;
    private boolean isNew = true;


    public Alert(){ };

    public Alert(int id, String road, double start, double end, String cause, String towards, String date,
                 String direction, String description, String description_type) {
        this.id = id;
        this.road = road;
        this.start = start;
        this.end = end;
        this.cause = cause;
        this.towards = towards;
        this.date = date;
        this.direction = direction;
        this.description = description;
        this.description_type = description_type;
    }

    public void send(){

    }

    public boolean isValid(){
        return true;
    }

    public static void addAlert(Alert alert) {
        EntityManager em = EMF.get().createEntityManager();
        EntityTransaction txn = em.getTransaction();
        try {
            txn.begin();
            em.persist(alert);
            txn.commit(); }
        catch (Exception e) {
            //log.warning(e.getMessage());
            }
        finally {
            if (txn.isActive()) txn.rollback();
            em.close(); }
    }

    public static void removeAlert(Alert alert) {
        EntityManager em = EMF.get().createEntityManager();
        EntityTransaction txn = em.getTransaction();
        try {
            txn.begin();
            em.remove(alert);
            txn.commit(); }
        catch (Exception e) {
            //log.warning(e.getMessage());
        }
        finally {
            if (txn.isActive()) txn.rollback();
            em.close(); }
    }

    public static void updateAlert(Alert alert) {
        EntityManager em = EMF.get().createEntityManager();
        EntityTransaction txn = em.getTransaction();
        try {
            txn.begin();
            em.refresh(alert);
            txn.commit(); }
        catch (Exception e) {
            //log.warning(e.getMessage());
        }
        finally {
            if (txn.isActive()) txn.rollback();
            em.close(); }
    }

    public static ArrayList<Alert> getStoredAlerts() {
        ArrayList<Alert> alerts = new ArrayList<Alert>();
        EntityManager em = EMF.get().createEntityManager();
        try {
            Query q = em.createQuery("SELECT t FROM Alert t");
            alerts = new ArrayList<Alert>(q.getResultList());
        } finally {
            em.close();
        }
        return alerts;
    }

    public boolean isNew(){return this.isNew;}

    public void notNew(){this.isNew=false;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public double getStart() {
        return start;
    }

    public void setStart(double start) {
        this.start = start;
    }

    public double getEnd() {
        return end;
    }

    public void setEnd(double end) {
        this.end = end;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getTowards() {
        return towards;
    }

    public void setTowards(String towards) {
        this.towards = towards;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription_type() {
        return description_type;
    }

    public void setDescription_type(String description_type) {
        this.description_type = description_type;
    }

}
