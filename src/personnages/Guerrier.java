package personnages;

public class Guerrier extends Joueur {

	 public Guerrier()
	 {
		 this.atk=8;
		 this.def=7;
		 this.lvl=1;
		 this.pvMax=20;
		 this.pv=this.pvMax;
		 this.xp=0;
		 this.nom="Guerrier"; 
		 this.manaMax=75;
		 this.mana=75;
	 }
}