  
package Simulator;

import State.Customer;
import State.State;
import  Event.*;
import View.*;

public class Simulator {
   
   private long seed = 13;
   private int maxCustomers = 7;
   private int registers = 2;
   private double closingTime = 8;
   private double minPickTime = 0.6;
   private double maxPickTime = 0.9;
   private double minPaytime = 0.35;
   private double maxPaytime = 0.6;
   private double lambda = 3;
  
   EventQueue eventQueue = new EventQueue();
   
   State state = new State(seed, maxCustomers, registers, closingTime
         , minPickTime, maxPickTime, minPaytime, maxPaytime, lambda);
   
   View view = new PrintView(state, state.getStore());
   
   public void run() {
      eventQueue.addEvent(new Start(state, eventQueue));
      eventQueue.addEvent(new Stop(state, eventQueue));
      while(state.getSimRunning())
      {
//         System.out.println(eventQueue);
         Event event = eventQueue.getFirst();
         eventQueue.removeFirst();
         event.doMe();
      }
   }
}