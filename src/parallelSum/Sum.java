package parallelSum;

public class Sum {
   public long sum(int[] arr){
       long sum =0;
       for(int a:arr){
           sum+=(long) a;
       }
       return sum;
   }

}
