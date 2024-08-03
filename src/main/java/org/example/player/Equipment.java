package org.example.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.items.Item;
import org.example.items.ItemType;
import org.example.items.improve.Improve;
import org.example.items.improve.ImproveTarget;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Equipment {
    private Map<ItemType, Item> equipment = new HashMap<>();

    public int getAdditionalAmount(ImproveTarget type) {
        return equipment.values().stream()
                .flatMap(items -> items.getImproves().stream())
                .filter(improve -> improve.getImproveTarget() == type)
                .map(Improve::getAmount)
                .reduce(0, Integer::sum);
    }
}
