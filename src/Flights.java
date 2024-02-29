import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

//TODO: 

public class Flights extends javax.swing.JFrame{
    public static void main(String[] args) {
        HttpClient httpClient = HttpClients.createDefault();
        String accessKey = "ae1422d5c1b3a93106056dcf186842e0";
        String apiUrl = "http://api.aviationstack.com/v1/flights";

        // Build the API request URL
        String requestUrl = apiUrl + "?access_key=" + accessKey;

        // Create an HTTP GET request
        HttpGet request = new HttpGet(requestUrl);

        try {
            // Execute the request
            HttpResponse response = httpClient.execute(request);

            // Check the status code
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // Parse and process the response
                String responseBody = EntityUtils.toString(response.getEntity());
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);

                // Extract data from the JSON object
                JsonArray dataArray = jsonObject.getAsJsonArray("data");
                for (JsonElement flightElement : dataArray) {
                JsonObject flightObject = flightElement.getAsJsonObject();

                // Extract flight details
                String flightDate = flightObject.get("flight_date").getAsString();
                String flightStatus = flightObject.get("flight_status").getAsString();

                // Extract departure information
                JsonObject departureObject = flightObject.getAsJsonObject("departure");
                String departureAirport = departureObject.get("airport").getAsString();
                String departureScheduled = departureObject.get("scheduled").getAsString();

                // Extract arrival information
                JsonObject arrivalObject = flightObject.getAsJsonObject("arrival");
                String arrivalAirport = arrivalObject.get("airport").getAsString();
                String arrivalScheduled = arrivalObject.get("scheduled").getAsString();

                // Print the extracted information
                System.out.println("Flight Date: " + flightDate);
                System.out.println("Flight Status: " + flightStatus);
                System.out.println("Departure Airport: " + departureAirport);
                System.out.println("Departure Scheduled Time: " + departureScheduled);
                System.out.println("Arrival Airport: " + arrivalAirport);
                System.out.println("Arrival Scheduled Time: " + arrivalScheduled);
                System.out.println();
                }
            } else {
                System.err.println("Failed to fetch data from API: " + statusCode);
            }
        } catch (IOException e) {
            System.err.println("Error executing HTTP request: " + e.getMessage());
        }
    }
}
