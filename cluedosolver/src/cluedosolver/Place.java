package cluedosolver;

public enum Place {
	FURDOSZOBA,
	DOLGOZO,
	EBEDLO,
	JATEKSZOBA,
	GARAZS,
	HALO,
	NAPPALI,
	KONYHA,
	UDVAR;
	
	public static String writeString = "1: F�rd�szoba \n" +
								"2: Dolgoz�szoba \n" +
								"3: Eb�dl� \n" +
								"4: J�t�kszoba \n" +
								"5: Gar�zs \n" +
								"6: H�l�szoba \n" +
								"7: Nappali \n" +
								"8: Konyha \n" +
								"9: Udvar \n";
	
	public static Place parseIntoPlace(int num){
		switch(num){
		case 1: return(Place.FURDOSZOBA);
		case 2: return(Place.DOLGOZO);
		case 3: return(Place.EBEDLO);
		case 4: return(Place.JATEKSZOBA);
		case 5: return(Place.GARAZS);
		case 6: return(Place.HALO);
		case 7: return(Place.NAPPALI);
		case 8: return(Place.KONYHA);
		case 9: return(Place.UDVAR);
		}
		return null;
	}
}
