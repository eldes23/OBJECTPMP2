import java.util.Random;
import java.util.Scanner;
// MAIN TARGET:	choosing > 2 sa options kung anong card ilalatag mo = error
//				onBoard max of 5
//				power thing

// Check PA RIN: 4 COPIES ONLY PER CARD see DrawCard Function
// ang Getter pang stop sa pagaccess sa ibang class directly
// Setter = for limitation purposes, so pag mali yung input ng user, probably magtthrow ng exception

//later on nalang ako magsset up ng getter setter
//pag tapos na yung buong program para di nakakalitong tingnan


/* Main Method is used only for showing up the current status of the 2 Players / Board*/
/* PlayGame method = Decision ng player kung ano gagawin niya */
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Player player1 = new Player(30, 0, 0);
		Player player2 = new Player(30, 0, 0);

		/* Name changing */
		/*
		 * int namechoice = 0; do { System.out.println(
		 * "Do you want to change your name? ([0] Yes [1] No)");
		 * System.out.print("Answer: "); namechoice = sc.nextInt();
		 * 
		 * if (namechoice > 1 || namechoice < 0) ErrorMsg(0);
		 * 
		 * if (namechoice == 0) { sc.nextLine(); // to remove \n from sc.nextInt
		 * 
		 * /* Asking Players' name System.out.print(
		 * "Please input Player 1 name: "); player1.name = sc.nextLine();
		 * 
		 * // Players' names should not be the same do { System.out.print(
		 * "Please input Player 2 name: "); player2.name = sc.nextLine();
		 * 
		 * if (player2.name.equals(player1.name)) { ErrorMsg(1); } } while
		 * (player2.name.equals(player1.name) == true);
		 * 
		 * System.out.println("Player 1's name is: " + player1.name);
		 * System.out.println("Player 2's name is: " + player2.name);
		 * 
		 * } else if (namechoice == 1) { player1.name = "Player 1"; player2.name
		 * = "Player 2";
		 * 
		 * System.out.println("Player 1's name is: " + player1.name);
		 * System.out.println("Player 2's name is: " + player2.name); }
		 * 
		 * } while (namechoice > 1 || namechoice < 0);
		 */

		/* TEMPORARY NAMING kasi OK na yung pagset ng name */
		player1.name = "Player 1";
		player2.name = "Player 2";

		System.out.println("Player 1's name is: " + player1.name);
		System.out.println("Player 2's name is: " + player2.name);

		// Start Game
		int turn = 2;
		int num = 1; //number of turns
		boolean done = false, p1Done = false, p2Done = false;
		
		
		do {
			System.out.println("\n***********STATUS [Turn " + num + "]**************");
			System.out.print(player1.name + "'s HP: " + player1.getHp() + " || ");
			System.out.println(player2.name + "'s HP: " + player2.getHp());

			System.out.print(player1.name + "'s MP: " + (player1.mp + 1) + " || ");
			System.out.println(player2.name + "'s MP: " + (player2.mp + 1));

			/* Board (kung san ilalagay yung minion */
			System.out.print("\nPlayer 1 Board:   ");
			for(int i = 0; i < player1.onBoard.size(); i++)
				System.out.print(player1.onBoard.get(i).name + " [HP: " + player1.onBoard.get(i).hp + " DMG: " + player1.onBoard.get(i).dmg + "]" + "   ");
			
			System.out.print("\nPlayer 2 Board:   ");
			for(int i = 0; i < player2.onBoard.size(); i++)
				System.out.print(player2.onBoard.get(i).name + " [HP: " + player2.onBoard.get(i).hp + " DMG: " + player2.onBoard.get(i).dmg + "]" + "   ");		
			
			// TURN
			if (!done) {
				if (turn == 2)
					init (player1, player2, num);
				turn = PlayGame(player1, player2);
				System.out.println("turn" + turn);

				if (turn == 2) { // Pag nag-press na yung user ng Done Turn 					
					done = true;
					p1Done = true;
				}
			} else {
				if (turn == 2)				
					init (player2, player1, num);				
				turn = PlayGame(player2, player1);
				
				if (turn == 2) {
					done = false;
					p2Done = true;
				}
			}
			
			if (p1Done && p2Done) {
				num++;
				p1Done = false;
				p2Done = false;
				turn = 2;
			}
			System.out.println("DUMAAN NA DITO");
		} while (turn > -1 & player1.getHp() > 0 & player2.getHp() > 0);

		if (player1.getHp() < 1)
			System.out.println(player2.name + " won!");
		else if (player2.getHp() < 1)
			System.out.println(player1.name + " won!");
		
		sc.close();
	}

	public static void init (Player playera, Player playerb, int turn){
		/* Before game starts, dapat may card na nabunot */
		// Player 1 and Player 2 gets random card (Player 1 = 3, Player 2 = 4)
		
		if (playera.cardHand.isEmpty()){
			for (int i = 0; i < 3; i++)
				DrawCard(playera);
			for (int i = 0; i < 4; i++)
				DrawCard(playerb);
		}

		DrawCard(playera);
		
		// Once na na call na yung method na to, ABLE to attack na yung minions ng current player
		for(int i = 0; i < playera.onBoard.size(); i++){
			playera.onBoard.get(i).setcanAttack(playera.onBoard.get(i).getcanAttack());
		}
		
		if (turn < 10){
			playera.setmpCap(playera.getmpCap() + turn);
			System.out.println("mp ko:" + playera.getmpCap());
			playera.mp = playera.getmpCap();			
		}
	}
	
	/* Play Game */
	public static int PlayGame(Player playera, Player playerb) {
		Scanner sc = new Scanner(System.in);
		int move = 0;
		int j = 0;
		int ch1;
		
		
		do {
			System.out.println("\n\n====== " +playera.name + "'s Turn ======\n");
			
			/* Card Hand, UNLI */ 
			System.out.println("Cards available: ");
			for (int i = 0; i < playera.cardHand.size(); i++) //shows all cards
				System.out.println(playera.cardHand.get(i).name);

			/* Possible moves this turn */		
			System.out.println("\nMoves:");

			System.out.println("[0] Place a minion on Board");
			System.out.println("[1] Attack Player");			
			System.out.println("[2] Done");			
			System.out.println("[3] Surrender");
			move = sc.nextInt();

			switch (move) {
			
			case 0: // Place minion on Board
				System.out.println("= Choose a card to be placed on board: ");
				
				for (j = 0; j < playera.cardHand.size(); j++)
					System.out.print("[" + j + "]" + " " + playera.cardHand.get(j).name + "   ");
				System.out.println("\n\n[" + j + "] CANCEL");
				ch1 = sc.nextInt();
				System.out.println(playera.cardHand.size());
				
				if (ch1 == j + 1)
					return move;
				else { // after mag add, balik sa main interface
					// check kung sufficient yung mana
					if (playera.mp >= playera.cardHand.get(ch1).manaCost){
						playera.onBoard.add(playera.cardHand.get(ch1));
						playera.cardHand.remove(ch1);
						System.out.println("mana cost:" + playera.cardHand.get(ch1));
						//later gagawing SETTER
						playera.mp -= playera.cardHand.get(ch1).manaCost;
						
						return move;
					} else {
						ErrorMsg(2);
						return move;
					}
				}
				

			case 1: // Attacking Player (Minions and Hero)
				if (playera.onBoard.isEmpty())
					ErrorMsg(3);
				else {
					for (int i = 0; i < playera.onBoard.size(); i++) {
						if (playera.onBoard.get(i).getcanAttack() == false) {
							ErrorMsg(3);
							return move;
						} else {
							System.out.println("= Choose a card to attack the player: ");
							for (j = 0; j < playera.onBoard.size(); j++)
								if (playera.onBoard.get(j).getcanAttack())
									System.out.print("[" + j + "]" + " " + playera.onBoard.get(j).name + "   ");
							System.out.println("\n\n[" + j + "] Cancel");
							ch1 = sc.nextInt();

							if (playerb.onBoard.isEmpty()) {
								playerb.setHp(playerb.getHp() - playera.onBoard.get(ch1).dmg);
								System.out.println(playera.name + "'s " + playera.onBoard.get(ch1).name + " attacked "
										+ playerb.name + " and it dealt " + playera.onBoard.get(ch1).dmg + " damage!");
							} else {
								System.out.println("= Select which enemy to attack: ");

								for (j = 0; j < playerb.onBoard.size(); j++)
									System.out.print("[" + j + "]" + " " + playerb.onBoard.get(j).name + "   ");
								System.out.println("\n\n[" + j + "] Cancel");
								int ch2 = sc.nextInt();

								playerb.onBoard.get(ch2).hp -= playera.onBoard.get(ch1).dmg;
								playera.onBoard.get(ch1).hp -= playerb.onBoard.get(ch2).dmg;

								System.out.println(playera.name + "'s " + playera.onBoard.get(ch1).name + " attacked "
										+ playerb.name + "'s " + playerb.onBoard.get(ch2).name + " and it dealt "
										+ playera.onBoard.get(ch1).dmg + " damage!");

								// Pag namatay, magannounce + ireremove sa board
								// reason kung bakit if lang not if else, kasi
								// pwede sila parehas mamatay
								if (playerb.onBoard.get(ch2).hp < 1 || playera.onBoard.get(ch1).dmg < 1) {

									if (playerb.onBoard.get(ch2).hp < 1) {
										System.out.println(
												playerb.name + "'s " + playerb.onBoard.get(ch2).name + " died!");
										playerb.onBoard.remove(ch2);
									}
									if (playera.onBoard.get(ch1).hp < 1) {
										System.out.println(
												playera.name + "'s " + playera.onBoard.get(ch1).name + " died!");
										playera.onBoard.remove(ch1);
									}
								}

							}
						}
					}
				}
				break;

			case 2: // Done
				return move;

			case 3: // Surrender
				playera.setHp(0);
				break;

			default: //error input
				ErrorMsg(0);
				return -999;
			}
		} while (move > 5 || move < 0);
		
		sc.close();
		return move;
	}

	public static void DrawCard(Player playera) {
		/*
		 * Constructor = eto kasi yung pang lagay mo sa arrayList.
		 * 
		 * Before kasi, gumawa ako ng iba't ibang subclass ng Minion 
		 * EXAMPLE:
		 * 
		 * public class SimpleMinion extends Minion {
		 * 			int dmg = 1;
		 * }
		 * 
		 * NOTE: extends = meaning yung nakasulat sa
		 * Minion class pwede mong gamitin at i-override sa SimpleMinion class
		 * 
		 * Ang problema kasi dito, pag nandito ka na sa method na to (sa SummonMinion)
		 * at nag-add ka ng object, pag pagdisplay, walang values lahat.
		 * 
		 * so inshort, Constructor is needed for you to add an object inside an array.
		 * 
		 * Constructor should be the same name as your class, and assign
		 * a value there, kaya naging Minion sm = new Minion (params...);
		 */
		
		Minion sm = new Minion("SimpleMinion", 2, 1, 1, false);
		Minion nm = new Minion("NormalMinion", 3, 2, 2, false);
		Minion am = new Minion("AverageMinion", 3, 3, 3, false);
		Minion mm = new Minion("MediumMinion", 4, 5, 4, false);
		Minion bm = new Minion("BigMinion", 5, 5, 5, false);
		Minion lm = new Minion("LargeMinion", 6, 7, 7, false);
		Minion monsm = new Minion("MonsterMinion", 8, 8, 9, false);
		
		/* Drawing Cards */
		Random rand = new Random();
		int value = rand.nextInt(2); // kasi Random counts from 1-n ;;; SET to 6 pag final na 
		
		if (playera.getDeck() < 20) { // kasi MAX of 20 Cards sa isang cardHand
			switch (value) {
			case 0: // SimpleMinion
				if (playera.getminionCount()[0] < 4){ // MAX of 4 SAME CARDS
					playera.setminionCount(playera.getminionCount(), 0);
					playera.cardHand.add(sm);
					
					playera.setDeck(playera.getDeck() + 1);
				}
				break;
			case 1: // Normal Minion
				if (playera.getminionCount()[1] < 4){
					playera.setminionCount(playera.getminionCount(), 1);
					playera.cardHand.add(nm);	
					
					playera.setDeck(playera.getDeck() + 1);
				}
				break;
			/*case 2: // AverageMinion
				if (playera.getminionCount()[2] < 4){			 
					playera.setminionCount(playera.getminionCount(), 2);
					playera.cardHand.add(am);

					playera.setDeck(playera.getDeck() + 1);
				}
				break;
			case 3: // MediumMinion
				if (playera.getminionCount()[3] < 4){			
					playera.setminionCount(playera.getminionCount(), 3);
					playera.cardHand.add(mm);
					
					playera.setDeck(playera.getDeck() + 1);					
				}
				break;
			case 4: // BigMinion
				if (playera.getminionCount()[4] < 4){			
					playera.setminionCount(playera.getminionCount(), 4);
					playera.cardHand.add(bm);

					playera.setDeck(playera.getDeck() + 1);
				}
				break;
			case 5: // LargeMinion
				if (playera.getminionCount()[5] < 4){			
					playera.setminionCount(playera.getminionCount(), 5);
					playera.cardHand.add(lm);
					
					playera.setDeck(playera.getDeck() + 1);					
				}
				break;
			case 6: // MonsterMinion
				if (playera.getminionCount()[6] < 4){			
					playera.setminionCount(playera.getminionCount(), 6);		
					playera.cardHand.add(monsm);
					
					playera.setDeck(playera.getDeck() + 1);					
				} */
			}
		}
	}	
	
	
	/* Displaying Messages */
	public static void ErrorMsg(int x) {
		switch (x) {
		case 1:
			System.out.println("ERROR: You should not have the same name with Player 1.");
			break;
		case 2:
			System.out.println("ERROR: Insufficient mana.");
			break;
		case 3:
			System.out.println("ERROR: You don't have any Active Minions at this point.");
			break;
		default:
			System.out.println("ERROR: Invalid Input. Please try again.");
		}

	}	
}
