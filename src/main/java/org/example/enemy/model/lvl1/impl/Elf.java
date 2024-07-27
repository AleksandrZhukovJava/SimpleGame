package org.example.enemy.model.lvl1.impl;

import org.example.enemy.model.lvl1.EnemyLevelOne;

public class Elf extends EnemyLevelOne {
    public Elf() {
        super("Elf", 80, 90);
    }

    @Override
    public int getMainHealth() {
        return 15;
    }

}
