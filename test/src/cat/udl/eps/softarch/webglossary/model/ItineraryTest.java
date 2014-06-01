package cat.udl.eps.softarch.webglossary.model;

import cat.udl.eps.softarch.webglossary.servlets.ItinerariesServlet;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by carlos on 5/29/14.
 */
public class ItineraryTest {
    @Test
    public void testAddItinerary() throws Exception {
        double x=3.0;
        double y= 67.5;
        Itinerary it = new Itinerary("carlos@largos.com","C-24",x,y,true);
        Itinerary.addItinerary(it);
        ArrayList<Itinerary> ites = Itinerary.getStoredItineraries("carlos@largos.com");
        System.out.println(ites.isEmpty());
    }
}
