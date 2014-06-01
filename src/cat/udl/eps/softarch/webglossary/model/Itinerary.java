package cat.udl.eps.softarch.webglossary.model;

import cat.udl.eps.softarch.webglossary.persistence.EMF;
import com.google.appengine.api.datastore.Key;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by Carlos on 14/05/2014.
 */

@Entity
public class Itinerary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key key;

    private String owner;
    private String road;
    private double start;
    private double end;
    private boolean enabled;

    protected Itinerary() {}

    public Itinerary(String owner, String road, double start, double end, boolean enabled) {
        this.owner = owner;
        this.road = road;
        this.start = start;
        this.end = end;
        this.enabled = enabled;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public static ArrayList<Itinerary> getStoredItineraries(String id) {
        ArrayList<Itinerary> itineraries = new ArrayList<Itinerary>();
        EntityManager em = EMF.get().createEntityManager();
        try {
            Query q = em.createQuery("SELECT t FROM Itinerary t "+
                    "WHERE (t.owner LIKE '" +id+"%')");
            /*Query q = em.createQuery("SELECT t FROM Itinerary t "+
                    "WHERE t.owner ='"+id+"'");*/
            itineraries = new ArrayList<Itinerary>(q.getResultList());
        } finally {
            em.close();
        }
        return itineraries;
    }

    public static void addItinerary(Itinerary itinerary) {
        EntityManager em = EMF.get().createEntityManager();
        EntityTransaction txn = em.getTransaction();
        System.out.println(itinerary.getOwner());
        System.out.println(itinerary.getRoad());
        try {
            txn.begin();
            em.persist(itinerary);
            txn.commit(); }
        catch (Exception e) {
            //log.warning(e.getMessage());
        }
        finally {
            if (txn.isActive()) txn.rollback();
            em.close(); }
    }

    public static void removeItinerary(Itinerary itinerary) {
        EntityManager em = EMF.get().createEntityManager();
        EntityTransaction txn = em.getTransaction();
        try {
            txn.begin();
            em.remove(itinerary);
            txn.commit(); }
        catch (Exception e) {
            //log.warning(e.getMessage());
        }
        finally {
            if (txn.isActive()) txn.rollback();
            em.close(); }
    }
}
