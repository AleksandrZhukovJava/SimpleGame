package org.example.player;

import org.example.items.Item;
import org.example.items.improve.ImproveTarget;
import org.example.operations.InventoryIsFullException;
import org.example.player.dto.Attack;
import org.example.player.dto.AttackResult;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;

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
    private int criticalMultiplicationFactor = 2;
    private int levelUpOptionsAmount = 2;
    private int lvlUpAndIncreaseHealthFactor = 20;
    private int lvlUpAndIncreaseDamageFactor = 5;
    private int missingChance = 0;
    private Item[][] inventory = new Item[8][5];
    private Equipment equipment = new Equipment();

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public void draw(Graphics g) {
        Image icon = getPlayerImage();
        if (icon != null) {
            g.drawImage(icon, 0, 100, 200, 250, null);
        } else {
            g.setColor(Color.BLUE);
            g.fillRect(650, 250, 50, 50);
        }
    }

    public Image getPlayerImage() {
        URL resource = getClass().getResource("/static/player/player.jpg");
        if (resource != null) {
            return new ImageIcon(resource).getImage();
        }
        return null;
    }

    public int getCurrentDamage() {
        return random.nextInt(currentMinDamage, currentMaxDamage) + equipment.getAdditionalAmount(ImproveTarget.ATTACK);
    }

    public int getCurrentHealthWithEquipment() {
        return currentHealth + equipment.getAdditionalAmount(ImproveTarget.HEALTH);
    }

    public int getCriticalChance() {
        return criticalChance + equipment.getAdditionalAmount(ImproveTarget.CRITICAL_CHANCE);
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getMissingChance() {
        return missingChance + equipment.getAdditionalAmount(ImproveTarget.MISSING_CHANCE);
    }

    public int getMainHealthWithEquipment() {
        return mainHealth + equipment.getAdditionalAmount(ImproveTarget.HEALTH);
    }

    public int getCurrentMinDamage() {
        return currentMinDamage + equipment.getAdditionalAmount(ImproveTarget.ATTACK);
    }

    public int getCurrentMaxDamage() {
        return currentMaxDamage + equipment.getAdditionalAmount(ImproveTarget.ATTACK);
    }

    public int getExperience() {
        return experience;
    }

    //todo придумать как динамически защищать
    public int getLevelUpOptionsAmount() {
        int amount = levelUpOptionsAmount + equipment.getAdditionalAmount(ImproveTarget.LVL_UP_CHOICE_AMOUNT_IMPROVE);
        return Math.min(amount, 4);
    }

    public Item[][] getInventory() {
        return inventory;
    }

    public Attack getAttack() {
        boolean critical = random.nextInt(0, 100) <= getCriticalChance();
        int damage = critical ? getCurrentDamage() * criticalMultiplicationFactor : getCurrentDamage();
        return Attack.builder()
                .damage(damage)
                .critical(critical)
                .accuracy(getCriticalChance())
                .build();
    }

    public AttackResult takeHit(Attack attack) {
        boolean miss = random.nextInt(0, 100) <= (getMissingChance() - getCriticalChance());
        if (!miss) {
            if (currentHealth - attack.getDamage() < 0) {
                currentHealth = 0;
            } else {
                currentHealth -= attack.getDamage();
            }
        }
        return AttackResult.builder()
                .isMiss(miss)
                .build();
    }


    public void refreshEntity() {
        this.currentMinDamage = mainMinDamage;
        this.currentMaxDamage = mainMaxDamage;
        this.currentHealth = getMainHealthWithEquipment();
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

    public void lvlUpAndIncreaseMissingChance() {
        level++;
        missingChance += 5;
    }

    public void addExperience(int amount) {
        experience += amount;
    }

    public void increaseLevelOptionsAmount() {
        levelUpOptionsAmount++;
    }

    public boolean checkLevelUp() {
        if (level == Levels.values().length) {
            return false;
        }
        return Levels.values()[level - 1].getExperienceNeeded() <= experience;
    }

    public void addItemToInventory(Item item) {
        for (int i = 0; i < inventory.length; i++) {
            for (int j = 0; j < inventory[i].length; j++) {
                if (inventory[i][j] == null) {
                    inventory[i][j] = item;
                    return;
                }
            }
        }
        throw new InventoryIsFullException();
    }

    public void removeItemFromInventory(int x, int y) {
        for (int i = 0; i < inventory.length; i++) {
            for (int j = 0; j < inventory[i].length; j++) {
                if (i == x && j == y) {
                    inventory[i][j] = null;
                    return;
                }
            }
        }
    }

    public void equipItem(int x, int y) {
        Item item = inventory[x][y];
        if (item == null) {
            return;
        }
        Item oldItem = equipment.getEquipment().put(item.getType(), item);
        inventory[x][y] = oldItem;
    }

    @Deprecated
    private void printInventory() {
        for (int i = 0; i < inventory.length; i++) {
            for (int j = 0; j < inventory[0].length; j++) {
                System.out.print(inventory[i][j] == null ? "|o" : "|x");
            }
            System.out.print("|");
            System.out.println();
        }
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public int getInventoryCellAmount() {
        return Arrays.stream(inventory)
                .map(ar -> ar.length)
                .reduce(0, Integer::sum);
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
        this.criticalMultiplicationFactor = 2;
        this.levelUpOptionsAmount = 2;
        this.lvlUpAndIncreaseHealthFactor = 20;
        this.lvlUpAndIncreaseDamageFactor = 5;
        this.missingChance = 0;
        this.inventory = new Item[8][5];
        this.equipment = new Equipment();
    }
}

