package org.example.enemy.model.lvl1;

import org.example.enemy.model.AbstractEnemy;

import static org.example.options.TestOptions.expValue;


public abstract class EnemyLevelOne extends AbstractEnemy {
    public EnemyLevelOne(String name, int minDamage, int maxDamage, int experienceValue) {

        super(name, minDamage, maxDamage, 1, experienceValue * expValue());
    }
}
