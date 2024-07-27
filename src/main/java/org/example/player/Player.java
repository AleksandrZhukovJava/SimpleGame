package org.example.player;

import lombok.Getter;
import org.example.enemy.model.AbstractEnemy;

import java.awt.*;

@Getter
public class Player {
    private final String name = "Sasha";
    private int currentHealth = 100;
    private int currentDamage = 10;
    private int mainHealth = 100;
    private int mainDamage = 10;
    private int level = 1;
    private int experience = 0;

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(100, 250, 50, 50);
    }

    public void attack(AbstractEnemy enemy) {
        enemy.decreaseHealth(currentDamage);
    }

    public void decreaseHealth(int damage) {
        if (currentHealth - damage < 0) {
            currentHealth = 0;
        } else {
            currentHealth -= damage;
        }
    }


    public void refreshEntity() {
        this.currentDamage = mainDamage;
        this.currentHealth = mainHealth;
    }

    public void lvlUpAndIncreaseDamage(int amount) {
        level++;
        mainDamage += amount;
    }

    public void lvlUpAndIncreaseHealth(int amount) {
        level++;
        mainHealth += amount;
    }

    public void addExperience(int amount) {
        experience += amount;
    }

    public boolean checkLevelUp() {
        return Levels.values()[level - 1].getExperienceNeeded() <= experience;
    }

    public void backToDefault() {
        this.currentHealth = 100;
        this.currentDamage = 10;
        this.mainHealth = 100;
        this.mainDamage = 10;
        this.level = 1;
        this.experience = 0;
    }
}

