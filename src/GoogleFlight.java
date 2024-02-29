import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

public class GoogleFlight {

    public static void main(String[] args) {
        HttpClient httpClient = HttpClients.createDefault();
        String apiKey = "b14dff6246422fc82e355728aa6c19ca75cfc58a899d7b30f2f0967ea3702aeb";
        String apiUrl = "https://www.googleapis.com/qpxExpress/v1/trips/search";

        // Build the API request URL
        String requestUrl = apiUrl + "?key=" + apiKey + "&origin=LAX&destination=JFK&date=2024-03-01";

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
                System.out.println("Response: " + responseBody);
                // Process the response JSON and display flight information
            } else {
                System.err.println("Failed to fetch data from API: " + statusCode);
            }
        } catch (IOException e) {
            System.err.println("Error executing HTTP request: " + e.getMessage());
        }
    }
}