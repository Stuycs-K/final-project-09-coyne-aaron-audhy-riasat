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

    // public static long chainAddition(long num){
    //     String stringVersion = String.valueOf(num);
    //     stringVersion = stringVersion.concat(String.valueOf(chainAdditionDiscard(num)));
    //     return Long.parseLong(stringVersion);
    // }

    // public static long chainAdditionDiscard(long num){
    //     String newNumString = "";
    //     String stringVersion = String.valueOf(num);
    //     for(int i = 0; i < stringVersion.length(); i++){
    //         newNumString = newNumString.concat(String.valueOf(modAddition(stringVersion.charAt(i)-'0', stringVersion.charAt((i+1)%stringVersion.length())-'0')));
    //     }
    //     return Long.parseLong(newNumString);
    // }

    public static int[] chainAdditionDiscard(int[] num){
        int[] newNum = new int[num.length];
        for(int i = 0; i < num.length; i++) newNum[i] = (num[i] + num[(i+1)%num.length])%10;
        return newNum;
    }
}