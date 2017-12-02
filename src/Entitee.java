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
	
	public boolean attaque(Entitee cible)
	{
		int deg=this.atk-cible.getDef();
		System.out.println(this.getNom()+" attaque "+cible.getNom()+" !");
		if(deg>0)
		{
			System.out.println(cible.getNom()+" subis "+deg+" points de dégats !");
		if(cible.getDegats(deg)==true)
		{
			System.out.println(cible.getNom()+" est neutralisé !");
			return true;
		}
		else return false;
		}
		System.out.println("Mais il n'inflige aucun dégats !");
		return false;
	}
	
	public int getLVL()
	{
		return this.lvl;
	}
	
	public abstract void action(ArrayList<Entitee> protagonistes);

	
	protected abstract boolean isFriendly();
	
	public abstract void getXP(int xp);
	
	
	
	protected ArrayList<Entitee> ciblage(ArrayList<Entitee> protagonistes)
	{
		ArrayList<Entitee> cibles=new ArrayList<Entitee>();
		
		for(int i=0;i<protagonistes.size();i++)
		{
			if(this.isFriendly()!=protagonistes.get(i).isFriendly() && protagonistes.get(i).getPV()>0)
			{
				cibles.add(protagonistes.get(i));
			}
		}
		
		return cibles;
		
	}
	
	public String getNom()
	{
		return this.nom;
	}
	
	public int getPV()
	{
		return this.pv;
	}
	
}
