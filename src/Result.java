import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

// TODO Add initial components for the result page, and API key and display results

public class Result extends javax.swing.JFrame{
    public Result(String username, String email, String pass, String date, String dept, String dest){  // Result constructor takes username, email, password, date, depature, and destination
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
            if (statusCode == 100) {
                // Parse and process the response
                String responseBody = EntityUtils.toString(response.getEntity());
                System.out.println("Response: " + responseBody);
            } else {
                System.err.println("Failed to fetch data from API: " + statusCode);
            }
        } catch (IOException e) {
            System.err.println("Error executing HTTP request: " + e.getMessage());
        }
        initComponents(username, email, pass, date, dept, dest);                  // just to recognize which account is currently active 
    }

    public void  initComponents(String username, String email, String pass, String date, String dept, String dest){}

}
