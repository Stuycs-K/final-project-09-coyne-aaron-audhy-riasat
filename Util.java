public class Util{
    //
    public static void main(String[] args){
        int output = 0;
        int[] digits = digitEncode(new int[]{});
        for(int i = 0; i < input.length; i++){  
            
        }
    }
    public static int[] digitEncode(int[] input, int[] key){
        int[] output = new int[input.length];
        int pos = 0;
        int[] ident = new int[]{1,2,3,4,5,6,7,8,9,0};
        for(int i = 0; i < input.length; i++){
            if(input[i] == 0) output[i] = key[9];
            else output[i] = key[(input[i]- 1)];
        }
        return output;
        
    }
}
