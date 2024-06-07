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
}