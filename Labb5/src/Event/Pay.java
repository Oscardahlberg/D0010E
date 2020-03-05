package Event;

import State.*;

/**
 * In this event, no new event will be created and the costumer will be removed.
 * The store will get paid with one coin.
 * If there is a queue it will be reduced by one.
 * 
 * @authorJesper Frisk, Shahriar Chegini, Oscar Dahlberg, Folke Forshed.
 * 
 */

public class Pay extends Event
{
   private Customer customer;
   private double time;
   
   public Pay(State state, EventQueue eventQueue, double time, Customer customer) 
   {
      super(state, eventQueue);
      
      this.customer = customer; 
      this.time = time;
   }

   public void doMe() 
   {
      state.update(this);
      
      state.getStore().addCoin();
      state.getStore().removeCustomer(customer);
      state.getStore().unoccupieRegister();
      
      if(!state.getStore().getFIFOQueue().isEmpty()) 
      {
         Customer customerFirstInLine = (Customer) state.getStore().getFIFOQueue().first();
         
         state.getStore().getFIFOQueue().removeFirst();
         
         double payTime = this.time + state.getPayTime().next();
         
         Pay payEvent = new Pay(this.state, this.eventQueue, payTime, customerFirstInLine);
         eventQueue.addEvent(payEvent);
         
         state.getStore().occupieRegister();
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
      return "Pay";
   }
}
