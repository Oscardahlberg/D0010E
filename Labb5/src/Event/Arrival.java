package Event;

import State.*;

/**
 * 
 * Creates a customer and adds it to the store class.
 * Also adds a pick event with the customer and puts it in the event queue.
 * 
 * @author Jesper Frisk, Shahriar Chegini, Oscar Dahlberg, Folke Forshed.
 *
 */

public class Arrival extends Event 
{  
   Pick pickEvent;
   double time;
   Customer customer;
   
   public Arrival(State state, EventQueue eventQueue, double time)
   {
      super(state, eventQueue);
      this.time = time;
      customer = this.state.getStore().createNewCustomer();
   }

   /**
    * 
    * Adds a customer to the store and if the customer can be added
    * (as in if the store is not full) it chooses a time it will take
    * for the customer to pick groceries and then adds a pick event to
    * the event queue.
    * 
    */
   public void doMe() 
   {
      
      state.update(this);
       
      this.state.getStore().addCustomer(customer);
      
      if(customer.getState() == CustomerState.inStore)
      {
         double pickTime = this.time + state.getPickTime().next();
         
         pickEvent = new Pick(this.state, this.eventQueue, customer, pickTime);
         
         eventQueue.addEvent(pickEvent);
      }
   }
   
   public double getTime() 
   {  
      return time;
   }

   @Override
   public Customer getCustomer()
   {
      return customer;
   }

   @Override
   public String getName()
   {
      // TODO Auto-generated method stub
      return "Arrival";
   }
}
