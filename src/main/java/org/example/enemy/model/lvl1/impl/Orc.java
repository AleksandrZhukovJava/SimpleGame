package org.example.enemy.model.lvl1.impl;

import org.example.enemy.model.lvl1.EnemyLevelOne;

public class Orc extends EnemyLevelOne {
    public Orc() {
        super("Orc", 7, 11, 25);
    }

    @Override
    public int getMainHealth() {
        return 100;
    }
}
