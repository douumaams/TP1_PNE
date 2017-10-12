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
			// traitement a faire
			parcours(n.getGauche());
			parcours(n.getDroite());
		}
	}
	
	public void parcours()
	{
		parcours(racine);
	}
	
	
	
	public Noeud getRacine() {
		return racine;
	}
	
}
