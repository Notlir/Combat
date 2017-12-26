import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import personnages.Assassin;
import personnages.Guerrier;
import personnages.Joueur;
import personnages.Paladin;

public class WindowTest extends StateBasedGame {

	
	public ArrayList<Joueur> groupe=new ArrayList<Joueur>();
	
	
	public WindowTest() throws SlickException {
		super("Fenetre test");
		groupe.add(new Guerrier());
		groupe.add(new Paladin());
		groupe.add(new Assassin());
		
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		addState(new NotCombat());
		addState(new CombatScreen(groupe));

	}

}
