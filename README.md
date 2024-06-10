[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/ecp4su41)
## Group Info
Group Members: Riasat Audhy, Aaron Coyne

Group Name: Riassian Coynerad

## Overview
We implemented the VIC cipher, a pen and paper Soviet-era cipher.

We implemented encoding with the entire algorithm detailed [here](https://en.wikipedia.org/wiki/VIC_cipher) and [here](http://www.quadibloc.com/crypto/pp1324.htm). However, we were unable to fully implement decoding, as undoing columnar transpositions* (one of our steps) ended up being much more complicated than we expected. 

Additionally, the online encoding and decoding tools for this cipher did not implement the entire cipher, and only followed a small portion of the algorithms in described in the websites linked above. Because of our issues with decoding the entire algorithm and the issues with online tools, we decided to implement a simpler encode and decode only using the portion of the algorithm (Straddling Checkerboard*) that also works with the online tool below.

Compatible Online Tool: https://www.cachesleuth.com/vic.html

Presentation Link: https://github.com/Stuycs-K/final-project-09-coyne-aaron-audhy-riasat/blob/main/PRESENTATION.md

Video Link: https://drive.google.com/file/d/1Mf47vC4QTMn0RqMwSXsrmllL_rS7WwQy/view?usp=sharing

Note - Video is ~15 minutes long .

There is also about 15 seconds that is part of a section of recording we meant to crop out but missed while editing.

*These are both explained in the presentation file.
## Instructions

Before running anything:

```javac Main.java```

Full Encryption:

```java Main "encode" "[phrase]" "[date]" "[personalNumber]" "[keyGroup]" "[message]" "[commonLetters]"```

[phrase] - >=20 digit phrase. NOTE - ALL CAPITAL LETTERS, NO SPACES (Eg. TWASTHENIGHTBEFORECHRISTMAS)

[date] - MMDDYYYY (NO LEADING ZEROS)

[personalNumber] - 1 or 2 digit number

[keyGroup] - 5 digit number

[message] - Message to be encrypted

[commonLetters] - permutation for the straddling checkerboard. "AT ONE SIR" is commonly used. A permutation consists of 7 Unique letters and two blank spaces.

Simple Encryption:

```java Main "encode" "[message]" "[commonLetters]"```

Simple Decryption:

```java Main "decode" "[cipherText]" "[commonLetters]"```

[cipherText] - DO NOT INCLUDE SPACES. Should consist of numbers, not letters.
