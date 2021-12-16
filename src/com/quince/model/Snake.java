package com.quince.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class Snake {
    private int snakeHead;
    private int snakeTail;

    public Snake(int snakeHead, int snakeTail){
        this.snakeHead = snakeHead;
        this.snakeTail = snakeTail;
    }

}
