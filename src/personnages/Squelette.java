package personnages;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Squelette extends Ennemi {
	
	

	
	public Squelette(int diff) throws SlickException
	{
		this.atk=10;
		this.def=6;
		this.lvl=diff;
		this.pvMax=25;
		this.pv=25;
		this.loot=1;
		this.nom="Squellete";
		this.profil=1;
		this.setText("src/personnages/sprites/squellete.png");
	}
	
	public static int getLoot()
	{
		return 1;
	}

}
