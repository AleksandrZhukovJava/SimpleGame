package org.example.enemy;

import org.example.enemy.model.lvl1.AbstractEnemy;
import org.example.enemy.model.lvl1.impl.Boss;
import org.example.enemy.model.lvl1.impl.Orc;
import org.example.enemy.model.lvl1.impl.Skeleton;
import org.example.enemy.model.lvl1.impl.Zombie;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyStorage {
    List<AbstractEnemy> enemy_lvl_1 = new ArrayList<>();

    public EnemyStorage() {
        enemy_lvl_1.add(new Orc());
        enemy_lvl_1.add(new Skeleton());
        enemy_lvl_1.add(new Zombie());
        enemy_lvl_1.add(new Boss());
    }

    public AbstractEnemy getRandomLvlOneEnemy(){
        return enemy_lvl_1.get(new Random().nextInt(0, enemy_lvl_1.size()));
    }
}
