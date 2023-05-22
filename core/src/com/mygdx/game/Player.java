package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Player extends Sprite {
    private String name;

    enum backStoriesEnum {
        FARMERSON,
        KING,
        SURGEON,
        HUNTERKID,
        KNEE,
        SAILOR,
        TOWNGUARD,
        DWARF,
        RAT,
        APPRENTICE,
        TELEPORT,
        NONE
    }
    backStoriesEnum backstory;
    private Body body;
    private Skills skills;
    private int gold, xp, level;
    public ArrayList<Equipment> equipment;
    public LinkedHashMap<Equipment.slots, Equipment> equiped;
    public LinkedHashMap<Equipment.slots, Equipment> defaultEquiped;
    public Player(){
        super(new Sprite(new Texture("p.png")));
        this.name = "";
        this.backstory = null;
        this.body = new Body();
        this.skills = new Skills();
        this.gold = 200;
        this.xp = 0;
        this.level = 5;
        this.equipment = new ArrayList<>();
        this.equiped = new LinkedHashMap<>();
        this.defaultEquiped = new LinkedHashMap<>();
        testEquipment();
        initEquiped(equiped);
        initEquiped(defaultEquiped);
    }

    public void initEquiped(LinkedHashMap<Equipment.slots, Equipment> hashMap){
        hashMap.put(Equipment.slots.HEAD, new Equipment(null, "armor/head.png", Equipment.slots.HEAD));
        hashMap.put(Equipment.slots.CHEST, new Equipment(null,"armor/chest.png", Equipment.slots.CHEST));
        hashMap.put(Equipment.slots.LEGS, new Equipment(null,"armor/legs.png", Equipment.slots.LEGS));
        hashMap.put(Equipment.slots.BOOTS, new Equipment(null,"armor/boots.png", Equipment.slots.BOOTS));
        hashMap.put(Equipment.slots.RING1, new Equipment(null,"armor/ring.png", Equipment.slots.RING1));
        hashMap.put(Equipment.slots.PRIMARY, new Equipment(null,"armor/primary.png", Equipment.slots.PRIMARY));

        hashMap.put(Equipment.slots.NECKLACE, new Equipment(null,"armor/necklace.png", Equipment.slots.NECKLACE));
        hashMap.put(Equipment.slots.SHOULDERS, new Equipment(null,"armor/shoulders.png", Equipment.slots.SHOULDERS));
        hashMap.put(Equipment.slots.TABARD, new Equipment(null,"armor/tabard.png", Equipment.slots.TABARD));
        hashMap.put(Equipment.slots.GLOVES, new Equipment(null,"armor/gloves.png", Equipment.slots.GLOVES));
        hashMap.put(Equipment.slots.RING2, new Equipment(null,"armor/ring.png", Equipment.slots.RING2));
        hashMap.put(Equipment.slots.SECONDARY, new Equipment(null,"armor/secondary.png", Equipment.slots.SECONDARY));
    }

    public void testEquipment(){
        String[] imgNames = {"textures/bigsword.png", "textures/littlesword.png", "textures/shield1.png", "textures/shield2.png", "textures/shield3.png"};
        Equipment.slots[] slotsArray = Equipment.slots.values();
        for (int i = 0; i < 100; i++ ){
            equipment.add(new Equipment((i + "pp"), imgNames[RandyRandom.getRandomNumber(0, imgNames.length -1)],
                            slotsArray[RandyRandom.getRandomNumber(0, slotsArray.length -1)]));
        }
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public backStoriesEnum getBackstory() {
        return backstory;
    }

    public void setBackstory(backStoriesEnum backstory) {
        this.backstory = backstory;
        backStoryModifiers();
    }

    private void backStoryModifiers() {
    }
}
