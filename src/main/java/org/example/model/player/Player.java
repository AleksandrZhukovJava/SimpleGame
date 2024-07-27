package org.example.model.player;

import org.example.model.enemy.AbstractEnemy;

import java.awt.*;

public class Player {
    private final String name = "Sasha";
    private int currentHealth = 100;
    private int currentDamage = 10;
    private int mainHealth = 100;
    private int mainDamage = 10;
    private int level = 1;

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(100, 250, 50, 50);
    }

    public void attack(AbstractEnemy enemy) {
        enemy.decreaseHealth(currentDamage);
    }

    public void decreaseHealth(int damage) {
        currentHealth -= damage;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getHealth() {
        return mainHealth;
    }

    public void refreshEntity() {
        this.currentDamage = mainDamage;
        this.currentHealth = mainHealth;
    }

    public void lvlUp() {
        level++;
        mainDamage++;
        mainHealth++;
    }

    public int getCurrentDamage() {
        return currentDamage;
    }

    public int getDamage() {
        return mainDamage;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }
}

