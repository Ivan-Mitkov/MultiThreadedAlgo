package mergeSort.mergeSortRecursiveAction;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class ParallelMS extends RecursiveAction {
    private int [] nums;
    SequentialMS ms = new SequentialMS();

    public ParallelMS(int[] nums) {
        this.nums = nums;
    }

    @Override
    protected void compute() {
        if(nums.length<1000){
            ms.mergeSort(nums);
            return;
        }
        int middle = nums.length/2;
        int[] left = Arrays.copyOfRange(nums,0,middle);
        int[] right = Arrays.copyOfRange(nums,middle,nums.length);
        ParallelMS leftTask = new ParallelMS(left);
        ParallelMS rightTask = new ParallelMS(right);
        invokeAll(leftTask,rightTask);
        ms.merge(left,right,nums);
    }
}
