package org.example.player;

import lombok.Getter;
import org.example.enemy.model.AbstractEnemy;
import org.example.player.dto.Attack;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;

@Getter
public class Player {
    private final Random random = new Random();
    private final String name = "Sasha";
    private int currentHealth = 100;
    private int mainHealth = 100;
    private int currentMinDamage = 8;
    private int currentMaxDamage = 12;
    private int mainMinDamage = 8;
    private int mainMaxDamage = 12;
    private int level = 1;
    private int experience = 0;
    private int criticalChance = 0;
    private int levelUpOptionsAmount = 2;
    private int lvlUpAndIncreaseHealthFactor = 20;
    private int lvlUpAndIncreaseDamageFactor = 5;

    public void draw(Graphics g) {
        Image icon = getEnemyImage();
        if (icon != null) {
            g.drawImage(icon, 0, 150, 200, 200, null); // Отображаем изображение на панели
        } else {
            g.setColor(Color.BLUE);
            g.fillRect(650, 250, 50, 50); // Если изображение не загрузилось, отображаем квадрат
        }
    }

    public Image getEnemyImage() {
        URL resource = getClass().getResource("/static/player.png");
        if (resource != null) {
            return new ImageIcon(resource).getImage();
        }
        return null;
    }

    public int getCurrentDamage() {
        return random.nextInt(currentMinDamage, currentMaxDamage);
    }

    public Attack attack(AbstractEnemy enemy) {
        boolean critical = random.nextInt(0, 100) <= criticalChance;
        int damage = critical ? getCurrentDamage() * 2 : getCurrentDamage();
        enemy.decreaseHealth(damage);
        return Attack.builder()
                .damage(damage)
                .critical(critical)
                .build();
    }

    public void decreaseHealth(int damage) {
        if (currentHealth - damage < 0) {
            currentHealth = 0;
        } else {
            currentHealth -= damage;
        }
    }


    public void refreshEntity() {
        this.currentMinDamage = mainMinDamage;
        this.currentMaxDamage = mainMaxDamage;
        this.currentHealth = mainHealth;
    }

    public void lvlUpAndIncreaseDamage() { //todo фабрикой
        level++;
        mainMinDamage += lvlUpAndIncreaseDamageFactor;
        mainMaxDamage += lvlUpAndIncreaseDamageFactor;
    }

    public void lvlUpAndIncreaseHealth() {
        level++;
        mainHealth += lvlUpAndIncreaseHealthFactor;
    }

    public void lvlUpAndIncreaseCriticalChance() {
        level++;
        criticalChance += 5;
    }

    public void addExperience(int amount) {
        experience += amount;
    }

    public void increaseLevelOptionsAmount() {
        levelUpOptionsAmount++;
    }

    public boolean checkLevelUp() {
        if (level == Levels.values().length){
            return false;
        }
        return Levels.values()[level - 1].getExperienceNeeded() <= experience;
    }

    public void backToDefault() {
        this.currentHealth = 100;
        this.mainHealth = 100;
        this.currentMinDamage = 8;
        this.currentMaxDamage = 12;
        this.mainMinDamage = 8;
        this.mainMaxDamage = 12;
        this.level = 1;
        this.experience = 0;
        this.criticalChance = 0;
        this.levelUpOptionsAmount = 2;
        this.lvlUpAndIncreaseHealthFactor = 20;
        this.lvlUpAndIncreaseDamageFactor = 5;
    }
}

