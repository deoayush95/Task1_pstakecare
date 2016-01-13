package com.iayon.retrofit20tutorial;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import models.Items;
import rest.RestClient;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;


public class SecondFragment extends android.support.v4.app.Fragment {

    private UserAdapters adapter ;
    View fragView;
    ArrayList<Items> Users ;

    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        fragView = inflater.inflate(R.layout.fragment_second, container, false);

        final ListView listView = (ListView) fragView.findViewById(R.id.listView);
        Users = new ArrayList<Items>();

        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        Boolean isInternetPresent = false;

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                isInternetPresent=true;
            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                isInternetPresent=true;
        }
        else
            isInternetPresent = false;

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        if(isInternetPresent == false) {

            String myObj = prefs.getString("Myobject", null);
            if(myObj != null) {
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<Items>>(){}.getType();
                Users  = gson.fromJson(myObj,type);
                Log.d("MainActivity", "Items = " + Users.size());
                adapter = new UserAdapters(getActivity(), Users);
                listView.setAdapter(adapter);
            }
        }
        else {

            final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "loading...");
            RestClient.GitApiInterface service = RestClient.getClient();
            Call<ArrayList<Items>> call = service.getUsersNamedTom();
            call.enqueue(new Callback<ArrayList<Items>>() {
                @Override
                public void onResponse(Response<ArrayList<Items>> response) {
                    dialog.dismiss();
                    Log.d("MainActivity", "Status Code = " + response.code());
                    if (response.isSuccess()) {
                        // request successful (status code 200, 201)
                        ArrayList<Items> result = response.body();
                        Collections.sort(result, new CustomComparator());
                        String resp = new Gson().toJson(result);
                        Log.d("MainActivity", "response = " + resp);

                        SharedPreferences.Editor editor = prefs.edit();

                        editor.putString("Myobject", resp);
                        editor.apply();

                        Users = result;
                        Log.d("MainActivity", "Items = " + Users.size());
                        adapter = new UserAdapters(getActivity(), Users);
                        listView.setAdapter(adapter);
                    } else {
                        // response received but request not successful (like 400,401,403 etc)
                        //Handle errors

                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    dialog.dismiss();
                }
            });
        }
        return fragView;
    }

    void printr(ArrayList<Items> a){
        Items i = a.get(0);
        System.out.println("Item 0 = " + i.getName() + "  " +  i.getArea());
    }

}
