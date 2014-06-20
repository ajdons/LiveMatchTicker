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
    public enum Building {
        DIRE_ANCIENT_TOP, DIRE_ANCIENT_BOTTOM, DIRE_BOTTOM_TIER3, DIRE_BOTTOM_TIER2, DIRE_BOTTOM_TIER1, DIRE_MIDDLE_TIER3, DIRE_MIDDLE_TIER2, DIRE_MIDDLE_TIER1, DIRE_TOP_TIER3, DIRE_TOP_TIER2, DIRE_TOP_TIER1, RADIANT_ANCIENT_TOP,
        RADIANT_ANCIENT_BOTTOM, RADIANT_BOTTOM_TIER3, RADIANT_BOTTOM_TIER2, RADIANT_BOTTOM_TIER1, RADIANT_MIDDLE_TIER3, RADIANT_MIDDLE_TIER2, RADIANT_MIDDLE_TIER1, RADIANT_TOP_TIER3, RADIANT_TOP_TIER2, RADIANT_TOP_TIER1};

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
        ImageView team1TowerLogo = (ImageView) rootView.findViewById(R.id.team1towerlogo);
        ImageView team2TowerLogo = (ImageView) rootView.findViewById(R.id.team2towerlogo);
        final ListView team1Team = (ListView) rootView.findViewById(R.id.team1team);
        final ListView team2Team = (ListView) rootView.findViewById(R.id.team2team);
        Switch itemSwitch =  (Switch) rootView.findViewById(R.id.switch3);


        itemSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (game.getScoreboard() != null) {
                    if (b == false) {
                        adapter1.setShowItems(false);
                        adapter2.setShowItems(false);
                        adapter1.notifyDataSetChanged();
                        adapter2.notifyDataSetChanged();
                    } else {
                        adapter1.setShowItems(true);
                        adapter2.setShowItems(true);
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

        int team1stdID =0;
        int team1bigID =0;
        int team2stdID =0;
        int team2bigID =0;
        if(game.getRadiant_team() != null) {
            team1Name.setText(game.getRadiant_team().getTeam_name());
            team1stdID = rootView.getResources().getIdentifier("drawable/team_" + fixString(game.getRadiant_team().getTeam_name()) + "_std", "drawable", getActivity().getPackageName());
            team1bigID = rootView.getResources().getIdentifier("drawable/team_" + fixString(game.getRadiant_team().getTeam_name()) + "_big", "drawable", getActivity().getPackageName());
        }

        if(game.getDire_team() != null) {
            team2Name.setText(game.getDire_team().getTeam_name());
            team2stdID = rootView.getResources().getIdentifier("drawable/team_" + fixString(game.getDire_team().getTeam_name()) + "_std", "drawable", getActivity().getPackageName());
            team2bigID = rootView.getResources().getIdentifier("drawable/team_" + fixString(game.getDire_team().getTeam_name()) + "_big", "drawable", getActivity().getPackageName());
        }

        team1Team.setAdapter(adapter1);
        team2Team.setAdapter(adapter2);


        if(team1stdID == 0) {
            team1Logo.setImageResource(R.drawable.team_default_std);
            team1TowerLogo.setImageResource(R.drawable.team_default_std);
        }
        else {
            team1Logo.setImageResource(team1stdID);
            team1TowerLogo.setImageResource(team1stdID);
        }

        if(team2stdID == 0) {
            team2Logo.setImageResource(R.drawable.team_default_std);
            team2TowerLogo.setImageResource(R.drawable.team_default_std);
        }
        else {
            team2Logo.setImageResource(team2stdID);
            team2TowerLogo.setImageResource(team2stdID);
        }

        team1Image.setImageResource(team1bigID);
        team2Image.setImageResource(team2bigID);


        setupTowers(rootView);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    public void setupTowers(View v) {

        ImageView radiantTopT1 = (ImageView) v.findViewById(R.id.rTopT1);
        ImageView radiantTopT2 = (ImageView) v.findViewById(R.id.rTopT2);
        ImageView radiantTopT3 = (ImageView) v.findViewById(R.id.rTopT3);
        ImageView radiantMidT1 = (ImageView) v.findViewById(R.id.rMidT1);
        ImageView radiantMidT2 = (ImageView) v.findViewById(R.id.rMidT2);
        ImageView radiantMidT3 = (ImageView) v.findViewById(R.id.rMidT3);
        ImageView radiantBotT1 = (ImageView) v.findViewById(R.id.rBotT1);
        ImageView radiantBotT2 = (ImageView) v.findViewById(R.id.rBotT2);
        ImageView radiantBotT3 = (ImageView) v.findViewById(R.id.rBotT3);
        ImageView radiantAncTop = (ImageView) v.findViewById(R.id.rAncTop);
        ImageView radiantAncBot = (ImageView) v.findViewById(R.id.rAncBot);

        ImageView direTopT1 = (ImageView) v.findViewById(R.id.dTopT1);
        ImageView direTopT2 = (ImageView) v.findViewById(R.id.dTopT2);
        ImageView direTopT3 = (ImageView) v.findViewById(R.id.dTopT3);
        ImageView direMidT1 = (ImageView) v.findViewById(R.id.dMidT1);
        ImageView direMidT2 = (ImageView) v.findViewById(R.id.dMidT2);
        ImageView direMidT3 = (ImageView) v.findViewById(R.id.dMidT3);
        ImageView direBotT1 = (ImageView) v.findViewById(R.id.dBotT1);
        ImageView direBotT2 = (ImageView) v.findViewById(R.id.dBotT2);
        ImageView direBotT3 = (ImageView) v.findViewById(R.id.dBotT3);
        ImageView direAncTop = (ImageView) v.findViewById(R.id.dAncTop);
        ImageView direAncBot = (ImageView) v.findViewById(R.id.dAncBot);

        radiantTopT1.setImageResource(R.drawable.tower_rtop_t1);
        radiantTopT2.setImageResource(R.drawable.tower_rtop_t2);
        radiantTopT3.setImageResource(R.drawable.tower_rtop_t3);
        radiantMidT1.setImageResource(R.drawable.tower_rmid_t1);
        radiantMidT2.setImageResource(R.drawable.tower_rmid_t2);
        radiantMidT3.setImageResource(R.drawable.tower_rmid_t3);
        radiantBotT1.setImageResource(R.drawable.tower_rbot_t1);
        radiantBotT2.setImageResource(R.drawable.tower_rbot_t2);
        radiantBotT3.setImageResource(R.drawable.tower_rbot_t3);
        radiantAncTop.setImageResource(R.drawable.tower_ranc_top);
        radiantAncBot.setImageResource(R.drawable.tower_ranc_bot);

        direTopT1.setImageResource(R.drawable.tower_dtop_t1);
        direTopT2.setImageResource(R.drawable.tower_dtop_t2);
        direTopT3.setImageResource(R.drawable.tower_dtop_t3);
        direMidT1.setImageResource(R.drawable.tower_dmid_t1);
        direMidT2.setImageResource(R.drawable.tower_dmid_t2);
        direMidT3.setImageResource(R.drawable.tower_dmid_t3);
        direBotT1.setImageResource(R.drawable.tower_dbot_t1);
        direBotT2.setImageResource(R.drawable.tower_dbot_t2);
        direBotT3.setImageResource(R.drawable.tower_dbot_t3);
        direAncTop.setImageResource(R.drawable.tower_danc_top);
        direAncBot.setImageResource(R.drawable.tower_danc_bot);

        String towersAsBinary = Integer.toBinaryString(game.getTower_state());
        char[] tower_status = towersAsBinary.toCharArray();

        //Compare chars to see if the tower is standing

        //--------------------------------------------
        // Set up Visibility for Radiant Team's Towers

        if(tower_status[Building.RADIANT_TOP_TIER1.ordinal()] == '0')
            radiantTopT1.setVisibility(View.INVISIBLE);

        if(tower_status[Building.RADIANT_TOP_TIER2.ordinal()] == '0')
            radiantTopT2.setVisibility(View.INVISIBLE);

        if(tower_status[Building.RADIANT_TOP_TIER3.ordinal()] == '0')
            radiantTopT3.setVisibility(View.INVISIBLE);

        if(tower_status[Building.RADIANT_MIDDLE_TIER1.ordinal()] == '0')
            radiantMidT1.setVisibility(View.INVISIBLE);

        if(tower_status[Building.RADIANT_MIDDLE_TIER2.ordinal()] == '0')
            radiantMidT2.setVisibility(View.INVISIBLE);

        if(tower_status[Building.RADIANT_MIDDLE_TIER3.ordinal()] == '0')
            radiantMidT3.setVisibility(View.INVISIBLE);

        if(tower_status[Building.RADIANT_BOTTOM_TIER1.ordinal()] == '0')
            radiantBotT1.setVisibility(View.INVISIBLE);

        if(tower_status[Building.RADIANT_BOTTOM_TIER2.ordinal()] == '0')
            radiantBotT2.setVisibility(View.INVISIBLE);

        if(tower_status[Building.RADIANT_BOTTOM_TIER3.ordinal()] == '0')
            radiantBotT3.setVisibility(View.INVISIBLE);

        if(tower_status[Building.RADIANT_ANCIENT_TOP.ordinal()] == '0')
            radiantAncTop.setVisibility(View.INVISIBLE);

        if(tower_status[Building.RADIANT_ANCIENT_BOTTOM.ordinal()] == '0')
            radiantAncBot.setVisibility(View.INVISIBLE);

        //-----------------------------------------
        // Set up Visibility for Dire Team's Towers

        if(tower_status[Building.DIRE_TOP_TIER1.ordinal()] == '0')
            direTopT1.setVisibility(View.INVISIBLE);

        if(tower_status[Building.DIRE_TOP_TIER2.ordinal()] == '0')
            direTopT2.setVisibility(View.INVISIBLE);

        if(tower_status[Building.DIRE_TOP_TIER3.ordinal()] == '0')
            direTopT3.setVisibility(View.INVISIBLE);

        if(tower_status[Building.DIRE_MIDDLE_TIER1.ordinal()] == '0')
            direMidT1.setVisibility(View.INVISIBLE);

        if(tower_status[Building.DIRE_MIDDLE_TIER2.ordinal()] == '0')
            direMidT2.setVisibility(View.INVISIBLE);

        if(tower_status[Building.DIRE_MIDDLE_TIER3.ordinal()] == '0')
            direMidT3.setVisibility(View.INVISIBLE);

        if(tower_status[Building.DIRE_BOTTOM_TIER1.ordinal()] == '0')
            direBotT1.setVisibility(View.INVISIBLE);

        if(tower_status[Building.DIRE_BOTTOM_TIER2.ordinal()] == '0')
            direBotT2.setVisibility(View.INVISIBLE);

        if(tower_status[Building.DIRE_BOTTOM_TIER3.ordinal()] == '0')
            direBotT3.setVisibility(View.INVISIBLE);

        if(tower_status[Building.DIRE_ANCIENT_TOP.ordinal()] == '0')
            direAncTop.setVisibility(View.INVISIBLE);

        if(tower_status[Building.DIRE_ANCIENT_BOTTOM.ordinal()] == '0')
            direAncBot.setVisibility(View.INVISIBLE);
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

