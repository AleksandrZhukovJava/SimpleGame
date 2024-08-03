package org.example.items.improve.impl;

import org.example.items.improve.Improve;
import org.example.items.improve.ImproveTarget;

public class CritChanceImprove extends Improve {
    public CritChanceImprove(int amount) {
        super(ImproveTarget.CRITICAL_CHANCE, amount);
    }
}
