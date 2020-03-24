package cluedosolver;

public class Question {
	private Person person;
	private Place place;
	private Weapon weapon;
	
	public Question(Person person, Place place, Weapon weapon){
		this.person = person;
		this.place = place;
		this.weapon = weapon;
	}
	
	public Person getPerson(){
		return this.person;
	}
	
	public Place getPlace(){
		return this.place;
	}
	
	public Weapon getWeapon(){
		return this.weapon;
	}
}
