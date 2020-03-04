package Event;

import java.util.ArrayList;

public class EventQueue 
{
   private ArrayList<Event> eventList = new ArrayList<Event>();
   
   public static void main(String[] args) 
   {
      EventQueue q = new EventQueue();
      
   }
   
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
   
   public void addEvent(Event newEvent) 
   {
      //ArrayList<Integer> newIntList = new ArrayList<Integer>();
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