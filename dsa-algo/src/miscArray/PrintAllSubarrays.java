package miscArray;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PrintAllSubarrays {

    private static int allSubArraysWithinGivenRangeCount(int[] arr, int minK, int maxK){
        //O(N*N*N) time O(1) space
        int count = 0;
        for(int i = 0;i< arr.length;i++){
            for (int j = i; j< arr.length;j++){

                int idx = 0;
                int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
                for (int start = i;  start <=j; start++){
                    //outside of given bound, don't generate that subarray
                    if(arr[start] < minK || arr[start] > maxK){
                        min = max =  arr[start];
                        break;
                    }else{
                        min = Math.min(min,arr[start]);
                        max = Math.max(max,arr[start]);
                    }

                }
                //if in given range, add that subarray
                if(min == minK && max == maxK){
                    count++;
                }
            }
        }

        return count;
    }
    private static List<int[]> allSubArraysWithinGivenRange(int[] arr, int minK, int maxK){
        //O(N*N*N) time O(N*N) space
        List<int[]> subarrays = new ArrayList<>();

        for(int i = 0;i< arr.length;i++){
            for (int j = i; j< arr.length;j++){
                int sub[] = new int[j-i+1];
                int idx = 0;
                int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
                for (int start = i;  start <=j; start++){
                    //outside of given bound, don't generate that subarray
                    if(arr[start] < minK || arr[start] > maxK){
                        min = max =  arr[start];
                        break;
                    }else{
                        sub[idx++] = arr[start];
                        min = Math.min(min,arr[start]);
                        max = Math.max(max,arr[start]);
                    }

                }
                //if in given range, add that subarray
                if(min == minK && max == maxK){
                    subarrays.add(sub);
                }
            }
        }

        return subarrays;
    }

    private static List<int[]> allSubArrays(int[] arr){
        List<int[]> subarrays = new ArrayList<>();
        //O(N*N*N) time O(N*N) space
        for(int i = 0;i< arr.length;i++){
            for (int j = i; j< arr.length;j++){
                int sub[] = new int[j-i+1];
                int idx = 0;
                int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
                for (int start = i;  start <=j; start++){
                       sub[idx++] = arr[start];
                }
                //add to subarrays collection
                subarrays.add(sub);
            }
        }

        return subarrays;
    }

    //O(N*N) time O(N*N) space
    private static List<String> allSubArraysNSquareTime(int[] arr){
        List<String> subarrays = new ArrayList<>();
        //O(N*N*N) time O(N*N) space
        for(int i = 0;i< arr.length;i++){
            StringBuilder strb = new StringBuilder();
            for (int j = i; j< arr.length;j++){
                    strb.append(arr[j]).append(" ");
                    //add this subarray in list
                    subarrays.add(strb.toString());
            }
        }
        return subarrays;
    }

    private static void allSubarrays(int[] arr, int start, int end, List<String> subarrays){
        if(end == arr.length)
            return;
        System.out.println("Call: start: "+start+" end: "+end);
        if(start <= end){
            StringBuilder strb = new StringBuilder();
            for(int i = start; i<= end; i++){
                strb.append(arr[i]).append(" ");
            }
            // add this subarray to ansList
            subarrays.add(strb.toString());
            //recurse on next start position
            allSubarrays(arr, start+1, end, subarrays);
        }else{
            //add all numbers from 0 to end in one subarray and recurse
            allSubarrays(arr, 0, end+1, subarrays);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,3,5,2,7,5};

        List<int[]> subarrays = allSubArrays(arr);
        List<String> subs = new ArrayList<>();
        allSubarrays(arr,0,0,subs);
        print(subs);
        //print(subarrays);
        System.out.println("SubArrays Again");
        print(allSubArraysNSquareTime(arr));

        /*
        List<int[]> subarrays = allSubArraysWithinGivenRange(arr,1,5);
        System.out.println("Count: "+allSubArraysWithinGivenRangeCount(arr,1,5));
        print(subarrays);

        arr = new int[]{1,1,1,1};
        subarrays = allSubArraysWithinGivenRange(arr,1,1);
        System.out.println("Count: "+allSubArraysWithinGivenRangeCount(arr,1,1));
        print(subarrays);
         */


    }

    private static void print(Collection<String> subarrays){
        for( String sub: subarrays){
            System.out.println(sub);
        }
        System.out.println();
    }

    private static void print(List<int[]> subarrays){
        for( int[] sub: subarrays){
            for (int el: sub){
                System.out.print(el+" ");
            }
            System.out.println();
        }
    }
}
