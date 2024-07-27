package org.example.enemy.model.lvl1.impl;

import org.example.enemy.model.lvl1.EnemyLevelOne;

public class Elemental extends EnemyLevelOne {
    public Elemental() {
        super("Elemental", 8, 60);
    }

    @Override
    public int getMainHealth() {
        return 200;
    }
}
