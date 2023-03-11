package miscArray;

public class GreatIndexes {
    public static void main(String[] args) {
        int[] A = {7,8,2,9,3,6,5};
        int[] B = {2,5,4,8,7,8,4};
        int k =4;
       int ans= solution(A,B,k);
        System.out.println(ans);
    }

    private static int solution(int [] A, int [] B, int k){
            int count = 0;
            int N = A.length;
            for(int i = 0;i< N;i++){
                for(int j = 0; j< N ;j++){
                    if(i > j || j >= i-k && A[j] < A[i] && B[j] < B[i]) {
                        count++;
                    }
                }
            }
            return count;
    }
}
