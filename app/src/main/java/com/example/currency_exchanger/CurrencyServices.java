package com.example.currency_exchanger;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class CurrencyServices extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // Elindítja a services (ami a háttérben fog futni)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        CurrencyData currencyData = new CurrencyData();
        final RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        String url = "https://api.exchangerate.host/fluctuation?start_date=" + getToday() + "&end_date=" + getLastWeek();
        JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET, url, null, (Response.Listener<JSONObject>) response -> {
            try {
                Toast.makeText(this, "A(z) értéke " + response.getJSONObject("rates").getJSONObject(currencyData.fromCurrency).get("change").toString()+"-vel modosult", Toast.LENGTH_SHORT).show();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {

        });
        queue.add(request);
        return START_STICKY;
    }

    private String getLastWeek()
    {
        Calendar c = GregorianCalendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
        c.add(Calendar.DAY_OF_WEEK, -1);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        return df.format(c.getTime());
    };

    private String getToday(){
        Calendar c = GregorianCalendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        return df.format(c.getTime());
    }
}


