package org.example.enemy.model.lvl1.impl;

import org.example.enemy.model.lvl1.EnemyLevelOne;

public class Pirate extends EnemyLevelOne {
    public Pirate() {
        super("Pirate", 8, 12, 40);
    }

    @Override
    public int getMainHealth() {
        return 100;
    }
}
