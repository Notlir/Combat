import java.util.ArrayList;
import java.util.Scanner;

public abstract class Joueur extends Entitee {
	
	protected int xp;
	
	
	
	
	private void lvlUp()//TODO:Augmenter les carac(voir redéfinir dans la classe de perso)
	{
		System.out.println(this.getNom()+" monte d'un niveau !");
		this.xp=this.xp-(this.lvl*100);
		this.lvl=this.lvl+1;
		getXP(0);
	}
	@Override
	public void getXP(int xp)
	{
		System.out.println(this.getNom()+" gagne "+xp+" points d'xp !");
	this.xp=this.xp+xp;
	
	
	if(this.xp>=this.lvl*100)
	{
		this.lvlUp();
	}
		
	}
	
	@Override
	protected boolean isFriendly()
	{
		return true;
	}
	
	
	private Entitee choixCible(ArrayList<Entitee> protagonistes)//Interface pour que le joueur choisisse sa cible
	{
		ArrayList<Entitee> cibles=this.ciblage(protagonistes);
		int choix=-1;
		while(choix>cibles.size() || choix<0)
		{
		for(int i=0;i<cibles.size();i++)
		{
			System.out.println((i+1)+". "+cibles.get(i).getNom()+" LVL"+cibles.get(i).getLVL());		
		}
		Scanner sc=new Scanner(System.in);
		choix=(sc.nextInt())-1;
		
		}
		return cibles.get(choix);
		
	}
	
	@Override
	public void action(ArrayList<Entitee> protagonistes)
	{
		System.out.println("->Action de "+this.getNom());
		System.out.println("Pv:"+this.getPV());
		Entitee cible=this.choixCible(protagonistes);
		
		this.attaque(cible);
	
		
	}

}
