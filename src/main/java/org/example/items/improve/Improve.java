package org.example.items.improve;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class Improve {
    protected final ImproveType improveType;
    protected final int amount;
}
