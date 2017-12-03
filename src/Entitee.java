import java.util.ArrayList;

public abstract class Entitee {
	protected String nom;
	protected int pvMax;
	protected int pv;
	protected int atk;
	protected int def;
	protected int lvl;
	
	

	public boolean getDegats(int deg)
	{
		this.pv=this.pv-deg;
		
		if(this.pv>0)
		{
			return false;
		}
		else 
		{
			return true;
		}
	}
	
	public int getDef()
	{
		return this.def;
	}
	
	

	public int getLVL()
	{
		return this.lvl;
	}
	


	
	protected abstract boolean isFriendly();
	
	public abstract void getXP(int xp);
	
	
	

	
	public String getNom()
	{
		return this.nom;
	}
	
	public int getPV()
	{
		return this.pv;
	}
	
}
