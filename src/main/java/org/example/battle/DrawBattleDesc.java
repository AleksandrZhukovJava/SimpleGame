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
        drawExperience(g, player);
    }

    private void drawCurrentHealth(Graphics g, Player player, AbstractEnemy enemy) {
        g.setFont(new Font("Arial", Font.BOLD, 24)); // Можно изменить на любой другой шрифт
        g.setColor(Color.BLACK);

        g.drawString("Player Health: " + player.getCurrentHealth(), 50, 80);
        g.drawString("Enemy Health: " + enemy.getCurrentHealth(), 500, 80);
    }

    private void drawLevel(Graphics g, Player player, AbstractEnemy enemy) {
        g.setFont(new Font("Arial", Font.BOLD, 14)); // Можно изменить на любой другой шрифт
        g.setColor(Color.BLACK);

        g.drawString("Level:" + player.getLevel(), 50, 370);
        g.drawString("Level: " + enemy.getLevel(), 600, 370);
    }

    private void drawHealth(Graphics g, Player player, AbstractEnemy enemy) {
        g.setFont(new Font("Arial", Font.BOLD, 14)); // Можно изменить на любой другой шрифт
        g.setColor(Color.BLACK);

        g.drawString("Health: " + player.getMainHealth(), 50, 390);
        g.drawString("Health: " + enemy.getMainHealth(), 600, 390);
    }

    private void drawAttack(Graphics g, Player player, AbstractEnemy enemy) {
        g.setFont(new Font("Arial", Font.BOLD, 14)); // Можно изменить на любой другой шрифт
        g.setColor(Color.BLACK);

        g.drawString("Player Attack: %s-%s".formatted(player.getCurrentMinDamage(), player.getCurrentMaxDamage()),
                50, 410);
        g.drawString("Enemy Attack: %s-%s".formatted(enemy.getMinDamage(), enemy.getMaxDamage()),
                600, 410);
    }

    private void drawExperience(Graphics g, Player player) {
        g.setFont(new Font("Arial", Font.BOLD, 14)); // Можно изменить на любой другой шрифт
        g.setColor(Color.BLACK);

        g.drawString("Experience: %s (%s)".formatted(
                        player.getExperience(),
                        player.getExperience() - Levels.values()[player.getLevel() - 1].getExperienceNeeded()),
                50, 430);
    }

    private void drawName(Graphics g, Player player, AbstractEnemy enemy) {
        g.setFont(new Font("Arial", Font.BOLD, 14)); // Можно изменить на любой другой шрифт
        g.setColor(Color.BLACK);

        g.drawString("Player: " + player.getName(), 50, 470);
        g.drawString("Enemy: " + enemy.getName(), 600, 470);
    }
}
