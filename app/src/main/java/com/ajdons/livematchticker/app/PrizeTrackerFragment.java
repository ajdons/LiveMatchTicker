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
import com.ajdons.livematchticker.models.Result;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by adamdonegan on 24/05/2014.
 */
public class PrizeTrackerFragment  extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private String money = "";
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PrizeTrackerFragment newInstance(int sectionNumber) {
        PrizeTrackerFragment fragment = new PrizeTrackerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public PrizeTrackerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_prizetracker, container, false);
        ImageView ti4Image = (ImageView) rootView.findViewById(R.id.ti4image);
        TextView prizePool = (TextView) rootView.findViewById(R.id.prizepool);


        int ti4ID = rootView.getResources().getIdentifier("drawable/" + "ti4logo", "drawable", getActivity().getPackageName());
        ti4Image.setImageResource(ti4ID);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                getPrizePool();
            }
        });
        thread.start();
        try {
            thread.join();
            prizePool.setText(money);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    public void getPrizePool() {
        System.out.println("Trying to make api call.....");
        String result = "";
        List<String> splittedStrings = new ArrayList<String>();
        try {

            InputStream in = new URL("http://www.dota2.com/jsfeed/intlprizepool").openStream();
            result = IOUtils.toString(in);
            IOUtils.closeQuietly(in);
            splittedStrings = Arrays.asList(result.split(":"));


        } catch (IOException e) {
            System.out.println("Problem encountered: ");
            System.out.println("There are currently no live games to display");
        }
        money = splittedStrings.get(1).substring(0, splittedStrings.get(1).length()-1);
        if(money.length()<=7){
            String temp = "$";
            temp = temp + money.substring(0, 1);
            temp = temp + ",";
            temp = temp + money.substring(1, 4);
            temp = temp + ",";
            temp = temp + money.substring(4, 7);
            money = temp;

        }
        else
        money = "$" + splittedStrings.get(1).substring(0, splittedStrings.get(1).length()-1);
    }

}
