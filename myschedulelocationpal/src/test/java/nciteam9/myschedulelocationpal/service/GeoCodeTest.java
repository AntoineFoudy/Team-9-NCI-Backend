package nciteam9.myschedulelocationpal.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertThrows;

class GeoCodeTest {

    private GeoCode geoCode;

    @BeforeEach
    public void setUp() {
        this.geoCode = new GeoCode();
    }

    @Test
    void addressToCoordinatesValidAddress() throws Exception {

        String testAddress = "21+Wolseley+Street,+Dublin,+D08E2C2";
        ArrayList<Double> coordinates = new ArrayList<>();


        double expectedLat = 53.3327941;
        double expectedLng = -6.2804578;

        coordinates.add(geoCode.addressToCoords(testAddress).get(0));
        coordinates.add(geoCode.addressToCoords(testAddress).get(1));

        assertEquals(expectedLat, coordinates.get(0));
        assertEquals(expectedLng, coordinates.get(1));

    }

    @Test
    void addressToCoordinatesInValidAddress() throws Exception {

        String testAddress = "8376763+NotAplace+yeet";


        assertThrows(Exception.class, () -> {
            geoCode.addressToCoords(testAddress);
        });


    }

    @Test
    void coordinatesToAddressValidCoordinates() throws Exception {

        double testLat = 53.3327941;
        double testLng = -6.2804578;



        String expectedResult = "21 Wolseley St, Dublin 8, D08 E2C2, Ireland";

        String result = geoCode.coordsToAddress(testLat, testLng);

        assertEquals(expectedResult, result);


    }

    @Test
    void coordinatesToAddressInValidCoordinates() throws Exception {

        double testLat = 1;
        double testLng = -0.1;

        assertThrows(Exception.class, () -> {
            geoCode.coordsToAddress(testLat, testLng);
        });


    }

}