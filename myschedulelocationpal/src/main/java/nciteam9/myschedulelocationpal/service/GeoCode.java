package nciteam9.myschedulelocationpal.service;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class GeoCode {

    private final String apiUrl = "https://maps.googleapis.com/maps/api/geocode/json?";
    private final String apiKey = "AIzaSyA7y6tiN4jCAqErJwRX9snh79AATgU7e8k";
    private final ObjectMapper oMapper = new ObjectMapper();

    // Find the coordinates of a location using the address to store in the database
    public ArrayList<Double> addressToCoords(String address) throws Exception {
        ArrayList<Double> coords = new ArrayList<>();

        String fullGetUrl = apiUrl + "address=" + address + "&key=" + apiKey;

        // Request the location data
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(fullGetUrl))
                .GET()
                .build();
        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());


        // Find the coordinates in the response
        JsonNode rootNode = oMapper.readTree(response.body());
        JsonNode location = rootNode
                .path("results")
                .get(0)
                .path("geometry")
                .path("location");

        double latitude = location.get("lat").asDouble();
        double longitude = location.get("lng").asDouble();

        coords.add(latitude);
        coords.add(longitude);

        return coords;
    }

    // Find the address of a location using the coordinates to send to the frontend
    public String coordsToAddress(double latitude, double longitude) throws Exception {
        String fullAddress;

        String fullGetUrl = apiUrl + "latlng=" + latitude + "," + longitude + "&key=" + apiKey;

        // Request the location data
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(fullGetUrl))
                .GET()
                .build();
        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
        // Find the formatted address in the response
        JsonNode rootNode = oMapper.readTree(response.body());
        JsonNode location = rootNode
                .path("results")
                .get(0);

        fullAddress = location.get("formatted_address").asString();

        return fullAddress;
    }
}
