/**
 * @author Alejandro Alcalde <algui91@gmail.com>
 * @author Vicente
 * @JSONRestRequest.java
 * Nov 10, 2012
 */
package thecocktaillab.restJsonLib;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONStringer;

/**
 * 
 * Encapsula los datos de la petición en JSON
 *
 */
public class JSONRestRequest extends RestRequest {

	@Override
	public boolean setContent(HashMap<String, Object> data) {
		
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
}