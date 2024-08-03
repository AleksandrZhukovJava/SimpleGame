package org.example.battle;

import org.example.enemy.model.AbstractEnemy;
import org.example.player.Levels;
import org.example.player.Player;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;

public class DrawBattleDesc {
    private void drawTextWithOutline(Graphics2D g2d, String text, Font font, Color textColor, Color outlineColor, int x, int y) {
        g2d.setFont(font);

        FontRenderContext frc = g2d.getFontRenderContext();
        TextLayout textLayout = new TextLayout(text, font, frc);

        Shape textShape = textLayout.getOutline(null);
        AffineTransform transform = AffineTransform.getTranslateInstance(x, y + textLayout.getAscent()); // Перемещаем текст
        Shape translatedTextShape = transform.createTransformedShape(textShape);

        // Рисуем обводку
        g2d.setColor(outlineColor);
        g2d.setStroke(new BasicStroke(2)); // Увеличьте значение для более жирной обводки
        g2d.draw(translatedTextShape);

        // Рисуем текст
        g2d.setColor(textColor);
        g2d.drawString(text, x, y + textLayout.getAscent()); // Обратите внимание на `+ textLayout.getAscent()`
    }

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
        int playerHealth = player.getCurrentHealth();
        int playerMaxHealth = player.getMainHealthWithEquipment();
        int enemyHealth = enemy.getCurrentHealth();
        int enemyMaxHealth = enemy.getMainHealth();

        int barWidth = 200;
        int barHeight = 20;
        int playerBarX = 30;
        int playerBarY = 60;
        int enemyBarX = 550;
        int enemyBarY = 60;

        int playerHealthBarWidth = (int) (Math.min((double) playerHealth / playerMaxHealth, 1.0) * barWidth);
        int enemyHealthBarWidth = (int) (Math.min((double) enemyHealth / enemyMaxHealth, 1.0) * barWidth);

        g.setColor(new Color(90, 0, 0));
        g.fillRect(playerBarX, playerBarY, barWidth, barHeight);
        g.fillRect(enemyBarX, enemyBarY, barWidth, barHeight);

        g.setColor(new Color(50, 120, 50));
        g.fillRect(playerBarX, playerBarY, playerHealthBarWidth, barHeight);
        g.fillRect(enemyBarX, enemyBarY, enemyHealthBarWidth, barHeight);

        g.setColor(new Color(165, 165, 165));
        g.drawRect(playerBarX, playerBarY, barWidth, barHeight);
        g.drawRect(enemyBarX, enemyBarY, barWidth, barHeight);

        Font font = new Font("Cooper Black", Font.PLAIN, 18);
        drawTextWithOutline((Graphics2D) g, "HP: " + playerHealth + "/" + playerMaxHealth, font, new Color(205,205,205),
                Color.BLACK, playerBarX, playerBarY - 25);
        drawTextWithOutline((Graphics2D) g, "HP: " + enemyHealth + "/" + enemyMaxHealth, font, new Color(205,205,205), Color.BLACK,
                enemyBarX, enemyBarY - 25);
    }

    // Остальные методы для отображения характеристик с использованием drawTextWithOutline
    private void drawLevel(Graphics g, Player player, AbstractEnemy enemy) {
        Font font = new Font("Arial", Font.BOLD, 14);
        drawTextWithOutline((Graphics2D) g, "Level:" + player.getLevel(), font, new Color(205,205,205), Color.BLACK, 50, 370);
        drawTextWithOutline((Graphics2D) g, "Level: " + enemy.getLevel(), font, new Color(205,205,205), Color.BLACK, 600, 370);
    }

    private void drawHealth(Graphics g, Player player, AbstractEnemy enemy) {
        Font font = new Font("Arial", Font.BOLD, 14);
        drawTextWithOutline((Graphics2D) g, "Health: " + player.getMainHealthWithEquipment(), font, new Color(205,205,205),
                Color.BLACK, 50, 390);
        drawTextWithOutline((Graphics2D) g, "Health: " + enemy.getMainHealth(), font, new Color(205,205,205), Color.BLACK, 600,
                390);
    }

    private void drawAttack(Graphics g, Player player, AbstractEnemy enemy) {
        Font font = new Font("Arial", Font.BOLD, 14);
        drawTextWithOutline((Graphics2D) g,
                "Attack: %s-%s".formatted(player.getCurrentMinDamage(), player.getCurrentMaxDamage()), font,
                new Color(205,205,205), Color.BLACK, 50, 410);
        drawTextWithOutline((Graphics2D) g, "Attack: %s-%s".formatted(enemy.getMinDamage(), enemy.getMaxDamage()), font,
                new Color(205,205,205), Color.BLACK, 600, 410);
    }

    private void drawExperience(Graphics g, Player player, AbstractEnemy enemy) {
        Font font = new Font("Arial", Font.BOLD, 14);
        drawTextWithOutline((Graphics2D) g, "Experience: %s (%s)".formatted(
                        player.getExperience(),
                        player.getExperience() - Levels.values()[player.getLevel() - 1].getExperienceNeeded()),
                font, new Color(205,205,205), Color.BLACK, 50, 430);
        drawTextWithOutline((Graphics2D) g, "Experience: %s".formatted(enemy.getExperienceValue()), font, new Color(205,205,205),
                Color.BLACK, 600, 430);
    }

    private void drawCriticalChance(Graphics g, Player player, AbstractEnemy enemy) {
        Font font = new Font("Arial", Font.BOLD, 14);
        drawTextWithOutline((Graphics2D) g, "Crit chance: %s%%".formatted(player.getCriticalChance()), font,
                new Color(205,205,205), Color.BLACK, 50, 450);
        drawTextWithOutline((Graphics2D) g, "Crit chance: %s%%".formatted(enemy.getCriticalChance()), font, new Color(205,205,205),
                Color.BLACK, 600, 450);
    }

    private void drawMissingChance(Graphics g, Player player, AbstractEnemy enemy) {
        Font font = new Font("Arial", Font.BOLD, 14);
        drawTextWithOutline((Graphics2D) g, "Miss chance: %s%%".formatted(player.getMissingChance()), font, new Color(205,205,205),
                Color.BLACK, 50, 470);
        drawTextWithOutline((Graphics2D) g, "Miss chance: %s%%".formatted(enemy.getMissingChance()), font, new Color(205,205,205),
                Color.BLACK, 600, 470);
    }

    private void drawName(Graphics g, Player player, AbstractEnemy enemy) {
        Font font = new Font("Arial", Font.BOLD, 14);
        drawTextWithOutline((Graphics2D) g, "Name: " + player.getName(), font, new Color(205,205,205), Color.BLACK, 50, 490);
        drawTextWithOutline((Graphics2D) g, "Type: " + enemy.getName(), font, new Color(205,205,205), Color.BLACK, 600, 490);
    }
}
