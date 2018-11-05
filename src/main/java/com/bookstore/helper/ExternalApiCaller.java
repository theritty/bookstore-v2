package com.bookstore.helper;

import com.bookstore.exceptions.CustomException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class ExternalApiCaller {

    private String apiUrl;
    private String apiKey;
    private String apiPath = "";
    private String apiParams = "";
    RestTemplate restTemplate;

    private static final ExternalApiCaller instance = new ExternalApiCaller();

    public static ExternalApiCaller getInstance() {
        return instance;
    }

    public ExternalApiCaller setApiPath(String apiPath) {
        this.apiPath = apiPath;
        return this;
    }

    public ExternalApiCaller addApiParams(String paramName, String paramValue) {
        if(!this.apiParams.equals("")) {
            this.apiParams += "&" + paramName + "=" + paramValue;
        }
        else {
            this.apiParams = paramName + "=" + paramValue;
        }
        return this;
    }

    private ExternalApiCaller() throws CustomException {
        //upload config file
        String resourceName = "config.properties";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            props.load(resourceStream);
        } catch (IOException e) {
            throw new CustomException("Config file cannot be loaded");
        }

        //get api url & key
        apiKey = props.getProperty("api.key");
        apiUrl = props.getProperty("api.url");

        //create rest template for external api calls
        ClientHttpRequestFactory requestFactory = new
                HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());

        restTemplate = new RestTemplate(requestFactory);
    }

    public Map<String, Object> callApi() throws CustomException {
        //create uri
        final String uri = apiUrl+apiPath+"?" + apiParams+ "&apiKey=" + apiKey;

        //call external api
        ResponseEntity<String> resp = restTemplate.getForEntity(uri, String.class);

        //map response into hash map for views page
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map;
        try {
            map = mapper.readValue(resp.getBody(), new TypeReference<Map<String,Object>>() { });
        } catch (IOException e) {
            throw new CustomException("Cannot map api call: " + uri);
        }
        return map;
    }
}
