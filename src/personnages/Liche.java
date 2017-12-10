package personnages;

import java.util.ArrayList;

import competences.Competence;
import competences.Eclair_givre;

public class Liche extends Ennemi {
	
	


	
	public Liche(int diff)
	{
		this.atk=3;
		this.def=3;
		this.lvl=diff;
		this.pvMax=25;
		this.pv=25;
		this.loot=1;
		this.nom="Liche";
		this.profil=1;
		this.manaMax=200;
		this.mana=200;
		this.sorts=new ArrayList<Competence> ();
		this.sorts.add(new Eclair_givre());
	}
	
	public static int getLoot()
	{
		return 1;
	}
	
	
	
	
	
	

}
