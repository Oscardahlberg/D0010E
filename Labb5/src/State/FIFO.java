package State;

import java.util.ArrayList;
import java.util.NoSuchElementException;



/**
 * @author Jesper Frisk, jesfri-8
 * The FIFO class represents a Queue where the first added element
 * is the first one removed. A so called "First in first out" queue.
 */
public class FIFO
{
   // The elements are stored in an ArrayList.
   private ArrayList<Object> myList;
   private int maxSize;
   
   public FIFO()
   {
      myList = new ArrayList<Object>(); 
      maxSize = 0;
   }
   
   /**
    * Returns the queue's size.
    */
   public int size()
   {
      return myList.size();
   }
   
   /**
    * Returns the previously largest size of the queue. 
    */
   public int maxSize()
   {
      return maxSize;
   }
   
   /**
    * Checks if the queue is empty and returns a boolean accordingly.
    */
   public boolean isEmpty()
   {
      if(myList.size() == 0)
      {
         return true;
      }
      return false;
   }
   
   /**
    * Returns the first element in the queue.
    * Throws an exception if the queue is empty.
    */
   public Object first()
   {
      try {
         return myList.get(0);
       }
      catch(Exception IndexOutOfBoundsException) {
         throw new NoSuchElementException();
      }
   }
   
   /**
    * Checks if the object f equals this queue.
    */
   public boolean equals(Object f)
   {
      // Checks if f and this are the same class.
      if(f.getClass() == this.getClass())
      {
         // Type cast f to a FIFO.
         FIFO fFIFO = (FIFO)f;
         
         // Checks that the queue fFIFO and this are the same size.
         // And checks that the list are the same.
         if( fFIFO.myList.size() == this.myList.size() 
               && listEquals(fFIFO.myList))
         {
            return true;
         }else
         {
            return false;
         }
         
      }else
      {
         throw new ClassCastException();
      }
   }
   
   private boolean listEquals(ArrayList<Object> list)
   {
      // Goes thru both lists.
      for(int i = 0; i < list.size(); i++)
      {
         //Checks if one of the elements is null.
         if(list.get(i) == null || myList.get(i) == null)
         {
            // If only one of the elements is null return false.
            if(list.get(i) == null ^ myList.get(i) == null)
            {
               return false;
            }
         }
         // Checks if the two elements are not the same.
         else if(!list.get(i).equals(myList.get(i)))
         {
            return false;
         }
      }
      return true;
   }
   
   public String toString()
   {
      String s = "";
      // Every element from the queue is added to the string.
      for(int i = 0; i < myList.size(); i++)
      {
         s += (i == 0 ? "":", ") + myList.get(i);
      }
      return "[" + s + "]";
   }
   
   /**
    * Adds object item to the end of the queue and checks if the current
    * size of the queue is the biggest yet. 
    */
   public void add(Object item)
   {
      myList.add(item);
      maxSize = Math.max(maxSize, myList.size());
   }
   
   /**
    * Returns the first element in the queue.
    * Throws an exception if the queue is empty.
    */
   public void removeFirst()
   {
      try {
         myList.remove(0);
       }
      catch(Exception IndexOutOfBoundsException) {
         throw new NoSuchElementException();
      }
   }
   
}
