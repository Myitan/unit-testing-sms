# Introduction

Early phones with 3x4 numeric keypads often used the following method to input text:

The keys have a certain number of letters associated with them.
Selecting a letter consists of the user pressing the appropriate key as many times as the letter's index is on the key.

The letter selection is committed when one of the following happens:

*   The user starts entering a letter on a different key.
*   A certain amount of time has elapsed.

## Mobile phone keypad

See the table of standard keypad below.

![](https://raw.githubusercontent.com/epam-java-cre/exercise-specification-images/main/unit-testing-sms/phone-keypad.png)


## Examples

Example encoded input sequences and their corresponding decoded messages:
 
| Text | Keycodes |
|------|----------|
| go   | 4666     |
| java | 528882   |
| bar  | 22 2777  |

## Example console input and output

    Please enter plaintext: java
    plaintext: java
    ciphertext: 528882
    decoded: JAVA (uppercase)

## Implementation 

`SmsEncoder` class defines two methods:

- `String encode(String plaintext)` - encodes the text passed as parameter;
   throws `IllegalArgumentException` if the text contains a character that can not be coded (e.g. 'é').
- `String decode(String ciphertext)` - decodes the SMS keycodes;
  throws `IllegalArgumentException` if the text contains characters that can not be recognized as keys.

# Task

Your task is to write unit test for the `SmsEncoder` methods. Please cover the following cases
- Encoding of character that is mapped to single key ('a' -> 2)
- Encoding of character that is mapped to double key ('b' -> 22)
- Encoding of multiple characters
- Encoding of multiple characters that are mapped to the same key, so requires space in the encoded text
- Empty input
- Invalid input

Please write similar tests for the decode method.
