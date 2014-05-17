package com.ajdons.livematchticker.models;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by adamdonegan on 16/05/2014.
 */
public class Result {

    private List<Game> games;

    public Result() {
        games = new ArrayList<Game>();
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
