package com.iayon.retrofit20tutorial;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class FirstFragment extends android.support.v4.app.Fragment {

    private View fragView;
    private Button refresh;
    private TextView timeRefresh;
    private boolean isInternetPresent;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragView =  inflater.inflate(R.layout.fragment_first, container, false);
        refresh = (Button) fragView.findViewById(R.id.button);
        timeRefresh = (TextView) fragView.findViewById(R.id.textView);

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());

        String refreshTime = prefs.getString("Last_Refreshed", null);

        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        isInternetPresent = false;

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                isInternetPresent=true;
            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                isInternetPresent=true;
        }
        else
            isInternetPresent = false;

        if(isInternetPresent == false) {
            timeRefresh.setText(refreshTime);
        }
        else {
            DateFormat mdf = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
            String mdate = mdf.format(Calendar.getInstance().getTime());
            timeRefresh.setText(mdate);
        }



        refresh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(isInternetPresent == false ) {
                    Toast.makeText(getActivity(),R.string.NoConnection,
                            Toast.LENGTH_SHORT).show();
                }
                else {

                    DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
                    String date = df.format(Calendar.getInstance().getTime());

                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("Last_Refreshed", date);
                    editor.apply();
                    String x = prefs.getString("Last_Refreshed", null);
                    timeRefresh.setText(date);
                }
                getActivity().recreate();

            }
        });

        return fragView;
    }

}
