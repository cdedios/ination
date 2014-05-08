package cat.udl.eps.softarch.webglossary.persistence;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author http://rhizomik.net/~roberto/
 */
public final class EMF {
    private static final EntityManagerFactory emfInstance =
            Persistence.createEntityManagerFactory("transactions-optional");

    private EMF() {}

    public static EntityManagerFactory get() {
        return emfInstance;
    }
}
