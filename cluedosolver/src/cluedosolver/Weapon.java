package cluedosolver;

public enum Weapon {
	VILLASKULCS,
	GYERTYATARTO,
	TOR,
	PISZTOLY,
	VASCSO,
	KOTEL;
	
	public static String writeString = "1: Vill�skulcs \n" +
			"2: Gyertyatart� \n" +
			"3: T�r \n" +
			"4: Pisztoly \n" +
			"5: Vascs� \n" +
			"6: K�t�l \n";
	
	public static Weapon parseIntoWeapon(int num){
		switch(num){
		case 1: return(Weapon.VILLASKULCS);
		case 2: return(Weapon.GYERTYATARTO);
		case 3: return(Weapon.TOR);
		case 4: return(Weapon.PISZTOLY);
		case 5: return(Weapon.VASCSO);
		case 6: return(Weapon.KOTEL);
		}
		return null;
	}
}
