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



    //more specific parameters
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

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeath() {
        return death;
    }

    public void setDeath(int death) {
        this.death = death;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getLast_hits() {
        return last_hits;
    }

    public void setLast_hits(int last_hits) {
        this.last_hits = last_hits;
    }

    public int getDenies() {
        return denies;
    }

    public void setDenies(int denies) {
        this.denies = denies;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getGold_per_min() {
        return gold_per_min;
    }

    public void setGold_per_min(int gold_per_min) {
        this.gold_per_min = gold_per_min;
    }

    public int getXp_per_min() {
        return xp_per_min;
    }

    public void setXp_per_min(int xp_per_min) {
        this.xp_per_min = xp_per_min;
    }

    public int getUltimate_state() {
        return ultimate_state;
    }

    public void setUltimate_state(int ultimate_state) {
        this.ultimate_state = ultimate_state;
    }

    public int getUltimate_cooldown() {
        return ultimate_cooldown;
    }

    public void setUltimate_cooldown(int ultimate_cooldown) {
        this.ultimate_cooldown = ultimate_cooldown;
    }

    public int getItem0() {
        return item0;
    }

    public void setItem0(int item0) {
        this.item0 = item0;
    }

    public int getItem1() {
        return item1;
    }

    public void setItem1(int item1) {
        this.item1 = item1;
    }

    public int getItem2() {
        return item2;
    }

    public void setItem2(int item2) {
        this.item2 = item2;
    }

    public int getItem3() {
        return item3;
    }

    public void setItem3(int item3) {
        this.item3 = item3;
    }

    public int getItem4() {
        return item4;
    }

    public void setItem4(int item4) {
        this.item4 = item4;
    }

    public int getItem5() {
        return item5;
    }

    public void setItem5(int item5) {
        this.item5 = item5;
    }

    public int getRespawn_timer() {
        return respawn_timer;
    }

    public void setRespawn_timer(int respawn_timer) {
        this.respawn_timer = respawn_timer;
    }

    public float getPosition_x() {
        return position_x;
    }

    public void setPosition_x(float position_x) {
        this.position_x = position_x;
    }

    public float getPosition_y() {
        return position_y;
    }

    public void setPosition_y(float position_y) {
        this.position_y = position_y;
    }

    public void printInfo(){
        System.out.println(getName());
        System.out.println(getAccount_id());
        System.out.println(getHero_id());
        System.out.println(getTeam());

    }

}
