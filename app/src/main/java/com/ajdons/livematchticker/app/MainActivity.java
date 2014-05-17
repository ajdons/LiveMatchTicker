package com.ajdons.livematchticker.app;

        import android.app.Activity;
        import android.support.v7.app.ActionBarActivity;
        import android.support.v7.app.ActionBar;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.content.Context;
        import android.os.Build;
        import android.os.Bundle;
        import android.text.method.ScrollingMovementMethod;
        import android.view.Gravity;
        import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.ViewGroup;
        import android.support.v4.widget.DrawerLayout;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.TextView;
        import com.ajdons.livematchticker.models.*;
        import com.thoughtworks.xstream.XStream;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.net.URL;
        import java.net.URLConnection;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private Button goButton;
    private TextView textView;
    private String resultAsXML = "";
    public static final String MY_KEY = "28594305340A0FD9BD498BF4663E69BC";
    public static final String GET_LIVE_LEAGUE_GAMES = "http://api.steampowered.com/IDOTA2Match_570/GetLiveLeagueGames/v1/?key=" + MY_KEY + "&format=xml";
    private XStream xstream;
    //Test comment to see if i can commit and push...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xstream = new XStream();
        xstream.alias("game", Game.class);
        xstream.alias("league", League.class);
        xstream.alias("player", Player.class);
        xstream.alias("scoreboard", Scoreboard.class);
        xstream.alias("team", Team.class);
        xstream.alias("result", Result.class);

        goButton = (Button) findViewById(R.id.button1);
        textView = (TextView) findViewById(R.id.textView1);
        textView.setMovementMethod(new ScrollingMovementMethod());
        goButton.setText("GO");
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        makeAPICall();
                    }
                });
                thread.start();
            }
        });

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    public void makeAPICall() {
        System.out.println("Trying to make api call.....");
        try{
            URL url = new URL(GET_LIVE_LEAGUE_GAMES);

            // read text returned by server
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;
            //discard first 2 lines
            in.readLine();
            in.readLine();
            while ((line = in.readLine()) != null) {
                resultAsXML = resultAsXML + line + "\n";

            }
            in.close();

            Result result = new Result();
            result = (Result)xstream.fromXML(resultAsXML);
            System.out.println("There are currently "  + result.getGames().size() + " live games being played.");
            for(Game g : result.getGames())
                System.out.println(g.getRadiant_team().getTeam_name() + " v.s. " + g.getDire_team().getTeam_name());
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView.setText(resultAsXML);
                }
            });

        }
        catch (IOException e){
            System.out.println("Problem encountered: " + e);
            System.out.println("It is likely that there are currently no live games to display!  ");
        }

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
