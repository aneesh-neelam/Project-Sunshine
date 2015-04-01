package me.aneeshneelam.sunshine.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.aneeshneelam.sunshine.R;

public class DetailsFragment extends Fragment {

    public static class PlaceholderFragment extends Fragment {

        Intent intent;
        private String forecastStr;

        public PlaceholderFragment() {
            intent = getActivity().getIntent();
            if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
                forecastStr = intent.getStringExtra(Intent.EXTRA_TEXT);
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
            TextView detailTextView = (TextView) rootView.findViewById(R.id.detail_text);
            detailTextView.setText(forecastStr);
            return rootView;
        }
    }
}
