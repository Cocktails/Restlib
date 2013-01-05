/**
 * @author Alejandro Alcalde <algui91@gmail.com>
 * @author Vicente
 * @JSONRestRequest.java
 * Nov 10, 2012
 */
package thecocktaillab.restJsonLib;

import org.json.JSONStringer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * Encapsula los datos de la petición en JSON
 *
 */
public class JSONRestRequest extends RestRequest {

	@Override
	public boolean setContent(HashMap<String, Object> data) {
		
	    if (this.getMethod() == RestRequest.GET_METHOD)
	        buildGetURI(data);
	    
		try {
			JSONStringer holder = new JSONStringer().object();
			Iterator<Entry<String, Object>> it = data.entrySet().iterator();

			while (it.hasNext()) {
				Map.Entry<String, Object> e = it.next();
				holder.key(e.getKey().toString()).value(e.getValue());
			}
			holder.endObject();

			content = holder.toString();
		} catch (Exception e) {
			return false;
		}

		return true;

	}
	
	private void buildGetURI(HashMap<String, Object> data){
	    String query = "?";
	    for (Iterator<Entry<String, Object>> iterator = data.entrySet().iterator(); iterator.hasNext();) {
            Map.Entry<String, Object> e = iterator.next();
            query = query.concat(e.getKey() + "=" + e.getValue() + "&");            
        }
	    query = query.replaceAll("\\s", "%20");
	    this.setURL(getURI().concat(query.substring(0,query.length()-1)));
	}
}