package com.example.flashcards.ui.practice;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReplyQualityTest {

    @Test
    public void valueOf() {
        assertEquals(ReplyQuality.LOW, ReplyQuality.valueOf(1));
        assertEquals(ReplyQuality.MEDIUM, ReplyQuality.valueOf(3));
        assertEquals(ReplyQuality.HIGH, ReplyQuality.valueOf(5));
    }

    @Test
    public void getValue() {
        assertEquals(1, ReplyQuality.LOW.getValue());
        assertEquals(3, ReplyQuality.MEDIUM.getValue());
        assertEquals(5, ReplyQuality.HIGH.getValue());
    }
}