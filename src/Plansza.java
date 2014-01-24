public class Plansza {

	String tablica[][];
	int rozmiarPlanszy;
	
	//tworz« kwadratowˆ plansz«
	public Plansza() {
		super();
		rozmiarPlanszy = 10;
		tablica = new String[rozmiarPlanszy][rozmiarPlanszy];
		stworzPlansze();
	}
	
	// na poczˆtku wype¸niam kaýdˆ plansze pustymi polami
	public void stworzPlansze() 
	{
		for (int k = 0; k < this.rozmiarPlanszy; k++) 
		{
			for (int l = 0; l < this.rozmiarPlanszy; l++) 
			{
				this.tablica[k][l] = "_ ";
			}
		}
	}
	
	//dadaje statki 
	public void wypelnijPlansze()
	{
		Ship statki = new Ship();
		statki.wpiszStatek(this, 4);
		statki.wpiszStatek(this, 3);
		statki.wpiszStatek(this, 2);
		statki.wpiszStatek(this, 2);
		statki.wpiszStatek(this, 1);
		statki.wpiszStatek(this, 1);
		statki.wpiszStatek(this, 1);
		wypiszPlansze();
		
		
		
	}
	
	
	//wypisuje gotowˆ plansze z uwzgl«dnionymi statkami
	public void wypiszPlansze() 
	{
		for (int k = 0; k < this.rozmiarPlanszy; k++) 
		{
			for (int l = 0; l < this.rozmiarPlanszy; l++) 
			{
				System.out.print(this.tablica[k][l]); 
			}
			System.out.println();
		}
		System.out.println("=======================");
	}

}
