package org.example.enemy.model.lvl1.impl;

import org.example.enemy.model.lvl1.EnemyLevelOne;

public class Boss extends EnemyLevelOne {
    public Boss() {
        super("Boss", 20);
    }

    @Override
    public int getHealth() {
        return 200;
    }
}
