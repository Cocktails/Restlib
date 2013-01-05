/**
 * @author Alejandro Alcalde <algui91@gmail.com>
 * @author Vicente
 * @RestServiceTask.java
 * hkr
 * Nov 10, 2012
 */

package thecocktaillab.restJsonLib;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import thecocktaillab.restJsonLib.R;

/**
 * Configura un AsynTask para realizar la petición en un hilo secundario
 */
public class RestServiceTask extends AsyncTask<RestRequest, Integer, RestResponse> {

    /** progress dialog to show user that the backup is processing. */
    private ProgressDialog dialog;
    private RestResponse response;
    private Context context;

    private AsyncTaskCompleteListener<RestResponse> callback;

    public RestServiceTask(Context context, AsyncTaskCompleteListener<RestResponse> cb,
            String title, String message) {
        this.context = context;
        this.callback = cb;

        dialog = new ProgressDialog(context);
        dialog.setTitle(title);
        dialog.setMessage(message); // TODO message
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.show();
    }

    protected RestResponse doInBackground(RestRequest... requests) {
        int status = RestServiceCaller.RET_SUCCESS;
        try {
            // TODO, Acoplamiento, no debo usar nada relacionado con el proyeto
            response = new RestServiceCaller().execute(requests[0]);
            // status = (Integer) response.getContent().get(Global.KEY_RET);
        } catch (Exception e) {
            // TODO comprobar tipo error
            status = RestServiceCaller.RET_ERR_WEBSERVICE;
            e.printStackTrace();
        }

        return response;
    }

    protected void onPreExecute() {
        response = null;
    }

    protected void onPostExecute(RestResponse result) {

        if (dialog.isShowing()) {
            dialog.dismiss();
        }

        callback.onTaskComplete(result);
    }

    public RestResponse getResponse() {
        return response;
    }
}
