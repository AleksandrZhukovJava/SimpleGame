package org.example.enemy.model.lvl1.impl;

import org.example.enemy.model.lvl1.EnemyLevelOne;
import org.example.items.Items;

import static org.example.options.TestOptions.drop100Percent;

public class Orc extends EnemyLevelOne {
    public Orc() {
        super("Orc", 7, 11, 25);
    }

    @Override
    public int getMainHealth() {
        return 100;
    }

    @Override
    protected void addDrop() {
        for (int i = 0; i < (drop100Percent ? 1000 : 200); i++) {
            this.drop[i] = Items.SMALL_AXE.getItem();
        }
    }
}
