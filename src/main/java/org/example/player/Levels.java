package org.example.player;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.logging.Level;

@RequiredArgsConstructor
@Getter
public enum Levels {
    LVL1(100),
    LVL2(200),
    LVL3(300),
    LVL4(450),
    LVL5(600),
    LVL6(800),
    LVL7(1000),
    LVL8(1300),
    LVL9(1600),
    LVL10(2000);
    private final int experienceNeeded;
}
