public class Main{
    public static void main(String[] args){
        // int a[] = {9,0,2,1,0};
        // int b[] = {6,7,8,9};
        // int sum[] = Util.chainAddition(a);

        int key[] = {5,9,6,1,3,2,8,4,7,0};
        String commonLetters = "AT ONE SIR";
        int cipher[] = Util.straddlingCheckerboard("Attack at dawn. By dawn I mean 0500. Not 0915 like you did last time.", key, commonLetters);
        //for(int i = 0; i < sum.length; i++) System.out.print(sum[i] + ",");
        int c = 0;
        for(int i = 0; i < cipher.length; i++){
            System.out.print(cipher[i]);
            c++;
            if(c == 5){
                System.out.print(" ");
                c = 0;
            }
        }

        System.out.println();
    }
}