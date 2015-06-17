/**
 * @author Alejandro Alcalde <algui91@gmail.com>
 * @author Vicente
 * @RestResponse.java
 * Nov 10, 2012
 */
package com.elbauldelprogramador.restlib;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Clase contenedora de la respuesta del Web Service
 * 
 */
public class RestResponse{

	private HashMap<String, Object> content;
	private int responseCode = 200;
	
	public void setResponse(String response) throws JSONException {
		JSONObject result = new JSONObject(response);
		content = new HashMap<String, Object>();
		
		for (Iterator iterator = result.keys(); iterator.hasNext();) {
			String key = (String) iterator.next();
			content.put(key, result.get(key));
		}
	}
	
	public HashMap<String, Object> getContent() {
		return content;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

}
