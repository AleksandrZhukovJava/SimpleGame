package org.example.service;

import org.example.model.enemy.AbstractEnemy;
import org.example.model.player.Player;

import java.awt.*;

public class DrawGameDesc {
    public void drawDesc(Graphics g, Player player, AbstractEnemy enemy) {
        player.draw(g);
        enemy.draw(g);
        drawCurrentHealth(g, player, enemy);
        drawHealth(g, player, enemy);
        drawAttack(g, player, enemy);
        drawName(g, player, enemy);
        drawLevel(g, player, enemy);
    }

    private void drawCurrentHealth(Graphics g, Player player, AbstractEnemy enemy) {
        g.setColor(Color.BLACK);
        g.drawString("Player Health: " + player.getCurrentHealth(), 50, 50);
        g.drawString("Enemy Health: " + enemy.getCurrentHealth(), 650, 50);
    }

    private void drawName(Graphics g, Player player, AbstractEnemy enemy) {
        g.setColor(Color.BLACK);
        g.drawString("Player: " + player.getName(), 50, 420);
        g.drawString("Enemy: " + enemy.getName(), 650, 420);
    }

    private void drawHealth(Graphics g, Player player, AbstractEnemy enemy) {
        g.setColor(Color.BLACK);
        g.drawString("Health: " + player.getHealth(), 50, 340);
        g.drawString("Health: " + enemy.getHealth(), 650, 340);
    }

    private void drawAttack(Graphics g, Player player, AbstractEnemy enemy) {
        g.setColor(Color.BLACK);
        g.drawString("Player Attack: " + player.getDamage(), 50, 360);
        g.drawString("Enemy Attack: " + enemy.getDamage(), 650, 360);
    }

    private void drawLevel(Graphics g, Player player, AbstractEnemy enemy) {
        g.setColor(Color.BLACK);
        g.drawString("Level:" + player.getLevel(), 50, 320);
        g.drawString("Level: " + enemy.getLevel(), 650, 320);
    }
}
