package Event;

import State.*;

public class Start extends Event
{ 
   
   private double closeTime;
   private double time; 
   
   private Close closing; 
   private Arrival arrival;
   
   public Start(State state, EventQueue eventQueue) 
   {
      super(state, eventQueue);
      this.time = 0d;
      this.closeTime = state.getStore().getClosingTime();
   }

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

   @Override
   public Customer getCustomer()
   {
      // TODO Auto-generated method stub
      return null;
   }
}