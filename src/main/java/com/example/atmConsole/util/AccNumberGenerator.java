package com.example.atmConsole.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class AccNumberGenerator {
    public int generateAccount() {
        Random random = new Random();
        int randomNumber = random.nextInt(900000000) + 100000000; // Generate a 9-digit random number
        Set<Integer> generatedNumbers = new HashSet<>();
        generatedNumbers.add(randomNumber);

        return randomNumber;
    }
}
