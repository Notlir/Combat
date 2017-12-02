
public class Squelette extends Ennemi {
	
	

	
	public Squelette(int diff)
	{
		this.atk=3;
		this.def=3;
		this.lvl=diff;
		this.pvMax=25;
		this.pv=20;
		this.loot=1;
		this.nom="Squellete";
	}
	
	public static int getLoot()
	{
		return 1;
	}

}
