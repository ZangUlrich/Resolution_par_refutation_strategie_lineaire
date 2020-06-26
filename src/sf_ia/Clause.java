package sf_ia;

import java.util.ArrayList;

/**
 * Cette classe met en oeuvre une clause, qui est une disjonction de litt�raux. <br>
 * Elle est caract�ris�e par un ArrayList de litt�raux <B>litterals </B>.
 * @author HARA ZANG Ulrich
 * @author DJONGANG DJOMI Darylle
 * @see Litteral
 *
 */

public class Clause {

	private ArrayList<Litteral> litterals=new ArrayList<Litteral>();

	/**
	 * Constructeur par d�faut
	 * 
	 */
	public Clause() {
		litterals= new ArrayList<Litteral>();
		this.duplicateLitteralsAvoidance();//on �vite ici des duplications du genre P|P
	}

	/**
	 * Constructeur avec un param�tre
	 * @param litterals ArrayList de litt�raux
	 */
	@SuppressWarnings("unchecked")
	public Clause(ArrayList<Litteral> litterals) {
		this.litterals=(ArrayList<Litteral>) litterals.clone();
		this.duplicateLitteralsAvoidance();//on �vite ici des duplications du genre P|P
	}

	/**
	 * M�thode permettant de v�rifier si la clause courante et la clause pass�e en param�tre <br>
	 * sont une paire r�ductible.
	 * @param cl2 une Clause.
	 * @return true si les deux clauses sont une paire r�ductible et false sinon.
	 */
	public boolean paireReductible(Clause cl2) {

		boolean b=false;
		int nbLitCompl=0;//nombre de litt�raux compl�mentaires dans les deux clauses

		for (Litteral l1 : litterals) {
			for (Litteral l2 : cl2.litterals) {
				if (l1.compLit(l2)) {
					nbLitCompl++;
				}
			}
		}
		
		/*
		 * Le nombre de litt�raux compl�mentaires doit �tre exactement 1.
		 * Autrement dit il doit avoir un unique litt�ral dans l'une des deux clauses
		 * qui a son compl�mentaire dans l'autre clause.
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
	 * M�thode permettant de trouver le r�solvant de deux clauses ( qui sont une paire r�ductible)
	 * @param cl2 une clause
	 * @return la clause r�solvante
	 */
	public Clause resolvant(Clause cl2) {
		int i=0,j=0;
		
		//listes copies
		ArrayList<Litteral> lit1=new ArrayList<Litteral>(litterals);
		ArrayList<Litteral> lit2=new ArrayList<Litteral>(cl2.litterals);
		
		//Si les litt�raux sont des paires r�ductibles
		if(this.paireReductible(cl2)) {

			for (Litteral l1 : litterals) {
				for (Litteral l2 : cl2.litterals) {
					if (l1.compLit(l2)) {
						i=litterals.indexOf(l1);
						j=cl2.litterals.indexOf(l2);
					}
				}
			}
			
			//suppression des litt�raux compl�mentaires dans les listes copies
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
	 * M�thode permettant de supprimer les litt�raux dupliqu�s dans la clause courante. 
	 */
	private void duplicateLitteralsAvoidance() {
		int i=0;
		
		//copie de litt�raux
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
	 * @return un ArrayList des litt�raux composant la clause.
	 */
	public ArrayList<Litteral> getLitterals() {
		return litterals;
	}


	/**
	 * Setter
	 * @param litterals un ArrayList de litt�raux.
	 */
	public void setLitterals(ArrayList<Litteral> litterals) {
		this.litterals = litterals;
	}

	
	/**
	 * M�thode permettant de v�rifier si la clause courante et la clause pass�e en param�tre <br>
	 * sont �gales.
	 * @param cl2 une Clause.
	 * @return true si les deux clauses sont �gales et false sinon.
	 */
	public boolean equal(Clause cl2) {
		boolean b=false;
		int nb=0;
		
		//Si le nombre de litt�raux est diff�rent on renvoie false
		if (this.litterals.size()!=cl2.getLitterals().size()) {
			b=false;
		}
		
		//sinon on d�termine le nombre de litt�raux �gaux 
		else {
			for (int i = 0; i < litterals.size(); i++) {
				
				for (int j = 0; j < litterals.size(); j++) {
					
					if (litterals.get(i).equal(cl2.getLitterals().get(j))) {
						nb++;
					}
				}
				
			}
			
			//si ce nombre est inf�rieur au nombre des litt�raux total on renvoie false.
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
	 * M�thode de description d'une Clause
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
