package com.quince.model;

import lombok.Getter;

@Getter
public class Board {
    private final int start;
    private final int end;
    private final int size;

    public Board(int size){
        this.start = 1;
        this.size = size;
        this.end = start+size -1;
    }
}
