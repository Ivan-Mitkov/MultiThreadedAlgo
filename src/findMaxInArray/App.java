package findMaxInArray;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class App {
    public static int THREASHOLD=0;
    public static void main(String[] args) {
        int [] nums = initialize();
        THREASHOLD=nums.length/Runtime.getRuntime().availableProcessors();

        SequentialMaxFind maxFind= new SequentialMaxFind();
        long start= System.currentTimeMillis();
        int res =maxFind.findMax(nums);
        System.out.println("Sequential "
                +(System.currentTimeMillis()-start)+" max "+res);

        System.out.println();
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        ParallelMaxFind parallelMaxFind= new ParallelMaxFind(nums,0,nums.length);

        long startP=System.currentTimeMillis();
        int res2= pool.invoke(parallelMaxFind);
        System.out.println("Parallel "
                +(System.currentTimeMillis()-startP)+" max "+res2);


    }

    private static int[] initialize() {
        Random random = new Random();
        int [] nums = new int[10_000_000];
        for (int i = 0; i <nums.length ; i++) {
            nums[i]= random.nextInt(1000)-500;
        }
        return nums;

    }
}
