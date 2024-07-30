package org.example.options;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BattleSpeed {
    SLOW(500),
    MIDDLE(250),
    FAST(100),
    TEST(10);
    private final int turnSpeed;
}
