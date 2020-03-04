package View;


import java.util.Observable;
import java.util.Observer;

import State.State;
import State.Store;

public abstract class View implements Observer {

   State state;
   Store store;
   
   public View(State state, Store store)
   {
      state.addObserver(this);
   }
    @Override
    abstract public void update(Observable o, Object arg);

}