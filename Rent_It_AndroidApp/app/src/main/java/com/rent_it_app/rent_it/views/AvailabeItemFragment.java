package com.rent_it_app.rent_it.views;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.cognito.CognitoSyncManager;
import com.amazonaws.regions.Regions;
import com.google.gson.Gson;
import com.rent_it_app.rent_it.Constants;
import com.rent_it_app.rent_it.EditItemActivity;
import com.rent_it_app.rent_it.R;
import com.rent_it_app.rent_it.firebase.Config;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.rent_it_app.rent_it.json_models.Item;
import com.rent_it_app.rent_it.json_models.ItemEndpoint;

/**
 * A simple {@link Fragment} subclass.
 */
public class AvailabeItemFragment extends Fragment {


    public AvailabeItemFragment() {
        // Required empty public constructor
    }

    Retrofit retrofit;
    ItemEndpoint itemEndpoint;
    TextView tv1;
    Gson gson;
    private ArrayList<Item> iList;
    private ListView list;
    CognitoCachingCredentialsProvider credentialsProvider;
    CognitoSyncManager syncClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_available_item, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("INVENTORY");

        gson = new Gson();
        tv1 = (TextView)view.findViewById(R.id.textView1);
        list = (ListView) view.findViewById(R.id.list);

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.REST_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        itemEndpoint = retrofit.create(ItemEndpoint.class);

        credentialsProvider = new CognitoCachingCredentialsProvider(
                getContext(),  // getApplicationContext(),
                Constants.COGNITO_POOL_ID, // Identity Pool ID
                Regions.US_WEST_2 // Region
        );

        // Initialize the Cognito Sync client
        syncClient = new CognitoSyncManager(
                getContext(),
                Regions.US_WEST_2, // Region
                credentialsProvider);

        return view;
    }
    /* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onDestroy()
	 */
    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

    /* (non-Javadoc)
     * @see android.support.v4.app.FragmentActivity#onResume()
     */
    @Override
    public void onResume()
    {
        super.onResume();
        loadItemList();

    }
    private void loadItemList() {

        iList = new ArrayList<Item>();

        Call<ArrayList<Item>> call = itemEndpoint.getItems();
        call.enqueue(new Callback<ArrayList<Item>>() {
            @Override
            public void onResponse(Call<ArrayList<Item>> call, Response<ArrayList<Item>> response) {
                int statusCode = response.code();
                //List<Item> items = response.body();
                iList = response.body();
                /*StringBuilder sb = new StringBuilder();
                for (Item i: items){
                    sb.append(i.getTitle() + ",");
                }*/

                //tv1.setText(sb.toString());
                list.setAdapter(new ItemListAdapter());
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0,
                                            View arg1, int pos, long arg3) {
                        startActivity(new Intent(getActivity(), EditItemActivity.class)
                                .putExtra(Config.EXTRA_DATA, iList.get(pos)));
                    }
                });

                Log.d("retrofit.call.enqueue", ""+statusCode);
            }

            @Override
            public void onFailure(Call<ArrayList<Item>> call, Throwable t) {
                Log.d("retrofit.call.enqueue", "failed");
            }
        });

    }
    private class ItemListAdapter extends BaseAdapter
    {

        /* (non-Javadoc)
         * @see android.widget.Adapter#getCount()
         */
        @Override
        public int getCount() { return iList.size(); }

        /* (non-Javadoc)
         * @see android.widget.Adapter#getItem(int)
         */
        @Override
        public Item getItem(int arg0)
        {
            return iList.get(arg0);
        }

        /* (non-Javadoc)
         * @see android.widget.Adapter#getItem_id(int)
         */
        @Override
        public long getItemId(int arg0)
        {
            return arg0;
        }

        /* (non-Javadoc)
         * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
         */
        @Override
        public View getView(int pos, View v, ViewGroup arg2)
        {
            if (v == null)
                v = getActivity().getLayoutInflater().inflate(R.layout.inventory_item, arg2, false);

            Item c = getItem(pos);

            TextView lbl = (TextView) v;
            lbl.setText(c.getTitle());

            /*lbl.setCompoundDrawablesWithIntrinsicBounds(
                    c.isOnline() ? R.drawable.ic_online
                            : R.drawable.ic_offline, 0, R.drawable.arrow, 0);*/

            return v;
        }

    }


}
