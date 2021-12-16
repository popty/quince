package com.quince.service;

import com.quince.exception.GameNotFoundException;
import com.quince.model.Board;
import com.quince.model.Game;
import com.quince.model.Ladder;
import com.quince.model.Snake;
import com.quince.repo.GameRepo;
import org.apache.commons.lang3.RandomUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class BoardInitService {
    private Game game;
    private Board board;
    private HashMap<Integer, Snake> snakes;
    private HashMap<Integer, Ladder> ladders;
    private int numberOfSnakes;
    private int numberOfLadders;

    public BoardInitService(String gameId, GameRepo gameRepo) {
        try {
            this.game = gameRepo.getGame(gameId);
            if(this.game == null) throw new GameNotFoundException();
        }catch(GameNotFoundException e){
            System.out.println("A game with given Id is not found. ");
        }

        this.board = game.getBoard();
        this.snakes = game.getSnakes();
        this.ladders = game.getLadders();
        this.numberOfLadders = game.getNumberOfLadders();
        this.numberOfSnakes = game.getNumberOfSnakes();
        initBoard();
    }

    private void initBoard() {
        Set<String> slSet = new HashSet<>();

        for(int i = 0; i<numberOfSnakes; i++){
            while(true){
                int start = RandomUtils.nextInt(board.getStart(), board.getEnd());
                int end = RandomUtils.nextInt(board.getStart(), board.getEnd());
                if(end >= start)
                    continue;
                String startEnd = start + " "+ end;
                if(!slSet.contains(startEnd)){
                    Snake snake = new Snake(start, end);
                    slSet.add(startEnd);
                    snakes.put(start, snake);
                    break;
                }
            }
        }

        for(int i = 0; i<numberOfLadders; i++){
            while(true){
                int start = RandomUtils.nextInt(board.getStart(), board.getEnd());
                int end = RandomUtils.nextInt(board.getStart(), board.getEnd());
                if(start >= end)
                    continue;
                String startEnd = start + " "+ end;
                if(!slSet.contains(startEnd)){
                    Ladder ladder = new Ladder(start, end);
                    slSet.add(startEnd);
                    ladders.put(start, ladder);
                    break;
                }
            }
        }
    }

}
