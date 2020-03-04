package Simulator;

import java.util.Random;

import State.State;

public class Optimize { 

   public int metod1(int registers, long seed, int maxCustomers, 
         double closingTime, double minPickTime, double maxPickTime, 
         double minPayTime, double maxPayTime, double lambda)
   {
      State state = new State(seed, maxCustomers, registers, closingTime, 
            minPickTime, maxPickTime, minPayTime, maxPayTime, lambda);
      
      Simulator sim = new Simulator(state, null);
      sim.run();
      
      return state.getStore().getCustomersTurnedAway();
   }
   
   public int metod2(long seed)
   {
      int maxCustomers = 5;
      double closingTime = 10;
      double minPickTime = 0.5;
      double maxPickTime = 1;
      double minPayTime = 2;
      double maxPayTime = 3;
      double lambda = 1;
      
      int minRegisters = maxCustomers;
      int missedCustomers = metod1(minRegisters, seed, maxCustomers, 
            closingTime, minPickTime, maxPickTime, 
            minPayTime, maxPayTime, lambda);;
      while(minRegisters >= 1)
      {
         int newMissedCustomers = metod1(minRegisters, seed, maxCustomers, 
               closingTime, minPickTime, maxPickTime, 
               minPayTime, maxPayTime, lambda);
         if(missedCustomers != newMissedCustomers)
         {
            return minRegisters + 1;
         }
         minRegisters -= 1;
      }
      return maxCustomers;
   }
   
   public int metod3(long seed)
   {
      Random ran = new Random(seed);
      int counter = 0;
      int maxMinRegisters = 0;
      
      while(counter < 100)
      {
         int newAmountOfRegisters = metod2(ran.nextLong());
         
         if(maxMinRegisters != Math.max(maxMinRegisters, newAmountOfRegisters))
         {
            counter = 0;
         }else
         {
            counter += 1;
         }
         maxMinRegisters = Math.max(maxMinRegisters, newAmountOfRegisters);
      }
      return maxMinRegisters;
   }
   
   public static void main(String[] args) {
      Optimize op = new Optimize();
      long seed = -969854487314619770l;
      System.out.println(op.metod2(Long.parseLong("1234")));
      
   }
}