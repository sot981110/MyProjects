package cluedosolver;

public enum Person {
	GREEN,
	MUSTARD,
	PEACOCK,
	PLUM,
	SCRALET,
	WHITE;
	
	public static String writeString = "1: Green(zöld) \n" +
			"2: Mustard(sárga) \n" +
			"3: Peacock(kék) \n" +
			"4: Plum(lila) \n" +
			"5: Scarlet(piros) \n" +
			"6: White(fehér) \n";
	
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
