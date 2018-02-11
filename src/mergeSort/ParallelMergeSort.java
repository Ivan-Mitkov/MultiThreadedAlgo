package mergeSort;

import java.util.Arrays;
import java.util.concurrent.CountedCompleter;

public class ParallelMergeSort extends CountedCompleter<Void>{
    private int[] nums;
    private int [] temparray;
    private int high,low;
    private int middle;

    public ParallelMergeSort(int [] nums, int low, int high, ParallelMergeSort parent){
        super(parent);
        this.nums=nums;
        this.high=high;
        this.low=low;
        this.middle = (low+high)>>>1;
        temparray= new int[nums.length];
    }
    @Override
    public void compute() {
        if (high - low >300_000) {
            ParallelMergeSort taskLeft = new ParallelMergeSort(nums, low, middle,this);
            ParallelMergeSort taskRight = new ParallelMergeSort(nums, middle, high,this);
            addToPendingCount(1);
            taskLeft.fork();
            taskRight.fork();
        } else {
            new SequentialMergeSort(nums);
            tryComplete();
        }
    }

    @Override
    public void onCompletion(CountedCompleter<?> caller) {
        if(high-low<2){
            return;
        }
        merge();
    }

    private void merge() {

        //copy from resulting array to temporary array
        for (int i = low; i<high; i++){
            temparray[i]=nums[i];
        }

        int i = low;
        int j = middle;
        //index of the original array for which we are making compare
        int index = low;
        //copy the smallest from the left and right partition to temp
        while ((i<middle)&&(j<high)){
            if(temparray[i]<=temparray[j]){
                nums[index]=temparray[i];
                i++;
            }
            else {
                nums[index]=temparray[j];
                j++;
            }
            index++;
        }
        // copy the rest of the the left side
        while (i<middle){
            nums[index]= temparray[i];
            i++;
            index++;
        }
        //and the right
        while (j<high){
            nums[index]=temparray[j];
            j++;
            index++;
        }

    }

    public void showResults(){
        Arrays.stream(nums).forEach(a->System.out.print(a+", "));
    }


}
