package personnages;
import java.util.ArrayList;
import java.util.Random;
public abstract class Ennemi extends Entitee {
	
	protected int loot;
	protected int profil;//1=combattant,2=mage offensif,3=support;
	
	
	
	@Override
	public boolean isFriendly()
	{
		return false;
	}
	
	
	public int getProfil()
	{
		return this.profil;
	}
	


			
		
		
	

	
	@Override
	public void getXP(int xp)
	{
		
	}
	
	
}
