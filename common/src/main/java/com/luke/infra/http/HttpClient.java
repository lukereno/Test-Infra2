package com.luke.infra.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

public class HttpClient {
	public static void getRequest() {

		String url = "http://localhost:8080/api/tasks/1";
		
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet request = new HttpGet(url);
		CloseableHttpResponse response = null;
		try {
			response = client.execute(request);
			int status = response.getStatusLine().getStatusCode();

			if (status >= 200 && status < 300) {
				BufferedReader br;
				
				br = new BufferedReader(new InputStreamReader(response
							.getEntity().getContent()));
				
				String line = "";
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
			} else {
				System.out.println("Unexpected response status: " + status);
			}
		} catch (IOException | UnsupportedOperationException e) {
			e.printStackTrace();
		} finally {
		    if(null != response){
		    	try {
					response.close();
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		}
	}
}
