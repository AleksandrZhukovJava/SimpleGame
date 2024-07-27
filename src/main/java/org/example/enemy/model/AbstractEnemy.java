package org.example.enemy.model;

import lombok.Getter;
import org.example.player.Player;

import java.awt.*;

@Getter
public abstract class AbstractEnemy {
    protected final String name;
    protected int currentHealth;
    protected int damage;
    protected int level;
    protected int experienceValue;

    public AbstractEnemy(String name, int damage, int level, int experienceValue) {
        this.currentHealth = getMainHealth();
        this.damage = damage;
        this.name = name;
        this.level = level;
        this.experienceValue = experienceValue;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(650, 250, 50, 50);
    }

    public void attack(Player player) {
        player.decreaseHealth(damage);
    }

    public void decreaseHealth(int damage) {
        if (currentHealth - damage < 0) {
            currentHealth = 0;
        } else {
            currentHealth -= damage;
        }
    }

    public abstract int getMainHealth();

    public void refreshEntity() {
        this.currentHealth = getMainHealth();
    }
}
