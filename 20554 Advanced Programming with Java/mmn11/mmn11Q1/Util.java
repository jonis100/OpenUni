package mmn11Q1;

import javax.swing.JOptionPane;

public class Util {
	public static void turn(Pile playerA, Pile playerB, Pile P1, Pile P2) {
			Card c1 = P1.peek();
			Card c2 = P2.peek();
			if 	(c1 == null) {
				JOptionPane.showMessageDialog(null, "player B  won the game!" +" B_pile_size: " + playerB.size() +"\r\n" +
													" A_pile_size: " + playerA.size());
				System.exit(0);
			}
			if (c2 == null) {
				JOptionPane.showMessageDialog(null, "player A  won the game!"+" A_pile_size: " + playerA.size() +"\r\n" +
													" B_pile_size: " + playerB.size());
				System.exit(0);
			}
			if (c1.getValue()>c2.getValue()) {
				JOptionPane.showMessageDialog(null, "player A  pic card: "+ c1.toString()+ " pile_size: " + playerA.size() +"\r\n" +
				"player B  pic card: "+ c2.toString()+ " pile_size: " + playerB.size() +"\r\n" + 
				"player A won the turn");
				playerA.addAll(P1);
				playerA.addAll(P2);
//				System.out.println("size of P1: " + P1.size());
//				System.out.println("size of P2: " + P2.size());

				return;
			}
			if (c2.getValue()>c1.getValue()) {
				JOptionPane.showMessageDialog(null, "player A  pic card: "+ c1.toString()+ " pile_size: " + playerA.size() +"\r\n" +
				"player B  pic card: "+ c2.toString()+ " pile_size: " + playerB.size() +"\r\n" +
				"player B won the turn");
				playerB.addAll(P1);
				playerB.addAll(P2);
//				System.out.println("size of P1: " + P1.size());
//				System.out.println("size of P2: " + P2.size());
				return;
			}
		 	for (int i=0;i<3;i++) {
		 		P1.addFirst(playerA.pollFirst());
		 		P2.addFirst(playerB.pollFirst());
		 	}
		 	System.out.println("It is war");
			turn(playerA,playerB, P1, P2);
			
		}
		
	}


