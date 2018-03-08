package xyz.robertsen.volleyexample;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button b1, b2;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.button_main_fetch1);
        b2 = findViewById(R.id.button_main_fetch2);
        t1 = findViewById(R.id.textView_main);
    }


/* ------------ Click Handlers ------------ */
    public void onButton1Clicked(View view) {
        setButtonsDisabled();
        sendStringRequest("https://www.google.com");
    }
    public void onButton2Clicked(View view) {
        setButtonsDisabled();
        sendJSONRequest("https://api.magicthegathering.io/v1/cards?name=windwalker");
    }
    public void onButton3Clicked(View view) {
        setButtonsDisabled();
        sendImageRequest("https://i.warosu.org/data/biz/img/0074/62/1518111948359.jpg");
    }

/* ------------ Helpers ------------ */
    private void setButtonsDisabled() {
        b1.setClickable(false);
        b2.setClickable(false);
    }
    private void setButtonsEnabled() {
        b1.setClickable(true);
        b2.setClickable(true);
    }

    private void sendStringRequest(String url) {
        RequestHandler handler = new RequestHandler(this);

        handler.sendStringRequest(url, new RequestHandler.StringResponseHandler() {
            @Override
            public void onStringResponse(String response) {
                // Handle the response from a successful request
                setButtonsEnabled();
                t1.setText(response);
            }

            @Override
            public void onError(VolleyError error) {
                // Handle the error from an unsuccessful request
                setButtonsEnabled();
                t1.setText(error.getMessage());
            }
        });
    }

    private void sendJSONRequest(String url) {
        RequestHandler handler = new RequestHandler(this);

        handler.sendJsonRequest(url, new RequestHandler.JSONResponseHandler() {
            @Override
            public void onJSONResponse(JSONObject response) {
                setButtonsEnabled();
                try {
                    t1.setText(response.toString(2));
                } catch(JSONException e) { t1.setText(e.toString()); }
            }

            @Override
            public void onError(VolleyError error) {
                setButtonsEnabled();
                t1.setText(error.getMessage());
            }
        });
    }
    private void sendImageRequest(String url) {
        RequestHandler handler = new RequestHandler(this);
        handler.sendImageRequest(url, 1920, 1080, new RequestHandler.ImageResponseHandler() {
            @Override
            public void onImageResponse(Bitmap bitmap) {
                setButtonsEnabled();

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("imageDialog");
                if (prev != null)
                    ft.remove(prev);
                ft.addToBackStack(null);

                ImageFragment fragment = ImageFragment.newInstance(new BitmapDrawable(getResources(), bitmap));
                fragment.show(ft, "imageDialog");
            }

            @Override
            public void onError(VolleyError error) {
                setButtonsEnabled();
                t1.setText(error.getMessage());
            }
        });
    }

}
