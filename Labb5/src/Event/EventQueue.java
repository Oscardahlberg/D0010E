package Event;

import java.util.ArrayList;

/**
 * In this class we will add and sort the events so that they 
 * will be triggered at the right moment.
 *
 * @author Jesper Frisk, Shahriar Chegini, Oscar Dahlberg, Folke Forshed.
 *
 */

public class EventQueue 
{
   private ArrayList<Event> eventList = new ArrayList<Event>();
   
   public int size()
   {
      return eventList.size();
   }
   
   public Event getFirst() 
   {
      return eventList.get(0);
   }
   
   public void removeFirst() 
   {
      eventList.remove(0);
   }
   
   /**
    * This method will add a new event and sort the queue,
    * by compare the time that the different events have.
    * It will first check all the events with lower execution
    * time and add them to a new list and then it will put the 
    * new event in the new list, lastly it will add all the events 
    * with higher execution time to the new list and change the old list
    * with the new one.
    * s
    * @param newEvent
    */
   public void addEvent(Event newEvent) 
   {
      ArrayList<Event> newEventList = new ArrayList<Event>();
      
      int counter = 0;
      
      for(int i = 0; i < eventList.size(); i++) 
      {
         if(eventList.get(i).getTime() <= newEvent.getTime()) 
         {
            newEventList.add(eventList.get(i));
            counter ++;
         }
      }
      
      newEventList.add(newEvent);
      
      for(int i = counter; i < eventList.size(); i++) 
      {
         if(eventList.get(i).getTime() > newEvent.getTime()) 
         {
            newEventList.add(eventList.get(i));
         }
      }
      eventList = newEventList;
   }
   
   public String toString()
   {
      return eventList.toString();
   }
}
