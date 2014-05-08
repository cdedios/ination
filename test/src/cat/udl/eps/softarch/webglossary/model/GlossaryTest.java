package cat.udl.eps.softarch.webglossary.model;

import cat.udl.eps.softarch.LocalDatastoreTestCase;
import cat.udl.eps.softarch.webglossary.persistence.EMF;
import org.junit.Assert;

import javax.persistence.EntityManager;
import java.util.ArrayList;

/**
 * @author http://rhizomik.net/~roberto/
 */
public class GlossaryTest extends LocalDatastoreTestCase {

    public void testBrowseLetterTermUpperLetterLower() {
        //EntityManager em = EMF.get().createEntityManager();
        GlossaryEntry ge = new GlossaryEntry();
        ge.setTerm("Glossary");
        ge.setDescription("glossary description");

        Glossary.addEntry(ge);

        ArrayList<GlossaryEntry> terms = Glossary.browseLetter('g');
        if (terms.size() > 0)
            Assert.assertEquals(ge.getTerm(), terms.get(0).getTerm());
        else
            fail("No results");
    }

    public void testBrowseLetterTermLowerLetterLower() {
        //EntityManager em = EMF.get().createEntityManager();
        GlossaryEntry ge = new GlossaryEntry();
        ge.setTerm("glossary");
        ge.setDescription("glossary description");

        Glossary.addEntry(ge);

        ArrayList<GlossaryEntry> terms = Glossary.browseLetter('g');
        if (terms.size() > 0)
            Assert.assertEquals(ge.getTerm(), terms.get(0).getTerm());
        else
            fail("No results");
    }

    public void testBrowseLetterTermUpperLetterUpper() {
        //EntityManager em = EMF.get().createEntityManager();
        GlossaryEntry ge = new GlossaryEntry();
        ge.setTerm("Glossary");
        ge.setDescription("glossary description");

        Glossary.addEntry(ge);

        ArrayList<GlossaryEntry> terms = Glossary.browseLetter('G');
        if (terms.size() > 0)
            Assert.assertEquals(ge.getTerm(), terms.get(0).getTerm());
        else
            fail("No results");
    }

    public void testBrowseLetterTermLowerLetterUpper() {
        //EntityManager em = EMF.get().createEntityManager();
        GlossaryEntry ge = new GlossaryEntry();
        ge.setTerm("glossary");
        ge.setDescription("glossary description");

        Glossary.addEntry(ge);

        ArrayList<GlossaryEntry> terms = Glossary.browseLetter('G');
        if (terms.size() > 0)
            Assert.assertEquals(ge.getTerm(), terms.get(0).getTerm());
        else
            fail("No results");
    }
}
