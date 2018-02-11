package parallelSum;

public class ParallelWorker extends Thread {
    private int[] nums;
    private int low;
    private int high;
    private long partialSum;

    public ParallelWorker(int[] nums, int low, int high) {
        this.nums = nums;
        this.low = low;
        this.high = high;
    }

    public long getPartialSum() {
        return partialSum;
    }

    @Override
    public void run() {
        long sum =0;
        for(int i =low; i<high; i++){
            sum+=(long) nums[i];
        }
        this.partialSum=sum;

    }
}
