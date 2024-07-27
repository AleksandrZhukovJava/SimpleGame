package org.example.enemy.model;

import org.example.enemy.model.lvl1.impl.Boss;
import org.example.enemy.model.lvl1.impl.Elemental;
import org.example.enemy.model.lvl1.impl.Elf;
import org.example.enemy.model.lvl1.impl.Orc;
import org.example.enemy.model.lvl1.impl.Pirate;
import org.example.enemy.model.lvl1.impl.Skeleton;
import org.example.enemy.model.lvl1.impl.Zombie;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyStorage {
    List<AbstractEnemy> enemy_lvl_1 = new ArrayList<>();

    public EnemyStorage() {
        addPercentWeightEnemy(new Orc(), 10);
        addPercentWeightEnemy(new Skeleton(), 20);
        addPercentWeightEnemy(new Zombie(), 15);
        addPercentWeightEnemy(new Elf(), 5);
        addPercentWeightEnemy(new Pirate(), 5);
        addPercentWeightEnemy(new Elemental(), 2);
        addPercentWeightEnemy(new Boss(), 1);
    }

    public AbstractEnemy getRandomLvlOneEnemy() {
        return enemy_lvl_1.get(new Random().nextInt(0, enemy_lvl_1.size()));
    }

    public void addPercentWeightEnemy(AbstractEnemy abstractEnemy, int times) { //todo подумать как процентно сделать
        for (int i = 0; i < times; i++) {
            enemy_lvl_1.add(abstractEnemy);
        }
    }
}
