package org.example.enemy.model;

import org.example.player.Player;

import java.awt.*;

public abstract class AbstractEnemy {
    protected final String name;
    protected int health;
    protected int damage;
    protected int level;

    public AbstractEnemy(String name, int damage, int level) {
        this.health = getHealth();
        this.damage = damage;
        this.name = name;
        this.level = level;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(650, 250, 50, 50);
    }

    public void attack(Player player) {
        player.decreaseHealth(damage);
    }

    public void decreaseHealth(int amount) {
        health -= amount;
    }

    public int getCurrentHealth() {
        return health;
    }

    public abstract int getHealth();

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public void refreshEntity(){
        this.health = getHealth();
    }
}
