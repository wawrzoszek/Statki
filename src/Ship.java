//SHIP - dodawanie nwoego statku (losowanie i ustawianie w poprawnym miejscu)

import java.util.Random;

public class Ship {
	
	public int[] losujMiejsce() 
	{
		int[] os = new int[2];
		Random r = new Random();
		os[0] = r.nextInt(8) + 1;
		os[1] = r.nextInt(8) + 1;
		return os;
	}

	// dodanie statku na plansze p
	public void wpiszStatek(Plansza p, int rozmiarStatku) {
		String[][] pk = p.tablica; //pk to tablica planszy
		int os[] = losujMiejsce();
		Random r = new Random();
		int kierunek = r.nextInt(4); //kierunek w kt�rym b�dzie rysowany statek 
		boolean poprawny_kierunek = true;

		// czy wylosowno punkt na kraw�dzi
		if (os[0] == 0 || os[0] == p.rozmiarPlanszy - 1 || os[1] == 0 || os[1] == p.rozmiarPlanszy - 1) {
			poprawny_kierunek = false;
		}

		// czy nast�pny element statku nie wyjdzie za plansze, je�li wyjdzie zaczyna od pocz�tku
		if (kierunek == 0) { 
			if (os[0] - rozmiarStatku + 1 < 0) {
				poprawny_kierunek = false;
				wpiszStatek(p, rozmiarStatku);
			}
		}

		if (kierunek == 1) { 
			if (os[0] + rozmiarStatku - 1 > p.rozmiarPlanszy - 1) {
				poprawny_kierunek = false;
				wpiszStatek(p, rozmiarStatku);
			}
		}
		
		if (kierunek == 2) { 
			if (os[1] - rozmiarStatku + 1 < 0) {
				poprawny_kierunek = false;
				wpiszStatek(p, rozmiarStatku);
			}
		}

		if (kierunek == 3) {
			if (os[1] + rozmiarStatku - 1 > p.rozmiarPlanszy - 1) {
				poprawny_kierunek = false;
				wpiszStatek(p, rozmiarStatku);
			}
		}

		// okre�lanie wsp��rz�dnych wszystkich element�w statku
		int[] elementy_statku_x = new int[rozmiarStatku]; //tablica, kt�ra zapisuje wsp��rz�dne x aktualnego statku
		int[] elementy_statku_y = new int[rozmiarStatku]; //tablica, kt�ra zapisuje wsp��rz�dne y aktualnego statku

		if (poprawny_kierunek == true) {

			//zapisanie pierwszego elementu
			elementy_statku_y[0] = os[0];
			elementy_statku_x[0] = os[1];
			
			//zapisanie kolejnych element�w
			for (int i = 1; i < rozmiarStatku; i++) {
				if (kierunek == 0) {
					os[0] = os[0] - 1;
				}
				if (kierunek == 1) {
					os[0] = os[0] + 1;
				}
				if (kierunek == 2) {
					os[1] = os[1] - 1;
				}
				if (kierunek == 3) {
					os[1] = os[1] + 1;
				}

				elementy_statku_y[i] = os[0];
				elementy_statku_x[i] = os[1];
			}

			// czy nie nachodzi na inny statek
			boolean wolneMiejsceNaStatek = true;

			
			for (int j = 0; j < rozmiarStatku; j++) {

				try {
					// jesli na kt�rym� z miejsc nowego statku na planszy jest juz statek wr�� do pocz�tku
					if (pk[elementy_statku_x[j]][elementy_statku_y[j]] != "_ ") {
						wolneMiejsceNaStatek = false;
						wpiszStatek(p, rozmiarStatku);
						return;
					}
					
					// jesli na kt�rym� z miejsc obok nowego statku na planszy jest juz statek wr�� do pocz�tku
					if (pk[elementy_statku_x[j] - 1][elementy_statku_y[j]] != "_ " && elementy_statku_x[j] != 0) {
						wolneMiejsceNaStatek = false;
						wpiszStatek(p, rozmiarStatku);
						return;
					}

					if (pk[elementy_statku_x[j] + 1][elementy_statku_y[j]] != "_ "&& elementy_statku_x[j] != 9) {
						wolneMiejsceNaStatek = false;
						wpiszStatek(p, rozmiarStatku);
						return;
					}

					if (pk[elementy_statku_x[j]][elementy_statku_y[j] + 1] != "_ "&& elementy_statku_x[j] != 9) {
						wolneMiejsceNaStatek = false;
						wpiszStatek(p, rozmiarStatku);
						return;
					}

					if (pk[elementy_statku_x[j]][elementy_statku_y[j] - 1] != "_ "&& elementy_statku_x[j] != 0) {
						wolneMiejsceNaStatek = false;
						wpiszStatek(p, rozmiarStatku);
						return;
					}
				} 
				catch (Exception e) {}
			}

			// je�li jest wolne miesce to zapisz w ka�dym punkcie nowego statku jego cyfr�
			if (wolneMiejsceNaStatek == true) {
				for (int j = 0; j < rozmiarStatku; j++) {
					try {
						pk[elementy_statku_x[j]][elementy_statku_y[j]] = Integer.toString(rozmiarStatku) + " ";
					} 
					catch (Exception e) {}
				}
			}
		}
	}
}