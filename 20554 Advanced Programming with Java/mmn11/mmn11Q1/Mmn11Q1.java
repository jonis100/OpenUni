package mmn11Q1;

import javax.swing.JOptionPane;

public class Mmn11Q1 {
	public static void main(String[] args) { 
	JOptionPane.showMessageDialog(null, "Welcom to Yoni game");
	Pile p = new Pile();
	p.Init();
	Pile playerA = p.first_half_pileList();
	Pile playerB = p.second_half_pileList();
	JOptionPane.showMessageDialog(null, "player A  And player B got the piles");
	while (playerA.size() != 0 && (playerA.size() != 0)){
		Pile P1 = new Pile();
		Pile P2 = new Pile();
 		P1.addFirst(playerA.pollFirst());
 		P2.addFirst(playerB.pollFirst());
		Util.turn(playerA, playerB, P1, P2);
		}
	if(playerA.size() == 0){
		JOptionPane.showMessageDialog(null, "player B  won the game!" +" B_pile_size: " + playerB.size() +"\r\n" +
											" A_pile_size: " + playerA.size());
	}
	if(playerB.size() == 0){
		JOptionPane.showMessageDialog(null, "player A  won the game!" +" A_pile_size: " + playerA.size() +"\r\n" +
											" B_pile_size: " + playerB.size());
	}	
	System.out.println("Finished");
	}

}
