package sf_ia;

/**
 * Cette classe mat�rialise un sommet, dans la structure de donn�es de graphe.<br>
 * Caract�ris�e par une clause.
 * @author HARA ZANG Ulrich
 * @author DJONGANG DJOMI Darylle
 * @see Clause
 *
 */
public class Sommet {

	private Clause cl=new Clause();
	
	/**
	 * Constructeur � un param�tre
	 * @param s un sommet
	 * @see Sommet
	 */
	public Sommet(Sommet s) {
		this.cl=s.getCl();
	}
	
	/**
	 * Constructeur � un param�tre
	 * @param clause une Clause
	 * @see Clause
	 */
	public Sommet(Clause clause) {
		cl=clause;
	}

	/**
	 * Getter
	 * @return Clause
	 * @see Clause
	 */
	public Clause getCl() {
		return cl;
	}

	/**
	 * Setter
	 * @param cl une Clause
	 * @see Clause
	 */
	public void setCl(Clause cl) {
		this.cl = cl;
	}
	
	
}
