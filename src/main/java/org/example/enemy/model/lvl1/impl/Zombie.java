package org.example.enemy.model.lvl1.impl;

import org.example.enemy.model.lvl1.EnemyLevelOne;
import org.example.items.Items;

import static org.example.options.TestOptions.drop100Percent;

public class Zombie extends EnemyLevelOne {
    public Zombie() {
        super("Zombie", 12, 18, 20);
    }

    @Override
    public int getMainHealth() {
        return 50;
    }

    @Override
    protected void addDrop() {
        for (int i = 0; i < (drop100Percent ? 1000 : 100); i++) {
            this.drop[i] = Items.CLUB.getItem();
        }
    }
}
