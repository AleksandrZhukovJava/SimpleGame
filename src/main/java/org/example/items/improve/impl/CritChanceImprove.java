package org.example.items.improve.impl;

import org.example.items.improve.Improve;
import org.example.items.improve.ImproveType;

public class CritChanceImprove extends Improve {
    public CritChanceImprove(int amount) {
        super(ImproveType.CRITICAL_CHANCE, amount);
    }
}
