import java.util.ArrayList;

public class Util{
    //
    public static void main(String[] args){
        
        System.out.println(arrayToString(sequenceLetters("OCTOPUS")));
        System.out.println(arrayToString(sequenceNumbers(new int[]{9,0,2,1,0})));
        System.out.println(arrayToString(columnarTransposition(new int[]{8,3,4,1,9,4,8,1,0,7,4,1,6,4,0,0,2,5,0,4,4,1,9,5,0,5,8,8,5,8,0,9,6,8,0,0,2,0,2,4,6,6,7,3,4,6,2,1,0,1,0,7,7,6,0,4,7,3,0,3,8,8,5,8,0,9,0,5,1,0,7,6,4,7,0,0,4,3,2,7,2,8,8,8,8,5,8,0,8,3,7,0,7,0,7,0,1,4,6,4,3,2,6,5,0,9,4,0,9,5,3,4,8,8,2,5,0,2,5,8,5,4,9,4,8,4,8,1,4,3,6,4,6,8,3,7,2,0,4,7,3,1,0,9,5,3,2,0,4,9}, new int[]{3,6,5,3,4,6,9,3,2,3,3,9,2,8})));
        System.out.println(arrayToString(diagonalTransposition(new int[]{0,9,2,0,0,2,7,4,5,3,4,6,8,6,0,1,8,1,3,8,4,8,0,5,7,7,7,8,6,8,8,3,1,5,9,6,3,7,0,2,5,3,9,1,1,0,1,8,3,0,9,8,8,0,7,5,0,7,9,7,0,0,4,7,9,4,0,2,7,0,2,7,9,9,2,9,0,6,2,8,0,8,6,0,6,5,4,2,0,4,0,4,8,3,2,4,0,3,0,8,3,3,6,5,4,8,1,1,4,4,8,1,8,0,3,5,2,4,3,4,8,6,4,0,8,4,4,4,7,8,4,0,0,5,4,7,0,5,6,2,1,5,4,6,5,8,0,5,4,0}, new int[]{9,4,7,3,5,2,3,6,2,7,0,3,9,8,1,3,4})));
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

    public static int[] columnarTransposition(int[] input, int[] key){
        int mult5Length = input.length;
        while(mult5Length % 5 != 0) mult5Length++;
        int multKeyLength = mult5Length;
        while(multKeyLength % key.length != 0) multKeyLength++;
        int[] fullInput = new int[multKeyLength];
        for(int i = 0; i < multKeyLength; i++){
            if(i < input.length) fullInput[i] = input[i];
            else if (i < mult5Length) fullInput[i] = 0;
            else fullInput[i] = -1;
        }

        int[][] grid = new int[key.length][fullInput.length/key.length];
        for(int i = 0; i < fullInput.length; i+= key.length){
            for(int j = 0; j < key.length; j++){
                grid[j][i/key.length] = fullInput[i+j];
            }
        }
        ArrayList<Integer> outputList = new ArrayList<Integer>();
        int[] numbers = new int[]{1,2,3,4,5,6,7,8,9,0};
        for(int i = 0; i < numbers.length; i++){
            for(int j = 0; j < key.length; j++){
                if(numbers[i] == key[j]){
                    //APPEND COLUMN TO FRONT
                    for(int k = 0; k < grid[j].length; k++){
                        if(grid[j][k] >= 0) outputList.add(grid[j][k]);                       
                    }
                }
            }
        }
        int[] output = new int[outputList.size()];
        for(int i = 0; i < output.length; i++){
            output[i] = outputList.get(i);
        }
        return output;
            
    }

    public static int[] diagonalTransposition(int[] input, int[] key){
        int[] lengths;
        if(input.length % key.length == 0) lengths = new int[input.length/key.length];
        else lengths = new int[input.length/key.length + 1];
        int[] numbers = new int[]{1,2,3,4,5,6,7,8,9,0};
        int[] starts = new int[key.length];
        int nextStart = 0;
        for(int i = 0; i < numbers.length; i++){
            for(int j = 0; j < key.length; j++){
                if(numbers[i] == key[j]) {
                    starts[nextStart] = j;
                    nextStart++;
                }
            }
        }
        nextStart = 1;
        lengths[0] = starts[0];
        for(int i = 0; i < lengths.length - 1; i++){
            if(lengths[i] == key.length){
                lengths[i+1] = starts[nextStart];
                nextStart++;
            }
            else lengths[i+1] = lengths[i] + 1;

        }
        

        int[] newInput = new int[input.length];
        int next = 0;
        for(int i = 0; i < input.length; i+= key.length){
            for(int j = 0; (j < key.length) && (j < lengths[i]) && (i+j < input.length); j++){
                newInput[i+j] = input[next];
            }
        }
        for(int i = 0; i < input.length; i+= key.length){
            for(int j = key.length - lengths[0]; (j < key.length) && (i+j < input.length); j++){
                newInput[i+j] = input[next];
            }
        }
        return columnarTransposition(newInput, key);
    }

    public static String arrayToString(int[] input){
        String output = "";
        for(int i = 0; i < input.length; i++){  
            output += input[i] + " ";
        }
        return output;
    }
}
