public class Main{
    public static void main(String[] args){
        int a[] = {1,2,3,4};
        int b[] = {6,7,8,9};
        int sum[] = Util.modAddition(a, b);

        for(int i = 0; i < sum.length; i++) System.out.print(sum[i] + ",");
        System.out.println();
    }
}