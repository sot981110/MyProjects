package cluedosolver;

public enum Card {
	GREEN,
	MUSTARD,
	PEACOCK,
	PLUM,
	SCRALET,
	WHITE,
	FURDOSZOBA,
	DOLGOZO,
	EBEDLO,
	JATEKSZOBA,
	GARAZS,
	HALO,
	NAPPALI,
	KONYHA,
	UDVAR,
	VILLASKULCS,
	GYERTYATARTO,
	TOR,
	PISZTOLY,
	VASCSO,
	KOTEL;
	
	public static Card parseIntoCard(Person person){
		switch(person){
		case GREEN: return(Card.GREEN);
		case MUSTARD: return(Card.MUSTARD);
		case PEACOCK: return(Card.PEACOCK);
		case PLUM: return(Card.PLUM);
		case SCRALET: return(Card.SCRALET);
		case WHITE: return(Card.WHITE);
		}
		return null;
	}
	
	public static Card parseIntoCard(Place place){
		switch(place){
		case DOLGOZO: return(Card.DOLGOZO);
		case EBEDLO: return(Card.EBEDLO);
		case FURDOSZOBA: return(Card.FURDOSZOBA);
		case GARAZS: return(Card.GARAZS);
		case HALO: return(Card.HALO);
		case JATEKSZOBA: return(Card.JATEKSZOBA);
		case KONYHA: return(Card.KONYHA);
		case NAPPALI: return(Card.NAPPALI);
		case UDVAR: return(Card.UDVAR);
		}
		return null;
	}
	
	public static Card parseIntoCard(Weapon weapon){
		switch(weapon){
		case GYERTYATARTO: return(Card.GYERTYATARTO);
		case KOTEL: return(Card.KOTEL);
		case PISZTOLY: return(Card.PISZTOLY);
		case TOR: return(Card.TOR);
		case VASCSO: return(Card.VASCSO);
		case VILLASKULCS: return(Card.VILLASKULCS);
		}
		return null;
	}
	
	public String toString(){
		switch(this){
		case DOLGOZO: return("Dolgozószoba");
		case EBEDLO: return("Ebédlõ");
		case FURDOSZOBA: return("Fürdõszoba");
		case GARAZS: return("Garázs");
		case GREEN: return("Green");
		case GYERTYATARTO: return("Gyertyatartó");
		case HALO: return("Hálószoba");
		case JATEKSZOBA: return("Játékszoba");
		case KONYHA: return("Konyha");
		case KOTEL: return("Kötél");
		case MUSTARD: return("Mustard");
		case NAPPALI: return("Nappali");
		case PEACOCK: return("Peacock");
		case PISZTOLY: return("Pisztoly");
		case PLUM: return("Plum");
		case SCRALET: return("Scarlet");
		case TOR: return("Tõr");
		case UDVAR: return("Udvar");
		case VASCSO: return("Vascsõ");
		case VILLASKULCS: return("Villáskulcs");
		case WHITE: return("White");
		}
		return("");
	}
	
	public boolean isPerson(){
		switch(this){
		case GREEN:
		case MUSTARD:
		case PEACOCK:
		case PLUM:
		case SCRALET:
		case WHITE: return(true);
		default: return(false);
		}
	}
	
	public boolean isPlace(){
		switch(this){
		case DOLGOZO:
		case EBEDLO:
		case FURDOSZOBA:
		case GARAZS:
		case HALO:
		case JATEKSZOBA:
		case KONYHA:
		case NAPPALI:
		case UDVAR: return(true);
		default: return(false);
		}
		
	}
	
	public boolean isWeapon(){
		switch(this){
		case GYERTYATARTO:
		case KOTEL:
		case PISZTOLY:
		case TOR:
		case VASCSO:
		case VILLASKULCS: return(true);
		default:return(false);
		}
	}
}
