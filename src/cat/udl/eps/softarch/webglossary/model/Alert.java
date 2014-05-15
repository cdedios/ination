package cat.udl.eps.softarch.webglossary.model;

import cat.udl.eps.softarch.webglossary.persistence.EMF;
import com.google.appengine.api.datastore.Key;

import javax.management.Query;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
//import java.

/**
 * Created by carlos on 5/8/14.
 **/

@Entity
public class Alert{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key key;

    //private Time time;

    private int id;
    private Date date;
    private String type;
    private int start;
    private int end;
    private String direction;
    private String towards;
    private String place;
    private String region;

    public Alert(){ };
    public Alert(int id, String type, int start, int end, String direction, String towards, String place, String region) {
        this.id = id;
        this.type = type;
        this.start = start;
        this.end = end;
        this.direction = direction;
        this.towards = towards;
        this.place = place;
        this.region = region;
    }

    public void send(){

    }

    public boolean isValid(){
        return true;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getStart() {
        return start;
    }
    public void setStart(int start) {
        this.start = start;
    }
    public int getEnd() {
        return end;
    }
    public void setEnd(int end) {
        this.end = end;
    }
    public String getDirection() {
        return direction;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }
    public String getTowards() {
        return towards;
    }
    public void setTowards(String towards) {
        this.towards = towards;
    }
    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }

    public static ArrayList<Alert> getList() {
        ArrayList<Alert> alerts = new ArrayList<Alert>();
        EntityManager em = EMF.get().createEntityManager();
        try {
            Query q = em.createQuery("SELECT * FROM Alert");
            terms = new ArrayList<Alert>(q.getResultList());
        } finally {
            em.close();
        }
        return terms;
    }


}
