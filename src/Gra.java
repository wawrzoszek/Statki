import java.util.Random;

public class Gra {

	int licznikTrafienGracza = 0;
	int licznikTrafienKomputera = 0;
	boolean graczWygral;
	boolean komputerWygral;
	
	
	
	private static Gra instance = null;
		  
		   public static Gra getInstance() {
		      if(instance == null) {
		         instance = new Gra();
		      }
		      return instance;
		   }
		
	
	private Gra() {

	}

	public void grajKomputer(Plansza plansza) {

		
		String b = "4";
		String c = "1";
		String d = "2";
		String e = "3";
		String x = "x";
		String y = "y";
		
		Punkt punkt = new Punkt();
		String a = plansza.tablica[punkt.x][punkt.y].trim();
		
		if (plansza.tablica[punkt.x][punkt.y] == "_ ") {
			plansza.tablica[punkt.x][punkt.y] = "x";
		}

		if (a.equals(b) || a.equals(d) || a.equals(c) || a.equals(e)) {
			plansza.tablica[punkt.x][punkt.y] = "y";
			licznikTrafienKomputera++;
		}

		//jesli to miejsce juz bylo wylosowane, losuj jeszcze raz (rekurencja)
		if (a.equals(x) || a.equals(y)) {
			grajKomputer(plansza);
		}
		
		if(licznikTrafienKomputera == 14)
		{
			komputerWygral = true;
		}
	}
	
	
public boolean grajGracz(Plansza planszaPusta, Plansza planszaPelna, Punkt punkt) {

		
		String b = "4";
		String c = "1";
		String d = "2";
		String e = "3";
		String x = "x";
		String y = "y";
		
		//sprawdzam pelnˆ , wpsiuje na pustˆ
		String a = planszaPelna.tablica[punkt.x][punkt.y].trim();
		
		if (planszaPelna.tablica[punkt.x][punkt.y] == "_ ") {
			planszaPusta.tablica[punkt.x][punkt.y] = "x";
		}

		if (a.equals(b) || a.equals(d) || a.equals(c) || a.equals(e)) {
			planszaPusta.tablica[punkt.x][punkt.y] = "y";
			licznikTrafienGracza++;
		}

		//jesli to miejsce juz bylo wylosowane, losuj jeszcze raz (rekurencja)
		
		if(licznikTrafienGracza == 14)
		{
			graczWygral=true;
		}
		
		return true;
	}

}
