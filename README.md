# VolleyExample
Eksempel på bruk av Volley i Android

## Oppsett
Volley er et tredjeparts bibliotek som må importeres ved å legge den til i build.gradle:
```
# build.gradle (Module: app)
------------------------------------------------
implementation 'com.android.volley:volley:1.1.0'
```
Etter en gradle sync er Volley tilgjengelig<br>
For å sende en request kreves tilgang på internett:
```
# AndroidManifest.xml
------------------------------------------------
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="xyz.robertsen.volleyexample">

    <uses-permission android:name="android.permission.INTERNET" />

(...)
```
## Kode
Deretter gjenstår det, for grunnleggende bruk, bare å opprette et **RequestQueue**-objekt og et **StringRequest**-objekt
```
// Vilkårlig metode
void sendStringRequest(Context context) {

  RequestQueue queue = Volley.newRequestQueue(context);

  StringRequest request = new StringRequest(
    Request.Method.GET,
    "https://www.google.com",
    new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
        // Behandle respons
      }
    },
    new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        // Behandle feil
      }
    }
  );

  queue.add(request);

}
```
RequestQueue behøver en Context når den opprettes. <br>
I koden over sendes Context med som parameter til en metode, mens i koden i dette repo'et opprettes RequestQueue-objektet som en del av RequestHandler-klassen, og blir tilgjengelig som en objekt-variabel.

## Ekstra Kode
Volley tilbyr flere metoder for vanlige requests, i koden er det eksempel på **JSONObjectRequest** og **ImageRequest**
```
void sendImageRequest(String url, int maxWidth, int maxHeight) {
        RequestQueue queue = Volley.newRequestQueue(context);

        ImageRequest request = new ImageRequest(
                url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        // Behandle Bitmap-respons
                    }
                },
                maxWidth,
                maxHeight,
                ImageView.ScaleType.CENTER,
                Bitmap.Config.RGB_565,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Behandle feil
                    }
                });
        queue.add(request);
    }

    void sendJsonRequest(String url) {
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Behandle JSON-respons
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Behandle feil
                    }
                }
        );
        queue.add(request);
    }
```

## Referanser
https://developer.android.com/training/volley/index.html <br>
Det kan være lurt å gå igjennom de fire linkene nederst på siden, for å forstå feilhåndtering m.m.
