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
- 8 common letters

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
6. Apply chain addition to the number in step 5, except this time only keep the newly generated 10 digits. Repeat this 4 more times, each time just keeping the newly generated digits, for a total of 50 pseudo-random digits.

   
### PART 2: CREATING THE STRADDLING CHECKERBOARD
![image](https://github.com/Stuycs-K/final-project-09-coyne-aaron-audhy-riasat/assets/88259584/32934e29-437e-4162-aaf4-cebeab221f94)

#### Components:
- Key (top row)
- Common letters (second row)
- Remaining letters, '.', and '/' (third row)
- Left Column

#### Steps:
1. The 10 digit key at the top is created by sequencing the last 10 degits generated in the pseudo random block (step 6 above).
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

```
EX: Encoding "THE 81ST NUM." with the checkerboard below

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


![image](https://github.com/Stuycs-K/final-project-09-coyne-aaron-audhy-riasat/assets/88259584/0463c08e-0057-4cd1-bf78-a17c36d612a9)



### PART 3: ENCRYPTING THE MESSAGE

1. Encode the message following the steps in part 2.
2. Generate `A` and `B`
   - `A` is the second to last digit of the pseudo random block added to the personal number.
   - `B` is the last digit added to the personal number.
   - If the digit for `A` is the same as the one for `B`, go back one until it is different and use that digit instead.
3. Do a standard columnar transposition of the 50 digit pseudo-random block.
   - First list out the digits in 10 digit rows.
   - Take the number from step 5 of part 1, generated right before the 50 digit block and use it as a key.
   - Turn the columns into rows in ascending order (0 is last) of the key digit that each column coresponds to, prioritizing left over right.
     ```
     EX: (This is just showing how the transposition works)
     Key: 6 3 2 4 4 1 
          W E A R E D
          I S C O V E
          R E D F L E
          E A T O N C
          E Q K J E U

     Transposes To (the number on the left is just to show the key and NOT kept):
     
     1 D E E C U
     2 A C D T K
     3 E S E A Q
     4 R O F O J
     4 E V L N E
     6 W I R E E

     Now just read the letters from the new rows from top right to bottom left for the transposed text.
     ```
4. Take the last `A+B` digits of this transposed block.
   - The first `A` digits are used as the key for the next transposition (step 5).
   - The remaining `B` digits are used as the key for the following one (step 6).
5. Do a standard columnar transposition of the encoded message with the first key from step 4.
6. Do a diagonal columnar transposition of the resulting text with the second key from step 4.
   - List out the rows the same way as you would at the start of a standard transposition, but do the following:
     - When you list out the first row, stop the row at the digit prior to the one that would correspond to the lowest key digit.
     - Start the next row and continue listing out the numbers, but make the row 1 digit longer than the previous one.
     - Repeat until you have a row that is back to the key length.
     - Do the last 3 bullet points again, but stop at the 2nd lowest key digit instead of the first.
     - Repeat with the 3rd lowest and so on, until you have as many rows as you would have if you listed them out like normal and every row was full.
     - Go back to the first row and fill up these "triangular" gaps with the remaining digits.
       ```
       Ex: (Simplified to demonstrate)

       Normal way to list out the rows:
       Key: 6 3 2 4 4 1 
            W E A R E D
            I S C O V E
            R E D F L E
            E A T O N C
            E Q K J E U

       How to list for diagonal (before filling in):
       Key: 6 3 2 4 4 1 
            W E A R E 
            D I S C O V
            E R 
            E D F
            L E E A

       After filling in:
       Key: 6 3 2 4 4 1 
            W E A R E T
            D I S C O V
            E R O N C E
            E D F Q K J
            L E E A E U
       ```
   - Do a standard columnar transposition to this new grid.
7. Insert the keygroup `N*5` digits from the end of the cipher text.
   - `N` is the 6th (unused) digit of the date.
  

### PART 4: DECRYPTING CIPHER TEXT

1. Extract the keygroup by looking at the 6th digit of the date.
2. Go through the steps in part 1 and steps 2-3 of part 3 to generate the necessary numbers for the transposition and checkerboard keys.
3. Transposition stuff????
4. Transposition stuff????
5. Generate the straddling checkerboard following part 2.
6. Go through the digits of the cipher text in order to decrypt with the checkerboard:
   - If the next two digits are 80, substitute every 3 following digits with the number being repeated, until the next 80.
     ```
     EX: 8033344499980

     80
     333 -> 3
     444 -> 4
     999 -> 9
     80
     
     Decodes To: 3498
     ```
   - If a digit is one of the numbers in the left column, look at the next digit and the substitute both with the letter corresponding to the second digit in the row of the first digit.
   - Otherwise replace the digit with the letter corresponding to that digit in the key from the second row of the checkerboard.
     ```
     EX: 66269166296274 (Using the checkerboard below)

     66 -> D
      2 -> E
     69 -> C
      1 -> O
     66 -> D
      2 -> E
      9 -> T
     62 -> H
      7 -> I
      4 -> S

     Decodes To: "DECODETHIS"
     ```
![image](https://github.com/Stuycs-K/final-project-09-coyne-aaron-audhy-riasat/assets/88259584/9111ce35-bcfd-4f54-a630-059b91c93712)


### NOTES

- The VIC Cipher is very secure for a pen and paper cipher. However, it should not be used anymore because of two issues excluding how relatively insecure it is compared to modern digital ciphers:
  - The algorithm was publicly disclosed.
  - The secret keys needed can be brute forced with modern computers.
