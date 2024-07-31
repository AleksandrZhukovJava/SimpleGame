package org.example.items;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.items.improve.impl.AttackImprove;
import org.example.items.improve.impl.CritChanceImprove;
import org.example.items.improve.impl.HealthImprove;
import org.example.items.improve.impl.LvlUpChoiceAmountImprove;
import org.example.items.improve.impl.MissChanceImprove;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.example.items.ItemType.AMULET;
import static org.example.items.ItemType.ARMOR;
import static org.example.items.ItemType.BOOTS;
import static org.example.items.ItemType.HELM;
import static org.example.items.ItemType.LEFT_HAND;
import static org.example.items.ItemType.PANTS;
import static org.example.items.ItemType.RIGHT_HAND;

@AllArgsConstructor
@Getter
public enum Items {
    GIANT_AXE(new Item("Giant Axe",
            LEFT_HAND,
            List.of(new AttackImprove(20)))),
    SMALL_AXE(new Item("Small Axe",
            LEFT_HAND,
            List.of(new AttackImprove(7)))),
    NECKLACE(new Item("Necklace",
            AMULET,
            List.of(new CritChanceImprove(5)))),
    MAGIC_NECKLACE(new Item("Magic necklace",
            AMULET,
            List.of(new CritChanceImprove(15),
                    new MissChanceImprove(5)))),
    NORMAL_SHIELD(new Item("Normal shield",
            RIGHT_HAND,
            List.of(new HealthImprove(5)))),
    MAGIC_SHIELD(new Item("Magic shield",
            RIGHT_HAND,
            List.of(new HealthImprove(50),
                    new MissChanceImprove(5),
                    new LvlUpChoiceAmountImprove()))),
    AMULET_OF_LUCK(new Item("Amulet of luck",
            AMULET,
            List.of(new CritChanceImprove(10),
                    new MissChanceImprove(10)))),
    CLUB(new Item("club",
            LEFT_HAND, List.of(new AttackImprove(2)))),
    AMULET_OF_CHOICE(new Item("Amulet of choice",
            AMULET, List.of(new LvlUpChoiceAmountImprove()))),
    ARMORY_PANTS(new Item("Armory pants",
            PANTS, List.of(new HealthImprove(15)))),
    HEAVY_ARMOR(new Item("Heavy armor",
            ARMOR, List.of(new HealthImprove(80)))),
    DEMON_HELM(new Item("Demon helm",
            HELM, List.of(new CritChanceImprove(30)))),
    MAGIC_BOOTS(new Item("Magic boots",
            BOOTS, List.of(new CritChanceImprove(10),
            new MissChanceImprove(10)))),
    SIMPLE_BOOTS(new Item("Simple boots",
            BOOTS, List.of(new HealthImprove(5)))),
    FIRE_CHAINMALE(new Item("Fire chainmale",
            ARMOR, List.of(new HealthImprove(30),
            new AttackImprove(5))));
    private final Item item;

    public static List<Item> getAllItems() {
        return new ArrayList<>(Arrays.stream(values())
                .map(it -> it.item)
                .toList());
    }

    public static Item getRandomItem() {
        return values()[new Random().nextInt(0, values().length - 1)].item;
    }
}
