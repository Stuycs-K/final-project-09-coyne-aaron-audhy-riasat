public class Util{
    //
    public static void main(String[] args){
        String output = "";
        int[] digits = digitEncode(new int[]{9,0,2,1,0}, new int[]{6,0,1,3,5,8,9,4,2,7});
        for(int i = 0; i < digits.length; i++){  
            output += digits[i] + " ";
        }
        System.out.println(output);
    }
    public static int[] digitEncode(int[] input, int[] key){
        int[] output = new int[input.length];
        int pos = 0;
        //int[] ident = new int[]{1,2,3,4,5,6,7,8,9,0};
        for(int i = 0; i < input.length; i++){
            if(input[i] == 0) output[i] = key[9];
            else output[i] = key[(input[i]- 1)];
        }
        return output;
        
    }

    public static int[] sequence (String input){
        

    }
}
