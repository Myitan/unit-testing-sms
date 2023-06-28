package com.epam.training.sms;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class App {

    public static void main(String[] args) throws Exception {
        System.out.println("Please enter plaintext");

        String plaintext = new BufferedReader(new InputStreamReader(System.in)).readLine();

        Encoder encoder = EncoderFactory.getSmsEncoder();

        String ciphertext = encoder.encode(plaintext);
        String decoded = encoder.decode(ciphertext);

        System.out.printf("plaintext: %s%nciphertext: %s%ndecoded: %s", plaintext, ciphertext, decoded);
    }
}
