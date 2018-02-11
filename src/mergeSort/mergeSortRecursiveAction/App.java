package mergeSort.mergeSortRecursiveAction;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class App {
    public static void main(String[] args) {
        int size =10000000;
        int[] nums = initialize(size);
        SequentialMS ms=new SequentialMS();

        long start = System.currentTimeMillis();
        ms.mergeSort(nums);
        System.out.println("Sequential: "+(System.currentTimeMillis()-start));
        System.out.println();


        int[] nums2 = initialize(size);
        ForkJoinPool pool= new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        ParallelMS pms=new ParallelMS(nums2);
        long startPar = System.currentTimeMillis();
        pool.invoke(pms);
        System.out.println("Parallel: "+(System.currentTimeMillis()-startPar));

    }
    private static int[] initialize(int m){
        int[] nums = new int[m];
        Random random = new Random();
        for (int i = 0; i <m ; i++) {
            nums[i]=random.nextInt(100)-50;
        }
        return nums;
    }
}
