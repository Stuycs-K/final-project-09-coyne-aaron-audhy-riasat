public class Main{
    public static void main(String[] args){
        int a[] = {9,0,2,1,0};
        int b[] = {6,7,8,9};
        int sum[] = Util.chainAddition(a);

        for(int i = 0; i < sum.length; i++) System.out.print(sum[i] + ",");
        System.out.println();
    }
}