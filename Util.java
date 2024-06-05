public class Util{
    //
    public static void main(String[] args){
        //int[] digits = digitEncode(new int[]{9,0,2,1,0}, new int[]{6,0,1,3,5,8,9,4,2,7});
        
        System.out.println(arrayToString(sequenceLetters("OCTOPUS")));
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

    public static int[] sequenceNumbers (int[] input){
        return null;
        

    }

    public static int[] sequenceLetters (String input){
        int[] temp = new int[input.length()];
        int[] output = new int[input.length()];
        for(int i = 0; i < input.length(); i++){
            temp[i] = (int)(input.charAt(i));
           // System.out.println((int)(input.charAt(i)));
        }
        int[] tempOld = new int[temp.length];
        for(int i = 0; i < temp.length; i++)
            tempOld[i] = temp[i];
        int[] tempNew = insertionSort(temp);
        //System.out.println(arrayToString(tempOld));
        //System.out.println(arrayToString(tempNew));
        int[] numbers = new int[]{1,2,3,4,5,6,7,8,9,0};
        boolean[] isUsed = new boolean[temp.length];
        for(int i = 0; i < isUsed.length; i++){
            isUsed[i] = false;
        }
        
        for(int i = 0; i < tempNew.length; i++){ //UNFINISHED
            for(int j = 0; j < tempOld.length; j++){
                if(tempOld[j] == tempNew[i] && isUsed[j] == false){
                    isUsed[j] = true;
                    output[j] = numbers[i];

                }
            }
        }
        return output;
    }

    public static int[] insertionSort(int[] input){
		int key = 0;
		for(int i = 0; i < (input.length - 1); i++){
			key = input[i + 1];
			for(int j = i; j > 0; j--){
				if(input[j] < key)
					input[j+1] = key;
				else
					input[j+1] = input[j];
					
			}
			if(key < input[i])
				input[i] = key;
				
		}
		return input;
	}

    public static String arrayToString(int[] input){
        String output = "";
        for(int i = 0; i < input.length; i++){  
            output += input[i] + " ";
        }
        return output;

    }
}
