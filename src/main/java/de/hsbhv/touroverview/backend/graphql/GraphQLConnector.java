package de.hsbhv.touroverview.backend.graphql;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class GraphQLConnector {
    private final URL url;

    private static final String API_ENDPOINT = "https://api-eu-central-1.graphcms.com/v2/ckbtfd1r500dl01z51rpf93bm/master";

    Logger logger;

    private static final String DEFAULT_AUTH_TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6ImdjbXMtbWFpbi1wcm9kdWN0aW9uIn0.eyJ2ZXJzaW9uIjozLCJpYXQiOjE1OTM0NTI2NTAsImF1ZCI6WyJodHRwczov" +
            "L2FwaS1ldS1jZW50cmFsLTEuZ3JhcGhjbXMuY29tL3YyL2NrYnRmZDFyNTAwZGwwMXo1MXJwZjkzYm0vbWFzdGVyIl0sImlzcyI6Imh0dHBzOi8vbWFuYWdlbWVudC5ncmFwaGNtcy5jb20vIiwic3ViIj" +
            "oiNGEwZWFjZmMtOWU0Zi00YTU3LTg4ODUtNGQ3NWEyMTMyMzM3IiwianRpIjoiY2tjMHNrZTI4MGpvMTAxeG5mNzI2Nm1nOCJ9.wKYXAYAPS80BFruCjmH5rsarB99M0WsH7-86DHBixDCFpSqxxH11aI4TTQVN" +
            "-HvsGXP3OZaO1JDff3-WGLKvgyhBb2QCWtqE4N4jiSLKrjg4pvQs0a8ajeaPL9Dc51PoIjlkDT7GD2iReIbk16U-5WUNwi7bd0yLzNpxkZ_6dpANJcAMFiawDfr3uIlX8Wt61_s8mQlcNt2fOsAyrPqfJkrcBa3stObwT-" +
            "UdDWX5CQuRKOM3tokr1NPsWCxE7orIz_CpgTL61kbru4D4DTtaaZpKd_7DbyPlaWOQeHc6rG3Y1Q2-ZHtrUiRkZ5ipSACSHhaeNgCj3qmq9X3S0ghFkt68tOqGR3tTVqQKcUsxLYECxUzvJFJoW4adZ4ejTIlOLyG4ojnT_6p6JyAXzL" +
            "-hXt6KpXyaSP-mV1asfccz1d3obhmFOjTIkuvF2_kdfr71UdkdqmNhMVHzqeJX9jUXomC-j48jLaWOkHhWpMi0xGsFM-WDo2dt1KDOzqyNU37-9102MLh9ZX_J3il5tgXgUsVsG2XMKGyuEoA9vPe48CwiLNt_skcreSfZmUN3VvO8A0sSxI" +
            "ByvycoU8iRyePwS0i9ZML5gZ32jop7PXT-oCUTxxFr2E-YilNIv-86aMWdVhYB972dAm7udT3vBPBjbWgUOPWyUF0dvL-tKx0fgyM";

    private String authToken;

    public GraphQLConnector() {
        logger = LoggerFactory.getLogger(GraphQLConnector.class);
        try{
            url = new URL(API_ENDPOINT);
        } catch (MalformedURLException e){
            logger.info("The URL of the API Endpoint isn't up to date:");
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

    }

    public GraphQLConnector(String url, String authToken) throws MalformedURLException {
        assert url != null;
        logger = LoggerFactory.getLogger(GraphQLConnector.class);
        this.url = new URL(url);
        this.authToken = authToken;
    }

    /**
     * Builds a connection to the API Endpoint. This connection can be used to send Requests.
     * @return returns the built connection to the API
     * @throws IOException gets thrown when there is a Problem openin the Connection from the url
     */
    public HttpsURLConnection connectToAPI() throws IOException {
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setInstanceFollowRedirects(false);
        connection.setRequestMethod(String.valueOf(RequestMethod.POST));
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("charset", "utf-8");
        //Is there a custom Authorization given - Standard behaviour is the hard programmed key
        connection.setRequestProperty("Authorization", "Bearer " + (authToken != null ? authToken : DEFAULT_AUTH_TOKEN));
        //TODO: In future there may be the need for more headers - maybe there should be the possibility to pass a Map of headers and their Values

        return connection;
    }

    /**
     *
     * @param query
     * @param variables
     * @return
     */
    protected String createRequestString(String query, Map<String, String> variables) {
        assert query != null;
        /*
        * Replaces line breaks with  blank spaces because the API apparently can't handle line breaks.
        * Because of this line you can just copy paste the query from GraphiQL.
        */
        query = query.replace("\n", " ");
        StringBuilder requestBuilder = new StringBuilder();
        requestBuilder.append("{\"query\":\"")
                .append(query).append("\"")
                .append(", ")
                .append("\"variables\":");
        if (variables != null) {
            requestBuilder.append("{");
            for (String key : variables.keySet()) {
                requestBuilder.append("\"").append(key).append("\": ")
                        .append("\"").append(variables.get(key)).append("\"")
                        .append("}");
            }
        } else {
            requestBuilder.append("{}");
        }
        requestBuilder.append("}");
        return requestBuilder.toString();
    }

    public byte[] createRequest(String query){
        return createRequest(query, null);
    }

    public byte[] createRequest(String query, Map<String, String> variables) {
        String requestString = createRequestString(query, variables);
        return requestString.getBytes();
    }

    public JSONObject sendRequest(byte[] requestData) {
        String responseMessage;
        String responseStatus;
        try {
            HttpsURLConnection connection = connectToAPI();
            connection.setRequestProperty("Content-Length", Integer.toString(requestData.length));
            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            dataOutputStream.write(requestData);
            dataOutputStream.close();
            Map<String, List<String>> responseHeaders = connection.getHeaderFields();
            responseMessage = connection.getResponseMessage();
            responseStatus = Integer.toString(connection.getResponseCode());
            InputStream inputStream;
            try {
                inputStream = connection.getInputStream();
            } catch (IOException e) {
                inputStream = connection.getErrorStream();
            }


            if(connection.getResponseCode() >= 400){
                int responseCode = connection.getResponseCode();
                logger.error("Error while sending the Request - Respone Code: " + responseCode + " - " + (HttpStatus.resolve(responseCode) != null ? HttpStatus.resolve(responseCode).getReasonPhrase() : ""));
                throw new IOException("Response code "+ responseCode + (HttpStatus.resolve(responseCode) != null ? HttpStatus.resolve(responseCode).getReasonPhrase() : "") + "\n"+
                        "response message: "+ responseMessage + "\n" +
                        "respone status: "+ responseStatus);
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            JSONTokener tokener = new JSONTokener(bufferedReader);
            JSONObject json = new JSONObject(tokener);

            if(json.isNull("data")){
                logger.error("Something went wrong processing the request.");
                int responseCode = connection.getResponseCode();
                throw new IOException("Response code "+ responseCode + (HttpStatus.resolve(responseCode) != null ? HttpStatus.resolve(responseCode).getReasonPhrase() : "") + "\n"+
                        "response message: "+ responseMessage + "\n" +
                        "response status: "+ responseStatus);
            }
            connection.disconnect();

            return json;

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();

            return null;
        }
    }

}
