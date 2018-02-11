package findMaxInArray;

import java.util.concurrent.RecursiveTask;

public class ParallelMaxFind extends RecursiveTask<Integer> {
    private int[] arr;
    private int low;
    private int high;

    public ParallelMaxFind(int[] arr, int low, int high) {
        this.arr = arr;
        this.low = low;
        this.high = high;
    }

    @Override
    protected Integer compute() {
        if(high-low<App.THREASHOLD){
            return sequentialMaxFind();
        }
        else {
            int middle = (low+high)>>>1;
            ParallelMaxFind task1 = new ParallelMaxFind(arr,low,middle);
            ParallelMaxFind task2 = new ParallelMaxFind(arr,middle,high);

            invokeAll(task1,task2);
            return Math.max(task1.join(),task2.join());
        }
    }

    private Integer sequentialMaxFind() {

        int max = arr[low];
        for (int i = low; i <high ; i++) {
            if(arr[i]>max){
                max=arr[i];
            }
        }
        return max;
    }
}
