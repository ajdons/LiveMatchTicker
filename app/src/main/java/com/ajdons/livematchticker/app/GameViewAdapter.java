package com.ajdons.livematchticker.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajdons.livematchticker.models.Game;
import com.ajdons.livematchticker.models.Player;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adamdonegan on 26/05/2014.
 */
public class GameViewAdapter extends ArrayAdapter<Player> {
    private final Context context;

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


    public static final String[] DOTA_HEROES = {"unknown", "antimage", "axe", "bane", "bloodseeker", "crystal_maiden", "drow_ranger",
            "earthshaker", "juggernaut", "mirana", "nevermore", "morphling", "phantom_lancer",
            "puck", "pudge", "razor", "sand_king", "storm_spirit", "sven", "tiny", "vengefulspirit",
            "windrunner", "zuus", "kunkka", "UNKNOWN","lina", "lion", "shadow_shaman", "slardar",
            "tidehunter", "witch_doctor",  "lich","riki", "enigma", "tinker", "sniper", "necrolyte",
            "warlock", "beastmaster", "queenofpain", "venomancer", "faceless_void", "skeleton_king", "death_prophet",
            "phantom_assassin", "pugna", "templar_assassin", "viper", "luna", "dragon_knight", "dazzle", "rattletrap",
            "leshrac", "furion", "life_stealer", "dark_seer", "clinkz", "omniknight", "enchantress", "huskar", "night_stalker",
            "broodmother", "bounty_hunter", "weaver", "jakiro", "batrider", "chen", "spectre",  "ancient_apparition", "doom_bringer",
            "ursa", "spirit_breaker", "gyrocopter", "alchemist", "invoker", "silencer", "obsidian_destroyer", "lycan", "brewmaster",
            "shadow_demon", "lone_druid", "chaos_knight", "meepo", "treant", "ogre_magi", "undying", "rubick", "disruptor",
            "nyx_assassin", "naga_siren", "keeper_of_the_light", "wisp", "visage", "slark", "medusa", "troll_warlord", "centaur",
            "magnataur", "shredder", "bristleback", "tusk", "skywrath_mage", "abaddon", "elder_titan", "legion_commander", "UNKNOWN",
            "ember_spirit", "earth_spirit", "UNKNOWN", "terrorblade", "phoenix"};

    public List<Player> getPlayersArrayList() {
        return playersArrayList;
    }

    private final List<Player> playersArrayList;
    private final Game theGame;



    public void setShowItems(boolean showItems) {
        this.showItems = showItems;
    }

    private boolean showItems;

    public GameViewAdapter(Context context, List<Player> playersList, Game theGame, boolean showItems) {
        super(context, R.layout.player_row, playersList);

        this.context = context;
        this.playersArrayList = playersList;
        this.theGame = theGame;
        this.showItems = showItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.player_row, parent, false);

        // 3. Get initialize rowView elements
        ImageView heroImage = (ImageView) rowView.findViewById(R.id.heroImage);
        TextView playerName = (TextView) rowView.findViewById(R.id.name);
        TextView playerKills = (TextView) rowView.findViewById(R.id.kills);
        TextView playerDeaths = (TextView) rowView.findViewById(R.id.deaths);
        TextView playerAssists = (TextView) rowView.findViewById(R.id.assists);
        TextView playerLHD = (TextView) rowView.findViewById(R.id.lasthitsdenies);
        TextView playerGold = (TextView) rowView.findViewById(R.id.gold);
        ImageView item1Image = (ImageView) rowView.findViewById(R.id.item1);
        ImageView item2Image = (ImageView) rowView.findViewById(R.id.item2);
        ImageView item3Image = (ImageView) rowView.findViewById(R.id.item3);
        ImageView item4Image = (ImageView) rowView.findViewById(R.id.item4);
        ImageView item5Image = (ImageView) rowView.findViewById(R.id.item5);
        ImageView item6Image = (ImageView) rowView.findViewById(R.id.item6);

        //Find player's screen name based on their Account ID
        for (Player p : theGame.getPlayers()) {
            if (p.getAccount_id().equals(playersArrayList.get(position).getAccount_id()))
                playerName.setText(p.getName());
        }
        playerName.setSelected(true);

        playerKills.setText(String.valueOf(playersArrayList.get(position).getKills()));
        playerDeaths.setText(String.valueOf(playersArrayList.get(position).getDeath()));
        playerAssists.setText(String.valueOf(playersArrayList.get(position).getAssists()));
        playerLHD.setText(String.valueOf(playersArrayList.get(position).getLast_hits()) + "/" + String.valueOf(playersArrayList.get(position).getDenies()));
        playerGold.setText(String.valueOf(playersArrayList.get(position).getGold()));

