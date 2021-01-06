package de.hsbhv.touroverview.backend.vuforia;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.DateUtils;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;


// See the Vuforia Web Services Developer API Specification - https://library.vuforia.com/articles/Solution/How-To-Use-the-Vuforia-Web-Services-API#How-To-Retrieve-a-Target-Record

public class GetTarget {

    //Server Keys
    private String accessKey = "[ server access key ]";
    private String secretKey = "[ server secret key ]";

    private String targetId = "[ target id ]";
    private String url = "https://vws.vuforia.com";

    public GetTarget(String accessKey, String secretKey, String targetId) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.targetId = targetId;
    }

    private void getTarget() throws URISyntaxException, ClientProtocolException, IOException {
        HttpGet getRequest = new HttpGet();
        HttpClient client = new DefaultHttpClient();
        getRequest.setURI(new URI(url + "/targets/" + targetId));
        setHeaders(getRequest);

        HttpResponse response = client.execute(getRequest);
        //TODO
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    private void setHeaders(HttpUriRequest request) {
        SignatureBuilder sb = new SignatureBuilder();
        request.setHeader(new BasicHeader("Date", DateUtils.formatDate(new Date()).replaceFirst("[+]00:00$", "")));
        request.setHeader("Authorization", "VWS " + accessKey + ":" + sb.tmsSignature(request, secretKey));
    }

//
//	public static void main(String[] args) throws URISyntaxException, ClientProtocolException, IOException {
//		GetTarget g = new GetTarget();
//		g.getTarget();
//	}
}
