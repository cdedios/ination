package cat.udl.eps.softarch.webglossary.model;

import com.google.appengine.api.datastore.Key;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * @author http://rhizomik.net/~roberto/
 */

@Entity
public class GlossaryEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key key;
    private String term;
    private String description;

    protected GlossaryEntry() {}

    public GlossaryEntry(String term, String description) {
        this.term = term;
        this.description = description;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
