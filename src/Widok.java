import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class Widok extends Applet implements MouseListener, MouseMotionListener
{
	Applet applet = this;
	Plansza planszaGracza;
	Plansza planszaKomputera;
	Plansza komputerGra;
	String planszaGraczaTablica[][]; 
	String planszaKomputeraTablica[][]; 
	String pustaPlanszaKomputeraTablica[][]; 
	
	
	
	
	public void init() 
	{
		
		
		planszaGracza = new Plansza(); // gracz ma 1 plansze
		planszaGracza.wypelnijPlansze();
		planszaGraczaTablica = planszaGracza.tablica; //plansza ma tablic«
		planszaKomputera = new Plansza(); //prawdziwa plansza komputera
		planszaKomputera.wypelnijPlansze();
		planszaKomputeraTablica = planszaKomputera.tablica;
		komputerGra = new Plansza(); //plansza komputera, kt—rˆ widzi gracz (nie wypelniam jej, na poczˆtku jest pusta)
		pustaPlanszaKomputeraTablica=komputerGra.tablica;
		
		
		
		addMouseListener(this);
        addMouseMotionListener(this);
        
		applet.setSize(1620, 800);
		applet.repaint();	
		
		
		
		
		applet.repaint();	
	}

	public void paint(Graphics g) {

		//ustawiam obrazki jako zmienne
		Image puste = getImage(getDocumentBase(), "obrazki/p.png");
		Image statek = getImage(getDocumentBase(), "obrazki/s.png");
		Image statektraf = getImage(getDocumentBase(), "obrazki/st.png");
		Image pustetraf = getImage(getDocumentBase(), "obrazki/pt.png");
		Image wygrales = getImage(getDocumentBase(), "obrazki/wygrales.jpg");
		Image przegrales = getImage(getDocumentBase(), "obrazki/przegrales.jpg");

		
		//rysowanie lewej planszy (gracza)
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				String a = planszaGraczaTablica[i][j].trim();
				String b = "4";
				String c = "1";
				String d = "2";
				String e = "3";
				String x = "x";
				String y = "y";
				
				
				//jeæli na danym miejscu jest statek narysuj go, winnym wypadku pusty kwadracik
				if (a.equals(b) || a.equals(d) || a.equals(c) || a.equals(e)) 
				{
					g.drawImage(statek, 80 * j, 80 * i, this);
				} 
				
				else if (a.equals(x))
				{
					g.drawImage(pustetraf, 80*j, 80*i,this);
				}
				
				else if (a.equals(y))
				{
					g.drawImage(statektraf, 80*j, 80*i,this);
				}
				
				else
				{
					g.drawImage(puste, 80 * j, 80 * i, this);
				}
			
				
			}
			
		}

		//rysowanie prawej planszy (pusta plansza kompuera)
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				String a = pustaPlanszaKomputeraTablica[i][j].trim();
				String b = "4";
				String c = "1";
				String d = "2";
				String e = "3";
				String x = "x";
				String y = "y";
				
				
				//jeæli na danym miejscu jest statek narysuj go, winnym wypadku pusty kwadracik
				if (a.equals(b) || a.equals(d) || a.equals(c) || a.equals(e)) 
				{
					g.drawImage(statek, 820+80 * j, 80 * i, this);
				} 
				
				else if (a.equals(x))
				{
					g.drawImage(pustetraf, 820+80*j, 80*i,this);
				}
				
				else if (a.equals(y))
				{
					g.drawImage(statektraf, 820+80*j, 80*i,this);
				}
				
				else
				{
					g.drawImage(puste, 820+80 * j, 80 * i, this);
				}
				
			}
		}

		if(Gra.getInstance().komputerWygral) g.drawImage(przegrales, 660, 300, this);
		if(Gra.getInstance().graczWygral) g.drawImage(wygrales, 660, 300, this);
		 
		
		
	}
	
	public void mouseClicked(MouseEvent e) {
		int x = e.getX()-820; //prawa plansza do klikania jest 820px od lewej
        int y = e.getY();
		
        if(x>0 && x<800 && y<800)
        {
        	Punkt strzalGracza = new Punkt().nowyPunkt(y/80, x/80);
        	System.out.println("X:"+x+"Modulo"+x/80+"PunktX: "+strzalGracza.x);
        	Gra.getInstance().grajGracz(komputerGra, planszaKomputera, strzalGracza);
        	
        }
		applet.repaint();
		
        System.out.println("mouseClicked");
        Gra.getInstance().grajKomputer(planszaGracza);
        
		applet.repaint();	
    }

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

