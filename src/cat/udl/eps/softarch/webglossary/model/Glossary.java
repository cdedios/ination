package cat.udl.eps.softarch.webglossary.model;

import cat.udl.eps.softarch.webglossary.persistence.EMF;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * @author http://rhizomik.net/~roberto/
 */
public class Glossary {
    private static final Logger log = Logger.getLogger(Glossary.class.getName());


    public static ArrayList<GlossaryEntry> browseLetter(char letter) {
        ArrayList<GlossaryEntry> terms = new ArrayList<GlossaryEntry>();
        EntityManager em = EMF.get().createEntityManager();
        try {
            Query q = em.createQuery("SELECT t FROM GlossaryEntry t "+
                                     "WHERE (UPPER(t.term) LIKE '"+Character.toUpperCase(letter)+"%')");
            terms = new ArrayList<GlossaryEntry>(q.getResultList());
        } finally {
            em.close();
        }
        return terms;
    }

    public static void addEntry(GlossaryEntry ge) {
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
    }
}
