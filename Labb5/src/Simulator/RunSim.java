package Simulator;

import State.State;
import View.PrintView;
import View.View;

/**
 * Runs a standard supermarket that will print the events that unfold.
 * The seed for the random number generator will be a set seed, meaning
 * the output will be the same each run.
 * 
 * @author Jesper Frisk, Shahriar Chegini, Oscar Dahlberg, Folke Forshed.
 *
 */

public class RunSim {

   public static void main(String[] args) {
      long seed = 1234;
      int maxCustomers = 5;
      int registers = 4;
      double closingTime = 10;
      double minPickTime = 0.5;
      double maxPickTime = 1;
      double minPayTime = 2;
      double maxPayTime = 3;
      double lambda = 1;
        

      State state = new State(seed, maxCustomers, registers, closingTime, 
            minPickTime, maxPickTime, minPayTime, maxPayTime, lambda);
      View view = new PrintView(state, state.getStore());
      
      Simulator sim = new Simulator(state, view);
      sim.run();
      
   }
}
