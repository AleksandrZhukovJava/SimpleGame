package org.example.enemy.model.lvl1.impl;

import org.example.enemy.model.lvl1.EnemyLevelOne;
import org.example.items.Items;

import static org.example.options.TestOptions.drop100Percent;

public class Elemental extends EnemyLevelOne {
    public Elemental() {
        super("Elemental", 6, 12, 60);
    }

    @Override
    public int getMainHealth() {
        return 200;
    }

    @Override
    protected void addDrop() {
        for (int i = 0; i < (drop100Percent ? 500 : 150); ) {
            if (drop[i] == null) {
                this.drop[i] = Items.MAGIC_SHIELD.getItem();
                i++;
            }
        }
        for (int i = 0; i < (drop100Percent ? 500 : 50); i++) {
            if (drop[i] == null) {
                this.drop[i] = Items.MAGIC_NECKLACE.getItem();
                i++;
            }
        }
    }
}
