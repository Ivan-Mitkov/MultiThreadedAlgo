package parallelSum;

public class ParallelSum {
    private ParallelWorker[] sums;
    private int numberOfThreads;


    public ParallelSum( int numberOfThreads) {
        this.sums = new ParallelWorker[numberOfThreads];
        this.numberOfThreads = numberOfThreads;
        }
    public long sum(int[] nums){
        //how many partitions of the array
        int steps = (int)Math.ceil(nums.length*1.0)/numberOfThreads;
        //starting threads
        for (int i =0; i<numberOfThreads; i++){
            sums[i]=new ParallelWorker(nums,i*steps, (i+1)*steps);
            sums[i].start();

        }
        //joining threads
        try {
            for (Thread thread: sums) {
                thread.join();
            }
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        //calculate total sum
        long total =0;
        for (ParallelWorker a:sums){
            total+=a.getPartialSum();
        }
        return total;
    }

}

