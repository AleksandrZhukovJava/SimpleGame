package org.example.model.enemy.lvl1.impl;

import org.example.model.enemy.lvl1.EnemyLevelOne;

public class Skeleton extends EnemyLevelOne {
    public Skeleton() {
        super("Skeleton", 80, 10);
    }

    @Override
    public int getHealth() {
        return 80;
    }
}
