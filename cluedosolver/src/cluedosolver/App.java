package cluedosolver;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

	public static int playerHandSize = 0;
	private static Player[] players;
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("�dv�z�llek a cluedo solverben! \n H�ny j�t�kos j�tszik? \n");
		int playerNumber = safeInput(1, 18, input);
		System.out.println("J�t�k elind�t�sa " + playerNumber + " sz�m� j�t�kossal...");
		players = new Player[playerNumber];
		String name;
		playerHandSize = (int) 18/playerNumber;
		for (int i = 0; i < playerNumber; i++) {
			System.out.println("J�t�kos " + (i+1) + " neve: ");
			name = input.next();
			players[i] = new Player(name);
		}
		int askedPlayer;
		int startedOnPlayer;
		Person askedPerson;
		Place askedPlace;
		Weapon askedWeapon;
		Question askedQuestion;
		boolean answered;
		int turnCounter = 1;
		int choice = -1;
		int plusCards = 18%playerNumber;
		int cardType;
		
		while(plusCards != 0){
			System.out.println("M�g van " + plusCards + " db kiteend� lap.");
			System.out.println("Milyen k�rtya? 1:Ember 2:Helys�g 3:T�rgy");
			cardType = safeInput(1, 3, input);
			switch(cardType){
			case 1: System.out.println("Melyik ember? " + Person.writeString);
					App.reactToReveal(Card.parseIntoCard(Person.parseIntoPerson(safeInput(1, 6, input))));
					break;
			case 2: System.out.println("Melyik helys�g? " + Place.writeString);
					App.reactToReveal(Card.parseIntoCard(Place.parseIntoPlace(safeInput(1, 9, input))));
					break;
			case 3: System.out.println("Melyik t�rgy? " + Weapon.writeString);
					App.reactToReveal(Card.parseIntoCard(Weapon.parseIntoWeapon(safeInput(1, 6, input))));
					break;
			}
			plusCards--;
		}
		
		System.out.println("J�t�k elind�that� " + playerNumber + " db j�t�kossal. \n Hanyas j�t�kos kezd? \n " + App.getPlayerNames());
		int currentPlayer = safeInput(1, playerNumber, input);
		int startingPlayer = currentPlayer;
		
		
		
		while(true){
			System.out.println(turnCounter + ". k�r: " + "Mit csin�l " + players[currentPlayer-1].getName() + " (J�t�kos " + currentPlayer + ") ? \n");
			System.out.println("1: Feltesz egy k�rd�st \n");
			System.out.println("2: Valakit k�ztet k�rtya felmutat�sra \n");
			System.out.println("3: Befejezi a k�r�t \n");
			System.out.println("0: J�t�k begfejez�se \n");
			choice = safeInput(0, 3, input);
			if(choice == 0){
				System.out.println("J�t�k le�ll�tva.");
				printCards(playerNumber);
				input.close();
				return;
			}
			else if(choice == 1){
				System.out.println("Kit�l k�rdez el�sz�r? \n " + App.getPlayerNames());
				askedPlayer = safeInput(1, playerNumber, input);
				startedOnPlayer = askedPlayer;
				System.out.println("Melyik emberr�l k�rdez? \n" + Person.writeString);
				askedPerson = Person.parseIntoPerson(safeInput(1, 6, input));
				System.out.println("Melyik helys�gr�l k�rdez? \n" + Place.writeString);
				askedPlace = Place.parseIntoPlace(safeInput(1, 9, input));
				System.out.println("Melyik t�rgyr�l k�rdez? \n" + Weapon.writeString);
				askedWeapon = Weapon.parseIntoWeapon(safeInput(1, 6, input));
				askedQuestion = new Question(askedPerson,askedPlace,askedWeapon);
				answered = false;
				do{
					if(askedPlayer != currentPlayer && askedPlayer != 0){
						System.out.println(players[askedPlayer-1].getName() + " (J�t�kos " + askedPlayer + ") v�laszolt? 0/1");
						answered = (Integer.parseInt(input.next()) == 1);
						players[askedPlayer-1].askQuestion(askedQuestion, answered);
					}
					
					askedPlayer=(askedPlayer+1)%(playerNumber+1);
				}while(!answered && startedOnPlayer != askedPlayer);
			}
			else if(choice == 2){
				System.out.println("Melyik j�t�kost k�zteti k�rtya felmutat�s�ra? \n" + App.getPlayerNames());
				askedPlayer = safeInput(1, playerNumber, input);
				System.out.println("Milyen k�rtya? 1:Ember 2:Helys�g 3:T�rgy");
				cardType = safeInput(1, 3, input);
				int answer;
				switch(cardType){
				case 1: System.out.println("Melyik ember? " + Person.writeString);
						answer = safeInput(1, 6, input);
						App.reactToReveal(Card.parseIntoCard(Person.parseIntoPerson(answer)));
						players[askedPlayer-1].revealCard(Card.parseIntoCard(Person.parseIntoPerson(answer)));
						break;
				case 2: System.out.println("Melyik helys�g? " + Place.writeString);
						answer = safeInput(1, 9, input);
						App.reactToReveal(Card.parseIntoCard(Place.parseIntoPlace(answer)));
						players[askedPlayer-1].revealCard(Card.parseIntoCard(Place.parseIntoPlace(answer)));
						break;
				case 3: System.out.println("Melyik t�rgy? " + Weapon.writeString);
						answer = safeInput(1, 6, input);
						App.reactToReveal(Card.parseIntoCard(Weapon.parseIntoWeapon(answer)));
						players[askedPlayer-1].revealCard(Card.parseIntoCard(Weapon.parseIntoWeapon(answer)));
						break;
				}
			}
			else if(choice == 3){
				currentPlayer = (currentPlayer+1)%(playerNumber+1);
				if(currentPlayer == 0){
					currentPlayer++;
				}
				if(currentPlayer == startingPlayer){
					turnCounter++;
				}
			}
			
			revealPlayers();
			
			printCards(playerNumber);
		}

		
	}
	
	public static String getPlayerNames(){
		String returnString = "";
		for (int i = 0; i < players.length; i++) {
			returnString += (i + 1) + ": " + players[i].getName() + " \n";
		}
		return(returnString);
	}
	
	public static void reactToReveal(Card card){
		if(card != null){
			for (Player player : players) {
				player.reactToReveal(card);
			}
		}
	}
	
	public static void revealPlayers(){
		for (Player player : players) {
			for (Card card : player.getCards()) {
				App.reactToReveal(card);
				player.revealCard(card);
			}
		}
		for (Player player : players) {
			player.checkAllQuestions();
			player.checkCards();
		}
	}
	
	public static int safeInput(int min, int max, Scanner input){
		int value = Integer.parseInt(input.next());
		while(value < min || max < value){
			System.out.println("Nem j� �rt�k. \n");
			System.out.print("Lehets�ges �rt�kek: ");
			for (int i = min; i < max+1; i++) {
				System.out.print(i + " ");
			}
			System.out.println();
			value = Integer.parseInt(input.next());
		}
		return value;
	}
	
	public static void printCards(int playerNumber){
		int possiblePeople = 6;
		int possiblePlaces = 9;
		int possibleWeapons = 6;
		
		int playerCount = 0;
		for (Player player : players) {
			System.out.println(player.getName() + " k�rty�i: ");
			for (Card card : player.getCards()) {
				if(card.isPerson()){
					System.out.println(card.toString() + " ");
					possiblePeople--;
				}
			}
			for (Card card : player.getCards()) {
				if(card.isPlace()){
					System.out.println(card.toString() + " ");
					possiblePlaces--;
				}
			}
			for (Card card : player.getCards()) {
				if(card.isWeapon()){
					System.out.println(card.toString() + " ");
					possibleWeapons--;
				}
			}
			if(player.getCards().size() == playerHandSize){
				playerCount++;
			}
		}
		
		System.out.println("Eltal�l�si val�szin�s�g: " + ((1/(possiblePeople*possiblePlaces*possibleWeapons))*100) + " % \n");
		
		if(playerCount == playerNumber){
			ArrayList<Card> remainingCards = new ArrayList<Card>();
			for (Card card : Card.values()) {
				remainingCards.add(card);
			}
			for (Player player : players) {
				for (Card card : player.getCards()) {
					remainingCards.remove(card);
				}
			}
			System.out.println("Fenmarad� k�rty�k: \n");
			for (Card card : remainingCards) {
				if(card.isPerson()){
					System.out.println("A gyilkos: " + card.toString());
				}
			}
			for (Card card : remainingCards) {
				if(card.isPlace()){
					System.out.println("A helyszin: " + card.toString());
				}
			}
			for (Card card : remainingCards) {
				if(card.isPerson()){
					System.out.println("A fegyver: " + card.toString());
				}
			}
		}
	}

}
