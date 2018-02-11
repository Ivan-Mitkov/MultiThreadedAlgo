package findMaxInArray;

public class SequentialMaxFind {
    public int findMax(int[] arr){
        int sz = arr.length;
        int max = arr[0];
        for (int i = 0; i <sz ; i++) {
            if(arr[i]>max){
                max=arr[i];
            }
        }
        return max;
    }
}
