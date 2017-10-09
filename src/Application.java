import java.util.ArrayList;

public class Application 
{
	public static void main(String[] args)
	{
		int capaciteMax = 17;
		int poidsActuel = 0;
		ArrayList<Float> varDecision = new ArrayList<>();
		varDecision.add(0f);
		varDecision.add(0f);
		varDecision.add(0f);
		varDecision.add(0f);
		
		
		ArrayList<Integer> contraintes = new ArrayList<>();
		contraintes.add(3);
		contraintes.add(7);
		contraintes.add(9);
		contraintes.add(6);
		
		ArrayList<Integer> fctObjective = new ArrayList<>();
		fctObjective.add(8);
		fctObjective.add(18);
		fctObjective.add(20);
		fctObjective.add(11);
		
		ArrayList<ObjetPossible> objSac = new ArrayList<>();
		
		SacAdos sacAdos = new SacAdos(capaciteMax, poidsActuel, varDecision, contraintes, fctObjective, objSac);
		
		ArrayList<ObjetPossible> obj= new ArrayList<>();
		obj.add(new ObjetPossible(8,3));
		obj.add(new ObjetPossible(18,7));
		obj.add(new ObjetPossible(20,9));
		obj.add(new ObjetPossible(11,6));
		
		
		sacAdos.putObj(sacAdos.triRatio(obj), obj);
		sacAdos.fraction(2, 7, obj);
		sacAdos.setBorneMin(sacAdos.calculBorneMin()); 
		sacAdos.printBorne();
		sacAdos.printVarDecison(sacAdos.getVarDecision());
		//sacAdos.respectContrainte();
				
	}
}
