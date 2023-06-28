Prerequisites

*   Set up SonarLint in your IDE.

Introduction
============

Early phones with 3x4 numeric keypads often used the following method to input text:

The keys have a certain number of letters associated with them. Selecting a letter consists of the user pressing the appropriate key as many times as the letter's index is on the key.

The letter selection is committed when one of the following happens:

*   The user starts entering a letter on a different key.
*   A certain amount of time has elapsed.
*   The user ends the input session.

The standard keypad is pictured below.

![](https://raw.githubusercontent.com/epam-mep-java/exercise-specification-images/main/clean-code-sms-decode/image2022-7-7_15-32-28.png)

We have created a console application that can transform the representation of these keypresses to their corresponding letters.

Unfortunately, this application was originally written in C and has been ported to Java very poorly, not following Clean Code principles.

We would like to rewrite the application so it follows best practices.

High-level Functionality
========================

*   The application prompts the user for a message to encode
*   The application encodes then decodes the message and prints both.

User Interaction
================

The representation of a user pressing buttons and waiting is the following:

 
| User action | String representation |
| --- | --- |
| Press a button | The button's numeric value |
| Wait | Space character |
| End the input sequence | Enter |

Example encoded input sequences and their corresponding decoded messages:

 
| Input | Message |
| --- | --- |
| 3372607777999777783367777 | EPAM SYSTEMS |
| 222044477770222299999 9999977777999992222444455558888 | C IS 299792458 |

Example console input and output
--------------------------------

    Please enter plaintext
    C IS 299792458
    plaintext: C IS 299792458
    ciphertext: 222044477770222299999 9999977777999992222444455558888
    decoded: C IS 299792458  

Technical Specification
=======================

The following classes are mandatory; without them, the tests won't run.

![](https://raw.githubusercontent.com/epam-mep-java/exercise-specification-images/main/clean-code-sms-decode/model.png)

(package: com.epam.training.sms)
 
|   Class   | Description |
| --- | --- |
| EncoderFactory | A factory that has one static factory method to get an SmsEncoder |
| Encoder | The interface the actual SMS encoder class should implement |

Implementation Details
----------------------

App2 should be deleted when all functionality has been refactored into an Encoder.

*   Encode/Decode should return "" for "" input. (empty string for empty string input)
*   Decode should throw `IllegalArgumentException` when input contains characters other than numbers or spaces.
*   Encode should throw `IllegalArgumentException` when input contains characters other than uppercase letters or spaces.

Acceptance
==========

*   Any string encoded then decoded should be strictly equal. That is: `str.equals(decode(encode(str)))` must always be true.
*   No need to implement lowercase characters

Satisfy the following requirements:

*   good, descriptive names
*   object-oriented structure
*   functions do one thing
*   all the operations in a method are at the same level of abstraction
*   no command & query operations in the same method
*   functions have no side effects
*   DRY
*   no flag arguments
*   no SonarLint errors

Notes
-----

For the time being, Autocode may give maximum points for code quality, even when no Sonar errors are fixed. Ignore the points and focus on fixing all the errors.

You can ignore  **System.out**  warnings from sonarLint.

  

**Document Last Modification: 2022.10.28. 10:16**