package cluedosolver;

public enum Weapon {
	VILLASKULCS,
	GYERTYATARTO,
	TOR,
	PISZTOLY,
	VASCSO,
	KOTEL;
	
	public static String writeString = "1: Villáskulcs \n" +
			"2: Gyertyatartó \n" +
			"3: Tõr \n" +
			"4: Pisztoly \n" +
			"5: Vascsõ \n" +
			"6: Kötél \n";
	
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
