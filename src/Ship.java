import java.util.Random;

public class Ship {
	public int[] losujMiejsce() {
		int[] os = new int[2];
		Random r = new Random();
		os[0] = r.nextInt(8) + 1;
		os[1] = r.nextInt(8) + 1;
		return os;
	}

	// dodanie statku
	public void wpiszStatek(Plansza p, int rozmiarStatku) {
		String[][] pk = p.tablica;
		int os[] = losujMiejsce();
		Random r = new Random();
		int kierunek = r.nextInt(4);
		boolean poprawny_kierunek = true;

		// czy wylosowno punkt na kraw«dzi
		if (os[0] == 0 || os[0] == p.rozmiarPlanszy - 1 || os[1] == 0
				|| os[1] == p.rozmiarPlanszy - 1) {
			poprawny_kierunek = false;
		}

		// czy nie wyjdzie za plansze, jeæli tak zaczyna od poczˆtku
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

		// okreælanie wsp—¸rz«dnych wszystkich element—w statku
		int[] elementy_statku_x = new int[rozmiarStatku];
		int[] elementy_statku_y = new int[rozmiarStatku];

		if (poprawny_kierunek == true) {

			elementy_statku_y[0] = os[0];
			elementy_statku_x[0] = os[1];
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

			// jesli na kt—rymæ z miejsc nowego statku jest juz statek wr— do
			// poczˆtku
			System.out.println("rozmiar statku: " + rozmiarStatku);
			for (int j = 0; j < rozmiarStatku; j++) {

				try {
					if (pk[elementy_statku_x[j]][elementy_statku_y[j]] != "_ ") {

						wolneMiejsceNaStatek = false;
						wpiszStatek(p, rozmiarStatku);
						return;
					}

					System.out
							.println("x"
									+ elementy_statku_x[j]
									+ " y"
									+ elementy_statku_y[j]
									+ " pk: "
									+ pk[elementy_statku_x[j] - 1][elementy_statku_y[j]]);

					if (pk[elementy_statku_x[j] - 1][elementy_statku_y[j]] != "_ "
							&& elementy_statku_x[j] != 0) {

						wolneMiejsceNaStatek = false;
						wpiszStatek(p, rozmiarStatku);
						return;
					}

					if (pk[elementy_statku_x[j] + 1][elementy_statku_y[j]] != "_ "
							&& elementy_statku_x[j] != 9) {

						wolneMiejsceNaStatek = false;
						wpiszStatek(p, rozmiarStatku);
						return;
					}

					if (pk[elementy_statku_x[j]][elementy_statku_y[j] + 1] != "_ "
							&& elementy_statku_x[j] != 9) {

						wolneMiejsceNaStatek = false;
						wpiszStatek(p, rozmiarStatku);
						return;
					}

					if (pk[elementy_statku_x[j]][elementy_statku_y[j] - 1] != "_ "
							&& elementy_statku_x[j] != 0) {

						wolneMiejsceNaStatek = false;
						wpiszStatek(p, rozmiarStatku);
						return;
					}

				} catch (Exception e) {
					System.out.println(e.toString());
				}
			}

			// jeæli jest wolne miesce to zapisz w kaýdym punkcie nowego statku
			// jego cyfr«
			if (wolneMiejsceNaStatek == true) {
				for (int j = 0; j < rozmiarStatku; j++) {
					try {
						pk[elementy_statku_x[j]][elementy_statku_y[j]] = Integer
								.toString(rozmiarStatku) + " ";
					} catch (Exception e) {
						System.out.println(e.toString());
					}
				}
			}
		}
	}
}
