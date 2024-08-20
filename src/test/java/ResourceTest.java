import com.nod.practice.Post;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;


public class ResourceTest {
    @Test
    void testGetResourceById() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet("https://jsonplaceholder.typicode.com/posts/1");
            CloseableHttpResponse response = httpClient.execute(request);
            // Assert that the response status code is 200 OK
            assertEquals(200, response.getCode());

            // Convert the response body to a String
            String responseBody = EntityUtils.toString(response.getEntity());

            // Assert that the response body contains expected values
            assertTrue(responseBody.contains("\"id\": 1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetResourceNotFound() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet("https://jsonplaceholder.typicode.com/posts/999");
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                assertEquals(404, response.getCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
