package com.ajdons.livematchticker.app;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajdons.livematchticker.models.Game;

/**
 * Created by adamdonegan on 23/05/2014.
 */
public class GameViewFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static Game game;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static GameViewFragment newInstance(int sectionNumber, Game g) {
        GameViewFragment fragment = new GameViewFragment();
        game = g;
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public GameViewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gameview, container, false);
        ImageView team1Image = (ImageView) rootView.findViewById(R.id.team1image);
        ImageView team2Image = (ImageView) rootView.findViewById(R.id.team2image);
        TextView t1Score = (TextView) rootView.findViewById(R.id.t1score);
        TextView t2Score = (TextView) rootView.findViewById(R.id.t2score);

        if(game.getScoreboard() == null) {
            t1Score.setText(String.valueOf(0));
            t2Score.setText(String.valueOf(0));
        }
        else {
            t1Score.setText(String.valueOf(game.getScoreboard().getRadiant().getScore()));
            t2Score.setText(String.valueOf(game.getScoreboard().getDire().getScore()));
        }
        int team1bigID = rootView.getResources().getIdentifier("drawable/" + fixString(game.getRadiant_team().getTeam_name()) + "_big", "drawable", getActivity().getPackageName());
        team1Image.setImageResource(team1bigID);
        int team2bigID = rootView.getResources().getIdentifier("drawable/" + fixString(game.getDire_team().getTeam_name()) + "_big", "drawable", getActivity().getPackageName());
        team2Image.setImageResource(team2bigID);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    public String fixString(String s){
        String temp = s;
        return temp.toLowerCase().replaceAll("[-_!|/., ?]", "");

    }
}

