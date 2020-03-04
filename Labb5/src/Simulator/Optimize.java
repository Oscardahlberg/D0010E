package Simulator;

import java.util.Random;
import State.State;

/**
 * The class runs a set of simulations to find the most optimal number
 * of registers while the other parameters a set.
 * @author Jesper Frisk, Shahriar Chegini, Oscar Dahlberg, Folke Forshed.
 *
 */
public class Optimize { 

   /**
    * Given all parameters a simulation is run and the number of missed
    * customer is returned. 
    * @param registers
    * @param seed
    * @param maxCustomers
    * @param closingTime
    * @param minPickTime
    * @param maxPickTime
    * @param minPayTime
    * @param maxPayTime
    * @param lambda
    * @return the number of missed customers.
    */
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
   
   /**
    * Runs the first method until the best number of registers are found.
    * It first tries with maxCustomer number of register because that gives 
    * the minimal number of missed customers. The amount of registers are 
    * lowered until one more customer is missed, then we know that the 
    * last amount of registers was the best.
    * @param seed
    * @return best number of registers for this seed.
    */
   public int metod2(long seed)
   {
      // Set parameters for the first method.
      int maxCustomers = 5;
      double closingTime = 10;
      double minPickTime = 0.5;
      double maxPickTime = 1;
      double minPayTime = 2;
      double maxPayTime = 3;
      double lambda = 1;
      
      // Minimal registers can not be more than maxCustomers.
      int minRegisters = maxCustomers;
      
      // Gets an initial value of missedCustomers.
      int missedCustomers = metod1(minRegisters, seed, maxCustomers, 
            closingTime, minPickTime, maxPickTime, 
            minPayTime, maxPayTime, lambda);
      
      // Gets a new value as long as the minRegisters are bigger than 1.
      while(minRegisters >= 1)
      {
         // Gets a new amount of missed customers.
         int newMissedCustomers = metod1(minRegisters, seed, maxCustomers, 
               closingTime, minPickTime, maxPickTime, 
               minPayTime, maxPayTime, lambda);
         
         // Checks if the new amount of missed customer is different from 
         // from the initial value.
         // if it is different that means that the last number of registers
         // was the best amount.
         if(missedCustomers != newMissedCustomers)
         {
            return minRegisters + 1;
         }
         minRegisters -= 1;
      }
      return maxCustomers;
   }
   
   /**
    * Runs the second method with different seeds. We always save the 
    * maximum of the minimum number of registers. The method stops tiring
    * when the maximum has not changed for a hundred times.
    * @param seed
    * @return the maximum of the minimum number of registers with the 
    * different seeds.
    */
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
      System.out.println(op.metod2(seed));
      
   }
}
