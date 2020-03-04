package Event;

import State.*;

/**
 * 
 * @author Shahriar 
 *
 *ställer sig i kön
 *
 *först kollar vi om kassorna är lediga
 *om ja: skapar payEvent 
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
      
//      System.out.println(state.getStore().getFreeRegisters());
//      System.out.println(customer);
//      System.out.println();
      //om det finns lediga kassor
      if(state.getStore().getFreeRegisters() > 0)
      {
         double payTime = this.time + state.getPayTime().next();
         
         payEvent = new Pay(this.state, this.eventQueue, payTime, customer);
         eventQueue.addEvent(payEvent);
         
         //säger att kassan är upptagen
         state.getStore().occupieRegister();
      }
      
      else 
      {
         //skapa kön kalla på FIFO
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
}
