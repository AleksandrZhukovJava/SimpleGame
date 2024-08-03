package org.example.items.improve.impl;

import org.example.items.improve.Improve;
import org.example.items.improve.ImproveTarget;

public class HealthImprove extends Improve {
    public HealthImprove(int amount) {
        super(ImproveTarget.HEALTH, amount);
    }
}
