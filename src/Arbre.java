import java.util.ArrayList;

public class Arbre 
{
	private Noeud racine = null;
	
	public Arbre(ArrayList<VariableDeDecision> varDecision)
	{
		racine = new Noeud(varDecision);
		
	}
	
	public Arbre(Noeud racine)
	{
		this.racine = racine;
	}
	
	private static void parcours(Noeud n)
	{
		if(n != null)
		{
			parcours(n.getGauche());
			parcours(n.getDroite());
		}
	}
	
	public void parcours()
	{
		parcours(racine);
	}
	
	/*
	 * faire une fonction qui prend un noeud en paramètre
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
	 * repeter les memes operations dans le les fils
	 * 
	 */
}
