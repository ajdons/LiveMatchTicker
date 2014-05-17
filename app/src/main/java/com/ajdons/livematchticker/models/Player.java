package com.ajdons.livematchticker.models;

/**
 * Created by adamdonegan on 15/05/2014.
 */
public class Player {

    //simple player
    private String account_id;
    private String name;
    private String hero_id;
    private int team;

    //more specific player
    private int player_slot;
    private int kills;
    private int death;
    private int assists;
    private int last_hits;
    private int denies;
    private int gold;
    private int level;
    private int gold_per_min;
    private int xp_per_min;
    private int ultimate_state;
    private int ultimate_cooldown;
    private int item0;
    private int item1;
    private int item2;
    private int item3;
    private int item4;
    private int item5;
    private int respawn_timer;
    private float position_x;
    private float position_y;

    public Player(){
        this.account_id = "unknown";
        this.name = "unknown";
        this.hero_id = "unknown";
        this.team = 0;

        this.player_slot = 0;
        this.kills = 0;
        this.death = 0;
        this.assists = 0;
        this.last_hits = 0;
        this.denies = 0;
        this.gold = 0;
        this.level = 0;
        this.gold_per_min = 0;
        this.gold = 0;
        this.xp_per_min = 0;
        this.ultimate_state = 0;
        this.ultimate_cooldown = 0;
        this.item0 = 0;
        this.item1 = 0;
        this.item2 = 0;
        this.item3 = 0;
        this.item4 = 0;
        this.item5 = 0;
        this.respawn_timer = 0;
        this.position_x = 0;
        this.position_y = 0;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHero_id() {
        return hero_id;
    }

    public void setHero_id(String hero_id) {
        this.hero_id = hero_id;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public void printInfo(){
        System.out.println(getName());
        System.out.println(getAccount_id());
        System.out.println(getHero_id());
        System.out.println(getTeam());

    }

}
