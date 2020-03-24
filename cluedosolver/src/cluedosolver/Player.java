package cluedosolver;

import java.util.ArrayList;

public class Player {
	
	private String name;
	private ArrayList<Card> impossibleCards;
	private ArrayList<Card> cards;
	private ArrayList<Question> answeredQuestions;
	
	public Player(String name){
		this.name = name;
		this.impossibleCards = new ArrayList<Card>();
		this.cards = new ArrayList<Card>();
		this.answeredQuestions = new ArrayList<Question>();
	}
	
	public void askQuestion(Question question, boolean answered){
		if(question.getPerson() == null || question.getPlace() == null || question.getWeapon() == null){
			return;
		}
		
		if(!answered){
			impossibleCards.add(Card.parseIntoCard(question.getPerson()));
			impossibleCards.add(Card.parseIntoCard(question.getPlace()));
			impossibleCards.add(Card.parseIntoCard(question.getWeapon()));
		}
		else if(!cards.contains(Card.parseIntoCard(question.getPerson())) && !cards.contains(Card.parseIntoCard(question.getPlace())) && !cards.contains(Card.parseIntoCard(question.getWeapon()))){
			answeredQuestions.add(question);
		}
		checkAllQuestions();
		checkCards();
	}
	
	public void checkQuestion(Question question){
		if(cards.contains(Card.parseIntoCard(question.getPerson())) || cards.contains(Card.parseIntoCard(question.getPlace())) || cards.contains(Card.parseIntoCard(question.getWeapon()))){
			answeredQuestions.remove(question);
			return;
		}
		
		if(!impossibleCards.contains(Card.parseIntoCard(question.getPerson())) && impossibleCards.contains(Card.parseIntoCard(question.getPlace())) && impossibleCards.contains(Card.parseIntoCard(question.getWeapon()))){
			cards.add(Card.parseIntoCard(question.getPerson()));
		}
		else if(impossibleCards.contains(Card.parseIntoCard(question.getPerson())) && !impossibleCards.contains(Card.parseIntoCard(question.getPlace())) && impossibleCards.contains(Card.parseIntoCard(question.getWeapon()))){
			cards.add(Card.parseIntoCard(question.getPlace()));
		}
		else if(impossibleCards.contains(Card.parseIntoCard(question.getPerson())) && impossibleCards.contains(Card.parseIntoCard(question.getPlace())) && !impossibleCards.contains(Card.parseIntoCard(question.getWeapon()))){
			cards.add(Card.parseIntoCard(question.getWeapon()));
		}
	}
	
	public void checkAllQuestions(){
		for (int i = 0; i < answeredQuestions.size(); i++) {
			checkQuestion(answeredQuestions.get(i));
		}
	}
	
	public void checkCards(){
		ArrayList<Card> possibleCards = new ArrayList<Card>();
		for (Card card : Card.values()) {
			possibleCards.add(card);
		}
		for (Card card : impossibleCards) {
			possibleCards.remove(card);
		}
		if(possibleCards.size() == App.playerHandSize){
			cards = possibleCards;
		}
	}
	
	public ArrayList<Card> getCards(){
		return cards;
	}
	
	public void reactToReveal(Card card){
		if(card != null){
			if(!impossibleCards.contains(card)){
				impossibleCards.add(card);
			}
		}
		
	}
	
	public void revealCard(Card card){
		if(card != null){
			impossibleCards.remove(card);
			if(!cards.contains(card)){
				cards.add(card);
				System.out.println("revealed");
			}
		}
	}
	
	public String getName(){
		return name;
	}
	
}
