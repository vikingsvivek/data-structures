package eularProject;

public class Problem2 {
    //https://projecteuler.net/problem=2

    public static void main(String[] args) {
        System.out.println(solve());
    }

    private  static long solve(){
        long f1 = 1,f2 = 2;
        long sum = 2, num = 0;

        do{

           num = f1 + f2;
          //  System.out.println(f1+ " "+ f2 + " curr: "+ num);
           //if its even
           if((num & 1) ==0)
               sum += num;
           f1 = f2;
           f2 = num;
        }while (num < 40_00_000);

        return sum;
    }
}
