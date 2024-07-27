package org.example.model.enemy.lvl1.impl;

import org.example.model.enemy.lvl1.EnemyLevelOne;

public class Zombie extends EnemyLevelOne {
    public Zombie() {
        super("Zombie", 50, 15);
    }

    @Override
    public int getHealth() {
        return 50;
    }

}
