package me.aneeshneelam.sunshine.fragment;

import android.os.Bundle;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.aneeshneelam.sunshine.R;
import me.aneeshneelam.sunshine.asyncTask.FetchWeatherTask;

/**
 * Created by Aneesh Neelam <neelam.aneesh@gmail.com>
 */
public class ForecastFragment extends Fragment {

    private ArrayAdapter<String> forecastAdapter;

    public ForecastFragment() {
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
                new FetchWeatherTask(this).execute("500011,IN");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_forecast, container, false);
        setHasOptionsMenu(true);

        String[] dummyForecast = {
                "Monday - Dummy Weather",
                "Tuesday - Dummy Weather",
                "Wednesday - Dummy Weather",
                "Thursday - Dummy Weather",
                "Friday - Dummy Weather",
                "Saturday - Dummy Weather",
                "Sunday - Dummy Weather",
        };
        List<String> weekForecast = new ArrayList<>(Arrays.asList(dummyForecast));
        forecastAdapter = new ArrayAdapter<>(getActivity(), R.layout.fragment_forecast_listview_item, R.id.list_item_forecast_textview, weekForecast);

        new FetchWeatherTask(this).execute("94043");

        ListView listView = (ListView) rootView.findViewById(R.id.listview_forecast);
        listView.setAdapter(forecastAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = forecastAdapter.getItem(position);
                Toast.makeText(getActivity(), item, Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    public void onFetchWeatherTaskComplete(String[] forecastArray) {

        forecastAdapter.clear();
        forecastAdapter.addAll(forecastArray);

    }
}
