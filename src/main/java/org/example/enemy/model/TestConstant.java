package org.example.enemy.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Deprecated
@RequiredArgsConstructor
@Getter
public enum TestConstant {
    EXPERIENCE_X1(1),
    EXPERIENCE_X3(3),
    EXPERIENCE_X5(5),
    EXPERIENCE_X10(10);
    private final int multiplication;
}
