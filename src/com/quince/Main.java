package com.quince;

import com.quince.model.Game;
import com.quince.model.Player;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
       Scanner sc = new Scanner(System.in);
        System.out.println("Enter board size: ");
        int boardSize = sc.nextInt();
        System.out.println("Enter number of snakes: ");
        int numSnakes = sc.nextInt();
        System.out.println("Enter number of ladders: ");
        int numLadders = sc.nextInt();
        System.out.println("Enter number of players: ");
        int numPlayers = sc.nextInt();
        System.out.println("Enter winning strategy (1 or 2): ");
        int winningStrategy = sc.nextInt();

        Game game = new Game(boardSize, numSnakes, numLadders);

        for(int i = 0; i<numPlayers; i++){
            System.out.println("Enter Player name: ");
            String name = sc.next();
            Player player = new Player(name);
            game.addPlayer(player);
        }

        game.playGame(winningStrategy);
    }
}
