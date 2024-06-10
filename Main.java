public class Main{
    public static void main(String[] args){
        if(args[0].equals("encode")){
            String phrase = args[1];
            int[] date = new int[6];
            for(int i = 0; i < 6; i++) date[i] = Integer.parseInt(args[2].substring(i, i+1));
            int personalNumber = Integer.parseInt(args[3]);
            int[] keyGroup = new int[5];
            for(int i = 0; i < 5; i++) keyGroup[i] = Integer.parseInt(args[4].substring(i, i+1));
            String message = args[5];
            String commonLetters = args[6];
            System.out.println(Util.arrayToString(encrypt(phrase, date, personalNumber, keyGroup, message, commonLetters)));
            
        }
        else if(args[0].equals("simpleEncode")){
            String message = args[1];
            String commonLetters = args[2];
            System.out.println(Util.arrayToString(simpleEncrypt(message, commonLetters)));
        }
        else if (args[0].equals("simpleDecode")){
            int[] cipherText = new int[args[1].length()];
            for(int i = 0; i < args[1].length(); i++) cipherText[i] = Integer.parseInt(args[1].substring(i, i+1));
            String commonLetters = args[2];
            System.out.println(simpleDecrypt(cipherText, commonLetters));

        }
        //int[] ciphertext = encrypt(phrase, date, personalNumber, keyGroup, message);

        //System.out.println(Util.arrayToString(ciphertext));

        //String plaintext = decrypt(phrase, date, personalNumber, ciphertext);

        //System.out.println(plaintext);
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
        System.out.println("intermediate3: " + Util.arrayToString(intermediate3) + "\n");

        int[][] keys = generateKeys(phrase, date, personalNumber, keyGroup);
        int[] intermediate2 = Util.diagonalTransposition(intermediate3, keys[1]);
        //System.out.println("intermediate2: " + Util.arrayToString(intermediate2) + "\n");
        int[] intermediate1 = Util.columnarTransposition(intermediate2, keys[0]);
        //System.out.println("intermediate1: " + Util.arrayToString(intermediate1) + "\n");
        String plainText = Util.decodeCheckerBoard(intermediate1, keys[2], "AT ONE SIR");
        return plainText;
        
    }

    public static int[] simpleEncrypt(String message, String commonLetters){
        return Util.straddlingCheckerboard(message, new int[]{0,1,2,3,4,5,6,7,8,9}, commonLetters);
    }

    public static String simpleDecrypt(int[] ciphertext, String commonLetters){
        return Util.decodeCheckerBoard(ciphertext, new int[]{0,1,2,3,4,5,6,7,8,9},commonLetters);
    }

    public static int[] encrypt(String phrase, int[] date, int personalNumber, int[] keyGroup, String message, String commonLetters){
        int[][] keys = generateKeys(phrase, date, personalNumber, keyGroup);
        //System.out.println("Line-Q: " + Util.arrayToString(keys[0]));
        //System.out.println("Line-R: " + Util.arrayToString(keys[1]));
        //System.out.println("Line-S: " + Util.arrayToString(keys[2]));
        //String commonLetters = "AT ONE SIR";
        int[] intermediate1 = Util.straddlingCheckerboard(message, keys[2], commonLetters);
        //System.out.println("intermediate1: " + Util.arrayToString(intermediate1) + "\n");
        
        int[] intermediate2 = Util.columnarTransposition(intermediate1, keys[0]);
        //System.out.println("intermediate2: " + Util.arrayToString(intermediate2) + "\n");
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
        //System.out.println("Line-C: " + Util.arrayToString(lineC));
        //lineD (phrase)
        //lineE.1 & lineE.2
        int[] lineE1 = Util.sequenceLetters(phrase.substring(0,10));
        int[] lineE2 = Util.sequenceLetters(phrase.substring(10,20));
        //System.out.println("Line-E1: " + Util.arrayToString(lineE1));
        //System.out.println("Line-E2: " + Util.arrayToString(lineE2));
        //lineF.1
        int[] lineF1 = Util.chainAddition(lineC);
        //System.out.println("Line-F1: " + Util.arrayToString(lineF1));
        //lineG
        int[] lineG = Util.modAddition(lineE1, lineF1);
        //System.out.println("Line-G: " + Util.arrayToString(lineG));
        //lineH
        int[] lineH = Util.digitEncode(lineG, lineE2);
        //System.out.println("Line-H: " + Util.arrayToString(lineH));
        //lineJ
        int[] lineHClone = new int[lineH.length];
        for(int i = 0; i < lineH.length; i++) lineHClone[i] = lineH[i];
        int[] lineJ = Util.sequenceNumbers(lineHClone);
        //System.out.println("Line-J: " + Util.arrayToString(lineJ));
        //System.out.println("Line-H: " + Util.arrayToString(lineH));
        //lineK - lineP
        int[][] linesK_P = new int[5][10];
        linesK_P[0] = Util.chainAdditionDiscard(lineH);
        for(int i = 1; i < 5; i++){
            linesK_P[i] = Util.chainAdditionDiscard(linesK_P[i-1]);
        }
        //System.out.println("Line-K: " + Util.arrayToString(linesK_P[0]));
        //System.out.println("Line-L: " + Util.arrayToString(linesK_P[1]));
       // System.out.println("Line-M: " + Util.arrayToString(linesK_P[2]));
        //System.out.println("Line-N: " + Util.arrayToString(linesK_P[3]));
        //System.out.println("Line-P: " + Util.arrayToString(linesK_P[4]));
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
        //System.out.println("a: " + a);
        //System.out.println("b: " + b);
        //lineQ & lineR
        int[] linesK_P_1D = new int[50];
        for(int i = 0; i < 5; i++){
            for (int j = 0; j < 10; j++){
                linesK_P_1D[i*10+j] = linesK_P[i][j];
            }
        }
        //System.out.println("LinesK_P: " + Util.arrayToString(linesK_P_1D));
        int[] linesQ_R = Util.columnarTransposition(linesK_P_1D,lineH);
        //System.out.println("LinesQ_R: " + Util.arrayToString(linesQ_R));
        int[] lineQ = new int[a];
        int[] lineR = new int[b];
        for(int i = 0; i < a + b; i++){
            if(i < a) lineQ[i] = linesQ_R[i];
            else lineR[i - a] = linesQ_R[i];
        }
        //System.out.println("Line-Q: " + Util.arrayToString(lineQ));
        //System.out.println("Line-R: " + Util.arrayToString(lineR));
        //lineS
        int[] lineS = Util.sequenceNumbers(linesK_P[4]);
        //System.out.println("Line-S: " + Util.arrayToString(lineS));
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