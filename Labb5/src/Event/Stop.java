package Event;

import State.Customer;
import State.State;

/**
 * 
 * Tells the simulation to stop.
 * Will allways be the last event in eventQeueu
 * 
 * @author Jesper Frisk, Shahriar Chegini, Oscar Dahlberg, Folke Forshed.
 *
 */

public class Stop extends Event
{
   private double time;  
   
   public Stop(State state, EventQueue eventQueue)
   {
      super(state, eventQueue);
      this.time = 999;
   }

   public void doMe() 
   {
      state.update(this);
      state.turnOfSimRunning();
   }

   public double getTime() 
   {
      return time;
   }

   public Customer getCustomer()
   {
      return null;
   }

   public String getName()
   {
      return "Stop";
   }
}
