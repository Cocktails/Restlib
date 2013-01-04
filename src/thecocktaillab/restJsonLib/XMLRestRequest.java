/**
 * @author Alejandro Alcalde <algui91@gmail.com>
 * @author Vicente
 * @XMLRestReuest.java
 * hkr
 * Nov 10, 2012
 */
package thecocktaillab.restJsonLib;

import java.util.HashMap;


public class XMLRestRequest extends RestRequest {

	@Override
	public boolean setContent(HashMap<String, Object> data) {
		return false;
	}

}
