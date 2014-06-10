package com.ajdons.livematchticker.app;


import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.ajdons.livematchticker.models.Game;
import com.ajdons.livematchticker.models.Player;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by adamdonegan on 23/05/2014.
 */
public class GameViewFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static Game game;
    private static  GameViewAdapter adapter1;
    private static GameViewAdapter adapter2;

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
        TextView team1Score = (TextView) rootView.findViewById(R.id.team1score);
        TextView team2Score = (TextView) rootView.findViewById(R.id.team2score);
        TextView timeElapsed = (TextView) rootView.findViewById(R.id.timeelapsed);
        TextView team1Name = (TextView) rootView.findViewById(R.id.team1name);
        TextView team2Name = (TextView) rootView.findViewById(R.id.team2name);
        ImageView team1Logo = (ImageView) rootView.findViewById(R.id.team1logo);
        ImageView team2Logo = (ImageView) rootView.findViewById(R.id.team2logo);
        final ListView team1Team = (ListView) rootView.findViewById(R.id.team1team);
        final ListView team2Team = (ListView) rootView.findViewById(R.id.team2team);
        Switch itemSwitch =  (Switch) rootView.findViewById(R.id.switch3);

        itemSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (game.getScoreboard() != null) {
                    if (b == false) {
                        adapter1 = new GameViewAdapter(getActivity(), game.getScoreboard().getRadiant().getPlayers(), game, false);
                        adapter2 = new GameViewAdapter(getActivity(), game.getScoreboard().getDire().getPlayers(), game, false);
                        team1Team.setAdapter(adapter1);
                        team2Team.setAdapter(adapter2);
                        adapter1.notifyDataSetChanged();
                        adapter2.notifyDataSetChanged();
                    }
                    else {
                        adapter1 = new GameViewAdapter(getActivity(), game.getScoreboard().getRadiant().getPlayers(), game, true);
                        adapter2 = new GameViewAdapter(getActivity(), game.getScoreboard().getDire().getPlayers(), game, true);
                        team1Team.setAdapter(adapter1);
                        team2Team.setAdapter(adapter2);
                        adapter1.notifyDataSetChanged();
                        adapter2.notifyDataSetChanged();
                    }
                }
            }
        });

        if(game.getScoreboard() == null) {
            team1Score.setText(String.valueOf(0));
            team2Score.setText(String.valueOf(0));
            adapter1 = new GameViewAdapter(getActivity(), new ArrayList<Player>(), game, false);
            adapter2 = new GameViewAdapter(getActivity(), new ArrayList<Player>(), game, false);
        }
        else {
            team1Score.setText(String.valueOf(game.getScoreboard().getRadiant().getScore()));
            team2Score.setText(String.valueOf(game.getScoreboard().getDire().getScore()));
            timeElapsed.setText(calculateTime(game.getScoreboard().getDuration()));
            adapter1 = new GameViewAdapter(getActivity(), game.getScoreboard().getRadiant().getPlayers(), game, false);
            adapter2 = new GameViewAdapter(getActivity(), game.getScoreboard().getDire().getPlayers(), game, false);
        }

        team1Name.setText(game.getRadiant_team().getTeam_name());
        team2Name.setText(game.getDire_team().getTeam_name());


        team1Team.setAdapter(adapter1);
        team2Team.setAdapter(adapter2);

        team1Team.setAdapter(adapter2);



        int team1stdID = rootView.getResources().getIdentifier("drawable/" + fixString(game.getRadiant_team().getTeam_name()) + "_std", "drawable", getActivity().getPackageName());
        int team1bigID = rootView.getResources().getIdentifier("drawable/" + fixString(game.getRadiant_team().getTeam_name()) + "_big", "drawable", getActivity().getPackageName());
        int team2stdID = rootView.getResources().getIdentifier("drawable/" + fixString(game.getDire_team().getTeam_name()) + "_std", "drawable", getActivity().getPackageName());
        int team2bigID = rootView.getResources().getIdentifier("drawable/" + fixString(game.getDire_team().getTeam_name()) + "_big", "drawable", getActivity().getPackageName());

        if(team1stdID == 0)
            team1Logo.setImageResource(R.drawable.default_std);
        else
            team1Logo.setImageResource(team1stdID);

        if(team2stdID == 0)
            team2Logo.setImageResource(R.drawable.default_std);
        else
            team2Logo.setImageResource(team2stdID);

        team1Image.setImageResource(team1bigID);
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

    public String calculateTime(float decimalTime){
        String output = "";
        output = output + Math.round(decimalTime/60);
        output = output + ":";
        int temp = Math.round(decimalTime%60);
        if(temp > 9)
        output = output + temp;
        else
        output = output + "0" + temp;

        return output;
    }
}

