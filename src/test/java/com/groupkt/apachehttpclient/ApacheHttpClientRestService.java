package com.groupkt.apachehttpclient;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStreamReader;
import java.util.ArrayList;

public class ApacheHttpClientRestService {

    private static final String URL = "http://services.groupkt.com/state/get/USA/%s";

    public ArrayList<Object> sendApacheGetRequest(String stateCode) throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpPost = new HttpGet(String.format(URL, stateCode));
        CloseableHttpResponse response = client.execute(httpPost);

        ArrayList<Object> resultArray = new ArrayList<>();
        Integer statusCode = response.getStatusLine().getStatusCode();
        resultArray.add(0, statusCode);


        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(new InputStreamReader(response.getEntity().getContent()));
        JsonNode restResponse = root.get("RestResponse");
        JsonNode messages = restResponse.get("messages");
        resultArray.add(1, messages.toString());

        if (statusCode == 200) {
            JsonNode result = restResponse.get("result");
            resultArray.add(2, result);
        }

        client.close();
        return resultArray;
    }

}
