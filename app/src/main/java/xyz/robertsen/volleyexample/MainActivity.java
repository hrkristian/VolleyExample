package xyz.robertsen.volleyexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.VolleyError;

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
        sendRequest("https://www.google.com");
    }
    public void onButton2Clicked(View view) {
        setButtonsDisabled();
        sendRequest("https://robertsen.xyz");
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
    private void sendRequest(String url) {
        RequestHandler handler = new RequestHandler(this);
        handler.sendStringRequest(url, new RequestHandler.ResponseHandler() {
            @Override
            public void handleResponse(String response) {
                // Handle the response from a successful request
                setButtonsEnabled();
                t1.setText(response);
            }

            @Override
            public void handleErrorResponse(VolleyError error) {
                // Handle the error from an unsuccessful request
                setButtonsEnabled();
                t1.setText(error.getMessage());
            }
        });
    }
}
