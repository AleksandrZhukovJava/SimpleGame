package org.example.enemy.model.lvl1.impl;

import org.example.enemy.model.lvl1.EnemyLevelOne;
import org.example.items.Items;

import static org.example.options.TestOptions.drop100Percent;

public class Elf extends EnemyLevelOne {
    public Elf() {
        super("Elf", 10, 100, 90);
    }

    @Override
    public int getMainHealth() {
        return 15;
    }

    @Override
    protected void addDrop() {
        for (int i = 0; i < (drop100Percent ? 1000 : 50); i++) {
            this.drop[i] = Items.AMULET_OF_LUCK.getItem();
        }
    }
}
