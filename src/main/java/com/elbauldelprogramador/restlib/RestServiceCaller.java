/**
 * @author Alejandro Alcalde <algui91@gmail.com>
 * @author Vicente
 * @RestServiceCaller.java
 * Nov 10, 2012
 */
package com.elbauldelprogramador.restlib;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RestServiceCaller {

	public static final int RET_SUCCESS = 1;
	public static final int RET_ERROR = 0;
	public static final int RET_ERR_NETWORK = -1;
	public static final int RET_ERR_WEBSERVICE = -2;

	public RestResponse execute(RestRequest request) {
		final RestResponse response;

		try {
			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpRequestBase httpRequest = null;
			HttpEntityEnclosingRequestBase entityRequest;

			switch (request.getMethod()) {
			case RestRequest.GET_METHOD:
				httpRequest = new HttpGet(request.getURI());
				break;

			case RestRequest.POST_METHOD:
				httpRequest = new HttpPost(request.getURI());
				entityRequest = (HttpEntityEnclosingRequestBase) httpRequest;

				entityRequest.setEntity(new StringEntity(request.getContent()));
				break;

			case RestRequest.PUT_METHOD:
				httpRequest = new HttpPut(request.getURI());
				entityRequest = (HttpEntityEnclosingRequestBase) httpRequest;

				entityRequest.setEntity(new StringEntity(request.getContent()));
				break;

			case RestRequest.DELETE_METHOD:
				httpRequest = new HttpDelete(request.getURI());
				entityRequest = (HttpEntityEnclosingRequestBase) httpRequest;

				entityRequest.setEntity(new StringEntity(request.getContent()));
				break;
			}

			httpRequest.setHeader("Accept", "application/json");
			httpRequest.setHeader("Content-type", "application/json");

			response = new RestResponse();
			ResponseHandler<String> handler = new ResponseHandler<String>() {

				public String handleResponse(HttpResponse httpresponse)
						throws ClientProtocolException, IOException {

					StringBuilder r = new StringBuilder();
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(httpresponse.getEntity()
									.getContent(), "UTF-8"));
					for (String line = null; (line = reader.readLine()) != null;)
						r.append(line).append("\n");

					response.setResponseCode(httpresponse.getStatusLine()
							.getStatusCode());

					return r.toString();
				}
			};

			String result = httpclient.execute(httpRequest, handler);
			response.setResponse(result);

		} catch (Exception e) {
			return null;
		}

		return response;
	}
}
