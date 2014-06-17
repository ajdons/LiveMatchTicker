package com.ajdons.livematchticker.app;

        import android.app.ProgressDialog;
        import android.os.AsyncTask;
        import android.os.Handler;
        import android.os.Message;
        import android.support.v4.app.FragmentManager;
        import android.support.v7.app.ActionBarActivity;
        import android.support.v7.app.ActionBar;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.support.v4.widget.DrawerLayout;
        import android.widget.Button;
        import android.widget.ListView;
        import android.widget.Toast;

        import com.ajdons.livematchticker.models.*;
        import com.thoughtworks.xstream.XStream;

        import org.apache.commons.io.IOUtils;

        import java.io.IOException;
        import java.io.InputStream;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.List;


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
    private Handler responseHandler;
    private ProgressDialog progressDialog;
    private ListGamesAdapter adapter;
    private String resultAsXML = "";
    private List<Game> importantGames;
    public static final String MY_KEY = "28594305340A0FD9BD498BF4663E69BC";

    public static final String GET_HERO_IMAGE = "http://cdn.dota2.com/apps/dota2/images/heroes/";//add <hero_name>_sb.png to the end
    public static final String GET_ITEM_IMAGE = "http://cdn.dota2.com/apps/dota2/images/items/"; //add <item_name>_lg.png to the end

    public static final String GET_LIVE_LEAGUE_GAMES = "http://api.steampowered.com/IDOTA2Match_570/GetLiveLeagueGames/v1/?key=" + MY_KEY + "&format=xml";

    public static final String[] PREMIERE_LEAGUES = {"600", "223", "1135", "1157", "1248", "1068", "1229", "1014", "1116", "1418", "1175", "1100", "1284", "1301"};

    //Array of Dota2 Heroes by order of their id #
    public static final String[] DOTA_HEROES = {"unknown", "antimage", "axe", "bane", "bloodseeker", "crystal_maiden", "drow_ranger",
            "earthshaker", "juggernaut", "mirana", "nevermore", "morphling", "phantom_lancer",
            "puck", "pudge", "razor", "sand_king", "storm_spirit", "sven", "tiny", "vengefulspirit",
            "windrunner", "zuus", "kunkka", "UNKNOWN","lina", "lich", "lion", "shadow_shaman", "slardar",
            "tidehunter", "witch_doctor", "riki", "enigma", "tinker", "sniper", "necrolyte",
            "warlock", "beastmaster", "queenofpain", "venomancer", "faceless_void", "skeleton_king", "death_prophet",
            "phantom_assassin", "pugna", "templar_assassin", "viper", "luna", "dragon_knight", "dazzle", "rattletrap",
            "leshrac", "furion", "life_stealer", "dark_seer", "clinkz", "omniknight", "enchantress", "huskar", "night_stalker",
            "broodmother", "bounty_hunter", "weaver", "jakiro", "batrider", "chen", "spectre", "doom_bringer", "ancient_apparition",
            "ursa", "spirit_breaker", "gyrocopter", "alchemist", "invoker", "silencer", "obsidian_destroyer", "lycan", "brewmaster",
            "shadow_demon", "lone_druid", "chaos_knight", "meepo", "treant", "ogre_magi", "undying", "rubick", "disruptor",
            "nyx_assassin", "naga_siren", "keeper_of_the_light", "wisp", "visage", "slark", "medusa", "troll_warlord", "centaur",
            "magnataur", "shredder", "bristleback", "tusk", "skywrath_mage", "abaddon", "elder_titan", "legion_commander", "UNKNOWN",
            "ember_spirit", "earth_spirit", "UNKNOWN", "terrorblade", "phoenix"};
    //Array if Dota2 Items by order of their id #
    public static final String[] DOTA_ITEMS = {"EMPTYITEM","blink","blades_of_attack", "broadsword","chainmail","claymore","helm_of_iron_will","javelin","mithril_hammer","platemail","quarterstaff","quelling_blade","ring_of_protection","gauntlets",
            "slippers","mantle","branches","belt_of_strength","boots_of_elves","robe","circlet","ogre_axe","blade_of_alacrity","staff_of_wizardry","ultimate_orb","gloves","lifesteal","ring_of_regen","sobi_mask",
            "boots","gem","cloak","talisman_of_evasion","cheese", "magic_stick","recipe_magic_wand", "magic_wand","ghost","clarity","flask","dust","bottle","ward_observer","ward_sentry","tango","courier","tpscroll",
            "recipe_travel_boots","travel_boots","recipe_phase_boots","phase_boots","demon_edge","eagle","reaver","relic","hyperstone","ring_of_health", "void_stone","mystic_staff","energy_booster","point_booster","vitality_booster",
            "recipe_power_treads","power_treads","recipe_hand_of_midas", "hand_of_midas","recipe_oblivion_staff","oblivion_staff","recipe_pers","pers","recipe_poor_mans_shield","poor_mans_shield","recipe_bracer","bracer",
            "recipe_wraith_band","wraith_band","recipe_null_talisman","null_talisman","recipe_mekansm","mekansm", "recipe_vladmir","vladmir","EMPTYITEM", "EMPTYITEM", "flying_courier","recipe_buckler","buckler","recipe_ring_of_basilius","ring_of_basilius",
            "recipe_pipe", "pipe","recipe_urn_of_shadows","urn_of_shadows", "recipe_headdress","headdress","recipe_sheepstick","sheepstick","recipe_orchid", "orchid","recipe_cyclone","cyclone","recipe_force_staff","force_staff",
            "recipe_dagon","dagon","recipe_necronomicon","necronomicon","recipe_ultimate_scepter","ultimate_scepter","recipe_refresher","refresher","recipe_assault","assault","recipe_heart","heart","recipe_black_king_bar",
            "black_king_bar","aegis","recipe_shivas_guard","shivas_guard","recipe_bloodstone","bloodstone","recipe_sphere","sphere","recipe_vanguard","vanguard","recipe_blade_mail", "blade_mail", "recipe_soul_booster",
            "soul_booster","recipe_hood_of_defiance","hood_of_defiance","recipe_rapier","rapier","recipe_monkey_king_bar","monkey_king_bar", "recipe_radiance", "radiance","recipe_butterfly","butterfly","recipe_greater_crit",
            "greater_crit","recipe_basher","basher","recipe_bfury","bfury","recipe_manta","manta","recipe_lesser_crit","lesser_crit","recipe_armlet","armlet","invis_sword","recipe_sange_and_yasha","sange_and_yasha","recipe_satanic",
            "satanic","recipe_mjollnir","mjollnir", "recipe_skadi","skadi","recipe_sange","sange", "recipe_helm_of_the_dominator","helm_of_the_dominator","recipe_maelstrom", "maelstrom","recipe_desolator","desolator","recipe_yasha",
            "yasha","recipe_mask_of_madness","mask_of_madness","recipe_diffusal_blade", "diffusal_blade","recipe_ethereal_blade","ethereal_blade","recipe_soul_ring", "soul_ring","recipe_arcane_boots","arcane_boots","orb_of_venom",
            "stout_shield","recipe_invis_sword","recipe_ancient_janggo","ancient_janggo","recipe_medallion_of_courage","medallion_of_courage","smoke_of_deceit","recipe_veil_of_discord", "veil_of_discord","recipe_necronomicon_2",
            "recipe_necronomicon_3", "necronomicon_2","necronomicon_3","recipe_diffusal_blade_2","diffusal_blade_2","recipe_dagon_2","recipe_dagon_3","recipe_dagon_4","recipe_dagon_5","dagon_2","dagon_3","dagon_4","dagon_5", "recipe_rod_of_atos",
            "rod_of_atos","recipe_abyssal_blade", "abyssal_blade", "recipe_heavens_halberd","heavens_halberd","recipe_ring_of_aquila","ring_of_aquila","recipe_tranquil_boots","tranquil_boots", "shadow_amulet"};
   private XStream xstream;

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

        new MySyncClass().execute();

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

            InputStream in = new URL(GET_LIVE_LEAGUE_GAMES).openStream();
            resultAsXML = IOUtils.toString(in);
            IOUtils.closeQuietly(in);

            Result test = new Result();
            test = (Result)xstream.fromXML(resultAsXML);
            System.out.println("There are currently "  + test.getGames().size() + " live games being played.");
            importantGames = new ArrayList<Game>();
            for(Game g : test.getGames()){
                importantGames.add(g);
//                for(int i=0; i<PREMIERE_LEAGUES.length; i++) {
//                    if (PREMIERE_LEAGUES[i].equals(g.getLeague_id())) {
//                        importantGames.add(g);
//                        break;
//                    }
//                    else
                    if(g.getLeague_tier() == 3)
                        importantGames.add(g);
//                }
            }

            adapter = new ListGamesAdapter(getApplicationContext(), importantGames);
        }
        catch (IOException e){
            System.out.println("Problem encountered: ");
            System.out.println("There are currently no live games to display");
        }

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch(position + 1) {
            case 1:
            fragmentManager.beginTransaction()
                    .replace(R.id.container, ListGamesFragment.newInstance(position + 1, adapter))
                    .commit();
                break;

            case 2:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, PrizeTrackerFragment.newInstance(position + 1)).commit();
                break;

        }
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

    public void showProgressDialog() {
        progressDialog = new ProgressDialog(this, R.style.MyTheme);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        progressDialog.show();
    }
    public void dismissProgressDialog() {
        if(progressDialog != null)
            progressDialog.dismiss();
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
        if(id == R.id.action_refresh){
            new MySyncClass().execute();

        }
        return super.onOptionsItemSelected(item);
    }

    public class MySyncClass extends AsyncTask<String, Void, Object> {


        @Override
        protected void onPreExecute() {
            showProgressDialog();
        }

        @Override
        protected String doInBackground(String... strings) {
            makeAPICall();
            return "Done";
        }

        @Override
        protected void onPostExecute(Object result) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ListGamesFragment.newInstance(1, adapter))
                    .commit();
            dismissProgressDialog();
        }
    }

}
