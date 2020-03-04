package State;

import java.util.Observable;

import Event.*;
import Time.*;

/**
 * The state class consists of all the variables that represents how
 * the simulation changes.
 * @author Jesper Frisk, Shahriar Chegini, Oscar Dahlberg, Folke Forshed.
 *
 */
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
   
   /**
    * Creates a store object and tree different time objects.
    * 
    * @param seed, passed on to UniformRandomStream and ExponentialRandomStream.
    * @param maxCustomers, passed on to store.
    * @param registers, passed on to store.
    * @param closingTime, passed on to store.
    * @param minPickTime, passed on to UniformRandomStream.
    * @param maxPickTime, passed on to UniformRandomStream.
    * @param minPayTime, passed on to UniformRandomStream.
    * @param maxPayTime, passed on to UniformRandomStream.
    * @param lambda, passed on to ExponentialRandomStream.
    */
   public State(long seed, int maxCustomers, int registers, 
         double closingTime, double minPickTime, double maxPickTime, 
         double minPayTime, double maxPayTime, double lambda)
   {
      // Sets the simulator to running.
      simRunning = true;
      
      currentTime = 0;
      lastEventTime = 0;
      
      // Creates tree different time objects.
      pickTime = new UniformRandomStream(minPickTime, maxPickTime, seed);
      payTime = new UniformRandomStream(minPayTime, maxPayTime, seed);
      arrivalTime = new ExponentialRandomStream(lambda, seed);
      
      // Creates a store.
      store = new Store(maxCustomers, registers, closingTime);
      
      this.seed = seed;
      this.minPickTime = minPickTime;
      this.maxPickTime = maxPickTime;
      this.minPayTime = minPayTime;
      this.maxPayTime = maxPayTime;
      this.lambda = lambda;
   }
   
   /**
    * @return the store.
    */
   public Store getStore()
   {
      return store;
   }
   
   /**
    * @return the simRunning variable.
    */
   public boolean getSimRunning()
   {
      return simRunning;
   }
   
   /**
    * Sets simRunning to false.
    */
   public void turnOfSimRunning()
   {
      simRunning = false;
   }
   
   /**
    * @return returns an instants of pickTime.
    */
   public UniformRandomStream getPickTime()
   {
      return pickTime;
   }
   
   /**
    * @return returns an instants of payTime.
    */
   public UniformRandomStream getPayTime()
   {
      return payTime;
   }
   
   /**
    * @return returns an instants of arrivalTime.
    */
   public ExponentialRandomStream getArrivalTime()
   {
      return arrivalTime;
   }
   
   /**
    * currentTime is the time of the latest event.
    * @return state's currrenTime. 
    */
   public double getCurrentTime()
   {
      return currentTime;
   }

   /**
    * Update set's currentEvent, currentCustomer, lastEventTime, currentTime,
    * and updates all the times. It also setsChange() and notifiesObservers(). 
    * @param thisEvent
    */
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
         store.setRegisterFreeTime(store.getRegisterFreeTime() 
               - ((lastEventTime - store.getLastPaymentTime()) 
                     * store.getFreeRegisters()));
      }
     
      setChanged();
      notifyObservers();
   }
   
   /**
    * @return currentEvent.
    */
   public Event getCurrentEvent()
   {
      return currentEvent;
   }
   
   /**
    * @return currentCustomer.
    */
   public Customer getCurrentCustomer()
   {
      return currentCustomer;
   }

   /**
    * Calculates the time in between the currentEvent and the last one.
    * @return time between the two events.
    */
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
