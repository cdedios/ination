package cat.udl.eps.softarch.webglossary.model;

import com.google.appengine.api.datastore.Key;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by Carlos on 14/05/2014.
 */
public class Itinerary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key key;
    private User owner;
    private int start;
    private int end;
    private boolean enabled;

    protected Itinerary() {}

    public Itinerary(User owner, String description) {
        this.term = term;
        this.description = description;
    }
}
