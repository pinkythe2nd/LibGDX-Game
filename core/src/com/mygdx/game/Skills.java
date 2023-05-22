package com.mygdx.game;

import java.util.HashMap;

public class Skills {
    enum SkillsEnum {
        ONEHANDED,
        SHIELDS,
        TWOHANDED,
        FIRSTAID,
        MAGIC
    }
    private HashMap<SkillsEnum, Integer> skills;

    public Skills() {
        this.skills = new HashMap<>();
        skills.put(SkillsEnum.ONEHANDED, 10);
        skills.put(SkillsEnum.SHIELDS, 10);
        skills.put(SkillsEnum.TWOHANDED, 10);
        skills.put(SkillsEnum.FIRSTAID, 10);
        skills.put(SkillsEnum.MAGIC, 10);
    }

    public int getSkillValue(SkillsEnum key){
        return skills.get(key);
    }

    public void addToSkill(SkillsEnum key, int value){
        skills.put(key, skills.get(key) + value);
    }

    public void minusFromSkill(SkillsEnum key, int value){
        skills.put(key, skills.get(key) - value);
    }
}
