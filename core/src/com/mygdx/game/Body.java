package com.mygdx.game;

import java.util.HashMap;

public class Body {
    enum BodyPartNames {
        SKULL, //head
        BRAIN,
        LEFTEYE,
        RIGHTEYE,
        NECK,

        HEART, //torso
        RIBS,
        LEFTLUNG,
        RIGHTLUNG,
        STOMACH,
        INTESTINES,
        LEFTKIDNEY,
        RIGHTKIDNEY,
        LIVER,

        LEFTSHOULDER, //arms
        LEFTFOREARM,
        LEFTHAND,
        RIGHTSHOULDER,
        RIGHTFOREARM,
        RIGHTHAND,

        LEFTTHIGH, //legs
        LEFTCALF,
        LEFTFOOT,
        RIGHTTHIGH,
        RIGHTCALF,
        RIGHTFOOT,
    }
    BodyPartNames[] bodyPartNamesArray = BodyPartNames.values();

    private final HashMap<BodyPartNames, BodyPart> body;

    public Body() {
        this.body = new HashMap<>();
        body.put(BodyPartNames.SKULL, new BodyPart(50));
        body.put(BodyPartNames.BRAIN, new BodyPart(25));
        body.put(BodyPartNames.LEFTEYE, new BodyPart(25));
        body.put(BodyPartNames.RIGHTEYE, new BodyPart(25));

        body.put(BodyPartNames.HEART, new BodyPart(25));
        body.put(BodyPartNames.RIBS, new BodyPart(75));
        body.put(BodyPartNames.LEFTLUNG, new BodyPart(35));
        body.put(BodyPartNames.RIGHTLUNG, new BodyPart(35));

        body.put(BodyPartNames.STOMACH, new BodyPart(40));
        body.put(BodyPartNames.INTESTINES, new BodyPart(50));
        body.put(BodyPartNames.LEFTKIDNEY, new BodyPart(25));
        body.put(BodyPartNames.RIGHTKIDNEY, new BodyPart(25));
        body.put(BodyPartNames.LIVER, new BodyPart(42));

        body.put(BodyPartNames.LEFTSHOULDER, new BodyPart(50));
        body.put(BodyPartNames.LEFTFOREARM, new BodyPart(35));
        body.put(BodyPartNames.LEFTHAND, new BodyPart(25));

        body.put(BodyPartNames.RIGHTSHOULDER, new BodyPart(50));
        body.put(BodyPartNames.RIGHTFOREARM, new BodyPart(35));
        body.put(BodyPartNames.RIGHTHAND, new BodyPart(25));

        body.put(BodyPartNames.LEFTTHIGH, new BodyPart(50));
        body.put(BodyPartNames.LEFTCALF, new BodyPart(35));
        body.put(BodyPartNames.LEFTFOOT, new BodyPart(25));

        body.put(BodyPartNames.RIGHTTHIGH, new BodyPart(50));
        body.put(BodyPartNames.RIGHTCALF, new BodyPart(35));
        body.put(BodyPartNames.RIGHTFOOT, new BodyPart(25));
    }

    public Body(int level) {
        this.body = new HashMap<>();
        body.put(BodyPartNames.SKULL, new BodyPart((int) (50 * (1 + (0.1 * level)))));
        body.put(BodyPartNames.BRAIN, new BodyPart((int) (25 * (1 + (0.1 * level)))));
        body.put(BodyPartNames.LEFTEYE, new BodyPart((int) (25 * (1 + (0.1 * level)))));
        body.put(BodyPartNames.RIGHTEYE, new BodyPart((int) (25 * (1 + (0.1 * level)))));
        body.put(BodyPartNames.HEART, new BodyPart((int) (25 * (1 + (0.1 * level)))));
        body.put(BodyPartNames.RIBS, new BodyPart((int) (75 * (1 + (0.1 * level)))));
        body.put(BodyPartNames.LEFTLUNG, new BodyPart((int) (35 * (1 + (0.1 * level)))));
        body.put(BodyPartNames.RIGHTLUNG, new BodyPart((int) (35 * (1 + (0.1 * level)))));
        body.put(BodyPartNames.STOMACH, new BodyPart((int) (40 * (1 + (0.1 * level)))));
        body.put(BodyPartNames.INTESTINES, new BodyPart((int) (50 * (1 + (0.1 * level)))));
        body.put(BodyPartNames.LEFTKIDNEY, new BodyPart((int) (25 * (1 + (0.1 * level)))));
        body.put(BodyPartNames.RIGHTKIDNEY, new BodyPart((int) (25 * (1 + (0.1 * level)))));
        body.put(BodyPartNames.LIVER, new BodyPart((int) (42 * (1 + (0.1 * level)))));
        body.put(BodyPartNames.LEFTSHOULDER, new BodyPart((int) (50 * (1 + (0.1 * level)))));
        body.put(BodyPartNames.LEFTFOREARM, new BodyPart((int) (35 * (1 + (0.1 * level)))));
        body.put(BodyPartNames.LEFTHAND, new BodyPart((int) (25 * (1 + (0.1 * level)))));
        body.put(BodyPartNames.RIGHTSHOULDER, new BodyPart((int) (50 * (1 + (0.1 * level)))));
        body.put(BodyPartNames.RIGHTFOREARM, new BodyPart((int) (35 * (1 + (0.1 * level)))));
        body.put(BodyPartNames.RIGHTHAND, new BodyPart((int) (25 * (1 + (0.1 * level)))));
        body.put(BodyPartNames.LEFTTHIGH, new BodyPart((int) (50 * (1 + (0.1 * level)))));
        body.put(BodyPartNames.LEFTCALF, new BodyPart((int) (35 * (1 + (0.1 * level)))));
        body.put(BodyPartNames.LEFTFOOT, new BodyPart((int) (25 * (1 + (0.1 * level)))));
        body.put(BodyPartNames.RIGHTTHIGH, new BodyPart((int) (50 * (1 + (0.1 * level)))));
        body.put(BodyPartNames.RIGHTCALF, new BodyPart((int) (35 * (1 + (0.1 * level)))));
        body.put(BodyPartNames.RIGHTFOOT, new BodyPart((int) (25 * (1 + (0.1 * level)))));
    }

    public BodyPart getBodyPart(BodyPartNames key){
        return body.get(key);
    }

    public void levelUp() {
        for (BodyPartNames bodyPartNames : bodyPartNamesArray) {
            this.body.get(bodyPartNames).levelUp();
        }
    }

    public int health() {
        float total = 0;
        for (BodyPartNames bodyPartNames : bodyPartNamesArray) {
            total += (float) body.get(bodyPartNames).getHealth() / body.get(bodyPartNames).getMaxHealth();
        }

        return (int) total / bodyPartNamesArray.length;
    }


}
