package com.mygdx.game;

import com.badlogic.gdx.input.GestureDetector;

public class Equipment extends Items {
    slots slot;

    enum slots {
        BOOTS, CHEST, GLOVES, HEAD, LEGS, RING1, RING2,
        SHOULDERS, TABARD, NECKLACE, PRIMARY, SECONDARY
    }
    public Equipment(String name, String fileName, slots slot) {
        super(name, fileName);
        this.slot = slot;

    }

    public slots getSlot() {
        return slot;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "slot=" + slot +
                ", name='" + name + '\'' +
                '}';
    }
}
