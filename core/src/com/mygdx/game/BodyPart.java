package com.mygdx.game;

public class BodyPart {

    private int maxHealth, health;

    public BodyPart(int health) {
        this.maxHealth = health;
        this.health = health;
    }

    public BodyPart( int maxHealth, int health) {
        this.maxHealth = maxHealth;
        this.health = health;
    }


    public int getHealth() {
        return health;
    }

    public void minusHealth(int damage) {
        if (this.health - damage > 0) {
            this.health -= damage;
        }else {
            this.health = 0;
        }
    }

    public void addHealth(int heal) {
        if (this.health + heal < maxHealth) {
            this.health += heal;
        } else {
            this.health = maxHealth;
        }
    }

    public void levelUp() {
        this.maxHealth = (int) (maxHealth * 1.10);
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public String toString() {
        return "BodyParts{" +
                "maxHealth=" + maxHealth +
                ", health=" + health +
                '}';
    }

    public static void main(String[] args){
        BodyPart foot = new BodyPart(30);

        System.out.println(foot);

        foot.minusHealth(15);
        System.out.println(foot); //should be 15

        foot.minusHealth(4040);
        System.out.println(foot); //should be 0

        foot.addHealth(20);
        System.out.println(foot); //should be 20

        foot.addHealth(3000);
        System.out.println(foot); // should be 30

    }

}
