import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


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
	private ArrayList<Entitee> ciblage(Entitee instigateur)
	{
		ArrayList<Entitee> cibles=new ArrayList<Entitee>();
		
		for(int i=0;i<protagonistes.size();i++)
		{
			if(instigateur.isFriendly()!=protagonistes.get(i).isFriendly() && protagonistes.get(i).getPV()>0)
			{
				cibles.add(protagonistes.get(i));
			}
		}
		
		return cibles;
		
	}
	
	public void attaque(Entitee cible,Entitee attaquant)
	{
		int deg=attaquant.atk-cible.getDef();
		System.out.println(attaquant.getNom()+" attaque "+cible.getNom()+" !");
		if(deg>0)
		{
			System.out.println(cible.getNom()+" subis "+deg+" points de dégats !");
			cible.getDegats(deg);
		}
		else
		{
		System.out.println("Mais il n'inflige aucun dégats !");
		}
	}
	
	private Entitee choixCible(Entitee instigateur)//Interface pour que le joueur choisisse sa cible
	{
		ArrayList<Entitee> cibles=ciblage(instigateur);
		int choix=-1;
		while(choix>=cibles.size() || choix<0)
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
	
	
	public void actionIA(Entitee actif)
	{
		ArrayList<Entitee> cibles=ciblage(actif);
		
		Random Choix= new Random();
		Entitee cible=cibles.get(Choix.nextInt(cibles.size()));
		attaque(cible,actif);
		
	}
	
	
	
	
	public void actionJoueur(Entitee actif)
	{
		if(ciblage(actif).size()>0) {
		int choix;
		System.out.println("->Action de "+actif.getNom());
		System.out.println("Pv:"+actif.getPV());
		System.out.println("1.Attaque\n2.Competences");
		Scanner sc=new Scanner(System.in);
		Entitee cible;
		choix=sc.nextInt();
		if(choix==1)
		{
		cible=this.choixCible(actif);
		attaque(cible,actif);
		}
		else if(choix==2)
		{
			int c=1;
			for(Competence i :actif.getSorts())
			{
				System.out.println(c+". "+i.getNom());
				c++;
			}
			
			choix=sc.nextInt();
			cible=this.choixCible(actif);
			cible.subirComp(actif.getComp().get(choix-1));
			
			
			
		}
		
		}
		
	}
	
	private void retirerBlesse(ArrayList<Entitee> passage)
	{
		boolean blesse=false;
		int compteur=0;
		while(blesse==false && compteur<this.protagonistes.size())
		{
			if(this.protagonistes.get(compteur).getPV()<=0)
			{
				passage.remove(this.protagonistes.get(compteur));
				this.protagonistes.remove(compteur);
				
				retirerBlesse(passage);
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
		ArrayList<Entitee>passage=new ArrayList<Entitee>();
		while(victory==0)
		{ 
			this.round++;
			System.out.println("###########ROUND "+this.round+"###########");
			ordre=this.Initiative();
			for(int j : ordre)
			{
				passage.add(this.protagonistes.get(j));
			}
			for(Entitee i : passage)
			{
				if(i.getPV()>0 && conditionVictoire()==0)
				{
					if(i.isFriendly()==true)
						{
							actionJoueur(i);
						}
				
					else
					{
						actionIA(i);
					}
				}
				
			}
		
			this.retirerBlesse(passage);
			
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
