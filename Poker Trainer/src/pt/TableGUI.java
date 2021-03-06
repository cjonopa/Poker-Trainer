package pt;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class TableGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFrame frame;
	JLabel lblMyHand, lblMyHand2, lblOpHand, lblOpHand2, lblBoard1, lblBoard2, lblBoard3, lblBoard4, lblBoard5, 
	lblOpHandName, lblYourHandName, lblWinner;
	



	/**
	 * Create the application.
	 */
	public TableGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 18));
		frame.setBounds(200, 200, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnDeal = new JButton("Deal");
		btnDeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Hand currentHand = new Hand();
				//Deal my Cards
				Card card1 = currentHand.getMyHoleCards()[0];
				Card card2 = currentHand.getMyHoleCards()[1];
				
				displayCard(card1, lblMyHand);
				displayCard(card2, lblMyHand2);
				
				//Deal Opponent Cards
				Card opcard1 = currentHand.getOpponentHoleCards()[0];
				Card opcard2 = currentHand.getOpponentHoleCards()[1];
				
				displayCard(opcard1, lblOpHand);
				displayCard(opcard2, lblOpHand2);

				//Deal Board Cards
				Card bc1 = currentHand.getFlop()[0];
				Card bc2 = currentHand.getFlop()[1];
				Card bc3 = currentHand.getFlop()[2];
				Card bc4 = currentHand.getTurn();
				Card bc5 = currentHand.getRiver();
				
				displayCard(bc1, lblBoard1);
				displayCard(bc2, lblBoard2);
				displayCard(bc3, lblBoard3);
				displayCard(bc4, lblBoard4);
				displayCard(bc5, lblBoard5);
				
				PokerHand myHand = new PokerHand(card1, card2, bc1, bc2, bc3, bc4, bc5);
				PokerHand opHand = new PokerHand(opcard1, opcard2, bc1, bc2, bc3, bc4, bc5);
				lblYourHandName.setText(myHand.getHandName());
				lblOpHandName.setText(opHand.getHandName());
				
				int winner = PokerHand.CompareHands(myHand, opHand);
				if (winner == 1)
					lblWinner.setText("You Win!");
				if (winner == 2)
					lblWinner.setText("You Lose!");
				if (winner == 0)
					lblWinner.setText("Tie!");
						

			}
		});
		
		
		btnDeal.setBounds(100, 500, 68, 23);
		frame.getContentPane().add(btnDeal);
		
		JLabel lblPlayer = new JLabel("Player 1");
		lblPlayer.setBounds(416, 523, 68, 14);
		frame.getContentPane().add(lblPlayer);
		
		lblMyHand = new JLabel("");
		lblMyHand.setBounds(336, 404, 96, 96);
		frame.getContentPane().add(lblMyHand);
		
		lblMyHand2 = new JLabel("");
		lblMyHand2.setBounds(442, 404, 96, 96);
		frame.getContentPane().add(lblMyHand2);
		
		JLabel lblOpponent = new JLabel("Opponent");
		lblOpponent.setBounds(400, 80, 68, 14);
		frame.getContentPane().add(lblOpponent);
		
		lblOpHand = new JLabel("");
		lblOpHand.setBounds(336, 105, 96, 96);
		frame.getContentPane().add(lblOpHand);
		
		lblOpHand2 = new JLabel("");
		lblOpHand2.setBounds(442, 105, 96, 96);
		frame.getContentPane().add(lblOpHand2);
		
		lblBoard1 = new JLabel("");
		lblBoard1.setBounds(148, 258, 96, 96);
		frame.getContentPane().add(lblBoard1);
		
		lblBoard2 = new JLabel("");
		lblBoard2.setBounds(273, 258, 96, 96);
		frame.getContentPane().add(lblBoard2);
		
		lblBoard3 = new JLabel("");
		lblBoard3.setBounds(388, 258, 96, 96);
		frame.getContentPane().add(lblBoard3);
		
		lblBoard4 = new JLabel("");
		lblBoard4.setBounds(518, 258, 96, 96);
		frame.getContentPane().add(lblBoard4);
		
		lblBoard5 = new JLabel("");
		lblBoard5.setBounds(652, 258, 96, 96);
		frame.getContentPane().add(lblBoard5);
		
		lblOpHandName = new JLabel("Op Hand");
		lblOpHandName.setBounds(615, 130, 120, 23);
		frame.getContentPane().add(lblOpHandName);
		
		lblYourHandName = new JLabel("Your Hand");
		lblYourHandName.setBounds(615, 463, 120, 23);
		frame.getContentPane().add(lblYourHandName);
		
		lblWinner = new JLabel("Who Wins?");
		lblWinner.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblWinner.setBounds(80, 80, 120, 40);
		frame.getContentPane().add(lblWinner);
		frame.setVisible(true);
	}
	
	//Set the image 
	private void displayCard(Card c, JLabel lbl) {
		Image cardImage = new ImageIcon(this.getClass().getResource("/"+c.toString()+".png")).getImage();
		lbl.setIcon(new ImageIcon(cardImage));

	}
}
