package com.example.flashcards.srm;

import com.example.flashcards.data.entities.Card;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class TestTest {

    @Test
    public void test() {
        Testing t = new Testing();
        System.out.println(
                t.calculateSuperMemo2Algorithm(
                        new Card("f", "f", 1, new Date()), 3).toString());
    }
}