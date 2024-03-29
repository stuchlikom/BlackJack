import java.util.Scanner; 

class Play {

	public static void main(String[] args) throws InterruptedException {
		char goOn = 'j';
		while (goOn == 'j'){
			System.out.print("\033[H\033[2J");
			GoOn();
			Scanner playerChoice = new Scanner(System.in);
	        System.out.print("\nMöchtest du nochmal spielen? Tippe j oder n: ");
            goOn = playerChoice.next().charAt(0);			
		}
	}

    public static void GoOn() throws InterruptedException {

        final String CARD_DOWN = "\u001B[47m\u001B[34m" + new String(new int[] {0x1F0A0}, 0, 1) + " \u001B[0m";

        Participant dealer = new Participant();
        Participant player = new Participant();
        char yesOrNo; // for Player-Inputs
        boolean lost = false;

        // shuffles the card deck
        Stack.shuffle();

        // Player's turn
        System.out.println("       ################################################################");
        System.out.println("       #    ____  _            _    _            _                    #");
        System.out.println("       #   |  _ \\| |          | |  (_)          | |                   #");
        System.out.println("       #   | |_) | | __ _  ___| | ___  __ _  ___| | __                #");
        System.out.println("       #   |  _ <| |/ _` |/ __| |/ / |/ _` |/ __| |/ /                #");
        System.out.println("       #   | |_) | | (_| | (__|   <| | (_| | (__|   <                 #");
        System.out.println("       #   |____/|_|\\__,_|\\___|_|\\_\\ |\\__,_|\\___|_|\\_\\                #");
        System.out.println("       #                          _/ |                                #");
        System.out.println("       #                         |__/                                 #");
        System.out.println("       #                                                              #");
        System.out.println("       #                   Copyright by Kadir, Markus, Thorsten, Timo #");
        System.out.println("       ################################################################");


  
  




             
                


        System.out.println("\nPlayer:");

        Card tempCard = Stack.giveCard(); // 1st card

        if (tempCard.getValue() == 1) {
        	tempCard.setValue(11);
        }
        player.takeCard(tempCard); // this very card given to player
 		tempCard = Stack.giveCard(); // 2nd card
        player.takeCard(tempCard); // this very card given to player
        System.out.print(player.toString()); // print out hand
        System.out.println(" " + player.getSum()); // print out sums


        if (tempCard.getValue() == 1) {
            Scanner playerChoice = new Scanner(System.in);
            System.out.printf("Möchtest du das Ass stattdessen als 11 werten lassen (%d)? Tippe j oder n: ", player.getSum() + 10);
            yesOrNo = playerChoice.next().charAt(0);
            if (yesOrNo == 'j') {
            	player.aceEleven();
            	System.out.println("OK, hier Deine neue Wertung: " + player.toString() + " " + player.getSum()); // print out hand
            }
        }

        if (player.getSum() > 21) {
            System.out.println("Sorry. You lost!");
        }


        // DEALER
        tempCard = Stack.giveCard(); // 1st card
        if (tempCard.getValue() == 1) {
        	tempCard.setValue(11);
        }
        dealer.takeCard(tempCard); // this very card given to dealer
        System.out.println("Dealer:");
        System.out.print(dealer.toString()); // print out hand
 		System.out.println(CARD_DOWN + " " + dealer.getSum() + " + ?");
 		System.out.println("\n###############################################################################\n");

        tempCard = Stack.giveCard(); // 2nd card
        dealer.takeCard(tempCard); // this very card given to player
        if (tempCard.getValue() == 1) {
			if (dealer.getSum() < 22) {
			    dealer.aceEleven();        	
			}            
        }

        boolean wannaDraw = true;
        while (wannaDraw) {
            Scanner playerChoice = new Scanner(System.in);
            System.out.print("Möchtest du eine Karte ziehen? Tippe j oder n: ");
            yesOrNo = playerChoice.next().charAt(0);
            if (yesOrNo == 'j') {
                tempCard = Stack.giveCard(); // card extracted from stack
                player.takeCard(tempCard); // this very card given to player

                System.out.println("Ok, hier ist dein neues Blatt:");
                System.out.print(player.toString()); // print out hand
                System.out.println(" " + player.getSum()); // print out sum
		        if (tempCard.getValue() == 1) {
        		    //Scanner playerChoice = new Scanner(System.in);
            		System.out.printf("Möchtest du das Ass stattdessen als 11 werten lassen (%d)? Tippe j oder n: ", player.getSum() + 10);
            		yesOrNo = playerChoice.next().charAt(0);
            		if (yesOrNo == 'j') {
            			player.aceEleven();
            			System.out.print("\nOK, hier Deine neue Wertung: " + player.toString() + " " + player.getSum()); // print out hand
            		}
        		}

                System.out.println();

                if (player.getSum() > 21) {
                    System.out.println("Sorry, Du hast verloren. :-(");
                    lost = true;
                    wannaDraw = false;
	           	} 
            } else {
                wannaDraw = false;
            }
        }
        System.out.println("###############################################################################");
        
        // Wenn noch nicht verloren....
        if (!lost) {
	        System.out.println("Dealer:");
	        System.out.println(dealer.toString() + " " + dealer.getSum());
	        // Dealer's turn
	        wannaDraw = true;
	        while (wannaDraw) {
	            if (dealer.getSum() > 16) {
	            	wannaDraw = false;
	            } else if (dealer.getSum() < 17) {
	                Thread.sleep(1000);
	                tempCard = Stack.giveCard();
	                dealer.takeCard(tempCard);
	                System.out.println("Der Dealer zieht noch eine Karte.");
	                Thread.sleep(1000);
	                System.out.println(dealer.toString() + " " + dealer.getSum());
	            }
	            if (dealer.getSum() > 21) {
	                Thread.sleep(1000);
	                System.out.println("Du gewinnst, weil der Dealer überreizt hat. " + player.getSum() + " zu " + dealer.getSum());
	                wannaDraw = false;
	            } else if (wannaDraw == false) {
			        if (player.getSum() > dealer.getSum()) {
			            Thread.sleep(1000);
			            System.out.println("Du gewinnst mit " + player.getSum() + " zu " + dealer.getSum());
			            wannaDraw = false;
			        } else if (player.getSum() < dealer.getSum()) {
			            Thread.sleep(1000);
			            System.out.println("Du verlierst mit " + player.getSum() + " zu " + dealer.getSum());
			            wannaDraw = false;
			        } else {
			            Thread.sleep(1000);
			            System.out.println("Das Spiel war unentschieden: " + dealer.getSum() + " zu " + player.getSum());
			            wannaDraw = false;
			        }    
	            }
	        }
	    }
    }

}                        