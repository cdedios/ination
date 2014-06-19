package cat.udl.eps.softarch.webglossary.model;

import cat.udl.eps.softarch.webglossary.persistence.EMF;
import com.google.appengine.api.datastore.Key;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import javax.jdo.PersistenceManager;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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

    private double id;
    private String owner;
    private String road;
    private double start;
    private double end;
    private boolean enabled;

    protected Itinerary() {}

    public Itinerary( String owner, String road, double start, double end, boolean enabled) {
        this.id = Math.random();
        this.owner = owner;
        this.road = road;
        this.start = start;
        this.end = end;
        this.enabled = enabled;
    }

    public String getOwner() {
        return owner;
    }

    public Key getKey(){return key;}

    public double getId(){ return this.id; }

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

    public static ArrayList<Itinerary> getStoredItinerariesByOwner(String id) {
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
    public static Itinerary getItinerary(long id) {
        Itinerary it = new Itinerary();
        EntityManager em = EMF.get().createEntityManager();
        try {
            Query q = em.createQuery("SELECT t FROM Itinerary t "+
                    "WHERE (t.key.getKey() LIKE '" +id+"%')");
            it = (Itinerary) q.getSingleResult();

        } finally {
            em.close();
        }
        return it;
    }

    public static ArrayList<Itinerary>  getAllItineraries() {
        ArrayList<Itinerary> itineraries = new ArrayList<Itinerary>();
        EntityManager em = EMF.get().createEntityManager();
        try {
            Query q = em.createQuery("SELECT t FROM Itinerary t");
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
        System.out.println("Intentant remove itinerary 1");
        /*EntityManager em = EMF.get().createEntityManager();
        EntityTransaction txn = em.getTransaction();
        System.out.println(itinerary.getOwner());
        System.out.println(itinerary.getRoad());
        try {
            txn.begin();
            em.remove(itinerary);
            txn.commit(); }
        catch (Exception e) {
            //log.warning(e.getMessage());
        }
        finally {
            if (txn.isActive()) txn.rollback();
            em.close(); }*/
        EntityManager em = EMF.get().createEntityManager();
        try {
            //Query q = em.createQuery("SELECT t FROM Itinerary t");
            Query q = em.createQuery("DELETE FROM Itinerary WHERE id = "+itinerary.getId());
            q.executeUpdate();

        } finally {
            em.close();
        }
    }

    public static void changeEnabled(Itinerary itinerary){
        /*EntityManager em = EMF.get().createEntityManager();
        EntityTransaction txn = em.getTransaction();
        try {
            txn.begin();
            itinerary.setEnabled(!itinerary.isEnabled());
            //em.refresh(itinerary);
            -->em.persist(itinerary);
            txn.commit(); }
        catch (Exception e) {
            //log.warning(e.getMessage());
        }
        finally {
            if (txn.isActive()) txn.rollback();
            //System.out.println("Update fine");
            em.close(); }*/

        /*EntityManager em = EMF.get().createEntityManager();
        try {
            Query q = em.createQuery("UPDATE Itinerary SET enabled="+!itinerary.isEnabled()+" WHERE id = "+itinerary.getId());
            q.executeUpdate();

        } finally {
            em.close();
        }*/
        removeItinerary(itinerary);
        itinerary.setEnabled(!itinerary.isEnabled());
        addItinerary(itinerary);

    }
    @Override
    public boolean equals(Object other)
    {
        System.out.println("He entrat al equals de Itinerary");
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Itinerary))return false;
        Itinerary otherMyClass = (Itinerary) other;
        return otherMyClass.getId() == this.getId();
    }
}
