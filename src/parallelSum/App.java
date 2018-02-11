package parallelSum;

import java.util.Random;

public class App {
    public static void main(String[] args) {
        Random random = new Random();
        int[] nums = new int[10_000_000];
        for (int i = 0; i <nums.length ; i++) {
            nums[i]=random.nextInt(100)-50;
        }
        Sum sum=new Sum();
//        System.out.println(Arrays.toString(nums));

        int numberOfThreads = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of processors"+numberOfThreads);
        ParallelSum parallelSum=new ParallelSum(numberOfThreads);
        long startParallel = System.currentTimeMillis();
        long praSum = parallelSum.sum(nums);
        System.out.println("Parallel time:"+ (System.currentTimeMillis()-startParallel));
        System.out.println(praSum);
        long start = System.currentTimeMillis();
        long res = sum.sum(nums);
        System.out.println("Sequential time:"+ (System.currentTimeMillis()-start));
        System.out.println(res);





    }
}
