package Event;
import State.*;

/**
 * 
 * A abstract class that tells how every event class should behave.
 * 
 * @author Jesper Frisk, Shahriar Chegini, Oscar Dahlberg, Folke Forshed.
 *
 */

abstract public class Event 
{
   protected State state;
   protected EventQueue eventQueue;
   
   abstract public void doMe();
   abstract public double getTime(); 
   abstract public String getName();
   abstract public Customer getCustomer();
   
   public Event(State state, EventQueue eventQueue) 
   {
      this.eventQueue = eventQueue;
      this.state = state;
   }
}
