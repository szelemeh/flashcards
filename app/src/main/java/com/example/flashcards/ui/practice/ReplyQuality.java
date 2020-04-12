package com.example.flashcards.ui.practice;

import java.util.HashMap;
import java.util.Map;

public enum ReplyQuality {
    LOW(1),
    MEDIUM(3),
    HIGH(5);

    private int value;
    private static Map<Integer, ReplyQuality> map = new HashMap<>();

    private ReplyQuality(int value) {
        this.value = value;
    }

    static {
        for (ReplyQuality q : ReplyQuality.values()) {
            map.put(q.value, q);
        }
    }

    public static ReplyQuality valueOf(int q) {
        return map.get(q);
    }

    public int getValue() {
        return value;
    }
}
