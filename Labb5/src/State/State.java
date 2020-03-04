package State;

import java.util.Observable;

import Event.*;
import Time.*;

public class State extends Observable
{
   private Store store;
   
   private boolean simRunning;
   
   private double currentTime;
   private double lastEventTime;
   
   private UniformRandomStream pickTime;
   private UniformRandomStream payTime;
   private ExponentialRandomStream arrivalTime;
   
   private Event currentEvent;
   private Customer currentCustomer;
   
   private long seed;
   private double minPickTime;
   private double maxPickTime;
   private double minPayTime;
   private double maxPayTime;
   private double lambda;
   
   public State(long seed, int maxCustomers, int registers, 
         double closingTime, double minPickTime, double maxPickTime, 
         double minPayTime, double maxPayTime, double lambda)
   {
      simRunning = true;
      
      currentTime = 0;
      lastEventTime = 0;
      
      pickTime = new UniformRandomStream(minPickTime, maxPickTime, seed);
      payTime = new UniformRandomStream(minPayTime, maxPayTime, seed);
      arrivalTime = new ExponentialRandomStream(lambda, seed);
      
      store = new Store(maxCustomers, registers, closingTime);
      
      this.seed = seed;
      this.minPickTime = minPickTime;
      this.maxPickTime = maxPickTime;
      this.minPayTime = minPayTime;
      this.maxPayTime = maxPayTime;
      this.lambda = lambda;
   }
   
   public Store getStore()
   {
      return store;
   }
   
   public boolean getSimRunning()
   {
      return simRunning;
   }
   
   public void turnOfSimRunning()
   {
      simRunning = false;
   }
   
   public UniformRandomStream getPickTime()
   {
      return pickTime;
   }
   
   public UniformRandomStream getPayTime()
   {
      return payTime;
   }
   
   public ExponentialRandomStream getArrivalTime()
   {
      return arrivalTime;
   }
   
   public double getCurrentTime()
   {
      return currentTime;
   }

   public void update(Event thisEvent)
   {
      currentEvent = thisEvent;
      currentCustomer = thisEvent.getCustomer();
            
      lastEventTime = currentTime;
      currentTime = thisEvent.getTime();
      
      if(thisEvent.getClass() != Stop.class)
      {
         store.setRegisterFreeTime(store.getRegisterFreeTime() + 
               (timeBetweenEvent() * store.getFreeRegisters()));
         store.setCustomerQueueTime(store.getCustomerQueueTime() +
               (timeBetweenEvent() * store.getFIFOQueue().size()));
         
         if(thisEvent.getClass() == Pay.class)
         {
            store.setLastPaymentTime(thisEvent.getTime());
         }
         
      }else
      {
//         System.out.println(store.getRegisterFreeTime());
//         System.out.println(store.getLastPaymentTime());
//         System.out.println(lastEventTime);
         store.setRegisterFreeTime(store.getRegisterFreeTime() 
               - ((lastEventTime - store.getLastPaymentTime()) 
                     * store.getFreeRegisters()));
      }
     
      setChanged();
      notifyObservers();
   }
   
   public Event getCurrentEvent()
   {
      return currentEvent;
   }
   
   public Customer getCurrentCustomer()
   {
      return currentCustomer;
   }

   private double timeBetweenEvent()
   {
      return currentTime - lastEventTime;
   }

   /**
    * @return the minPickTime
    */
   public double getMinPickTime()
   {
      return minPickTime;
   }

   /**
    * @return the maxPickTime
    */
   public double getMaxPickTime()
   {
      return maxPickTime;
   }

   /**
    * @return the minPaytime
    */
   public double getMinPayTime()
   {
      return minPayTime;
   }

   /**
    * @return the maxPaytime
    */
   public double getMaxPayTime()
   {
      return maxPayTime;
   }

   /**
    * @return the lambda
    */
   public double getLambda()
   {
      return lambda;
   }

   /**
    * @return the seed
    */
   public long getSeed()
   {
      return seed;
   }
   
}
