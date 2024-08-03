package org.example.items.improve.impl;

import org.example.items.improve.Improve;
import org.example.items.improve.ImproveTarget;

public class MissChanceImprove extends Improve {
    public MissChanceImprove(int amount) {
        super(ImproveTarget.MISSING_CHANCE, amount);
    }
}
