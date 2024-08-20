package com.nod.practice;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.util.Timeout;

public class HTTPClient {
    public static void main(String[] args) {
        // Create an instance of HttpClient
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // Create Configuration with timeouts for Connecting and Response
            RequestConfig config = RequestConfig.custom().setConnectTimeout(Timeout.ofSeconds(5)).setResponseTimeout(Timeout.ofSeconds(5)).build();


            // Create a GET request to a specific URL
            HttpGet request = new HttpGet("https://jsonplaceholder.typicode.com/posts/4");

            //Configure request with configuration made before
            request.setConfig(config);

            //Create POST request
            HttpPost post = new HttpPost("https://jsonplaceholder.typicode.com/posts");
            post.setHeader("Content-Type", "application/json");

            // Add JSON data to the request
            String json = "{\"title\":\"foo\",\"body\":\"bar\",\"userId\":1}";
            post.setEntity(new StringEntity(json));

            // Execute the request
            try (CloseableHttpResponse response = httpClient.execute(post)) {

                // Get the response status
                System.out.println(response.getCode());

                // Get the response body as a String
                String responseBody = EntityUtils.toString(response.getEntity());
                System.out.println(responseBody);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
