package com.mygdx.game;

import java.util.Arrays;

public class RandyRandom {

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max + 1 - min)) + min);
    }

    public static void main(String[] args){
        int[] array = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < 1000; i++){
            array[RandyRandom.getRandomNumber(0, 9)] += 1;
        }
        System.out.println(Arrays.toString(array));
    }

}
