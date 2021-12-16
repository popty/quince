package com.quince.repo;

import com.quince.model.Game;

import java.util.HashMap;

public class GameRepo {
    private HashMap<String, Game> gameHashMap;

    public GameRepo(){
        gameHashMap = new HashMap<>();
    }

    public void addOrUpdateGame(String gameId, Game game){
        gameHashMap.put(gameId, game);
    }

    public Game getGame(String gameId){
        return gameHashMap.getOrDefault(gameId, null);
    }

}
