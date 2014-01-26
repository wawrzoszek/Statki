//Nadaje wsp—¸rz«dnego wylosowanemu/klikni«temu punktowi na planszy

import java.util.Random;

public class Punkt {

	int x;
	int y;

	// losowanie miejsca strzalu
	public Punkt() {
		Random r = new Random();
		x = r.nextInt(10);
		y = r.nextInt(10);
	}

	public Punkt nowyPunkt(int pozycjaX, int pozycjaY) {
		x = pozycjaX;
		y = pozycjaY;
		return this;
	}
}
