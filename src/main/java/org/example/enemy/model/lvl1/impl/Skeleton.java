package org.example.enemy.model.lvl1.impl;

import org.example.enemy.model.lvl1.EnemyLevelOne;

public class Skeleton extends EnemyLevelOne {
    public Skeleton() {
        super("Skeleton", 10);
    }

    @Override
    public int getHealth() {
        return 80;
    }
}
