package com.dexlock.monad;

import org.apache.commons.validator.routines.UrlValidator;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Helper {

    public boolean validateUrl(String url) {
        UrlValidator urlValidator = new UrlValidator();
        return urlValidator.isValid(url);
    }

    public final CloseableHttpClient httpClient = HttpClients.createDefault();

    public void close() throws IOException {
        httpClient.close();
    }

    public String getUrl(String url) throws Exception {
        HttpGet request = new HttpGet(url);

        // add request headers
        request.addHeader("custom-key", "abh");
        request.addHeader(HttpHeaders.USER_AGENT, "abh");

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            // Get HttpResponse Status
            // System.out.println(response.getStatusLine().toString());
            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();

            // System.out.println(headers);
            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                return result;
            }
        }
        return null;
    }
}