        int heroID;
        try {
            heroID = context.getResources().getIdentifier("drawable/" + DOTA_HEROES[Integer.parseInt(playersArrayList.get(position).getHero_id())] + "_mini", "drawable", context.getPackageName());
        } catch (ArrayIndexOutOfBoundsException e) {
            heroID = 0;
        }
        heroImage.setImageResource(heroID);

        int item1ID = 0;
        int item2ID = 0;
        int item3ID = 0;
        int item4ID = 0;
        int item5ID = 0;
        int item6ID = 0;
        try {
            item1ID = context.getResources().getIdentifier("drawable/" + DOTA_ITEMS[playersArrayList.get(position).getItem0()] + "_lg", "drawable", context.getPackageName());
            item2ID = context.getResources().getIdentifier("drawable/" + DOTA_ITEMS[playersArrayList.get(position).getItem1()] + "_lg", "drawable", context.getPackageName());
            item3ID = context.getResources().getIdentifier("drawable/" + DOTA_ITEMS[playersArrayList.get(position).getItem2()] + "_lg", "drawable", context.getPackageName());
            item4ID = context.getResources().getIdentifier("drawable/" + DOTA_ITEMS[playersArrayList.get(position).getItem3()] + "_lg", "drawable", context.getPackageName());
            item5ID = context.getResources().getIdentifier("drawable/" + DOTA_ITEMS[playersArrayList.get(position).getItem4()] + "_lg", "drawable", context.getPackageName());
            item6ID = context.getResources().getIdentifier("drawable/" + DOTA_ITEMS[playersArrayList.get(position).getItem5()] + "_lg", "drawable", context.getPackageName());
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        item1Image.setImageResource(item1ID);
        item2Image.setImageResource(item2ID);
        item3Image.setImageResource(item3ID);
        item4Image.setImageResource(item4ID);
        item5Image.setImageResource(item5ID);
        item6Image.setImageResource(item6ID);

        //If the item is a recipe item, just show the default recipe image
        //Ignore, StringIndexOutOfBounds, it just means the item name is shorter than "recipe"
        try {
            if (DOTA_ITEMS[playersArrayList.get(position).getItem0()].substring(0, 7).equals("recipe"))
                item1Image.setImageResource(R.drawable.recipe_lg);
            if (DOTA_ITEMS[playersArrayList.get(position).getItem1()].substring(0, 7).equals("recipe"))
                item2Image.setImageResource(R.drawable.recipe_lg);
            if (DOTA_ITEMS[playersArrayList.get(position).getItem2()].substring(0, 7).equals("recipe"))
                item3Image.setImageResource(R.drawable.recipe_lg);
            if (DOTA_ITEMS[playersArrayList.get(position).getItem3()].substring(0, 7).equals("recipe"))
                item4Image.setImageResource(R.drawable.recipe_lg);
            if (DOTA_ITEMS[playersArrayList.get(position).getItem4()].substring(0, 7).equals("recipe"))
                item5Image.setImageResource(R.drawable.recipe_lg);
            if (DOTA_ITEMS[playersArrayList.get(position).getItem5()].substring(0, 7).equals("recipe"))
                item6Image.setImageResource(R.drawable.recipe_lg);
        }
        catch(StringIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        //Visibility Rules for toggle switch

        //If show items is toggled on, show items, hide stats
        if(showItems){
            item1Image.setVisibility(View.VISIBLE);
            item2Image.setVisibility(View.VISIBLE);
            item3Image.setVisibility(View.VISIBLE);
            item4Image.setVisibility(View.VISIBLE);
            item5Image.setVisibility(View.VISIBLE);
            item6Image.setVisibility(View.VISIBLE);

            playerKills.setVisibility(View.INVISIBLE);
            playerAssists.setVisibility(View.INVISIBLE);
            playerDeaths.setVisibility(View.INVISIBLE);
            playerLHD.setVisibility(View.INVISIBLE);
            playerGold.setVisibility(View.INVISIBLE);
        }

        //If show items is toggled off, show stats, hide items
        if(!showItems){
            playerKills.setVisibility(View.VISIBLE);
            playerAssists.setVisibility(View.VISIBLE);
            playerDeaths.setVisibility(View.VISIBLE);
            playerLHD.setVisibility(View.VISIBLE);
            playerGold.setVisibility(View.VISIBLE);

            item1Image.setVisibility(View.INVISIBLE);
            item2Image.setVisibility(View.INVISIBLE);
            item3Image.setVisibility(View.INVISIBLE);
            item4Image.setVisibility(View.INVISIBLE);
            item5Image.setVisibility(View.INVISIBLE);
            item6Image.setVisibility(View.INVISIBLE);
        }
        //5. return the rowView
        return rowView;
    }

}
