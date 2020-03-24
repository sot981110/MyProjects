package cluedosolver;

public enum Person {
	GREEN,
	MUSTARD,
	PEACOCK,
	PLUM,
	SCRALET,
	WHITE;
	
	public static String writeString = "1: Green(z�ld) \n" +
			"2: Mustard(s�rga) \n" +
			"3: Peacock(k�k) \n" +
			"4: Plum(lila) \n" +
			"5: Scarlet(piros) \n" +
			"6: White(feh�r) \n";
	
	public static Person parseIntoPerson(int num){
		switch(num){
		case 1: return(Person.GREEN);
		case 2: return(Person.MUSTARD);
		case 3: return(Person.PEACOCK);
		case 4: return(Person.PLUM);
		case 5: return(Person.SCRALET);
		case 6: return(Person.WHITE);
		}
		return null;
	}
}
