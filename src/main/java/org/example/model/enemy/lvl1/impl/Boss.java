package org.example.model.enemy.lvl1.impl;

import org.example.model.enemy.lvl1.EnemyLevelOne;

public class Boss extends EnemyLevelOne {
    public Boss() {
        super("Boss", 200, 90);
    }

    @Override
    public int getHealth() {
        return 200;
    }
}
