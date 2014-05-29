package cat.udl.eps.softarch.webglossary.model;

import com.google.appengine.api.datastore.Key;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by carlos on 5/14/14.
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key key;
    private String username;
    private String password;

    public User(){};

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public boolean authenticate(String pass){
        return true;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Alert))return false;
        User otherMyClass = (User) other;
        return otherMyClass.getUsername() == this.username;
    }
}
