package org.example.items.improve;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class Improve {
    protected final ImproveTarget improveTarget;
    protected final int amount;

    public String getDescription() {
        return "%s: %s%s".formatted(
                improveTarget.getDescription(),
                "+", //todo знак должен менятся
                amount);
    }
}
