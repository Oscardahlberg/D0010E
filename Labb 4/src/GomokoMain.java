import lab4.*;
import lab4.client.GomokuClient;
import lab4.data.GomokuGameState;
import lab4.gui.*;

public class GomokoMain {
    public static void main(String[] args) {

        GomokuClient client;

        if(args.length == 1){
            client = new GomokuClient(Integer.parseInt(args[0]));
        }
        else{
            client = new GomokuClient(4124);
        }

        GomokuGameState gameState = new GomokuGameState(client);
        GomokuGUI gui = new GomokuGUI(gameState, client);
    }
}
