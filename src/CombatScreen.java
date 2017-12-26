import java.util.ArrayList;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import competences.Competence;
import personnages.Entitee;
import personnages.Joueur;

public class CombatScreen extends BasicGameState {
	
	private GameContainer container;
	public static final int ID=3;
	private Combat combat;
	Image background;
	Image menu;
	private ArrayList<Joueur> groupe;
	Music music;
	private boolean tourJoueur=false;
	private boolean actif=false;
	private Entitee current;
	private boolean debut=true;
	private int curseur=1;
	private int status=1;
	private Competence choix;
	ArrayList<Entitee> passage;
	
	public CombatScreen(ArrayList<Joueur> groupe)
	{
		this.groupe=groupe;
		
	}
	

	
	@Override
    public void keyReleased(int key, char c) {
        if (Input.KEY_ESCAPE == key) {
            container.exit();
        }
    }
	
	@Override
	public void keyPressed(int key, char c)
	{
		if(this.tourJoueur==true)
		{
			if(key==Input.KEY_DOWN)
			{
				if(this.status==1)
				{
					if(this.curseur<2)
					{
						this.curseur++;
					}
					else this.curseur=1;
				}
				
				else if(this.status==2)
				{
					if(this.curseur<this.current.getComp().size())
					{
						this.curseur++;
					}
					
					else this.curseur=0;	
				}
				
				else if(this.status==3 || this.status==5 || this.status==4)
				{
					if(this.curseur<this.combat.ciblage(current, true).size()-1)
					{
						this.curseur++;
					}
					else
					{
						this.curseur=0;
					}
				}
				
				
			}
			
		
			if(key==Input.KEY_UP)
			{

				if(this.status==1)
				{
					if(this.curseur>1)
					{
						this.curseur--;
					}
					else this.curseur=2;
				}
				
				else if(this.status==2)
				{
					if(this.curseur>0)
					{
						this.curseur--;
					}
					
					else this.curseur=this.current.getComp().size();	
				}
				
				else if(this.status==3)
				{
					if(this.curseur>0)
					{
						this.curseur--;
					}
					else
					{
						this.curseur=this.combat.ciblage(current, true).size()-1;
					}
				
			}
			
			
		}
		}
		
		if(key==Input.KEY_ENTER)
		{
			if(this.status==1)
			{
				if(this.curseur==1)
				{
					this.curseur=0;
					this.status=3;
				}
				
				
				
				if(this.curseur==2)
				{
					this.curseur=0;
					this.status=2;
				}
			}
			
			else if(this.status==2)
			{
				if(this.curseur==this.current.getComp().size())
				{
					this.status=1;
					this.curseur=1;
				}
				else
				{
					this.curseur=0;
					this.choix=this.current.getComp().get(curseur);
					if(this.choix.getCible()==true)
					{
						this.status=4;
					}
					else
					{
						this.status=5;
					}
				}
			}
			
			else if(this.status==3)
			{
				this.combat.actionJoueur(current, null, this.combat.ciblage(current, true).get(curseur));
				this.status=6;
			}
			
			else if(this.status==6)
			{
				this.status=7;
			}
			
		}
		
	}



	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		this.container=arg0;
		this.background=new Image("src/background/battle.jpg");
		this.music=new Music("src/battle-song.ogg");
		this.menu=new Image("src/background/interface.png");
		
		
		
		
	}
	
	public void affichagePerso(Graphics g, ArrayList<Entitee> prota)
	{
		for(Entitee i : prota)
		{
			g.drawAnimation(i.anim[0],i.getX(),i.getY());
		}
	}



	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		
		if(!music.playing())
		{
			  music.loop();
		}
		if(debut==true)
		{
			this.combat=new Combat(groupe,arg0);
			this.combat.debutCombat(0);
			debut=false;
			for( Entitee i : this.groupe)
			{
				i.setText("src/personnages/sprites/generique.png");
			}
			
		}
		
		this.background.draw(0,0,container.getWidth(),container.getHeight());
		affichagePerso(arg2,this.combat.getProta());
		this.menu.draw(0, 0, arg0.getWidth(),arg0.getHeight());
		//arg2.drawString("Interface de combat styl�", (arg0.getWidth()/12)*4, (arg0.getHeight()/5)*4);
		if(tourJoueur==true)
		{
			if(this.status==1)
			{
			afficherAction(arg2,arg0);
			afficherCurseur(arg2,arg0);
			}
			else if(this.status==2)
			{
				
				
				afficherComp(arg2,arg0);
				
				afficherCurseur(arg2,arg0);
			}
			else if(this.status==3 || this.status==4)
			{
				
				afficherCibles(arg2,arg0,true);
				afficherCurseur(arg2,arg0);
			}
			
			else if(this.status==5)
			{
				afficherCibles(arg2,arg0,false);
				afficherCurseur(arg2,arg0);
			}
			
			else if(this.status==6)
			{
				for(int i=0;i<this.combat.log.size();i++)
				{
				
				arg2.drawString(this.combat.log.get(i),(arg0.getWidth()/12)*4, i*(arg0.getHeight()/19)+(arg0.getHeight()/5)*4);
				
				}
			}
				
			
			
		}
		
		
			
	
	}
	
	public void afficherCibles(Graphics g,GameContainer con,boolean allies)
	{
		ArrayList<Entitee> cibles=this.combat.ciblage(this.current, allies);
		for(int i=0;i<cibles.size();i++)
		{
			g.drawString(cibles.get(i).getNom(),(con.getWidth()/12)*4, i*(con.getHeight()/19)+(con.getHeight()/5)*4);
			
		}
		
		
	}
	
	public void afficherComp(Graphics g,GameContainer con)
			{
			int i=0;
				for( i=0;i<this.current.getComp().size();i++)
				{
					g.drawString(this.current.getComp().get(i).getNom(),(con.getWidth()/12)*4, i*(con.getHeight()/19)+(con.getHeight()/5)*4);
				}
				
				g.drawString("Retour",(con.getWidth()/12)*4, i*(con.getHeight()/19)+(con.getHeight()/5)*4);
		
			}
	
	public void afficherAction(Graphics g,GameContainer con)
	{	g.drawString("Action de "+this.current.getNom()+"(PV:"+this.current.getPV()+")",(con.getWidth()/12)*4, 0*(con.getHeight()/19)+(con.getHeight()/5)*4);
		g.drawString("Attaque",(con.getWidth()/12)*4, 1*(con.getHeight()/19)+(con.getHeight()/5)*4);
		g.drawString("Competences",(con.getWidth()/12)*4, 2*(con.getHeight()/19)+(con.getHeight()/5)*4);
		
	}
	
	public void afficherCurseur(Graphics g,GameContainer con)
	{
		
		g.fillOval(((con.getWidth()/12)*4)-(con.getWidth()/55), this.curseur*(con.getHeight()/19)+(con.getHeight()/5)*4, 10, 10);
		
	}



	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		
		if(this.actif==false && debut==false)
		{
			maitreCombat();
			
		}
	
		
		
	}



	@Override
	public int getID() {
		
		return this.ID;
	}
	
	
	public void maitreCombat()
	{
		
		if(this.actif==false)
		{
		this.combat.mainCombat();
		
		passage = this.combat.getProta();//this.combat.getPassage();
		}
		this.actif=true;
		this.current=passage.get(0);
	
		if(this.current.isFriendly()==true)
		{
			this.tourJoueur=true;
		}
		
		if(this.status==7)
		{
			this.combat.retirerBlesse(passage);
			this.passage.remove(0);
		}
		
		
		
		
		
	}
	
	


}
