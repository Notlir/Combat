import java.util.ArrayList;

public abstract class Competence {
	
	protected String nom;
	protected boolean cible; //true=ennemis false =alli�es
	protected int zone;//1=1cibles, 0=self,-1:groupe
	protected int duree;//0=instant, 1=1 round
	protected ArrayList<Integer> bolus;//0=bolus sur l'atk, 1=bolus sur la def
	protected int degheal;
	protected int deghealDurr;
	protected int cout; // cout en mana
	
	
	public int getDegDurr()
	{
		return this.deghealDurr;
	}
	public int getDuree()
	{
		return this.duree;
	}
	
	public boolean getCible()
	{
		return this.cible;
	}
	
	public int getZone()
	{
		return this.zone;
	}
	
	
	public ArrayList<Integer> getBolus()
	{
		return this.bolus;
	}
	
	public int getDeg()
	{
		return this.degheal;
	}
	
	public int getCout()
	{
		return this.cout;
	}
	
	public String getNom()
	{
		return this.nom;
	}
	
}
