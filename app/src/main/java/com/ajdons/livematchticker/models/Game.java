package com.ajdons.livematchticker.models;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adamdonegan on 15/05/2014.
 */
public class Game {

    private List<Player> players;
    private Team radiant_team;
    private Team dire_team;
    private String lobby_id;
    private int spectators;
    private int tower_state;
    private String league_id;
    private int stream_delay_s;

    private int league_tier;

    public int getMatch_id() {
        return match_id;
    }

    public void setMatch_id(int match_id) {
        this.match_id = match_id;
    }

    private int match_id;



    private int series_type;
    private int radiant_series_wins;
    private int dire_series_wins;
    private Scoreboard scoreboard;

    public Game() {
        this.players = new ArrayList<Player>();
        this.radiant_team = new Team();
        this.dire_team = new Team();
        this.lobby_id = "unknown";
        this.spectators = 0;
        this.tower_state = 0;
        this.league_id = "unknown";
        this.stream_delay_s = 0;
        this.scoreboard = new Scoreboard();
        this.league_tier = 0;
    }

    public Game(List<Player> players, Team radiant_team, Team dire_team, String lobby_id, int spectators, int tower_state, String league_id, int stream_delay_s, Scoreboard scoreboard) {
        this.players = players;
        this.radiant_team = radiant_team;
        this.dire_team = dire_team;
        this.lobby_id = lobby_id;
        this.spectators = spectators;
        this.tower_state = tower_state;
        this.league_id = league_id;
        this.stream_delay_s = stream_delay_s;
        this.scoreboard = scoreboard;
    }

    public int getSeries_type() {
        return series_type;
    }

    public void setSeries_type(int series_type) {
        this.series_type = series_type;
    }

    public int getRadiant_series_wins() {
        return radiant_series_wins;
    }

    public void setRadiant_series_wins(int radiant_series_wins) {
        this.radiant_series_wins = radiant_series_wins;
    }

    public int getDire_series_wins() {
        return dire_series_wins;
    }

    public void setDire_series_wins(int dire_series_wins) {
        this.dire_series_wins = dire_series_wins;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Team getRadiant_team() {
        return radiant_team;
    }

    public void setRadiant_team(Team radiant_team) {
        this.radiant_team = radiant_team;
    }

    public Team getDire_team() {
        return dire_team;
    }

    public void setDire_team(Team dire_team) {
        this.dire_team = dire_team;
    }

    public String getLobby_id() {
        return lobby_id;
    }

    public void setLobby_id(String lobby_id) {
        this.lobby_id = lobby_id;
    }

    public int getSpectators() {
        return spectators;
    }

    public void setSpectators(int spectators) {
        this.spectators = spectators;
    }

    public int getTower_state() {
        return tower_state;
    }

    public void setTower_state(int tower_state) {
        this.tower_state = tower_state;
    }

    public String getLeague_id() {
        return league_id;
    }

    public void setLeague_id(String league_id) {
        this.league_id = league_id;
    }

    public int getStream_delay_s() {
        return stream_delay_s;
    }

    public void setStream_delay_s(int stream_delay_s) {
        this.stream_delay_s = stream_delay_s;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public void setScoreboard(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    public int getLeague_tier() {
        return league_tier;
    }

    public void setLeague_tier(int league_tier) {
        this.league_tier = league_tier;
    }
}
