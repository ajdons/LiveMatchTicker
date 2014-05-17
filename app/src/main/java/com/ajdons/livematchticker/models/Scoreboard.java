package com.ajdons.livematchticker.models;

/**
 * Created by adamdonegan on 15/05/2014.
 */
public class Scoreboard {

    private float duration;
    private float roshan_respawn_timer;
    private Team radiant;
    private Team dire;

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public float getRoshan_respawn_timer() {
        return roshan_respawn_timer;
    }

    public void setRoshan_respawn_timer(float roshan_respawn_timer) {
        this.roshan_respawn_timer = roshan_respawn_timer;
    }

    public Team getRadiant() {
        return radiant;
    }

    public void setRadiant(Team radiant) {
        this.radiant = radiant;
    }

    public Team getDire() {
        return dire;
    }

    public void setDire(Team dire) {
        this.dire = dire;
    }

}
