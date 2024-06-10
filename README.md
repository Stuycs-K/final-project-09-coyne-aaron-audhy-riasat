[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/ecp4su41)
## Group Info
Group Members: Riasat Audhy, Aaron Coyne
Group Name: Riassian Coynerad
## Overview
We implemented the VIC cipher, a pen and paper Soviet-era cipher.

We implemented encoding with the entire algorithm detailed [here]([url](https://en.wikipedia.org/wiki/VIC_cipher)) and [here]([url](http://www.quadibloc.com/crypto/pp1324.htm)). However, we were unable to fully implement decoding, as undoing columnar transpositions* (one of our steps) ended up being much more complicated than we expected. 

Additionally, the online encoding and decoding tools for this cipher did not implement the entire cipher, and only followed a small portion of the algorithms in described in the websites linked above. Because of our issues with decoding the entire algorithm and the issues with online tools, we decided to implement a simpler encode and decode only using the portion of the algorithm (Straddling Checkerboard*) that also works with the online tool below.

Compatible Online Tool: https://www.cachesleuth.com/vic.html
Presentation Link: https://github.com/Stuycs-K/final-project-09-coyne-aaron-audhy-riasat/blob/main/PRESENTATION.md
Video Link:

*These are both explained in the presentation file.
## Instructions

