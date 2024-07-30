package org.example.enemy.model;

import lombok.Getter;
import org.example.items.Item;
import org.example.player.dto.Attack;
import org.example.player.dto.AttackResult;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;

@Getter
public abstract class AbstractEnemy {
    protected final Random random = new Random();

    protected final String name;
    protected int currentHealth;
    protected int minDamage;
    protected int maxDamage;
    protected int level;
    protected int experienceValue;
    protected int missingChance = 0;
    protected int criticalChance = 0;

    protected Item[] drop = new Item[1000];

    public AbstractEnemy(String name, int minDamage, int maxDamage, int level, int experienceValue) {
        this.currentHealth = getMainHealth();
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.name = name;
        this.level = level;
        this.experienceValue = experienceValue;
        addDrop();
    }

    public void draw(Graphics g) {
        URL resource = getClass().getResource("/static/enemy/%s.jpg".formatted(name.toLowerCase()));
        Image icon = null;
        if (resource != null) {
            icon = new ImageIcon(resource).getImage();
        }

        if (icon != null) {
            g.drawImage(icon, 600, 150, 180, 200, null);
        } else {
            g.setColor(Color.BLUE);
            g.fillRect(650, 250, 50, 50);
        }
    }

    public int getCurrentDamage() {
        return random.nextInt(minDamage, maxDamage);
    }

    public Attack getAttack() {
        return Attack.builder()
                .damage(getCurrentDamage())
                .critical(false)
                .accuracy(0)
                .build();
    }

    public AttackResult takeHit(Attack attack) {
        boolean miss = random.nextInt(0, 100) <= (missingChance - criticalChance);

        if (!miss) {
            if (currentHealth - attack.getDamage() <= 0) {
                currentHealth = 0;
            } else {
                currentHealth -= attack.getDamage();
            }
        }

        return AttackResult.builder()
                .isMiss(miss)
                .build();
    }

    public abstract int getMainHealth();

    public void refreshEntity() {
        this.currentHealth = getMainHealth();
    }

    protected abstract void addDrop();

    //todo принимать модификаторы?
    public Item getDrop() {
        return drop[random.nextInt(0, drop.length - 1)];
    }
}
