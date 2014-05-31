package cat.udl.eps.softarch.webglossary.model;

import org.junit.Test;

/**
 * Created by carlos on 5/29/14.
 */
public class ItineraryTest {
    @Test
    public void testAddItinerary() throws Exception {
        String road = "AP-10";
        double start =  10.0;
        double end =  40.0;
        boolean enabled = false;
        String email = "test@example.com";

        Itinerary.addItinerary(new Itinerary(email,road,start,end,enabled));
    }
}
