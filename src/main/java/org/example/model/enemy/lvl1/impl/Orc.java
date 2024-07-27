package org.example.model.enemy.lvl1.impl;

import org.example.model.enemy.lvl1.EnemyLevelOne;

public class Orc extends EnemyLevelOne {
    public Orc() {
        super("Orc", 100, 9);
    }

    @Override
    public int getHealth() {
        return 100;
    }

}
