package Event;

import State.Customer;
import State.State;

/**
 * Tells the store when to close and wont let any costumers in the store.
 * 
 * @author Jesper Frisk, Shahriar Chegini, Oscar Dahlberg, Folke Forshed. 
 *
 */

public class Close extends Event
{
   private double time;
   
   public Close(State state, EventQueue eventQueue, double time) 
   {
      super(state, eventQueue);
      this.time = time;
      
   }

   public void doMe() 
   {
      state.update(this);
      
      state.getStore().setIsOpen(false);
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
      return "Close";
   }
}
