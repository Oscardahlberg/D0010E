package Event;

import State.Customer;
import State.State;

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

   @Override
   public Customer getCustomer()
   {
      return null;
   }

   @Override
   public String getName()
   {
      // TODO Auto-generated method stub
      return "Close";
   }
}