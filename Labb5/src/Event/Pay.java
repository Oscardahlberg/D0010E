package Event;

import State.*;

/**
 * The pick event will create a queue if all checkouts are busy. 
 * Otherwise it will create the pay event.
 * 
 * @author Jesper Frisk, Shahriar Chegini, Oscar Dahlberg, Folke Forshed.
 *
 */

public class Pick extends Event
{
   private Customer customer;
   private Pay payEvent;
  
   private double time;
   
   public Pick(State state, EventQueue eventQueue, Customer customer, double time)
   {
      super(state, eventQueue);
      
      this.customer = customer;
      this.time = time;
   }

   /**
    * If there is any free register available the method will create a pay event,
    * and tell the simulation that the register is now occupied.
    * If all the check outs is occupied a common queue will be created,
    * with help of FIFO class.
    */
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


   public Customer getCustomer()
   {
      return customer;
   }

   public String getName()
   {
      return "Pick";
   }
}
