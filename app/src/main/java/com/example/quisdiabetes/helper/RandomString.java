package com.example.quisdiabetes.helper;

import java.util.UUID;

public class RandomString {
    public static String generateRandomStringByUUID() {
        return UUID.randomUUID().toString();
    }

    public static String generateRandomStringByUUIDNoDash() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
