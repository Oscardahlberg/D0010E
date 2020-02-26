package lab4.gui;
import lab4.gui.ConnectionWindow;

import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import java.awt.event.*;
import java.awt.FlowLayout;
import java.awt.MouseInfo;
import java.awt.event.*;
import lab4.client.GomokuClient;
import lab4.data.GameGrid;
import lab4.data.GomokuGameState;

/*
 * The GUI class
 */

public class GomokuGUI implements Observer{

	private JFrame frame;
	private JLabel messageLabel;
	private JPanel panel;
	
	private JButton connectButton;
	private JButton newGameButton;
	private JButton disconnectButton;

    private GamePanel gameGridPanel;
    private GomokuClient client;
    private GomokuGameState gamestate;

    /**
     * The constructor
     *
     * @param g   The game state that the GUI will visualize
     * @param c   The client that is responsible for the communication
     */
    
    public GomokuGUI(GomokuGameState g, GomokuClient c){
        this.client = c;
        this.gamestate = g;
        client.addObserver(this);
        gamestate.addObserver(this);
        
        frame = new JFrame("Gomoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel = new JPanel();
        
        connectButton = new JButton("connect");
        newGameButton = new JButton("New game");
        disconnectButton = new JButton("Disconnect");
        
        gameGridPanel = new GamePanel(gamestate.getGameGrid());
        
        gameGridPanel.addMouseListener(new MouseAdapter(){
        	public void mouseClicked(MouseEvent e){
        		int[] gridPosition = gameGridPanel.getGridPosition(e.getX(), e.getY());

        		gamestate.move(gridPosition[0], gridPosition[1]);
                gameGridPanel.grid = gamestate.getGameGrid();
        	}
        });
        
        this.connectButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ConnectionWindow connectWin = new ConnectionWindow(c);
			}
        	
        });
        this.newGameButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gamestate.newGame();
			}
        	
        });

        this.disconnectButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gamestate.disconnect();
			}
        	
        });
        
        panel.add(gameGridPanel);
        panel.add(connectButton);
        panel.add(newGameButton);
        panel.add(disconnectButton);
        
        messageLabel = new JLabel();
        messageLabel.setText("Welcome to Gomoku!");
        panel.add(messageLabel);
        

		//frame.setLayout(new FlowLayout(FlowLayout.TRAILING));
		
		frame.pack();
        frame.setSize(325, 450);
        frame.add(panel);
        frame.setVisible(true);
    }


	public void update(Observable arg0, Object arg1) {

        // Update the buttons if the connection status has changed
        if(arg0 == client){
            if(client.getConnectionStatus() == GomokuClient.UNCONNECTED){
                connectButton.setEnabled(true);
                newGameButton.setEnabled(false);
                disconnectButton.setEnabled(false);
            }else{
                connectButton.setEnabled(false);
                newGameButton.setEnabled(true);
                disconnectButton.setEnabled(true);
            }
        }

        // Update the status text if the gamestate has changed
        if(arg0 == gamestate){
            messageLabel.setText(gamestate.getMessageString());
        }
        gameGridPanel.update(arg0, arg1);

    }

}