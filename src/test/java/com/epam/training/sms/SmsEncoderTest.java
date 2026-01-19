package com.epam.training.sms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmsEncoderTest {
    private SmsEncoder smsEncoder;

    @BeforeEach
    public void setupEncoder(){
        smsEncoder = new SmsEncoder();
    }

    @DisplayName("Testing encode of character that is mapped to single key ('a' -> 2)")
    @Test
    public void testEncodeOfOneCharThatIsMappedToSingleKey() {
        String input = "a";
        String expected = "2";
        String actual = smsEncoder.encode(input);

        assertEquals(expected, actual, "Characters do not match!");
    }

    @DisplayName("Testing encode of character that is mapped to double key ('b' -> 22)")
    @Test
    public void testEncodeOfTwoCharThatIsMappedToDoubleKey(){
        String input = "b";
        String expected = "22";
        String actual = smsEncoder.encode(input);

        assertEquals(expected, actual,"Characters do not match!");
    }

    @DisplayName("Testing encode of characters that are mapped to the same key ('bb' -> 22 22) ")
    @Test
    public void testEncodeOfTwoIdenticalCharactersThatAreMappedToTheSameKey(){
        String input = "bb";
        String expected = "22 22";
        String actual = smsEncoder.encode(input);

        assertEquals(expected,actual,"Characters do not match");
    }

    @DisplayName("Testing encode of multiple characters")
    @Test
    public void testEncodeOfMultipleCharsThatBelongToDiffrentKeys(){
        String input = "ad";
        String expected = "23";
        String actual = smsEncoder.encode(input);

        assertEquals(expected,actual,"Characters do not match");
    }

    @DisplayName("Testing encode of multiple characters that are mapped to the same key, so requires space in the encoded text")
    @Test
    public void testEncodeManyCharsThatAreMappedToTheSameKeySeparatedWithSpace(){
        String input = "n o m6";
        String expected = "66066606 6666";
        String actual = smsEncoder.encode(input);

        assertEquals(expected,actual,"Characters do not match");
    }

    @DisplayName("Testing encode of empty input")
    @Test
    public void testEncodeOfEmptyInput(){
        assertEquals("",smsEncoder.encode(""));
    }

    @DisplayName("Testing encode of invalid input")
    @Test
    public void testEncodeOfInvalidInput(){
        String input = "Ą";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            smsEncoder.encode(input);
        });

        assertTrue(exception.getMessage().contains("Substitute string not found"),
                "Test");
    }

    @DisplayName("Testing decode of character that is mapped to single key (2 -> 'a') ")
    @Test
    public void testDecodeOfOneCharThatIsMappedToSingleKey() {
        String input = "2";
        String expected = "a";
        String actual = smsEncoder.decode(input);

        assertEquals(expected, actual, "Characters do not match!");
    }

    @DisplayName("Testing decode of character that is mapped to double key (22 -> 'b')")
    @Test
    public void testDecodeOfTwoCharThatIsMappedToDoubleKey(){
        String input = "22";
        String expected = "b";
        String actual = smsEncoder.decode(input);

        assertEquals(expected, actual,"Characters do not match!");
    }

    @DisplayName("Testing decode of characters that are mapped to the same key ('bb' -> 22 22) ")
    @Test
    public void testDecodeOfTwoIdenticalCharactersThatAreMappedToTheSameKey(){
        String input = "22 22";
        String expected = "bb";
        String actual = smsEncoder.decode(input);

        assertEquals(expected,actual,"Characters do not match");
    }

    @DisplayName("Testing decode of multiple characters")
    @Test
    public void testDecodeOfMultipleChars(){
        String input = "23";
        String expected = "ad";
        String actual = smsEncoder.decode(input);

        assertEquals(expected,actual,"Characters do not match");
    }

    @DisplayName("Testing decode of multiple characters that are mapped to the same key, so requires space in the encoded text")
    @Test
    public void testDecodeOfManyCharsThatAreMappedToTheSameKeySeparatedWithSpace(){
        String input = "66066606 6666";
        String expected = "n o m6";
        String actual = smsEncoder.decode(input);

        assertEquals(expected,actual,"Characters do not match");
    }
    @DisplayName("Testing decode of empty input")
    @Test
    public void testDecodeOfEmptyInput(){
        assertEquals("",smsEncoder.decode(""));
    }

    @DisplayName("Testing decode of invalid input")
    @Test
    public void testDecodeOfInvalidInput(){
        String input = "14829ag95842";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            smsEncoder.decode(input);
        });

        assertTrue(exception.getMessage().contains("Ciphertext must match"),
                "Error: trying to decode invalid input");
    }
}