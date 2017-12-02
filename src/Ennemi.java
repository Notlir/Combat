import java.util.ArrayList;
import java.util.Random;
public abstract class Ennemi extends Entitee {
	
	protected int loot;
	
	
	
	@Override
	protected boolean isFriendly()
	{
		return false;
	}
	

	@Override
	public void action(ArrayList<Entitee> protagonistes)
	{
		ArrayList<Entitee> cibles=this.ciblage(protagonistes);
		
		Random Choix= new Random();
		Entitee cible=cibles.get(Choix.nextInt(cibles.size()));
		this.attaque(cible);
			
		
		
	}

	
	@Override
	public void getXP(int xp)
	{
		
	}
	
	
}
