public class Main{
    public static void main(String[] args){
        int a[] = {7,3,4};
        int b[] = {5,8,4,5,6};
        int sum[] = Util.modAddition(a, b);

        for(int i = 0; i < sum.length; i++) System.out.print(sum[i] + ",");
        System.out.println();
    }
}