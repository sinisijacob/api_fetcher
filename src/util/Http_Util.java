package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class Http_Util {
	public static String sendGetRequest(String urlString, Map<String, String> args) throws Exception {
        // Create a URL object from the urlString
        URL url = new URL(buildUrlWithParams(urlString, args));

        // Open a connection to the URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set the request method to GET
        connection.setRequestMethod("GET");
        
        // Add additional arguments to the request
        for (String key : args.keySet()) {
        	connection.addRequestProperty(key, args.get(key));
        }

        // Get the response code
        int responseCode = connection.getResponseCode();

        // If the response code is HTTP OK (200)
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Create a BufferedReader to read the response from the input stream
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            // Read the input line by line and append it to the response StringBuilder
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            // Close the BufferedReader
            in.close();

            // Return the response as a String
            return response.toString();
        } else {
            // If the response code is not OK, throw an exception
            throw new Exception("HTTP request failed with response code " + responseCode);
        }
    }
	

    private static String buildUrlWithParams(String baseUrl, Map<String, String> params) throws Exception {
        StringBuilder urlWithParams = new StringBuilder(baseUrl);
        if (params != null && !params.isEmpty()) {
            urlWithParams.append("?");
            for (Map.Entry<String, String> entry : params.entrySet()) {
                urlWithParams.append(URLEncoder.encode(entry.getKey(), "UTF-8"))
                             .append("=")
                             .append(URLEncoder.encode(entry.getValue(), "UTF-8"))
                             .append("&");
            }
            // Remove the trailing "&"
            urlWithParams.setLength(urlWithParams.length() - 1);
        }
        return urlWithParams.toString();
    }
}
