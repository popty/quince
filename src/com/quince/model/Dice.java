package com.quince.model;

import org.apache.commons.lang3.RandomUtils;

public class Dice {
    private int minVal;
    private int maxVal;

    public Dice(int minVal, int maxVal){
        this.minVal = minVal;
        this.maxVal = maxVal;
    }

    public int roll(){return RandomUtils.nextInt(minVal, maxVal);
    }

}
