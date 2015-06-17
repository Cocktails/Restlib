/**
 * @author Alejandro Alcalde <algui91@gmail.com>
 * @AsyncTaskCompleteListener.java @ RESTJsonLib
 * Nov 11, 2012
 */
package com.elbauldelprogramador.restlib;


/**
 * @author Alejandro Alcalde
 * @author Vicente
 *
 * Permite tratar los datos una vez ejecutada la petición al Web Service
 */
public interface AsyncTaskCompleteListener<T> {
	public void onTaskComplete(T result);
}
