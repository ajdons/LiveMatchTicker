package com.ajdons.livematchticker.models;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by adamdonegan on 16/05/2014.
 */
public class Result {

    private List<Game> games;
    private int status;

    public Result() {
        games = new ArrayList<Game>();
        status = 0;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
