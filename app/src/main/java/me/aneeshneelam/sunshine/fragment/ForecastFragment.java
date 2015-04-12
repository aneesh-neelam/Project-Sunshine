package me.aneeshneelam.sunshine.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import me.aneeshneelam.sunshine.R;
import me.aneeshneelam.sunshine.activity.DetailActivity;
import me.aneeshneelam.sunshine.asyncTask.FetchWeatherTask;

/**
 * Created by Aneesh Neelam <neelam.aneesh@gmail.com>
 */
public class ForecastFragment extends Fragment {

    private String location;
    private ArrayAdapter<String> forecastAdapter;

    public ForecastFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();
        refreshWeather();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_forecast, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_refresh:
                refreshWeather();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_forecast, container, false);
        setHasOptionsMenu(true);

        forecastAdapter = new ArrayAdapter<>(getActivity(), R.layout.fragment_forecast_listview_item, R.id.list_item_forecast_textview, new ArrayList<String>());

        ListView listView = (ListView) rootView.findViewById(R.id.listview_forecast);
        listView.setAdapter(forecastAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = forecastAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), DetailActivity.class).putExtra(Intent.EXTRA_TEXT, item);
                startActivity(intent);
            }
        });

        return rootView;
    }

    public void onFetchWeatherTaskComplete(String[] forecastArray) {

        forecastAdapter.clear();
        forecastAdapter.addAll(forecastArray);

    }

    private void refreshWeather() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        location = prefs.getString(getString(R.string.pref_location_key), getString(R.string.pref_location_default));
        new FetchWeatherTask(this).execute(location);
    }

}
