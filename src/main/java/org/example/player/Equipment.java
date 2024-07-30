package org.example.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.items.Item;
import org.example.items.ItemType;
import org.example.items.improve.Improve;
import org.example.items.improve.ImproveType;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Equipment {
    private Map<ItemType, Item> equipment = new HashMap<>();

    public int getAdditionalAmount(ImproveType type) {
        return equipment.values().stream()
                .flatMap(items -> items.getImproves().stream())
                .filter(improve -> improve.getImproveType() == type)
                .map(Improve::getAmount)
                .reduce(0, Integer::sum);
    }
}
