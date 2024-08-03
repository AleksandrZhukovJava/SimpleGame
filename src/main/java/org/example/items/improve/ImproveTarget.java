package org.example.items.improve;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ImproveTarget {
    ATTACK("attack"),
    HEALTH("health"),
    CRITICAL_CHANCE("cr.chance"),
    MISSING_CHANCE("miss.chance"),
    LVL_UP_CHOICE_AMOUNT_IMPROVE("lvl.up.choiceses");

    private final String description;
}
