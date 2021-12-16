package com.quince.model;

import com.quince.repo.GameRepo;
import com.quince.service.BoardInitService;
import com.quince.service.GamePlayService1;
import com.quince.service.GamePlayService2;
import lombok.Getter;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.UUID;

@Getter
public class Game {
    private final String gameId;
    private final int numberOfSnakes;
    private final int numberOfLadders;
    private final GameRepo gameRepo;
    private Board board;
    private Dice dice;

    private final HashMap<Integer, Snake> snakes;
    private final HashMap<Integer, Ladder> ladders;
    private final Queue<Player> players;

    private BoardInitService boardInitService;
    private GamePlayService1 gamePlayService1;
    private GamePlayService2 gamePlayService2;

    public Game(int boardSize, int numberOfSnakes, int numberOfLadders){
        this.gameId = UUID.randomUUID().toString();
        this.numberOfSnakes = numberOfSnakes;
        this.numberOfLadders = numberOfLadders;
        this.board = new Board(boardSize);
        this.dice = new Dice(1, 6);
        this.snakes = new HashMap<>();
        this.ladders = new HashMap<>();
        this.players = new ArrayDeque<>();
        this.gameRepo = new GameRepo();//getInstance()
        gameRepo.addOrUpdateGame(gameId, this);
        this.boardInitService = new BoardInitService(gameId, gameRepo);
        this.gamePlayService1 = new GamePlayService1(gameId, gameRepo);
        this.gamePlayService2 = new GamePlayService2(gameId, gameRepo);

    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void playGame(int strategy){
        if(strategy == 1)
            gamePlayService1.playGame();
        else
            gamePlayService2.playGame();
    }




}
