package miscArray;

public class MaxProfitSellingComputers {
    public static void main(String[] args) {
      /*
        int stockC[] = new int [] {3};
        int stockF[] = new int [] {3253};
        int stockP[] = new int [] {744};
        int orderC[] = new int [] {1};
        int orderF[] = new int [] {2012};
        int orderP[] = new int [] {798};
        int maxProfit[] = {Integer.MIN_VALUE};
       */

        int stockC[] = new int [] {1};
        int stockF[] = new int [] {2291};
        int stockP[] = new int [] {728};
        int orderC[] = new int [] {1};
        int orderF[] = new int [] {3024};
        int orderP[] = new int [] {858};
        int maxProfit[] = {Integer.MIN_VALUE};

        solution(stockC,stockF,stockP, orderC,orderF,orderP,0,0,0,maxProfit);
        System.out.println(maxProfit[0]);
    }

    private static void solution( int stockC[],int stockF[] , int stockP[] ,int orderC[],int orderF[] ,
    int orderP[], int i, int j,int profit, int [] maxProfit ){
        if(i == stockC.length || j == orderC.length){
            maxProfit[0] = Math.max(maxProfit[0], profit);
            return ;
        }

        if(stockC[i] >= orderC[j] && stockF[i] >= orderF[j]  && stockP[i] <= orderP[j]){
            System.out.println(profit+"  "+ (orderP[j] - stockP[i]));
            profit+= (orderP[j] - stockP[i]);
            // move in array further
            solution(stockC,stockF,stockP,orderC,orderF,orderP,i+1,j+1, profit, maxProfit);
            return;
        }else{
            solution(stockC,stockF,stockP,orderC,orderF,orderP,i,j+1, profit, maxProfit);
            solution(stockC,stockF,stockP,orderC,orderF,orderP,i+1,j, profit, maxProfit);
        }

    }
}
