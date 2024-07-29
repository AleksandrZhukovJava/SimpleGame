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
    LVL20(9000),
    LVL21(10000),
    LVL22(11100),
    LVL23(12200),
    LVL24(13400),
    LVL25(14600),
    LVL26(15900),
    LVL27(17200),
    LVL28(18600),
    LVL29(20000),
    LVL30(21500);
    private final int experienceNeeded;
}
