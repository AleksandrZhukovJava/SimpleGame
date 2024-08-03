package org.example.enemy.model.lvl1.impl;

import org.example.enemy.model.lvl1.EnemyLevelOne;
import org.example.items.Items;

import static org.example.options.TestOptions.drop100Percent;

public class FireDemon extends EnemyLevelOne {
    public FireDemon() {
        super("Fire_Demon", 15, 20, 120);
    }

    @Override
    public int getMainHealth() {
        return 180;
    }

    @Override
    protected void addDrop() {
        for (int i = 0; i < (drop100Percent ? 500 : 20); i++) {
            if (drop[i] == null) {
                this.drop[i] = Items.DEMON_HELM.getItem();

            }
        }
        for (int i = 0; i < (drop100Percent ? 1000 : 20);  i++) {
            if (drop[i] == null) {
                this.drop[i] = Items.FIRE_CHAINMALE.getItem();
            }
        }
    }
}
