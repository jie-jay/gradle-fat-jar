package au.edu.unimelb.ds.week3.ThreadSync;

import java.util.concurrent.ThreadLocalRandom;

class PrintDemo {

   public int counter = 10;
   public void  printCount(String threadName) {
      try {
         for(int i = 500; i > 0; i--) {
            System.out.println("Thread "+threadName+" Counter   ---   "  + i +" Global Counter:"+counter);
            
            // Make the thread sleep from between 100 to 999 ms
            int randomSleepTime = ThreadLocalRandom.current().nextInt(100,2000);
            //Thread.sleep(randomSleepTime);
            counter--;
         }
      }catch (Exception e) {
         System.out.println("Thread  interrupted.");
      }
   }
}