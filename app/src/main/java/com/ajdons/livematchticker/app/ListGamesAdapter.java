package com.ajdons.livematchticker.app;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.ajdons.livematchticker.app.MainActivity;

import com.ajdons.livematchticker.models.Game;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adamdonegan on 18/05/2014.
 */
public class ListGamesAdapter extends ArrayAdapter<Game> {
    private final Context context;

    public List<Game> getGamesArrayList() {
        return gamesArrayList;
    }

    private final List<Game> gamesArrayList;

    public ListGamesAdapter(Context context, List<Game> gamesArrayList) {
      super(context, R.layout.row, gamesArrayList);

        this.context = context;
        this.gamesArrayList = gamesArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.row, parent, false);

        // 3. Get initialize rowView elements
        TextView team1Name = (TextView) rowView.findViewById(R.id.textView1);
        TextView team2Name = (TextView) rowView.findViewById(R.id.textView2);

        TextView team1Score = (TextView) rowView.findViewById(R.id.textView3);
        TextView team2Score = (TextView) rowView.findViewById(R.id.textView4);

        ImageView team1Logo = (ImageView) rowView.findViewById(R.id.imageView1);
        ImageView team2Logo = (ImageView) rowView.findViewById(R.id.imageView2);


        // 4. Set the text and images for elements
        if(gamesArrayList.get(position).getRadiant_team() != null)
            team1Name.setText(gamesArrayList.get(position).getRadiant_team().getTeam_name());
        else
            team1Name.setText("");

        if(gamesArrayList.get(position).getDire_team() != null)
            team2Name.setText(gamesArrayList.get(position).getDire_team().getTeam_name());
        else
            team2Name.setText("");

        if(gamesArrayList.get(position).getScoreboard() == null) {
            team1Score.setText(String.valueOf(0));
            team2Score.setText(String.valueOf(0));
        }
        else {
            team1Score.setText(String.valueOf(gamesArrayList.get(position).getScoreboard().getRadiant().getScore()));
            team2Score.setText(String.valueOf(gamesArrayList.get(position).getScoreboard().getDire().getScore()));
        }



        int team1ID = context.getResources().getIdentifier("drawable/team_" + fixString(team1Name.getText().toString()) + "_std", "drawable", context.getPackageName());
        int team2ID = context.getResources().getIdentifier("drawable/team_" + fixString(team2Name.getText().toString()) + "_std", "drawable", context.getPackageName());

        if(team1ID != 0)
            team1Logo.setImageResource(team1ID);
        else
            team1Logo.setImageResource(R.drawable.team_default_std);

        if(team2ID != 0)
            team2Logo.setImageResource(team2ID);
        else
            team2Logo.setImageResource(R.drawable.team_default_std);

        // 5. return the rowView
        return rowView;
    }

    public String fixString(String s){
        String temp = s;
        return temp.toLowerCase().replaceAll("[-_!|/., ?]", "");

    }

}
