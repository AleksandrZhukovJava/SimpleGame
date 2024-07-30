package org.example.enemy.model.lvl1.impl;

import org.example.enemy.model.lvl1.EnemyLevelOne;
import org.example.items.Items;

import static org.example.options.TestOptions.drop100Percent;

public class IceDemon extends EnemyLevelOne {
    public IceDemon() {
        super("Ice_Demon", 20, 25, 100);
    }

    @Override
    public int getMainHealth() {
        return 1;
    }

    @Override
    protected void addDrop() {
        for (int i = 0; i < (drop100Percent ? 1000 : 333); i++) {
            this.drop[i] = Items.AMULET_OF_CHOICE.getItem();
        }
    }
}
