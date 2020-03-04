package View;

import Event.*;
import State.*;

import java.util.Observable;

public class PrintView extends View
{
   private State state;
   private Store store;

   public PrintView(EventQueue eventQueue, State state, Store store)
   {
      state.addObserver(this);

      this.state = state;
      this.store = store;
   }

   private void firstPrint()
   {
      System.out.println("PARAMETRAR");
      System.out.println("==========");
      System.out.println("Antal kassor, N..........: " + store.getRegisters());
      System.out.println("Max som ryms, M..........: " + store.getMaxCustomers());
      System.out.println("Ankomshastighet, lambda..: " + state.getLambda());
      System.out.println("Plocktider, [P_min..Pmax]: [" + state.getMinPickTime() + ".." + state.getMaxPickTime() + "]");
      System.out.println("Betaltider, [K_min..Kmax]: [" + state.getMinPayTime() + ".." + state.getMaxPayTime() + "]");
      System.out.println("Frö, f...................: " + state.getSeed());
      System.out.println("");
      System.out.println("FÖRLOPP");
      System.out.println("=======");
      System.out.println("Tid\tHändelse\tKund\t?\tled\tledT\tI\t$\t:-(\tköat\tköT\tköar\t[Kassakö..]");
   }

   public void lastPrint()
   {
      System.out.println("RESULTAT");
      System.out.println("========");
      System.out.println("");
      System.out.println("1) Av " + store.getTotalCustomers() + " handlade " + store.getCoinMade() + " medan "
            + store.getCustomersTurnedAway() + " missade.");
      System.out.println("");
      System.out.println("2) Total tid " + store.getRegisters() + " kassor varit lediga:" + " "
            + store.getRegisterFreeTime() + " te.");
      System.out.println("\tGenomsnittlig ledig kassatid: " + store.getRegisterFreeTime() / store.getRegisters()
            + " te (dvs " + store.getClosingTime() / store.getRegisterFreeTime() + "% av tiden från öppning "
            + "tills sista kunden betalat).");
      System.out.println("");
      System.out.println("3) Total tid " + store.getTotalCustomersInQueue() + " kunder tvingats köa: "
            + store.getCustomerQueueTime() + " te.");
      System.out.println(
            "\tGenomsnittlig kötid: " + store.getCustomerQueueTime() / store.getTotalCustomersInQueue() + " te.");
   }

   private void printEvent()
   {
      System.out.println(state.getCurrentTime() + "\t" + state.getCurrentEvent() + "\t\t" + state.getCurrentCustomer()
            + "\t" + store.getIsOpen() + "\t" + store.getFreeRegisters() + "\t" + store.getRegisterFreeTime()
            + "\t" + store.getCustomersInStore() + "\t" + store.getCoinMade() + "\t"
            + store.getCustomersTurnedAway() + "\t" + store.getTotalCustomersInQueue() + "\t" + store.getCustomerQueueTime()
            + "\t" + store.getFIFOQueue().size() + "\t" + store.getFIFOQueue() + "\t");
   }

   @Override
   public void update(Observable o, Object arg)
   {
      if (state.getCurrentEvent().getClass() == Start.class)
      {
         firstPrint();
         printEvent();
      } else if (state.getCurrentEvent().getClass() == Stop.class)
      {
         printEvent();
         lastPrint();
      } else
      {
         printEvent();
      }
   }
}