package com.quince.service;

import com.quince.exception.GameNotFoundException;
import com.quince.model.*;
import com.quince.repo.GameRepo;
import com.quince.strategy.WinningStrategy;

import java.util.HashMap;
import java.util.Queue;

public class GamePlayService1 implements WinningStrategy {
    private Game game;
    private Dice dice;
    private Board board;
    private Queue<Player> players;
    private HashMap<Integer, Snake> snakes;
    private HashMap<Integer, Ladder> ladders;

    public GamePlayService1(String gameId, GameRepo gameRepo) {
        try {
            this.game = gameRepo.getGame(gameId);
            if(this.game == null) throw new GameNotFoundException();
        }catch(GameNotFoundException e){
            System.out.println("A game with given Id is not found. ");
        }

        this.board = game.getBoard();
        this.dice = game.getDice();
        this.players = game.getPlayers();
        this.snakes = game.getSnakes();
        this.ladders = game.getLadders();

    }

    @Override
    public void playGame() {
        while(true){
            Player player = players.poll();
            int val = dice.roll();
            int newPosition = player.getPosition() + val;
            if(newPosition > board.getEnd()){
                player.setPosition(player.getPosition());
                players.offer(player);
            }else{
                player.setPosition(getNewPosition(newPosition));
                if(player.getPosition() == board.getEnd()){
                    player.setWon(true);
                    System.out.println("Player "+ player.getName()+ " has won the game.");
                    break;
                }else{
                    System.out.println("Player "+ player.getName()+ " has moved to position "+ player.getPosition());
                    players.offer(player);
                }
            }
        }
    }

    private int getNewPosition(int newPosition) {
        if(snakes.containsKey(newPosition)){
            return snakes.get(newPosition).getSnakeTail();
        }

        if(ladders.containsKey(newPosition)){
            return ladders.get(newPosition).getEnd();
        }

        return newPosition;
    }
}
