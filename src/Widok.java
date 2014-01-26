//WIDOK to g¸owna klasa (aplet) - tworzenie plansz, wywo¸ywanie fukncji gry, mysz i grafika.

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Widok extends Applet implements MouseListener {
	Applet applet = this;
	
	//Sˆ 3 plansze (1 gracza, 2 komputera (widoczna i niewidoczna)), kaýda plansza ma tablice.
	Plansza planszaGracza;
	Plansza planszaKomputera;
	Plansza pustaPlanszaKomputera;
	String planszaGraczaTablica[][];
	String planszaKomputeraTablica[][];
	String pustaPlanszaKomputeraTablica[][];

	public void init() {

		planszaGracza = new Plansza(); // gracz ma 1 plansze 
		planszaGracza.wypelnijPlansze();
		planszaGraczaTablica = planszaGracza.tablica; // plansza ma tablice, kt—ra juz jest wype¸niona
		planszaKomputera = new Plansza(); // prawdziwa plansza komputera
		planszaKomputera.wypelnijPlansze();
		planszaKomputeraTablica = planszaKomputera.tablica;
		pustaPlanszaKomputera = new Plansza(); // plansza komputera, kt—rˆ widzi gracz (nie wypelniam jej, na poczˆtku jest pusta)
		pustaPlanszaKomputeraTablica = pustaPlanszaKomputera.tablica; //same "_ "

		// OBSERWATOR
		addMouseListener(this); // dodaj aplet do listy, kt—rˆ nasluchuje mysz

		applet.setSize(1250, 680);
		applet.repaint();
	}

	//wywo¸anie kolejki, to klikni«cie gracza
	public void mouseClicked(MouseEvent e) {
		//pobiera wsp—¸rz«dne klikni«cia
		int x = e.getX() - 650; // prawa plansza do klikania jest 650px od lewej
		int y = e.getY();

		//jeæli gracz kliknˆ¸ na planszy komputera
		if (x > 0 && x < 600 && y < 600) {
			Punkt strzalGracza = new Punkt();
			strzalGracza.nowyPunkt(y / 60, x / 60); //znalezienie odpowieniego pola na planszy (gdzie kliknˆ¸ gracz)
			boolean graczStrzelil = Gra.getInstance().grajGracz(pustaPlanszaKomputera,planszaKomputera, strzalGracza); //przekazanie miejsca klikni«cia do gry
			if (graczStrzelil) Gra.getInstance().grajKomputer(planszaGracza); //jesli gracz poprawnie strzelil, nast«puje ruch komputera
			applet.repaint(); // to pe¸nej turze przerysowanie apleta
		}
	}
	
	//wywo¸ywanie przez repaint - przyerysowanie ca¸ego apleta po turze ruch—w
	public void paint(Graphics g) {

		// ustawiam obrazki jako zmienne
		Image puste = getImage(getDocumentBase(), "obrazki/p.png");
		Image statek = getImage(getDocumentBase(), "obrazki/s.png");
		Image statektraf = getImage(getDocumentBase(), "obrazki/st.png");
		Image pustetraf = getImage(getDocumentBase(), "obrazki/pt.png");
		Image wygrales = getImage(getDocumentBase(), "obrazki/wygrales.jpg");
		Image przegrales = getImage(getDocumentBase(), "obrazki/przegrales.jpg");

		//Rysowanie licznik—w podczas gry (pobieram licznik z insntacji gry - wykorzystanie SINGLETON'a)
		g.drawString("Trafiono " + Gra.getInstance().licznikTrafienKomputera+ " z " + Gra.getInstance().licznikStrzalow + " strza¸—w ",200, 640);
		g.drawString("Trafiono " + Gra.getInstance().licznikTrafienGracza+ " z " + Gra.getInstance().licznikStrzalow + " strza¸—w ",900, 640);
		g.drawString("Zosta¸o "+ (20 - Gra.getInstance().licznikTrafienKomputera)+ " element—w do zestrzelenia. ", 150, 670);
		g.drawString("Zosta¸o " + (20 - Gra.getInstance().licznikTrafienGracza)+ " element—w do zestrzelenia. ", 850, 670);

		// rysowanie lewej planszy (gracza)
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				String a = planszaGraczaTablica[i][j].trim();
				String b = "4";
				String c = "1";
				String d = "2";
				String e = "3";
				String x = "x"; //trafiono puste pole
				String y = "y"; //trafiony statek

				// nietrafiony jeszcze statek
				if (a.equals(b) || a.equals(d) || a.equals(c) || a.equals(e)) {
					g.drawImage(statek, 60 * j, 60 * i, this);
					}
				
				//puste pole, w kt—re juz strzeli¸ komputer
				else if (a.equals(x)) {
					g.drawImage(pustetraf, 60 * j, 60 * i, this);
					}

				//statek, w kt—ry juý strzeli¸ kompuer
				else if (a.equals(y)) {
					g.drawImage(statektraf, 60 * j, 60 * i, this);
					}

				//niestrafione jeszcze puste pole
				else {
					g.drawImage(puste, 60 * j, 60 * i, this);
				}
			}
		}

		// rysowanie prawej planszy (plansza w kt—rˆ gracz strzela komputerowi)
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				String a = pustaPlanszaKomputeraTablica[i][j].trim();		
				String x = "x";//trafiono puste pole
				String y = "y";//trafiony statek

				//puste pole, w kt—re juz strzeli¸ gracz
				if (a.equals(x)) {
					g.drawImage(pustetraf, 650 + 60 * j, 60 * i, this);
				}

				//statek, w kt—ry juý strzeli¸ gracz
				else if (a.equals(y)) {
					g.drawImage(statektraf, 650 + 60 * j, 60 * i, this);
				}

				//niestrafione jeszcze puste pole
				else {
					g.drawImage(puste, 650 + 60 * j, 60 * i, this);
				}

			}
		}

		//komunikat zakoÄczenia gry
		if (Gra.getInstance().komputerWygral) g.drawImage(przegrales, 460, 200, this);
		if (Gra.getInstance().graczWygral) g.drawImage(wygrales, 460, 200, this);

	}

//Inne fukcje potrzebne do MouseListener
	@Override public void mouseEntered(MouseEvent arg0) {}
	@Override public void mouseExited(MouseEvent arg0) {}
	@Override public void mousePressed(MouseEvent arg0) {}
	@Override public void mouseReleased(MouseEvent arg0) {}
}
