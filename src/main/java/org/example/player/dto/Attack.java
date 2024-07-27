package org.example.player.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Attack {
    private int damage;
    private boolean critical;
}
