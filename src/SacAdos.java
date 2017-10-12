import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SacAdos 
{
	private int capaciteMax;
	private int poidsActuel;
	private float borneMin;
	private float borneMax;
	
	/**
	 * a l'initialisation s'assurer que la taille des variables de décisions, des contraintes et de la fonction objectif sont égaux
	 */
	private ArrayList<VariableDeDecision> varDecision; // x_i
	private ArrayList<Integer> contraintes; // coefficients de la fonction contrainte
	private ArrayList<Integer> fonctionObjectif;
	private ArrayList<ObjetPossible> objetDansSac; // liste des objets qui sont dans le sac dans un état x
	
	public SacAdos(int capaciteMax,int poidsActuel, ArrayList<VariableDeDecision> varDecision, ArrayList<Integer> contraintes,
			ArrayList<Integer> fonctionObjectif, ArrayList<ObjetPossible> objetDansSac) {
		super();
		this.capaciteMax = capaciteMax;
		this.poidsActuel = poidsActuel;
		this.varDecision = varDecision;
		this.contraintes = contraintes;
		this.fonctionObjectif = fonctionObjectif;
		this.objetDansSac = objetDansSac;
	}

	public ArrayList<Ratio> triRatio(ArrayList<ObjetPossible> objetsPossibles)
	{
		ArrayList<Ratio> listRatio = new ArrayList<>();
				
		for (int i = 0; i < objetsPossibles.size(); i++)
		{
			listRatio.add(new Ratio(this.fonctionObjectif.get(i).floatValue()/(float)(objetsPossibles.get(i).getPoids()), i));
		}
		
		Collections.sort(listRatio, new Comparator<Ratio>() {

			@Override
			public int compare(Ratio o1, Ratio o2) 
			{
				return o1.compareTo(o2);
			}
		});
		
		
		return listRatio;
	}
	
	public void printRatio(ArrayList<Ratio> ratios)
	{
		System.out.println("\nAffichage des ratios");
		for (Ratio r : ratios) {
			
			System.out.println("[" + r.getIndex() + " , " + r.getRatio() + "]");
		}
	}
	
	public void printVarDecison(ArrayList<VariableDeDecision> varDecision)
	{
		
		System.out.println("\nAffichage des variables de décisions");
		for (int i =0; i<varDecision.size(); i++) {
			System.out.println("[" + i + " , " + varDecision.get(i).getValue() + "]");
		}
	}
	
	public void printBorne()
	{
		System.out.println("\nEncadrement de la solution : " + this.borneMin + " < Solution < " + this.borneMax);
	}
	
	// retourne l'indexe de l'objet qu'on ne peut pas rajouter
	public void putObj(ArrayList<Ratio> ratios, ArrayList<ObjetPossible> objetsPossibles)
	{
		int index = ratios.get(0).getIndex();
		// mettre les objets dans le sac en vérifant la contrainte de poids
		while( (this.poidsActuel + objetsPossibles.get(index).getPoids() < this.getCapaciteMax()) && (this.respectContrainte()) )
		{
			// prendre le 1er objet de la liste
			// l'ajouter à la liste des objets dans le sac
			// mettre à jour le poids du sac
			
			
			this.objetDansSac.add(objetsPossibles.get(index));
			this.varDecision.set(index, new VariableDeDecision(1f, false));
			this.poidsActuel += objetsPossibles.get(index).getPoids();
			//System.out.println(this.poidsActuel);
			ratios.remove(0);
			printRatio(ratios);
			// modifier les valeurs de x_i quand on ajoute un objet
			
			//printRatio(ratios);
			if(! ratios.isEmpty()) {	
				index = ratios.get(0).getIndex();
			}
		}
		
		int capaciteRestante = this.capaciteMax - this.poidsActuel;
		
		this.varDecision.get(index).setValue(this.fraction(index, capaciteRestante, objetsPossibles));
		this.printVarDecison(this.varDecision);
		this.borneMax = this.calculF(this.varDecision);
		
		
	}
	
	
	public boolean respectContrainte()
	{
		float res = 0f;
		
		for(int i=0; i<this.contraintes.size(); i++)
		{
			res += (float)this.contraintes.get(i) * this.varDecision.get(i).getValue();
//			System.out.println("coeff contrainte : " + (float)this.contraintes.get(i));
//			System.out.println("var decision : " + this.varDecision.get(i));
//			System.out.println(res);
		}
		return ( res <= this.capaciteMax );
	
	}
	
	public float fraction(int index, int capaciteRestante, ArrayList<ObjetPossible> listObj)
	{
		int poids = listObj.get(index).getPoids();
		//System.out.println();
		float fraction = capaciteRestante / (float)poids;
		//System.out.println(fraction);
		return fraction;
	}
	
	public float calculF(ArrayList<VariableDeDecision> varDecision)
	{
		float res = 0f;
		
		for(int i=0; i<varDecision.size(); i++)
		{
			res += (float)this.fonctionObjectif.get(i) * varDecision.get(i).getValue();
		}
		
		return res;
	}
	
	public float calculBorneMin()
	{
		float borneMin = 0f;
		ArrayList<VariableDeDecision> clone = (ArrayList<VariableDeDecision>)this.varDecision.clone();
		int i = 0;
		while(clone.get(i).getValue() == 1f)
		{
			i++;
		}
		clone.get(i).setValue(0f);
		borneMin = this.calculF(clone);
		//System.out.println(borneMin);
		return borneMin;
	}
	
	public boolean addObject(ObjetPossible objet)
	{
		if(this.poidsActuel + objet.getPoids() > this.capaciteMax)
			return false;
		
		return this.objetDansSac.add(objet);
	}
	
		/*
	 * faire une fonction qui prend un arbre en paramètre
	 * placer les noeuds qui sont locked en 1er dans le sac
	 * 
	 * si 
	 *   	la capacite max est depassee on arrete tout 
	 * 
	 * sinon 
	 * 		trier les ratios par ordre decroissant
	 * 		placer dans le sac les objets non locked en fonction du ratio
	 * 		
	 * 		si tous les objets rentrent sans fraction calculer la fonction pour les xi
	 * 		mettre à jour la borne min et sauvegarder les valeurs de qui donnent ce min
	 * 
	 * fractionner l'objet qui ne rentre pas en entier
	 * calculer la valeur de la fonction au niveau du noeud
	 * mettre à jour les bornes si necessaire
	 * creer un fils gauche et locked l'objet qui depasse à 0
	 * creer un fils droite et locked l'objet qui depasse à 1
	 * 
	 * repeter les memes operations dans les fils
	 * 
	 */
	
	
	
	public int getCapaciteMax()
	{
		return capaciteMax;
	}

	public int getPoidsActuel() {
		return poidsActuel;
	}

	public void setPoidsActuel(int poidsActuel) {
		this.poidsActuel = poidsActuel;
	}

	public float getBorneMin() {
		return borneMin;
	}

	public void setBorneMin(float borneMin) {
		this.borneMin = borneMin;
	}

	public float getBorneMax() {
		return borneMax;
	}

	public void setBorneMax(float borneMax) {
		this.borneMax = borneMax;
	}

	public ArrayList<VariableDeDecision> getVarDecision() {
		return varDecision;
	}

	public void setVarDecision(ArrayList<VariableDeDecision> varDecision) {
		this.varDecision = varDecision;
	}

	public ArrayList<Integer> getContraintes() {
		return contraintes;
	}

	public void setContraintes(ArrayList<Integer> contraintes) {
		this.contraintes = contraintes;
	}

	public ArrayList<Integer> getFctObjective() {
		return fonctionObjectif;
	}

	public void setFctObjective(ArrayList<Integer> fctObjective) {
		this.fonctionObjectif = fctObjective;
	}

	public ArrayList<ObjetPossible> getObjSac() {
		return objetDansSac;
	}

	public void setObjSac(ArrayList<ObjetPossible> objSac) {
		this.objetDansSac = objSac;
	}

	public void setCapaciteMax(int capaciteMax) {
		this.capaciteMax = capaciteMax;
	}
	
	
	
	
	
}
