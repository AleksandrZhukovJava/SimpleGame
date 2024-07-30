package org.example.enemy.model.lvl1.impl;

import org.example.enemy.model.lvl1.EnemyLevelOne;
import org.example.items.Items;

import static org.example.options.TestOptions.drop100Percent;

public class Pirate extends EnemyLevelOne {
    public Pirate() {
        super("Pirate", 8, 12, 40);
    }

    @Override
    public int getMainHealth() {
        return 100;
    }

    @Override
    protected void addDrop() {
        for (int i = 0; i < (drop100Percent ? 1000 : 50); i++) {
            this.drop[i] = Items.NECKLACE.getItem();
        }
    }
}
