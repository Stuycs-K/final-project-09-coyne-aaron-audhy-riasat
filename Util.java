import java.util.ArrayList;

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
        
        ArrayList<Integer> cipherText = new ArrayList<Integer>();
        char[] text = plainText.toUpperCase().toCharArray();

        for(int i = 0; i < text.length; i++){
            if(text[i] != ' ' && findIndex(row0, text[i]) != -1){
                cipherText.add(key[findIndex(row0, text[i])]);
            }
            else if(findIndex(row1,text[i]) != -1){
                cipherText.add(columns[0]);
                cipherText.add(key[findIndex(row1,text[i])]);
            }
            else if(findIndex(row2,text[i]) != -1){
                cipherText.add(columns[1]);
                cipherText.add(key[findIndex(row2,text[i])]);
            }
            else if(Character.isDigit(text[i])){
                if(i == 0 || Character.isDigit(text[i-1]) == false){
                    cipherText.add(8);
                    cipherText.add(0);
                }
                for(int j = 0; j < 3; j++) cipherText.add((int)text[i]-'0');
                if(i == text.length-1 || Character.isDigit(text[i+1]) == false){
                    cipherText.add(8);
                    cipherText.add(0);
                }
            }
        }

        int finalCipher[] = new int[cipherText.size()];
        for(int i = 0; i < finalCipher.length; i++) finalCipher[i] = cipherText.get(i);
        return finalCipher;
    }
}