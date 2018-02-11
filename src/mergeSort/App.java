package mergeSort;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class App {
    public static void main(String[] args) {
        int numberOfThreads = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of cores "+ numberOfThreads);
        Random random = new Random();

        int[] nums = new int[1_000_000];
        System.out.println("Sorting array wit length "+ nums.length);
        //int[] smallNums= new int[10];

//        for (int i = 0; i <smallNums.length ; i++) {
//            smallNums[i]=random.nextInt(100)-50;
//        }
        for (int i = 0; i <nums.length ; i++) {
            nums[i]=random.nextInt(100_000)-50_000;
        }
        //sequential
        //Arrays.stream(smallNums).forEach(a-> System.out.print(a +", "));
        System.out.println();
        SequentialMergeSort mergeSort= new SequentialMergeSort(nums);
        long start = System.currentTimeMillis();
        mergeSort.mergeSort(0,nums.length);
        System.out.println("Sequential time:"+ (System.currentTimeMillis()-start));
        //mergeSort.showResults();


        //parallel 1
        for (int i = 0; i <nums.length ; i++) {
            nums[i]=random.nextInt(100_000)-50_000;
        }
        MultiThreadMergeSort multiThreadMergeSort= new MultiThreadMergeSort(nums);
        long startParallel = System.currentTimeMillis();
        multiThreadMergeSort.parallelMergeSort(0,nums.length,numberOfThreads);
        System.out.println("Parallel time:"+ (System.currentTimeMillis()-startParallel));
        //multiThreadMergeSort.showResults();

        //parallel with fork join
        int h= nums.length;
        long startParallelForkJoin = System.currentTimeMillis();
        parallelMergeSort(nums,0,h);
        System.out.println("ForkJoin time:"+ (System.currentTimeMillis()-startParallelForkJoin));
        //System.out.println(Arrays.toString(smallNums));

    }
    public static void parallelMergeSort(int[] arr, int start, int end){
        ParallelMergeSort task= new ParallelMergeSort(arr,start,end,null);
        ForkJoinPool.commonPool().invoke(task);
    }
}
