package org.example.enemy.model.lvl1.impl;

import org.example.enemy.model.lvl1.EnemyLevelOne;

public class Zombie extends EnemyLevelOne {
    public Zombie() {
        super("Zombie", 15, 20);
    }

    @Override
    public int getMainHealth() {
        return 50;
    }

}
