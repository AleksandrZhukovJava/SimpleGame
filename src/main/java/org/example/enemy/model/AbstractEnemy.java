package org.example.enemy.model;

import lombok.Getter;
import org.example.player.Player;
import org.example.player.dto.Attack;

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

    public AbstractEnemy(String name, int minDamage, int maxDamage, int level, int experienceValue) {
        this.currentHealth = getMainHealth();
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.name = name;
        this.level = level;
        this.experienceValue = experienceValue;
    }

    public void draw(Graphics g) {
        URL resource = getClass().getResource("/static/%s.jpg".formatted(name.toLowerCase()));
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

    public Attack attack(Player player) {
        int damage = getCurrentDamage();
        player.decreaseHealth(damage);
        return Attack.builder()
                .damage(damage)
                .critical(false)
                .build();
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
