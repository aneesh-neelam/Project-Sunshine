package me.aneeshneelam.sunshine.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.aneeshneelam.sunshine.R;

/**
 * Created by Aneesh Neelam <neelam.aneesh@gmail.com>
 */
public class PlaceholderFragment extends Fragment {

    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

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
        ArrayAdapter<String> forecastAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item_forecast, R.id.list_item_forecast_textview, weekForecast);

        return rootView;
    }
}
