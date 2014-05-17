package com.ajdons.livematchticker.models;

/**
 * Created by adamdonegan on 15/05/2014.
 */
public class League {

    private String name;
    private String leagueid;
    private String description;
    private String tournament_url;
    private String itemdef;

    public League(){
        this.name = "unknown";
        this.leagueid = "unknown";
        this.description = "unknown";
        this.tournament_url = "unknown";
        this.itemdef = "unknown";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeagueid() {
        return leagueid;
    }

    public void setLeagueid(String leagueid) {
        this.leagueid = leagueid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTournament_url() {
        return tournament_url;
    }

    public void setTournament_url(String tournament_url) {
        this.tournament_url = tournament_url;
    }

    public String getItemdef() {
        return itemdef;
    }

    public void setItemdef(String itemdef) {
        this.itemdef = itemdef;
    }


}
