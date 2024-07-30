package org.example.options;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Deprecated
@RequiredArgsConstructor
@Getter
public class TestOptions {
    private static final int gameSpeed = 1;
    private static final int expValue = 1;
    public static final boolean drop100Percent = true;

    public static int expValue() {
        return expValue;
    }

    public static int gameSpeed() {
        return gameSpeed;
    }
}
