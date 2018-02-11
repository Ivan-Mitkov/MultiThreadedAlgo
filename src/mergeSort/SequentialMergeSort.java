package mergeSort;

import java.util.Arrays;

public class SequentialMergeSort {

    private int[] nums;
    private int [] temparray;

    public SequentialMergeSort(int [] nums){
        this.nums=nums;
        temparray= new int[nums.length];
    }
    public void mergeSort(int low, int high){
        if(high-low<2){
            return;
        }
        int middle = (low+high)>>>1;
        mergeSort(low,middle);
        mergeSort(middle, high);
        merge(low,middle,high);
    }

    private void merge(int low, int middle, int high) {

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
