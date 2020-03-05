package Event;

import State.*;

/**
 * 
 * Takes a customer and the time the arrived at the pick event
 * and either assigns them to a register or puts them into a queue for a register
 * 
 * @author Jesper Frisk, Shahriar Chegini, Oscar Dahlberg, Folke Forshed.
 *
 */

public class Pick extends Event
{
   private Customer customer;
   private double time;
   
   private Pay payEvent;
   
   
   public Pick(State state, EventQueue eventQueue, Customer customer, double time)
   {
      super(state, eventQueue);
      
      this.customer = customer;
      this.time = time;
   }

   public void doMe() 
   {
      state.update(this);
      
      if(state.getStore().getFreeRegisters() > 0)
      {
         double payTime = this.time + state.getPayTime().next();
         
         payEvent = new Pay(this.state, this.eventQueue, payTime, customer);
         eventQueue.addEvent(payEvent);
         state.getStore().occupieRegister();
      }
      
      else 
      {
         state.getStore().getFIFOQueue().add(customer);
         state.getStore().addTotalCustomersInQueue();
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
      return "Pick";
   }
}
