# VIC Cipher Presentation
By Riasat Audhy & Aaron Coyne


## HISTORICAL CONTEXT

- Late 19th century Russian Nihilists created and used the Nihilist cipher to organize terrorism against the tsarist regime.
  - This involves a 5 by 5 "Polybius square" to encrypt text (shown below).

    ![image](https://github.com/Stuycs-K/final-project-09-coyne-aaron-audhy-riasat/assets/88259584/11af48c4-f0fe-4122-b904-674094106b7e)

    

- This basic cipher was similar to the Vigenère cipher, and not very secure. However, by World War II, it had evolved into a very strong cipher.
  - The two biggest changes made were that instead of a Polybius square, a straddling checkerboard (shown below) was used and that all addition was done mod 10.

    ![image](https://github.com/Stuycs-K/final-project-09-coyne-aaron-audhy-riasat/assets/88259584/f2b545ef-b6e8-4188-8990-448953392525)



- The final development / evolutionary pinnacle was the VIC cipher that was used in the 1950s by  Reino Häyhänen.
  - It kept the straddling checkerboard & modulo addition.
  - At this time most Soviet angents used one-time pad ciphers.
  - It resisted all cryptanalysis by the NSA until Häyhänen defected.

## HOW IT WORKS

#### Along with the message to be encoded, the following is needed:
- A phrase (20 letters long)
- A date (just numbers with no leading 0s)
- A 1 or 2 digit number
- A 5 digit keygroup

#### These are the general steps:
1. The things listed above are used to create a 50 digit block of pesudo-random numbers.
2. Use the block to create keys for a straddling checkerboard & two columnar transpositions.
3. Encrypt the message using the straddling checkerboard.
4. Apply a regular columnar transposition and a diagonal columnar transposition.
5. Insert the keygroup.


### PART 1: GENERATING THE PSEUDO-RANDOM BLOCK

1. Subtract the first 5 digits of the date from the keygroup by modular (digit by digit) subtraction.
   - This means each digit is subtracted separately, rolling over to 10 at 0 but does not "borrow" from the next column.
     ```
     EX: 36 - 47 = 99
     ```
2. "Sequence" the first and second halves of the phrase separately to create 2 new 10 digit numbers.
   - This means a letter is replaced by a number that is the position it would have when you sort the letters by ascending value.
     ```
     EX: CAACB -> AABCC -> 41253
         41253    12345    CAACB
     ```
3. Extend the 5 digit number from step 1 to 10 digits through "Chain Addition"
   - This works by adding the first two digits modulo 10 to create a new digit, and repeat until you reach the last digit to which you add the first newly generated digit.
     ```
     EX: Start with 69592
     6 + 9 = 15 -> 5
     9 + 5 = 14 -> 4
     5 + 9 = 14 -> 4
     9 + 2 = 11 -> 1
     2 + 5 = 07 -> 7
     New Number: 6959254417
     ```
4. Add the first 10 digit number created in step 2 to the number created in step 3 with digit by digit modular addition.
5. Digit encode the number from step 4 using the second 10 digit number from step 2 as a key.
   - This form of encoding involves matching up a number with the digits from 1 to 0 (shown below) with the key. Then replace the digits in the number being encoded with the key digit that matches up with that digit in the 1-0 number.
     ```
     EX: Encode 4966196060 with 6013589427
     1234567890
     6013589427

     4 -> 3
     9 -> 2
     6 -> 8
     6 -> 8
     1 -> 6
     9 -> 2
     6 -> 8
     0 -> 7
     6 -> 8
     0 -> 7

     Encoded Number:3288628787
     ```
6. Sequence the number from step 5 using the same logic as step 2, except with digits instead of letters.
7. Apply chain addition to the number in step 6, except this time only keep the newly generated 10 digits. Repeat this 4 more times, each time just keeping the newly generated digits, for a total of 50 pseudo-random digits.

   
### PART 2: CREATING THE STRADDLING CHECKERBOARD
![image](https://github.com/Stuycs-K/final-project-09-coyne-aaron-audhy-riasat/assets/88259584/32934e29-437e-4162-aaf4-cebeab221f94)

#### Components:
- Key (top row)
- Common letters (second row)
- Remaining letters, '.', and '/' (third row)
- Left Column

#### Steps:
1. The 10 digit key at the top is created by sequencing the last 10 degits generated in the pseudo random block (step 7 above).
2. The next row is filled with eight high frequency letters and two gaps/spaces.
   - This is so the most common letters encrypt to one digit instead of two, compressing the text a little.
   - Some common mnemonics to remember letters:
     - 'A SIN TO ER(R)'
     - 'AT ONE SIR'
     - 'AERO TINS'
3. The two numbers in the column on the left are determined by the digits in the key corresponding to the two spaces.
4. The remaining two rows are filled with the rest of the letters in the alphabet in order, followed by '.' and '/'.

#### Using the checkerboard:
Go through the characters in the message being encoded in order.
- Ignore spaces.
- Ignore capitalization / assume all letters are capitalized.
- If a letter is found in the first row, it is just substituted by the corresponding key digit.
- If a character is in the second or third row, it's the same except the key digit is preceded by the left column digit from that row.
- If the character is a digit, substitute it with itself repeated three times.
  - Precede and follow any series of digits with 80, indicating that the numbers inbetween represent numbers & not letters.

![image](https://github.com/Stuycs-K/final-project-09-coyne-aaron-audhy-riasat/assets/88259584/0463c08e-0057-4cd1-bf78-a17c36d612a9)

```
EX: Encoding "THE 81ST NUM." with the checkerboard above

T -> 9
H -> 62
E -> 2
80
8 -> 888
1 -> 111
80
S -> 4
T -> 9
N -> 3
U -> 86
M -> 60
. -> 87

Encoded Message: 96228088811180493866087
```

### PART 3: ENCRYPTING THE MESSAGE

1. Encode the message following the steps in part 2.
2. Then
