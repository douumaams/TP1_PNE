import java.util.ArrayList;

public class Noeud {
	
	private ArrayList<VariableDeDecision> varDecision; 
	
	private Noeud gauche;
	private Noeud droite;
	
	
	
	public Noeud(ArrayList<VariableDeDecision> varDecision, Noeud gauche, Noeud droite)
	{

		this.varDecision = varDecision;
		
		this.gauche = gauche;
		this.droite = droite;
	}
	
	
	public Noeud(ArrayList<VariableDeDecision> varDecision)
	{
		this(varDecision, null, null);
	}
	
	
	public Noeud getDroite() {
		return droite;
	}
	
	public Noeud getGauche() {
		return gauche;
	}
	
	
	
	
	
	

}
