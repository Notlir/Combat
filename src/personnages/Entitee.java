package personnages;
import java.util.ArrayList;

import competences.*;


public abstract class Entitee {
	protected String nom;
	protected int pvMax;
	protected int pv;
	protected int atk;
	protected int def;
	protected int lvl;
	protected ArrayList<Effet> effets_subis=new ArrayList<Effet>();
	protected int manaMax;
	protected int mana;
	protected ArrayList<Competence> sorts=new ArrayList<Competence>();

		
	public boolean getDegats(int deg)
	{
		this.pv=this.pv-deg;
		
		if(this.pv>0)
		{
			return false;
		}
		else 
		{
			System.out.println(this.getNom()+" est neutralisé !");
			return true;
		}
	}
	
	public void getHeal(int heal)
	{
		this.pv=this.pv+heal;
		System.out.println(this.nom+" regagne "+heal+" PV!");
		
		if(this.pv>this.pvMax)
		{
			this.pv=this.pvMax;
		}
	}
	
	public int getAtk()
	{
		return this.atk;
	}
	
	
	public int getDef()
	{
		return this.def;
	}
	
	

	public int getLVL()
	{
		return this.lvl;
	}
	


	
	public abstract boolean isFriendly();
	
	public abstract void getXP(int xp);
	
	
	public void reduireMana(int cout)
	{
		this.mana=this.mana-mana;
	}
	

	public ArrayList<Competence> getComp()
	{
		return this.sorts;
	}
	
	public String getNom()
	{
		return this.nom;
	}
	
	public int getPV()
	{
		return this.pv;
	}
	
	
	
	public void subirComp(Competence sort)
	{ 
		
		if (sort.getDuree()>0)
		{
			this.effets_subis.add(new Effet(sort.getDuree(),sort.getBolus(),sort.getDegDurr(),sort.getNom()));
			this.atk=this.atk+sort.getBolus().get(0);
			this.def=this.def+sort.getBolus().get(1);
			
		}
		
		
		
			int degheal=sort.getDeg();
			if(degheal<0)
				{
				System.out.println(this.nom+" subis "+(-degheal)+" points de degats de "+sort.getNom());
					this.getDegats(-degheal);
				}
			else if(degheal>0)
				{
					this.getHeal(degheal);
				}
		
		
		
	}
	
	public void subirEffet()
	{
		for (Effet i : this.effets_subis)
		{
			if(i.getDuree()==0)
			{
				this.atk=this.atk-i.getBolus().get(0);
				this.def=this.def-i.getBolus().get(1);
				this.effets_subis.remove(i);
			}
			
			int deg=i.getDegheal();
			if(deg<0)
			{
				System.out.println(this.nom+" subis "+(-deg)+" points de degats de l'effet de "+i.getNom());
				this.getDegats(-deg);
				
			}
			else if(deg>0)
			{
				this.getHeal(deg);
			}
			
			i.reduireDurée();
			
		}
	}
	
	public ArrayList<Competence> getSorts()
	{
		return this.sorts;
	}
	
	public int getAttack()
	{
		return this.atk;
	}
	
	public int getManaMax()
	{
		return this.manaMax;
	}
	
	public int getMana()
	{
		return this.mana;
	}
	
	public boolean fullLife()
	{
		if(this.pv==this.pvMax)
		{
			return true;
		}
		
		else return false;
	}
	
	public int getPVMax()
	{
		return this.pvMax;
	}
	
	public ArrayList<Effet> getEffet()
	{
		return this.effets_subis;
	}
	

}
