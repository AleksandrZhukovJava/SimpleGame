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
    LVL10(2000),
    LVL11(2400),
    LVL12(2900),
    LVL13(3500),
    LVL14(4100),
    LVL15(4800),
    LVL16(5500),
    LVL17(6300),
    LVL18(7100),
    LVL19(8000),
    LVL20(8900);
    private final int experienceNeeded;
}
