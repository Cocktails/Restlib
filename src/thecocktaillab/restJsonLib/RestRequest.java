package thecocktaillab.restJsonLib;

import java.util.HashMap;

/**
 * 
 * Clase base para la petición al Web Service
 *
 */

public abstract class RestRequest {

	/**
	 * Method Identifiers
	 */
	public static final int GET_METHOD 	  = 0;
	public static final int POST_METHOD   = 1;
	public static final int PUT_METHOD 	  = 2;
	public static final int DELETE_METHOD = 3;
	
	protected String content;
	private int method;
	private String uri;

	public int getMethod() {
		return method;
	}

	public void setMethod(int method) {
		this.method = method;
	}

	public String getURI() {
		return uri;
	}

	public void setURL(String uri) {
		this.uri = uri;
	}

	public String getContent() {
		return content;
	}

	public abstract boolean setContent(HashMap<String, Object> data);
}
