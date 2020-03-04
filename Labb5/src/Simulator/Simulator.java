
package Simulator;

import State.State;
import Event.*;
import View.*;

public class Simulator {

   private State state;
   private EventQueue eventQueue;
   private View view;
   
   public Simulator(State state, View view) {
      this.state = state;
      this.view = view;
      this.eventQueue = new EventQueue();
   }

   public void run() {
      
      eventQueue.addEvent(new Start(state, eventQueue));
      eventQueue.addEvent(new Stop(state, eventQueue));
      while (state.getSimRunning()) {
//         System.out.println(eventQueue);
         Event event = eventQueue.getFirst();
         eventQueue.removeFirst();
         event.doMe();
      }
   }
}