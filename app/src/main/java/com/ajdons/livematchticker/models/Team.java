package com.ajdons.livematchticker.models;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by adamdonegan on 15/05/2014.
 */
public class Team {

    //simple team
    private String team_name;
    private String team_id;
    private String team_logo;
    private boolean complete;

    //more specific team
    private int score;
    private List<Player> players;

    public Team(){
        this.team_name = "unknown";
        this.team_id = "unknown";
        this.team_logo = "unknown";
        this.complete = false;

        this.score = 0;
        this.players = new ArrayList<Player>();
    }

    public Team(int score, List<Player> players) {
        this.score = score;
        this.players = players;
    }

    public Team(String team_name, String team_id, String team_logo, boolean complete) {
        this.team_name = team_name;
        this.team_id = team_id;
        this.team_logo = team_logo;
        this.complete = complete;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public String getTeam_logo() {
        return team_logo;
    }

    public void setTeam_logo(String team_logo) {
        this.team_logo = team_logo;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

}
