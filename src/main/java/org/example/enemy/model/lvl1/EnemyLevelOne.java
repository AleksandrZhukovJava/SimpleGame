package org.example.enemy.model.lvl1;

import org.example.enemy.model.AbstractEnemy;

import static org.example.enemy.model.TestConstant.EXPERIENCE_X10;

public abstract class EnemyLevelOne extends AbstractEnemy {
    public EnemyLevelOne(String name, int minDamage, int maxDamage, int experienceValue) {

        super(name, minDamage, maxDamage, 1, experienceValue * EXPERIENCE_X10.getMultiplication());
    }
}
