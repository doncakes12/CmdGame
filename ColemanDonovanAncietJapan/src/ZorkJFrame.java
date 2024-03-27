




import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;

public class ZorkJFrame {

	private JFrame frmZorkCheckpoint;
	private JTextField textField;
	private boolean hasWon = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ZorkJFrame window = new ZorkJFrame();
					window.frmZorkCheckpoint.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ZorkJFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmZorkCheckpoint = new JFrame();
		frmZorkCheckpoint.setTitle("Ancient Japan");
		frmZorkCheckpoint.setResizable(false);
		frmZorkCheckpoint.setBounds(100, 100, 1000, 500);
		frmZorkCheckpoint.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmZorkCheckpoint.getContentPane().setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(SystemColor.control);
		textArea.setBounds(27, 29, 749, 373);
		frmZorkCheckpoint.getContentPane().add(textArea);
		textArea.setText(Commands.gameIntro());
		textArea.setEditable(false);

		
		textField = new JTextField();
		textField.setBounds(27, 416, 750, 23);
		frmZorkCheckpoint.getContentPane().add(textField);
		textField.setColumns(10);
		
//------------------------------------------------------------------------------------------------------------------	
		
		JButton northButton = new JButton("North");
		northButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(hasWon)
					frmZorkCheckpoint.dispose();
				if(Commands.getHealth() <= 0){
					frmZorkCheckpoint.dispose();
				}
				textArea.setText(Commands.north());
			}
		});
		
		northButton.setBounds(837, 216, 89, 23);
		frmZorkCheckpoint.getContentPane().add(northButton);

//------------------------------------------------------------------------------------------------------------------		
		JButton eastButton = new JButton("East");
		eastButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(hasWon)
					frmZorkCheckpoint.dispose();
				if(Commands.getHealth() <= 0){
					frmZorkCheckpoint.dispose();
				}
				textArea.setText(Commands.east());
			}
		});
		
		eastButton.setBounds(882, 241, 89, 23);
		frmZorkCheckpoint.getContentPane().add(eastButton);
		
//------------------------------------------------------------------------------------------------------------------		
		JButton westButton = new JButton("West");
		westButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(hasWon)
					frmZorkCheckpoint.dispose();
				if(Commands.getHealth() <= 0){
					frmZorkCheckpoint.dispose();
				}
				textArea.setText(Commands.west());
			}
		});
		
		westButton.setBounds(792, 241, 89, 23);
		frmZorkCheckpoint.getContentPane().add(westButton);
		
//------------------------------------------------------------------------------------------------------------------		
		JButton southButton = new JButton("South");
		southButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(hasWon)
					frmZorkCheckpoint.dispose();
				if(Commands.getHealth() <= 0){
					frmZorkCheckpoint.dispose();
				}
				textArea.setText(Commands.south());
			}
		});
			
		southButton.setBounds(837, 265, 89, 23);
		frmZorkCheckpoint.getContentPane().add(southButton);
		
		
//------------------------------------------------------------------------------------------------------------------		
		JButton helpButton = new JButton("Help");
		helpButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(hasWon)
					frmZorkCheckpoint.dispose();
				if(Commands.getHealth() <= 0){
					frmZorkCheckpoint.dispose();
				}
				textArea.setText(Commands.help());
			}
		});
		
		helpButton.setBounds(887, 392, 89, 23);
		frmZorkCheckpoint.getContentPane().add(helpButton);
		
//------------------------------------------------------------------------------------------------------------------		
		JButton fightButton = new JButton("Fight");
		fightButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(hasWon)
					frmZorkCheckpoint.dispose();
				if(Commands.getHealth() <= 0){
					frmZorkCheckpoint.dispose();
				}
				textArea.setText(Commands.fight());
			}
		});
		
		fightButton.setBounds(887, 344, 89, 23);
		frmZorkCheckpoint.getContentPane().add(fightButton);
//------------------------------------------------------------------------------------------------------------------		
		JButton mapButton = new JButton("Inventory");		
		mapButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(hasWon)
					frmZorkCheckpoint.dispose();
				if(Commands.getHealth() <= 0){
					frmZorkCheckpoint.dispose();
				}
				textArea.setText(Commands.inventory());
			}
		});
		
		mapButton.setBounds(887, 368, 89, 23);
		frmZorkCheckpoint.getContentPane().add(mapButton);
//------------------------------------------------------------------------------------------------------------------
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String userInput = textField.getText();
				//get the command from the user input
				
				
				if(Commands.getHealth() <= 0) {
					textArea.setText("You have died. Game Over");
					frmZorkCheckpoint.dispose();
				} 
				
				
				if (userInput.equals("quit"))
					frmZorkCheckpoint.dispose();
				String command = Commands.getFirstWord(userInput);
				
				//get the rest of the string after the first word if available
				
				String item = Commands.getRestOfSentence(userInput);
				
				//use the command and item to determine the proper output
				
				String output = Commands.executeCommand(command, item);
				 
//				output += "\n" + Commands.getCurrentRoom();
//				output += "\n" + Commands.look();
				//print the output for the user
				textArea.setText(output);
				
				textField.setText("");
				
				if(Commands.getRoom(15, 4).getEnemies().isEmpty()){
					textArea.setText("\nCongrats you have won the game!");
					hasWon = true;
				}
			}
		});
		
		submitButton.setBounds(887, 416, 89, 23);
		frmZorkCheckpoint.getContentPane().add(submitButton);
		frmZorkCheckpoint.getRootPane().setDefaultButton(submitButton);
		
//------------------------------------------------------------------------------------------------------------------

		Commands.initializeMap();

//------------------------------------------------------------------------------------------------------------------

		
	}
	
}
