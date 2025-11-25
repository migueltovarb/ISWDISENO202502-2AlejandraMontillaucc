package com.proyecto.reservas.utils;

import java.security.SecureRandom;
import java.time.Instant;

public class CodeGenerator {
    private static final String ALPHANUM = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generate(String prefix) {
        StringBuilder sb = new StringBuilder();
        sb.append(prefix).append("-").append(Instant.now().toEpochMilli()).append("-");
        for (int i = 0; i < 6; i++) {
            sb.append(ALPHANUM.charAt(RANDOM.nextInt(ALPHANUM.length())));
        }
        return sb.toString();
    }
}
