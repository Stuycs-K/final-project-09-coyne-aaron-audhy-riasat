public class Main{
    public static void main(String[] args){
        // int a[] = {9,0,2,1,0};
        // int b[] = {6,7,8,9};
        // int sum[] = Util.chainAddition(a);
        /* 
        int key[] = {5,9,6,1,3,2,8,4,7,0};
        String commonLetters = "AT ONE SIR";
        int cipher[] = Util.straddlingCheckerboard("Attack at dawn. By dawn I mean 0500. Not 0915 like you did last time.", key, commonLetters);
        //for(int i = 0; i < sum.length; i++) System.out.print(sum[i] + ",");
        for(int i = 0; i < cipher.length; i++){
            System.out.print(cipher[i]);
            if(i%5==4) System.out.print(" ");
        }
        */
        String phrase = "IDREAMOFJEANNIEWITHT";
        int[] date = new int[]{6,4,1,8,8,6};
        int personalNumber = 13;
        int[] keyGroup = new int[]{8,8,6,5,1};
        String message = "Vic you will be sent some money before dawn";

        int[] ciphertext = encrypt(phrase, date, personalNumber, keyGroup, message);

        System.out.println(Util.arrayToString(ciphertext));
    }
    public static String decrypt(String phrase, int[] date, int personalNumber, int[] ciphertext){
        int[] keyGroup = new int[5];
        int e = date[date.length - 1];
        int nextKeyDigit = 0;
        int next = 0;
        int[] intermediate3 = new int[ciphertext.length - 5];
        for(int i = 0; i < ciphertext.length; i++){
            if(i >= ciphertext.length - (e*5) && nextKeyDigit < 5){
                keyGroup[nextKeyDigit] = ciphertext[i];
                nextKeyDigit++;
            }
            else{
                intermediate3[next] = ciphertext[i];
                next++;
            }
        }
        int[][] keys = generateKeys(phrase, date, personalNumber, keyGroup);
        int[] intermediate2 = Util.diagonalTransposition(intermediate3, keys[1]);
        int[] intermediate1 = Util.columnarTransposition(intermediate2, keys[0]);
        
        return "";
        
    }
    public static int[] encrypt(String phrase, int[] date, int personalNumber, int[] keyGroup, String message){
        int[][] keys = generateKeys(phrase, date, personalNumber, keyGroup);
        System.out.println("Line-Q: " + Util.arrayToString(keys[0]));
        System.out.println("Line-R: " + Util.arrayToString(keys[1]));
        System.out.println("Line-S: " + Util.arrayToString(keys[2]));
        String commonLetters = "AT ONE SIR";
        int[] intermediate1 = Util.straddlingCheckerboard(message, keys[2], commonLetters);
        System.out.println("intermediate1: " + Util.arrayToString(intermediate1) + "\n");
        
        int[] intermediate2 = Util.columnarTransposition(intermediate1, keys[0]);
        System.out.println("intermediate2: " + Util.arrayToString(intermediate2) + "\n");
        int[] intermediate3 = Util.diagonalTransposition(intermediate2, keys[1]);
        int[] ciphertext = new int[intermediate3.length + 5];
        int next = 0;
        int nextKeyDigit = 0;
        for(int i = 0; i < ciphertext.length; i++){
            if(i >= ciphertext.length - (date[5]*5) && nextKeyDigit < 5){
                ciphertext[i] = keyGroup[nextKeyDigit];
                nextKeyDigit++;
            }
            else{
                ciphertext[i] = intermediate3[next];
                next++;
            }
        }
        return ciphertext;
    }
    
    public static int[][] generateKeys(String phrase, int[] date, int personalNumber, int[] keyGroup){
        //lineB
        int[] lineB = new int[5];
        for(int i = 0; i < 5; i++){
            lineB[i] = date[i];
        }
        //lineC
        int[] lineC = Util.modSubtraction(keyGroup,lineB);
        System.out.println("Line-C: " + Util.arrayToString(lineC));
        //lineD (phrase)
        //lineE.1 & lineE.2
        int[] lineE1 = Util.sequenceLetters(phrase.substring(0,10));
        int[] lineE2 = Util.sequenceLetters(phrase.substring(10,20));
        System.out.println("Line-E1: " + Util.arrayToString(lineE1));
        System.out.println("Line-E2: " + Util.arrayToString(lineE2));
        //lineF.1
        int[] lineF1 = Util.chainAddition(lineC);
        System.out.println("Line-F1: " + Util.arrayToString(lineF1));
        //lineG
        int[] lineG = Util.modAddition(lineE1, lineF1);
        System.out.println("Line-G: " + Util.arrayToString(lineG));
        //lineH
        int[] lineH = Util.digitEncode(lineG, lineE2);
        System.out.println("Line-H: " + Util.arrayToString(lineH));
        //lineJ
        int[] lineHClone = new int[lineH.length];
        for(int i = 0; i < lineH.length; i++) lineHClone[i] = lineH[i];
        int[] lineJ = Util.sequenceNumbers(lineHClone);
        System.out.println("Line-J: " + Util.arrayToString(lineJ));
        System.out.println("Line-H: " + Util.arrayToString(lineH));
        //lineK - lineP
        int[][] linesK_P = new int[5][10];
        linesK_P[0] = Util.chainAdditionDiscard(lineH);
        for(int i = 1; i < 5; i++){
            linesK_P[i] = Util.chainAdditionDiscard(linesK_P[i-1]);
        }
        System.out.println("Line-K: " + Util.arrayToString(linesK_P[0]));
        System.out.println("Line-L: " + Util.arrayToString(linesK_P[1]));
        System.out.println("Line-M: " + Util.arrayToString(linesK_P[2]));
        System.out.println("Line-N: " + Util.arrayToString(linesK_P[3]));
        System.out.println("Line-P: " + Util.arrayToString(linesK_P[4]));
        //a,b
        int lastDigit1 = 0;
        int lastDigit2 = 0;
        for(int i = 9; i > 0; i--){
            if(linesK_P[4][i] != linesK_P[4][i-1]){
                lastDigit2 = linesK_P[4][i];
                lastDigit1 = linesK_P[4][i-1];
                i = -1;
            }
        }
        int a = personalNumber + lastDigit1;
        int b = personalNumber + lastDigit2;
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        //lineQ & lineR
        int[] linesK_P_1D = new int[50];
        for(int i = 0; i < 5; i++){
            for (int j = 0; j < 10; j++){
                linesK_P_1D[i*10+j] = linesK_P[i][j];
            }
        }
        System.out.println("LinesK_P: " + Util.arrayToString(linesK_P_1D));
        int[] linesQ_R = Util.columnarTransposition(linesK_P_1D,lineH);
        System.out.println("LinesQ_R: " + Util.arrayToString(linesQ_R));
        int[] lineQ = new int[a];
        int[] lineR = new int[b];
        for(int i = 0; i < a + b; i++){
            if(i < a) lineQ[i] = linesQ_R[i];
            else lineR[i - a] = linesQ_R[i];
        }
        System.out.println("Line-Q: " + Util.arrayToString(lineQ));
        System.out.println("Line-R: " + Util.arrayToString(lineR));
        //lineS
        int[] lineS = Util.sequenceNumbers(linesK_P[4]);
        System.out.println("Line-S: " + Util.arrayToString(lineS));
        int[][] keys = new int[3][];
        keys[0] = new int[lineQ.length];
        keys[1] = new int[lineR.length];
        keys[2] = new int[lineS.length];
        for(int i = 0; i < lineQ.length; i++) keys[0][i] = lineQ[i];
        for(int i = 0; i < lineR.length; i++) keys[1][i] = lineR[i];
        for(int i = 0; i < lineS.length; i++) keys[2][i] = lineS[i];
        return keys;
        
    }
}