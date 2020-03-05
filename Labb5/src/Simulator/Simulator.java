
package Simulator;

import State.State;
import Event.*;
import View.*;

/**
 * 
 * Starts the event queue and creates two events which are put into the queue
 * event Start and event Stop. When the program comes to running the method of
 * the start event, more events will be added.
 *
 * @author Jesper Frisk, Shahriar Chegini, Oscar Dahlberg, Folke Forshed.
 *
 */


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
      
      //While simulator is running, it will keep on getting the first event in
      //queue and running the ques "doMe" function.
      
      while (state.getSimRunning()) {
         Event event = eventQueue.getFirst();
         eventQueue.removeFirst();
         event.doMe();
      }
   }
}
