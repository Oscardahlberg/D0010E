package Event;

import State.*;

/**
 * 
 * The Start event creates a number of arrival events and a close event
 * by adding the events to the eventQueue. All the arrival events creates 
 * at the same time. This event will be triggerd at the start of the 
 * simulation and will allways be the first in eventQueue.
 * 
 * @author Jesper Frisk, Shahriar Chegini, Oscar Dahlberg, Folke Forshed.
 *
 */

public class Start extends Event
{ 
   private Close closing; 
   private Arrival arrival;
   
   private double closeTime;
   private double time; 
   
  /**
   * start will have State and EventQueue as argument so that we can 
   * get access to the necessary variables and so that we can 
   * add events to the eventQueue
   * 
   * @param state
   * @param eventQueue
   */
   public Start(State state, EventQueue eventQueue) 
   {
      super(state, eventQueue);
      this.time = 0d;
      this.closeTime = state.getStore().getClosingTime();
   }

   /**
    * Firstly the method will "open" the store. 
    * It will add close event to eventQueue so that we know when
    * the store will close.
    * All the arrival events will be created at the same time but  
    * will be executed at different time. 
    * Arrival time will be created if closeTime is greater than arrivalTime
    * but the side effect will be that one person will be arriving to the 
    * store after its closed.
    */
   public void doMe() 
   {
      state.update(this);
      
      state.getStore().setIsOpen(true);
      closing = new Close(this.state, eventQueue, closeTime);
      eventQueue.addEvent(closing);
      
      double arrivalTime = 0;
      do 
      {
         arrivalTime = arrivalTime + state.getArrivalTime().next();
         arrival = new Arrival(this.state, this.eventQueue, arrivalTime);
         eventQueue.addEvent(arrival);
      }while(closeTime >  arrivalTime);
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
      return "Start";
   }
}
