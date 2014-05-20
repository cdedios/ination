package cat.udl.eps.softarch.webglossary.model;

import cat.udl.eps.softarch.webglossary.utils.XQueryHelper;
import org.junit.Before;
import org.junit.Test;

import javax.xml.xquery.XQException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by carlos on 5/20/14.
 */
public class AlertTest {
    XQueryHelper helper;
    Alert alert;
    @Before
    public void setUp(){
        alert = new Alert();
        try {
            helper = new XQueryHelper();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (XQException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testAddAlert() throws Exception {

    }

    @Test
    public void testRemoveAlert() throws Exception {

    }

    @Test
    public void testUpdateAlert() throws Exception {

    }

    @Test
    public void testGetStoredAlerts() throws Exception {
        ArrayList<Alert> alerts = Alert.getStoredAlerts();
        for(Alert a: alerts){
            System.out.println(a.getId());
        }
    }
}
