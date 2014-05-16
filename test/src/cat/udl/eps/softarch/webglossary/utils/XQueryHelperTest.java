package cat.udl.eps.softarch.webglossary.utils;

import cat.udl.eps.softarch.webglossary.model.Alert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.xquery.XQException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by carlos on 5/15/14.
 */
public class XQueryHelperTest {
    XQueryHelper tester;

    /*@BeforeClass
    public static void testSetup() {
        tester = new XQueryHelper();
    }*/
    @Test
    public void testXquery() throws ClassNotFoundException, XQException,
            InstantiationException, IOException, IllegalAccessException {
        tester = new XQueryHelper();
        ArrayList<Alert> alerts = tester.getAlerts();
        for (Alert a : alerts ){
            System.out.println(a.getTowards());
        }
    }
}
