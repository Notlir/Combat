package personnages;

public class Squelette extends Ennemi {
	
	

	
	public Squelette(int diff)
	{
		this.atk=10;
		this.def=6;
		this.lvl=diff;
		this.pvMax=25;
		this.pv=25;
		this.loot=1;
		this.nom="Squellete";
		this.profil=1;
	}
	
	public static int getLoot()
	{
		return 1;
	}

}
