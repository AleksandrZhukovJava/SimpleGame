package org.example.enemy.model.lvl1.impl;

import org.example.enemy.model.lvl1.EnemyLevelOne;

public class Skeleton extends EnemyLevelOne {
    public Skeleton() {
        super("Skeleton", 8, 12, 16);
    }

    @Override
    public int getMainHealth() {
        return 70;
    }
}
