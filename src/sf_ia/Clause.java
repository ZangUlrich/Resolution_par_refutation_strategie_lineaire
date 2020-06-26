package sf_ia;

import java.util.ArrayList;

/**
 * Cette classe met en oeuvre une clause, qui est une disjonction de littéraux. <br>
 * Elle est caractérisée par un ArrayList de littéraux <B>litterals </B>.
 * @author HARA ZANG Ulrich
 * @author DJONGANG DJOMI Darylle
 * @see Litteral
 *
 */

public class Clause {

	private ArrayList<Litteral> litterals=new ArrayList<Litteral>();

	/**
	 * Constructeur par défaut
	 * 
	 */
	public Clause() {
		litterals= new ArrayList<Litteral>();
		this.duplicateLitteralsAvoidance();//on évite ici des duplications du genre P|P
	}

	/**
	 * Constructeur avec un paramètre
	 * @param litterals ArrayList de littéraux
	 */
	@SuppressWarnings("unchecked")
	public Clause(ArrayList<Litteral> litterals) {
		this.litterals=(ArrayList<Litteral>) litterals.clone();
		this.duplicateLitteralsAvoidance();//on évite ici des duplications du genre P|P
	}

	/**
	 * Méthode permettant de vérifier si la clause courante et la clause passée en paramètre <br>
	 * sont une paire réductible.
	 * @param cl2 une Clause.
	 * @return true si les deux clauses sont une paire réductible et false sinon.
	 */
	public boolean paireReductible(Clause cl2) {

		boolean b=false;
		int nbLitCompl=0;//nombre de littéraux complémentaires dans les deux clauses

		for (Litteral l1 : litterals) {
			for (Litteral l2 : cl2.litterals) {
				if (l1.compLit(l2)) {
					nbLitCompl++;
				}
			}
		}
		
		/*
		 * Le nombre de littéraux complémentaires doit être exactement 1.
		 * Autrement dit il doit avoir un unique littéral dans l'une des deux clauses
		 * qui a son complémentaire dans l'autre clause.
		 */
		if(nbLitCompl==0)
			b=false;
		if(nbLitCompl==1)
			b=true;
		if(nbLitCompl>=2)
			b=false;

		return b;
	}

	/**
	 * Méthode permettant de trouver le résolvant de deux clauses ( qui sont une paire réductible)
	 * @param cl2 une clause
	 * @return la clause résolvante
	 */
	public Clause resolvant(Clause cl2) {
		int i=0,j=0;
		
		//listes copies
		ArrayList<Litteral> lit1=new ArrayList<Litteral>(litterals);
		ArrayList<Litteral> lit2=new ArrayList<Litteral>(cl2.litterals);
		
		//Si les littéraux sont des paires réductibles
		if(this.paireReductible(cl2)) {

			for (Litteral l1 : litterals) {
				for (Litteral l2 : cl2.litterals) {
					if (l1.compLit(l2)) {
						i=litterals.indexOf(l1);
						j=cl2.litterals.indexOf(l2);
					}
				}
			}
			
			//suppression des littéraux complémentaires dans les listes copies
			lit1.remove(i);
			lit2.remove(j);

			//regroupement des listes copies
			lit1.addAll(lit2);

			return new Clause(lit1);
		}
		
		// s'ils ne le sont pas
		else 
			return null;

	}

	/**
	 * Méthode permettant de supprimer les littéraux dupliqués dans la clause courante. 
	 */
	private void duplicateLitteralsAvoidance() {
		int i=0;
		
		//copie de littéraux
		ArrayList<Litteral> litteralsCopy=new ArrayList<Litteral>(litterals);

		for (i=0;i<litterals.size()-1;i++) {
			for (int j=i+1;j<litterals.size();j++) {
				
				if (litterals.get(i).equal(this.litterals.get(j))) {
					litteralsCopy.remove(i);
				}
			}
		}
		this.litterals=new ArrayList<Litteral>(litteralsCopy);
	}

	/**
	 * Getter
	 * @return un ArrayList des littéraux composant la clause.
	 */
	public ArrayList<Litteral> getLitterals() {
		return litterals;
	}


	/**
	 * Setter
	 * @param litterals un ArrayList de littéraux.
	 */
	public void setLitterals(ArrayList<Litteral> litterals) {
		this.litterals = litterals;
	}

	
	/**
	 * Méthode permettant de vérifier si la clause courante et la clause passée en paramètre <br>
	 * sont égales.
	 * @param cl2 une Clause.
	 * @return true si les deux clauses sont égales et false sinon.
	 */
	public boolean equal(Clause cl2) {
		boolean b=false;
		int nb=0;
		
		//Si le nombre de littéraux est différent on renvoie false
		if (this.litterals.size()!=cl2.getLitterals().size()) {
			b=false;
		}
		
		//sinon on détermine le nombre de littéraux égaux 
		else {
			for (int i = 0; i < litterals.size(); i++) {
				
				for (int j = 0; j < litterals.size(); j++) {
					
					if (litterals.get(i).equal(cl2.getLitterals().get(j))) {
						nb++;
					}
				}
				
			}
			
			//si ce nombre est inférieur au nombre des littéraux total on renvoie false.
			if (nb<litterals.size()) {
				b=false;
			}
			
			//sinon on renvoie true
			else {
				b=true;
			}
		}
		
		return b;
	}
	
	/**
	 * Méthode de description d'une Clause
	 * @return une description de la clause courante
	 */
	@Override
	public String toString() {
		String str=new String();
		if (!litterals.isEmpty()) {
			for (int i = 0; i < litterals.size()-1; i++) {
				str+=litterals.get(i).toString()+" | ";
			}
			str+=litterals.get(litterals.size()-1);	
		}
		else 
			str="La clause vide";

		return str;

	}

}
