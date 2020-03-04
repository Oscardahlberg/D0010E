package Event;

import State.Customer;
import State.State;

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

   @Override
   public Customer getCustomer()
   {
      // TODO Auto-generated method stub
      return null;
   }
}