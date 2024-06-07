# Work Log

## AARON COYNE

### 2024-05-23

We read into VIC, blowfish, and Solitaire ciphers and decided on doing our project with VIC.

### 2024-05-24

Was absent from class.

At home, I watched this video on the VIC cipher:
https://www.youtube.com/watch?v=LeMD-XFgtVc

### 2024-05-28

Did a lot of research on each aspect of the VIC cipher (Straddling Checkerboard, Chain Addition, Modular addition / subtraction) and began planning out how we would do it in Java.

### 2024-05-29

Continued learning about the components.

### 2024-05-30

Working on implementing digit encode. Will implement sequencing next.

### 2024-06-03

Finished digit encode & working on sequencing.

## RIASAT AUDHY

### 2024-05-23

We read into VIC, blowfish, and Solitaire ciphers and decided on doing our project with VIC.

### 2024-05-24

https://web.stanford.edu/class/archive/cs/cs108/cs108.1092/JavaBasicRefresher.pdf

Read through this document in class to get refreshed on using Java for the project.

### 2024-05-28

We started breaking down how we would implement the cipher, and learned how a bunch of the components work (Straddling Checkerboard, Chain Addition, Modular addition / subtraction).

### 2024-05-29

Continued learning about the components.

### 2024-05-30

Created the files to start coding. Currently working on implementing digit by digit modular (base 10) addition and subtraction. Will implement chain addition next.

### 2024-06-03

Finished implementing modAddition and modSubtraction. Halfway done with chain addition.

### 2024-06-05

Implemented chain addition.

### 2024-06-07

Before Class:
Re-implemented modAddition, modSubtraction, and chain addition in int[] to work better with some of the stuff Aaron has written (also because it looks like this'll be better long term)
Mapping out how to implement the straddling checkerboard.
Finished the plan:
Implementation Steps:
        MAKING THE GRID:
        1. Make a char[] containing the commonLetters called commonLetters[]
        2. Make a int[2] called columns.
        3. Look through commonLetters[] for the 2 indexes with ' '(ASCII 32) and set columns[i] = key[index]
        4. Make find(char[] text, char c) which returns the index of c in text or -1 if it's not there.
        5. Make char[] row1 and char[] row2
        6. Loop through the letters in the alphabet, and if a letter is not found in commonLetters[] then add it to row1, or if it's full, row2,
           Then add . and / to the end of row2.
        
        USING THE GRID:
        7. Make an arrayList called cipherText.
        8. Loop through plainText:
            - Use find() to check for the characters in commonLetters[], row1[], row2[] in that order
            - If found in commonLetters[] just append the corresponding index's number from key[] to the arrayList
            - If found in row1[] or row2[], precede that with the appropriate column[] number
            - If its a digit, do 80 followed by the digit 3x
                - Do this IF this is the first character of the text OR the previous one was not a digit
                - Add an 80 after as well IF the next character is NOT a digit OR this is the last digit
        9. Make an int[] with the length of the arrayList and transfer the contents
        10. Return the int[]
Working on implementing.  
