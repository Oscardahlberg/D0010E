package View;

import Event.EventQueue;
import State.*;

import java.util.Observable;

public class PrintView implements View{

    EventQueue eventQueue;
    State state;
    Store store;

    Boolean isFirstPrint;

    public PrintView(Boolean isFirstPrint, EventQueue eventQueue, State state, Store store){
        this.isFirstPrint = isFirstPrint;
        this.eventQueue = eventQueue;
        this.state = state;
        this.store = store;
    }

    private void firstPrint(){
        /*System.out.println("PARAMETRAR");
        System.out.println("==========");
        System.out.println("Antal kassor, N..........: " + store.getRegisters());
        System.out.println("Max som ryms, M..........: " + store.getMaxCustomers());
        System.out.println("Ankomshastighet, lambda..: 1.0");
        System.out.println("Plocktider, [P_min..Pmax]: [" + state.getPickMin()
                + ".." + state.getPickMax() + "]");
        System.out.println("Betaltider, [K_min..Kmax]: [" + state.getPaykMin()
                + ".." + state.getPayMax() + "]");
        System.out.println("Frö, f...................: " + state.getSeed());
        System.out.println("");
        System.out.println("FÖRLOPP");
        System.out.println("=======");*/
    }

    private static void printEvent(){
        System.out.println("Tid\tHändelse\tKund\t?\tled\tledT\tI\t$\t:-(\tköat\tköT\tköar\t[Kassakö..]");
        System.out.println("123\tPay\t0");
    }

    public static void main(String[] args){
        PrintView.printEvent();
    }

    @Override
    public void update(Observable o, Object arg) {
        if(isFirstPrint){
            firstPrint();
        } else {
            printEvent();
        }
    }
}
