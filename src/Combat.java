import java.util.ArrayList;
import java.util.Random;


public class Combat {
	
	private int round;
	private int recompense;
	private ArrayList<Entitee> protagonistes;
	
	
	public Combat(ArrayList<Joueur> groupe)
	{
		this.protagonistes=new ArrayList<Entitee>();
		this.round=0;
		for(int i=0;i<groupe.size();i++)
		{
			this.protagonistes.add(groupe.get(i));
			
		}
		this.recompense=0;
	}
	
	
	
	public void debutCombat(int difficult)
	{
		int moyenneNiveau=0;
		int i;
		for(i=0;i<this.protagonistes.size();i++)
		{
			moyenneNiveau=moyenneNiveau+this.protagonistes.get(i).getLVL();
		}
		moyenneNiveau=(moyenneNiveau/i)+difficult;
		
		for(int j=0;j<3;j++) //TODO:MODIFIER LE 3
		{
			this.protagonistes.add(new Squelette(moyenneNiveau));
			
		}
		
		this.recompense=moyenneNiveau*25*Squelette.getLoot();
		
		mainCombat();
	}
	
	private ArrayList<Integer> Initiative()
	{
		int max=0;
		Random dice=new Random();
		ArrayList<Integer> init=new ArrayList <Integer>();
		
		for (int i=0;i<this.protagonistes.size();i++)
		{
			init.add(dice.nextInt(10));//TODO: ajouter la vitesse des entitées
		}
		
		ArrayList<Integer> ordre=new ArrayList<Integer>();
		
		for (int j=0;j<this.protagonistes.size();j++)
		{
			max=0;
			for(int k=0;k<this.protagonistes.size();k++)
			{
				if(init.get(k)>init.get(max))
				{
					max=k;
				}
			}
			
			ordre.add(max);
			init.set(max, -1);
		}
		
		return ordre;
		
	}
	
	private void retirerBlesse()
	{
		boolean blesse=false;
		int compteur=0;
		while(blesse==false && compteur<this.protagonistes.size())
		{
			if(this.protagonistes.get(compteur).getPV()<=0)
			{
				this.protagonistes.remove(compteur);
				retirerBlesse();
				blesse=true;
			}
			
			compteur++;
		}
	}
	
	
	protected int conditionVictoire()
	{
		int compteur=0;
		boolean team=this.protagonistes.get(0).isFriendly();
		while(compteur<this.protagonistes.size())
		{
			if(this.protagonistes.get(compteur).isFriendly()!=team)
			{
				return 0;
			}
			
			compteur++;
		}
		
		if(team==true)
		{
			return 1;
		}
		
		else
		{
			return 2;
		}

	}
	
	
	private void mainCombat()
	{
		
		int victory=0;
		ArrayList<Integer> ordre;
		while(victory==0)
		{ 
			this.round++;
			System.out.println("###########ROUND "+this.round+"###########");
			ordre=this.Initiative();
			for(int i=0;i<ordre.size();i++)
			{
				if(this.protagonistes.get(ordre.get(i)).getPV()>0 && conditionVictoire()==0)
				{
				this.protagonistes.get(ordre.get(i)).action(protagonistes);
				}
			}
		
			this.retirerBlesse();
			
			victory=conditionVictoire();
			
			
		}
		
		if(victory == 1)
		{
			
			System.out.println("Victoire !");
			
			for(Entitee i : protagonistes)
			{
				i.getXP(this.recompense);
			}
		}
		
		if(victory==2)
		{
			System.out.println("Défaite...");
		}
		
		
	}
	

}
