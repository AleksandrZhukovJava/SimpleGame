package org.example.enemy.model.lvl1.impl;

import org.example.enemy.model.lvl1.EnemyLevelOne;

public class Elemental extends EnemyLevelOne {
    public Elemental() {
        super("Elemental", 8);
    }

    @Override
    public int getHealth() {
        return 200;
    }

}
