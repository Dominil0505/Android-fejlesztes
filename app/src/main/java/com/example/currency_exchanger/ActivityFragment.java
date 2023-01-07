package com.example.currency_exchanger;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Currency;
import java.util.GregorianCalendar;
import java.util.Locale;

public class ActivityFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_activity, container, false);

        EditText firstCurrencyEditText = view.findViewById(R.id.currency1EditText);
        TextView secondCurrencyEditText = view.findViewById(R.id.resultTextView);
        Spinner firstCurrencySpinner = view.findViewById(R.id.currency1Spinner);
        Spinner secondCurrencySpinner = view.findViewById(R.id.currency2Spinner);
        Button copyBtn = view.findViewById(R.id.copyButton);
        Button saveBtn = view.findViewById(R.id.saveButton);
        EditText saveEditText = view.findViewById(R.id.saveEditText);
        TextView saveTextView = view.findViewById(R.id.saveTextView);
        Button reminderBtn = view.findViewById(R.id.reminderButton);
        CurrencyData currencyData = new CurrencyData();

        copyBtn.setEnabled(false);
        saveBtn.setEnabled(false);
        saveEditText.setEnabled(false);
        saveTextView.setEnabled(false);
        reminderBtn.setEnabled(false);
        saveEditText.setVisibility(View.INVISIBLE);
        saveTextView.setVisibility(View.INVISIBLE);
        reminderBtn.setVisibility(View.INVISIBLE);

        final RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());

        ArrayAdapter adapter = new ArrayAdapter(getActivity().getApplicationContext(), R.layout.color_spinner_layout);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        firstCurrencySpinner.setAdapter(adapter);
        firstCurrencyEditText.setText("1");



        firstCurrencyEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                currencyData.setFromCurrency(firstCurrencySpinner.getSelectedItem().toString());
                currencyData.setToCurrency(secondCurrencySpinner.getSelectedItem().toString());
                currencyData.setAmount(firstCurrencyEditText.getText().toString());

                String url = currencyData.BASE_URL + currencyData.fromCurrency + "&amount=" + currencyData.amount + "&to=" + currencyData.toCurrency; // from=USD&to=EUR&amount=1

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
                    try {
                        secondCurrencyEditText.setText(response.get("result").toString());
                        copyBtn.setEnabled(true);
                        saveBtn.setEnabled(true);
                    } catch (JSONException e) {
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                    }
                }, error -> {

                });
                queue.add(request);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        ClipboardManager clipboard = (ClipboardManager)getActivity().getSystemService(getContext().CLIPBOARD_SERVICE);

        copyBtn.setOnClickListener(view12 -> {
            ClipData clip = ClipData.newPlainText("label", secondCurrencyEditText.getText().toString());
            clipboard.setPrimaryClip(clip);
                Toast.makeText(getActivity().getApplicationContext(), "Copied to clipboard", Toast.LENGTH_LONG).show();
        });

        saveBtn.setOnClickListener(view1 -> {
            saveEditText.setEnabled(true);
            saveTextView.setEnabled(true);
            reminderBtn.setEnabled(true);
            saveEditText.setVisibility(View.VISIBLE);
            saveTextView.setVisibility(View.VISIBLE);
            reminderBtn.setVisibility(View.VISIBLE);

            String url = currencyData.BASE_URL + currencyData.fromCurrency + "&amount=" + currencyData.amount + "&to=" + currencyData.toCurrency; // from=USD&to=EUR&amount=1

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
                try {
                    saveEditText.setText(response.get("result").toString());
                    saveTextView.setText("Click on ADD REMINDER to get a notification when currency is changing. Current value:" + saveEditText.getText().toString() + ".");
                } catch (JSONException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }, error -> {

            });
            queue.add(request);
        });

        saveEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                saveTextView.setText("Click on ADD REMINDER to get a notification when currency is changing. Current value: "+ saveEditText.getText().toString() + ".");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        reminderBtn.setOnClickListener(view13 -> {
            
        });

        // setting currency to spinners
        initSpinner(R.array.currencies_list, firstCurrencySpinner);
        initSpinner(R.array.currencies_list, secondCurrencySpinner);

        return view;
    }

    private void initSpinner(int resId, Spinner spinner) {
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(getContext(),
                        resId,
                        android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

}
