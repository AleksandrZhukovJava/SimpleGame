package org.example.enemy.model.lvl1;

import org.example.enemy.model.AbstractEnemy;

public abstract class EnemyLevelOne extends AbstractEnemy {
    public EnemyLevelOne(String name, int damage) {
        super(name, damage, 1);
    }
}
