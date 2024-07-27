package org.example.model.enemy.lvl1;

import org.example.model.enemy.AbstractEnemy;

public abstract class EnemyLevelOne extends AbstractEnemy {
    public EnemyLevelOne(String name, int health, int damage) {
        super(name, health, damage, 1);
    }
}
