import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		ArrayList<Joueur> groupe=new ArrayList<Joueur>();
		groupe.add(new Guerrier());
		groupe.add(new Paladin());
		groupe.add(new Assassin());
		Combat insatance=new Combat(groupe);
		insatance.debutCombat(0);
		
	}

}
