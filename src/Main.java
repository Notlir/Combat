import java.util.ArrayList;

import personnages.*;



public class Main {

	//test
	public static void main(String[] args) {
		ArrayList<Joueur> groupe=new ArrayList<Joueur>();
		groupe.add(new Guerrier());
		groupe.add(new Paladin());
		groupe.add(new Assassin());
		Combat insatance=new Combat(groupe);
		insatance.debutCombat(0);
		
	}

}
