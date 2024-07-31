package org.example.battle;

import org.example.enemy.model.AbstractEnemy;
import org.example.player.Levels;
import org.example.player.Player;

import java.awt.*;

public class DrawBattleDesc {

    public void drawDesc(Graphics g, Player player, AbstractEnemy enemy) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        player.draw(g);
        enemy.draw(g);
        drawCurrentHealth(g, player, enemy);
        drawHealth(g, player, enemy);
        drawAttack(g, player, enemy);
        drawName(g, player, enemy);
        drawLevel(g, player, enemy);
        drawExperience(g, player, enemy);
        drawCriticalChance(g, player, enemy);
        drawMissingChance(g, player, enemy);
    }

    private void drawCurrentHealth(Graphics g, Player player, AbstractEnemy enemy) {
        int playerHealth = player.getCurrentHealthWithEquipment();
        int playerMaxHealth = player.getMainHealthWithEquipment();
        int enemyHealth = enemy.getCurrentHealth();
        int enemyMaxHealth = enemy.getMainHealth();

        int barWidth = 200;
        int barHeight = 20;
        int playerBarX = 50;
        int playerBarY = 80;
        int enemyBarX = 500;
        int enemyBarY = 80;

        int playerHealthBarWidth = (int) ((double) playerHealth / playerMaxHealth * barWidth);
        int enemyHealthBarWidth = (int) ((double) enemyHealth / enemyMaxHealth * barWidth);

        g.setColor(new Color(90, 0, 0));
        g.fillRect(playerBarX, playerBarY, barWidth, barHeight);
        g.fillRect(enemyBarX, enemyBarY, barWidth, barHeight);

        g.setColor(new Color(50, 120, 50));
        g.fillRect(playerBarX, playerBarY, playerHealthBarWidth, barHeight);
        g.fillRect(enemyBarX, enemyBarY, enemyHealthBarWidth, barHeight);

        g.setColor(Color.BLACK);
        g.drawRect(playerBarX, playerBarY, barWidth, barHeight);
        g.drawRect(enemyBarX, enemyBarY, barWidth, barHeight);

        g.setFont(new Font("Cooper Black", Font.BOLD, 18));
        g.setColor(Color.BLACK);
        g.drawString("HP: " + playerHealth + "/" + playerMaxHealth, playerBarX, playerBarY - 5);
        g.drawString("HP: " + enemyHealth + "/" + enemyMaxHealth, enemyBarX, enemyBarY - 5);
    }

    // Остальные методы для отображения характеристик
    private void drawLevel(Graphics g, Player player, AbstractEnemy enemy) {
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.setColor(Color.BLACK);

        g.drawString("Level:" + player.getLevel(), 50, 370);
        g.drawString("Level: " + enemy.getLevel(), 600, 370);
    }

    private void drawHealth(Graphics g, Player player, AbstractEnemy enemy) {
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.setColor(Color.BLACK);

        g.drawString("Health: " + player.getMainHealthWithEquipment(), 50, 390);
        g.drawString("Health: " + enemy.getMainHealth(), 600, 390);
    }

    private void drawAttack(Graphics g, Player player, AbstractEnemy enemy) {
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.setColor(Color.BLACK);

        g.drawString("Attack: %s-%s".formatted(player.getCurrentMinDamage(), player.getCurrentMaxDamage()), 50, 410);
        g.drawString("Attack: %s-%s".formatted(enemy.getMinDamage(), enemy.getMaxDamage()), 600, 410);
    }

    private void drawExperience(Graphics g, Player player, AbstractEnemy enemy) {
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.setColor(Color.BLACK);

        g.drawString("Experience: %s (%s)".formatted(
                        player.getExperience(),
                        player.getExperience() - Levels.values()[player.getLevel() - 1].getExperienceNeeded()),
                50, 430);
        g.drawString("Experience: %s".formatted(enemy.getExperienceValue()), 600, 430);
    }

    private void drawCriticalChance(Graphics g, Player player, AbstractEnemy enemy) {
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.setColor(Color.BLACK);

        g.drawString("Crit chance: %s%%".formatted(player.getCriticalChance()), 50, 450);
        g.drawString("Crit chance: %s%%".formatted(enemy.getCriticalChance()), 600, 450);
    }

    private void drawMissingChance(Graphics g, Player player, AbstractEnemy enemy) {
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.setColor(Color.BLACK);

        g.drawString("Miss chance: %s%%".formatted(player.getMissingChance()), 50, 470);
        g.drawString("Miss chance: %s%%".formatted(enemy.getMissingChance()), 600, 470);
    }

    private void drawName(Graphics g, Player player, AbstractEnemy enemy) {
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.setColor(Color.BLACK);

        g.drawString("Name: " + player.getName(), 50, 490);
        g.drawString("Type: " + enemy.getName(), 600, 490);
    }
}
