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
