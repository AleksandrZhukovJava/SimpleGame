package org.example.items.improve.impl;

import org.example.items.improve.Improve;
import org.example.items.improve.ImproveTarget;

public class AttackImprove extends Improve {
    public AttackImprove(int amount) {
        super(ImproveTarget.ATTACK, amount);
    }
}
