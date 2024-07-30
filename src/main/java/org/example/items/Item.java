package org.example.items;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.example.items.improve.Improve;

import java.util.List;


@Getter
@EqualsAndHashCode
@ToString
public class Item {
    private final String name;
    private final String imagePath;
    private final ItemType type;
    private final List<Improve> improves;

    public Item(String name, ItemType type, List<Improve> improves) {
        this.name = name;
        this.imagePath = getPathFromName();
        this.type = type;
        this.improves = improves;
    }

    private String getPathFromName() {
        return "/static/inventory/equipment/%s.jpg".formatted(name.replace(" ", "_").toLowerCase());
    }
}
