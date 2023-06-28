package com.epam.training.sms;

public class EncoderFactory {
    private EncoderFactory() {
    }

    public static Encoder getSmsEncoder() {
        return new SmsEncoder();
    }
}
