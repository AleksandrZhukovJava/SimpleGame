package org.example.enemy.model.lvl1.impl;

import org.example.enemy.model.lvl1.EnemyLevelOne;
import org.example.items.Items;

import static org.example.options.TestOptions.drop100Percent;

public class Boss extends EnemyLevelOne {
    public Boss() {
        super("Boss", 15, 30, 200);
    }

    @Override
    public int getMainHealth() {
        return 300;
    }

    @Override
    protected void addDrop() {
        for (int i = 0; i < (drop100Percent ? 500 : 10); i++) {
            if (drop[i] == null) {
                this.drop[i] = Items.GIANT_AXE.getItem();

            }
        }
        for (int i = 0; i < (drop100Percent ? 1000 : 10); i++) {
            if (drop[i] == null) {
                this.drop[i] = Items.HEAVY_ARMOR.getItem();
            }
        }
    }
}
