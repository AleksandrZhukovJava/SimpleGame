package org.example.enemy.model.lvl1.impl;

import org.example.enemy.model.lvl1.EnemyLevelOne;

public class Boss extends EnemyLevelOne {
    public Boss() {
        super("Boss", 15, 30, 200);
    }

    @Override
    public int getMainHealth() {
        return 300;
    }
}
