package com.ajdons.livematchticker.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by adamdonegan on 24/05/2014.
 */
public class ListGamesFragment extends  ListFragment{

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static ListGamesAdapter adapter;
        private static TextView error;
        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static ListGamesFragment newInstance(int sectionNumber, ListGamesAdapter a) {

            ListGamesFragment fragment = new ListGamesFragment();
            adapter=a;
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public ListGamesFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            setListAdapter(adapter);
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView tv = (TextView) rootView.findViewById(R.id.errorText);
            tv.setText("There are currently no live games to display...");
            tv.setVisibility(View.INVISIBLE);

            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        System.out.println("CLICK");
        getFragmentManager().beginTransaction()
                .replace(R.id.container, GameViewFragment.newInstance(position + 1, adapter.getGamesArrayList().get(position))).addToBackStack("fragback").commit();
    }
}

