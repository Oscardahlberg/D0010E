package Event;

import State.*;

/**
 * 
 * @author Shahriar 
 * 
 * skapa en person 
 * men kolla först om det är för många eller inte 
 * beräkna även antal missade kunder 
 * 
 * jag skapar pick 
 * 
 * 
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

   public void doMe() 
   {
      //på alla doMe: updateTime(); ligger i store ska ta emot this.time i updateTime()
      
      state.update(this);
       
      this.state.getStore().addCustomer(customer);
      
//      System.out.println(customer.getState());
      if(customer.getState() == CustomerState.inStore)
      {
//         System.out.println(customer);
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