package org.example.battle;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BattleSpeed {
    SLOW(500),
    MIDDLE(300),
    FAST(100),
    TEST(10);
    private final int turnSpeed;
}
