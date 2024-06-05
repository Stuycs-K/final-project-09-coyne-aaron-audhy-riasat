public class Util{
    //
    public static void main(String[] args){
        //int[] digits = digitEncode(new int[]{9,0,2,1,0}, new int[]{6,0,1,3,5,8,9,4,2,7});
        
        System.out.println(arrayToString(sequenceLetters("OCTOPUS")));
        System.out.println(arrayToString(sequenceNumbers(new int[]{9,0,2,1,0})));
    }
    public static int[] digitEncode(int[] input, int[] key){
        int[] output = new int[input.length];
        //int pos = 0;
        //int[] ident = new int[]{1,2,3,4,5,6,7,8,9,0};
        for(int i = 0; i < input.length; i++){
            if(input[i] == 0) output[i] = key[9];
            else output[i] = key[(input[i]- 1)];
        }
        return output;
        
    }

    public static int[] sequenceNumbers (int[] input){
        int[] output = new int[input.length];
        int[] tempOld = new int[input.length];
        for(int i = 0; i < input.length; i++){
            if(input[i] == 0) input[i] = 10;
            tempOld[i] = input[i];
            
        }
            int[] tempNew = bubbleSort(input);
        int[] numbers = new int[]{1,2,3,4,5,6,7,8,9,0};
        boolean[] isUsed = new boolean[input.length];
        for(int i = 0; i < isUsed.length; i++){
            isUsed[i] = false;
        }
        //FOR EACH VALUE IN SORTED, CHECK FOR MATCH IN UNSORTED
        //SET output[Pos in Old] TO POSITION OF NEW
        for(int i = 0; i < tempNew.length; i++){ 
            for(int j = 0; j < tempOld.length; j++){
                if(tempOld[j] == tempNew[i] && isUsed[j] == false){
                    isUsed[j] = true;
                    output[j] = numbers[i];
                    j = tempOld.length;
                }
            }
        }
        return output;
    }
        

    

    public static int[] sequenceLetters (String input){
        int[] temp = new int[input.length()];
        int[] output = new int[input.length()];
        //REPRESENT CHARACTERS AS ASCII VALUES AND SORT THEM
        for(int i = 0; i < input.length(); i++){
            temp[i] = (int)(input.charAt(i));
        }
        int[] tempOld = new int[temp.length];
        for(int i = 0; i < temp.length; i++)
            tempOld[i] = temp[i];
        int[] tempNew = bubbleSort(temp);
        int[] numbers = new int[]{1,2,3,4,5,6,7,8,9,0};
        boolean[] isUsed = new boolean[temp.length];
        for(int i = 0; i < isUsed.length; i++){
            isUsed[i] = false;
        }
        //FOR EACH VALUE IN SORTED, CHECK FOR MATCH IN UNSORTED
        //SET output[Pos in Old] TO POSITION OF NEW
        for(int i = 0; i < tempNew.length; i++){ 
            for(int j = 0; j < tempOld.length; j++){
                if(tempOld[j] == tempNew[i] && isUsed[j] == false){
                    isUsed[j] = true;
                    output[j] = numbers[i];
                    j = tempOld.length;
                }
            }
        }
        return output;
    }

    public static int[] bubbleSort(int[] input){
		int end = input.length - 1;
		for(int i = 0; i < input.length; i++){
			for(int j = 0; j < end; j++){
				if(input[j] > input[j + 1]){
					//swap
					int swapHelper = input[j];
					input[j] = input[j + 1];
					input[j + 1] = swapHelper;
				}
					
			}
			end--;
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
