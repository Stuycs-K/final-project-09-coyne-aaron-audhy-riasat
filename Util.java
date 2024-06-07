public class Util{
    public static int[] modAddition(int[] a, int[] b){
        int numDigits = 0;
        int temp[];
        if (a.length > b.length){ 
            numDigits = a.length;
            temp = new int[numDigits];
            for(int i = 0; i < numDigits; i++){
                if(i < a.length-b.length) temp[i] = 0;
                else temp[i] = b[i - (b.length-a.length)];
            }
            b = temp;
        }
        else {
            numDigits = b.length;
            temp = new int[numDigits];
            for(int i = 0; i < numDigits; i++){
                if(i < b.length-a.length) temp[i] = 0;
                else temp[i] = a[i - (b.length-a.length)];
            }
            a = temp;
        }

        int sum[] = new int[numDigits];

        for(int i = 0; i < numDigits; i++){
            sum[i] = (a[i] + b[i])%10;
        }

        return sum;
    }

    public static int[] modSubtraction(int[] a, int[] b){
        int numDigits = 0;
        int temp[];
        if (a.length > b.length){ 
            numDigits = a.length;
            temp = new int[numDigits];
            for(int i = 0; i < numDigits; i++){
                if(i < a.length-b.length) temp[i] = 0;
                else temp[i] = b[i - (b.length-a.length)];
            }
            b = temp;
        }
        else {
            numDigits = b.length;
            temp = new int[numDigits];
            for(int i = 0; i < numDigits; i++){
                if(i < b.length-a.length) temp[i] = 0;
                else temp[i] = a[i - (b.length-a.length)];
            }
            a = temp;
        }

        int diff[] = new int[numDigits];

        for(int i = 0; i < numDigits; i++){
            if (a[i] >= b[i]) diff[i] = a[i] - b[i];
            else diff[i] = a[i] + 10 - b[i];
        }

        return diff;
    }

    public static int[] chainAddition(int[] num){
        int[] newNum = new int[num.length*2];
        int[] add = chainAdditionDiscard(num);

        for(int i = 0; i < newNum.length; i++){
            if (i < num.length) newNum[i] = num[i];
            else newNum[i] = add[i-num.length];
        }

        return newNum;
    }

    public static int[] chainAdditionDiscard(int[] num){
        int[] newNum = new int[num.length];
        for(int i = 0; i < num.length; i++) newNum[i] = (num[i] + num[(i+1)%num.length])%10;
        return newNum;
    }

    public static int findIndex(char[] text, char c){
        int index = -1;
        for(int i = 0; i < text.length; i++) if(text[i] == c) index = i;
        return index;
    }

    public static int[] straddlingCheckerboard(String plainText, int[] key, String commonLetters){
        /*
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
        */
        char row0[] = commonLetters.toCharArray();

        int columns[] = new int[2];
        int filled = 0;
        for(int i = 0; i < row0.length; i++){
            if(row0[i] == ' ' && filled == 0){
                columns[0] = key[i];
                filled = 1;
            }
            if(row0[i] == ' ' && filled == 1) columns[1] = key[i];
        }

        char row1[] = new char[10];
        char row2[] = new char[10];
        filled = 0;
        for(int i = 0; i < 26; i++){
            if(findIndex(row0, (char)(i+'A')) == -1 && filled < 10){
                row1[filled] = (char)(i+'A');
                filled++;
            } 
            else if(findIndex(row0, (char)(i+'A')) == -1 && filled >= 10){
                row2[filled-10] = (char)(i+'A');
                filled++;
            }
        }
        row2[8] = '.';
        row2[9] = '/';

        System.out.println(commonLetters);
        System.out.println(row1);
        System.out.println(row2);

        return null;
    }
}