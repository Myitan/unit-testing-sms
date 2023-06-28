package com.epam.training.sms;

public interface Encoder {
    String encode(String plaintext);
    String decode(String ciphertext);
}
