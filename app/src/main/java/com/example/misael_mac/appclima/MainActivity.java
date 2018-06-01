package com.example.misael_mac.appclima;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private  static final String TAG ="openweatherImagen";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView iv_weather = (ImageView)findViewById(R.id.iv_weather);
        final TextView iv_temp = (TextView)findViewById(R.id.iv_temp);



        int identificador = getResources().getIdentifier("imagen_01n","drawable", getPackageName());
        iv_weather .setImageDrawable(getResources().getDrawable(identificador,null));

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://10.0.2.2:8888/unidad_3_actividad_1/clima/3530597";


        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                        if(response.has("weather")){

                                JSONArray weatherArray = response.getJSONArray("weather");
                                JSONObject weather = weatherArray.getJSONObject(0);

                                if(weather.has("icon")){
                                    String icon = weather.getString("icon");
                                    int indentiicador = getResources().getIdentifier("imagen_" + icon,"drawable", getPackageName());
                                    iv_weather.setImageDrawable(getResources().getDrawable(indentiicador,null));
                                }


                        }

                        if(response.has("main")){


                                JSONObject main = response.getJSONObject("main");

                                if(main.has("temp")){
                                    double temp = main.getInt("temp");
                                     iv_temp.setText(""+temp + "\u00b0");
                                }


                        }

                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG,error.toString());

                    }
                });

            queue.add(jsonObjectRequest);
        Log.d(TAG,"prueba");
    }
}
