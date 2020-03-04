  
package Simulator;

import State.State;
import  Event.*;
import View.*;

public class Simulator {
   
   private long seed = 1234;
   private int maxCustomers = 5;
   private int registers = 2;
   private double closingTime = 10;
   private double minPickTime = 1;
   private double maxPickTime = 2;
   private double minPaytime = 1;
   private double maxPaytime = 2;
   private double lambda = 1;
  
   EventQueue eventQueue = new EventQueue();
   
   State state = new State(seed, maxCustomers, registers, closingTime
         , minPickTime, maxPickTime, minPaytime, maxPaytime, lambda);
   
   View view = new PrintView(eventQueue, state, state.getStore());
   
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