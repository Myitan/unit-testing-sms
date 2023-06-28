package com.epam.training.sms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmsEncoder {

    // Technically this will not work for code points outside the BMP, but we don't handle those anyway.
    private static final Map<Character, String> charToCode = new HashMap<>();

    static {
        // 0
        charToCode.put(' ', "0");
        charToCode.put('0', "00");
        // 1
        charToCode.put('1', "1");
        // 2
        charToCode.put('A', "2");
        charToCode.put('B', "22");
        charToCode.put('C', "222");
        charToCode.put('2', "2222");
        // 3
        charToCode.put('D', "3");
        charToCode.put('E', "33");
        charToCode.put('F', "333");
        charToCode.put('3', "3333");
        // 4
        charToCode.put('G', "4");
        charToCode.put('H', "44");
        charToCode.put('I', "444");
        charToCode.put('4', "4444");
        // 5
        charToCode.put('J', "5");
        charToCode.put('K', "55");
        charToCode.put('L', "555");
        charToCode.put('5', "5555");
        // 6
        charToCode.put('M', "6");
        charToCode.put('N', "66");
        charToCode.put('O', "666");
        charToCode.put('6', "6666");
        // 7
        charToCode.put('P', "7");
        charToCode.put('Q', "77");
        charToCode.put('R', "777");
        charToCode.put('S', "7777");
        charToCode.put('7', "77777");
        // 8
        charToCode.put('T', "8");
        charToCode.put('U', "88");
        charToCode.put('V', "888");
        charToCode.put('8', "8888");
        // 9
        charToCode.put('W', "9");
        charToCode.put('X', "99");
        charToCode.put('Y', "999");
        charToCode.put('Z', "9999");
        charToCode.put('9', "99999");
    }

    private static final Map<Character, List<Character>> codeToChar = new HashMap<>();

    static {
        codeToChar.put('0', List.of(' ', '0'));
        codeToChar.put('1', List.of('1'));
        codeToChar.put('2', List.of('A', 'B', 'C', '2'));
        codeToChar.put('3', List.of('D', 'E', 'F', '3'));
        codeToChar.put('4', List.of('G', 'H', 'I', '4'));
        codeToChar.put('5', List.of('J', 'K', 'L', '5'));
        codeToChar.put('6', List.of('M', 'N', 'O', '6'));
        codeToChar.put('7', List.of('P', 'Q', 'R', 'S', '7'));
        codeToChar.put('8', List.of('T', 'U', 'V', '8'));
        codeToChar.put('9', List.of('W', 'X', 'Y', 'Z', '9'));
        
    }

    public String encode(String plaintext) {
        StringBuilder result = new StringBuilder();
        for (char c : plaintext.toCharArray()) {
            String currentCode = charToCode.get(c);
            if (currentCode == null) {
                throw new IllegalArgumentException("Substitute string not found for character " + c);
            }
            boolean needsSpace = result.length() != 0 && lastCharacterIsTheSameAsCurrent(result, currentCode);
            if (needsSpace) {
                result.append(" ");
            }
            result.append(currentCode);

        }
        return result.toString();
    }

    private boolean lastCharacterIsTheSameAsCurrent(StringBuilder result, String current) {
        return result.charAt(result.length() - 1) == current.charAt(0);
    }

    public String decode(String ciphertext) {
        if("".equals(ciphertext))
            return "";

        if(!ciphertext.matches("[\\d ]*"))
            throw new IllegalArgumentException("Ciphertext must match [\\d ]*");

        StringBuilder result = new StringBuilder();
        int i;
        for (i = 0; i < ciphertext.length() - 1; i++) {
            if (ciphertext.charAt(i) == ' ') {
                continue;
            }
            int repetitionCount = 1;
            while (i < ciphertext.length() - 1 && ciphertext.charAt(i) == ciphertext.charAt(i + 1)) {
                i++;
                repetitionCount++;
            }
            result.append(getCharByCode(ciphertext.charAt(i), repetitionCount));
        }
        if (i == ciphertext.length() - 1) {
            result.append(getCharByCode(ciphertext.charAt(i), 1));
        }
        return result.toString();
    }

    private char getCharByCode(char code, int repetitionCount) {
        List<Character> mappedCharacters = codeToChar.get(code);
        return mappedCharacters.get(repetitionCount - 1);
    }
}
