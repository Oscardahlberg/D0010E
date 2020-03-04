package Event;

import State.*;

/**
 * 
 * @author Shahriar Chegini, Oscar Dahlberg, Jesper Frisk, Folke Forshed
 * 
 * samla ihop pengar och tar bort en kund
 * måste få tillgång till store coinMade 
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
      //add coin och remove costumer och ta bort första i kön
      state.update(this);
      state.getStore().addCoin();
      state.getStore().removeCustomer(customer);
      
      //lägger till free kassa igen 
      state.getStore().unoccupieRegister();
      
//      System.out.println(state.getStore().getFIFOQueue());
//      System.out.println(customer);
//      System.out.println(!state.getStore().getFIFOQueue().isEmpty());
      if(!state.getStore().getFIFOQueue().isEmpty()) 
      {
         Customer customerFirstInLine = (Customer) state.getStore().getFIFOQueue().first();
         
         state.getStore().getFIFOQueue().removeFirst();
         
         double payTime = this.time + state.getPayTime().next();
         
         Pay payEvent = new Pay(this.state, this.eventQueue, payTime, customerFirstInLine);
         eventQueue.addEvent(payEvent);
//         System.out.println(eventQueue);
         
         //säger att kassan är upptagen
         state.getStore().occupieRegister();
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