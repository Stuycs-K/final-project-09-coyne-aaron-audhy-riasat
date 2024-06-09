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

