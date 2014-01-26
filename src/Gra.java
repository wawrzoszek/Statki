//Gra to singleton. W klasie wykonywane s� zmiany na planszy po ruchu w trakcie gry.

public class Gra {

	int licznikTrafienGracza = 0;
	int licznikTrafienKomputera = 0;
	int licznikStrzalow = 0;
	boolean graczWygral;
	boolean komputerWygral;

	//SINGLETON - tworzenie.
	private static Gra instance = null; // Gra jest prywatna, wi�c nie mo�na stworzy� instancji klasy prze newGra();

	//tworzenie instancji tylko raz, je�li ju� jest zwraca j�
	public static Gra getInstance() {
		if (instance == null) {
			instance = new Gra();
		}
		return instance;
	}
	private Gra() {} //prywatny konstruktor

	
	public void grajKomputer(Plansza plansza) {

		licznikStrzalow++;
		String b = "4";
		String c = "1";
		String d = "2";
		String e = "3";
		String x = "x";//trafiono puste pole
		String y = "y";//trafiono statek

		Punkt punkt = new Punkt();
		String naPlanszyGracza = plansza.tablica[punkt.x][punkt.y].trim(); //wylosowany punkt

		//jesli by�o puste zmie� na x
		if (plansza.tablica[punkt.x][punkt.y] == "_ ") {
			plansza.tablica[punkt.x][punkt.y] = "x";
		}

		//je�li by�o ze statkiem zmie� na y
		if (naPlanszyGracza.equals(b) || naPlanszyGracza.equals(d) || naPlanszyGracza.equals(c) || naPlanszyGracza.equals(e)) {
			plansza.tablica[punkt.x][punkt.y] = "y";
			licznikTrafienKomputera++;
		}

		// jesli to miejsce juz bylo wylosowane, losuj jeszcze raz (rekurencja)
		if (naPlanszyGracza.equals(x) || naPlanszyGracza.equals(y)) {
			grajKomputer(plansza);
		}

		if (licznikTrafienKomputera == 20) {
			komputerWygral = true;
		}
	}

	public boolean grajGracz(Plansza planszaPusta, Plansza planszaPelna, Punkt punkt) {

		String b = "4";
		String c = "1";
		String d = "2";
		String e = "3";
		String x = "x";//trafiono puste pole
		String y = "y";//trafiono statek

		// sprawdzam peln� plansz�, wpsiuje na pust�
		String naPelnejPlanszy = planszaPelna.tablica[punkt.x][punkt.y].trim();//co jest na pe�nej planszy komputera w miejscu trafienia
		String naPustejPlanszy = planszaPusta.tablica[punkt.x][punkt.y].trim(); //co jest na pustej planszy w miejscu trafienia
		
		//jesli gracz jeszcze nie klikn�� w to pole
		if(!naPustejPlanszy.equals(y) && !naPustejPlanszy.equals(x))
		{
			//jesli na pe�nej nic nie by�o to daj na pust� x
			if (planszaPelna.tablica[punkt.x][punkt.y] == "_ ") {
				planszaPusta.tablica[punkt.x][punkt.y] = "x";
			}

			//jesli na pe�nej by� statek to daj na pust� y 
			if (naPelnejPlanszy.equals(b) || naPelnejPlanszy.equals(d) || naPelnejPlanszy.equals(c) || naPelnejPlanszy.equals(e)) {
				planszaPusta.tablica[punkt.x][punkt.y] = "y";
				licznikTrafienGracza++;
			}
			
			if (licznikTrafienGracza == 20) {
				graczWygral = true;
			}
			return true;
		}
		return false;
	}
}