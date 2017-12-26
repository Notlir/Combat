package competences;

import java.util.ArrayList;

public class Smite extends Competence {
	
		public Smite()
		{
			this.nom="Smite";
			this.bolus=new ArrayList<Integer>();
			this.bolus.add(-2);
			this.bolus.add(-2);
			this.cible=true;
			this.duree=2;
			this.cout=20;
			this.zone=1;
			this.degheal=-20;
			this.deghealDurr=0;
			this.cout=50;
		}

}
