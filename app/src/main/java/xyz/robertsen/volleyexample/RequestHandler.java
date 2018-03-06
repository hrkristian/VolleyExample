package xyz.robertsen.volleyexample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

class RequestHandler {
    private final RequestQueue queue;

    RequestHandler(@NonNull Context c) {
        this.queue = Volley.newRequestQueue(c);
    }

    void sendStringRequest(String url, @NonNull final ResponseHandler command) {
        Log.i("RequestHandler", "Sending request...");

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        command.handleResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        command.handleErrorResponse(error);
                    }
                }
        );
        queue.add(request);
    }

    /**
     * Command Pattern as a contract between the RequestHandler and invoker
     */
    interface ResponseHandler {
        void handleResponse(String response);
        void handleErrorResponse(VolleyError error);
    }
}
