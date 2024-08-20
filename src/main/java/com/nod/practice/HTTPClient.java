package com.nod.practice;

import com.fasterxml.jackson.core.JsonParser;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.util.Timeout;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HTTPClient {
    public static void main(String[] args) {
        // Create an instance of HttpClient
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // Create Configuration with timeouts for Connecting and Response
            RequestConfig config = RequestConfig.custom().setConnectTimeout(Timeout.ofSeconds(5)).setResponseTimeout(Timeout.ofSeconds(5)).build();


            // Create a GET request to a specific URL
            HttpGet request = new HttpGet("https://jsonplaceholder.typicode.com/posts/5");

            //Configure request with configuration made before
            request.setConfig(config);

            // Instantiate object for serialisation
            ObjectMapper objectMapper = new ObjectMapper();

            // Execute the request
            try (CloseableHttpResponse response = httpClient.execute(request)) {

                // Get the response status
                System.out.println(response.getCode());

                // Deserialize request entity as Post Object
                Post post = objectMapper.readValue(EntityUtils.toString(response.getEntity()), Post.class);

                // Check if Deserialization is successful - calling Post toString Method
                System.out.println(post);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
