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
        for (int i = 0; i < (drop100Percent ? 333 : 150); i++) {
            if (drop[i] == null) {
                this.drop[i] = Items.MAGIC_SHIELD.getItem();
            }
        }
        for (int i = 0; i < (drop100Percent ? 666 : 50); i++) {
            if (drop[i] == null) {
                this.drop[i] = Items.MAGIC_NECKLACE.getItem();
            }
        }
        for (int i = 0; i < (drop100Percent ? 1000 : 50); i++) {
            if (drop[i] == null) {
                this.drop[i] = Items.MAGIC_BOOTS.getItem();
            }
        }
    }
}
