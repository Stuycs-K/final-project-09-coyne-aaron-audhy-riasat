public class Util{
    public static int modAddition(int a, int b){
        int sum = 0;

        int numDigits = 0;
        if (a > b) numDigits = a;
        else numDigits = b;
        numDigits = String.valueOf(numDigits).length();

        for(int i = 0; i < numDigits; i++){
            sum += (a%Math.pow(10,i+1) - a%Math.pow(10,i) + b%Math.pow(10,i+1) - b%Math.pow(10,i))%Math.pow(10,i+1);
        }

        return sum;
    }

    public static int modSubtraction(int a, int b){
        int diff = 0;

        int numDigits = 0;
        if (a > b) numDigits = a;
        else numDigits = b;
        numDigits = String.valueOf(numDigits).length();

        for(int i = 0; i < numDigits; i++){
            if(a%Math.pow(10,i+1) - a%Math.pow(10,i) >= b%Math.pow(10,i+1) - b%Math.pow(10,i)) diff += (a%Math.pow(10,i+1) - a%Math.pow(10,i)) - (b%Math.pow(10,i+1) - b%Math.pow(10,i));
            else diff += (a%Math.pow(10,i+1) - a%Math.pow(10,i) + Math.pow(10,i+1)) - (b%Math.pow(10,i+1) - b%Math.pow(10,i));
        }

        return diff;
    }

    public static int chainAddition(int num){
        int numDigits = String.valueOf(num).length();
        int addedDigits = 0;
        int newNumber = num * Math.pow(10, numDigits);

        newNumber += chainAdditionDiscard(num);

        return newNumber;
    }
    public static int chainAdditionDiscard(int num){
        int numDigits = String.valueOf(num).length();
        newNumber = 0;
        for(int i = numDigits; i > 0; i--){
            newNumber += chainAddition(num%Math.pow(10,i+1)-num%Math.pow(10,i),num);
        }
        return 0;
    }
    4390%10000 - 4390%1000
}