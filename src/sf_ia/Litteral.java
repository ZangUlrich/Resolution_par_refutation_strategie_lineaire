package sf_ia;

/**
 * Cette classe représente un litteral, c'est à dire une proposition ou sa négation.<br>
 * Elle est caractérisée par un tableau de char <B> l[] </B> de taille 2.
 * @author HARA ZANG Ulrich
 * @author DJONGANG DJOMI Darylle
 * 
 *
 */
public class Litteral {

	private char l[] =new char[2];
	
	/**
	 * Constructeur à un paramètre, il s'agit ici d'une proposition.
	 * @param a un char
	 */
	public Litteral(char a) {
		l[0]=a;
	}
	
	/**
	 * Constructeur à deux paramètres, il s'agit ici de la négation d'une proposition.
	 * @param a un char
	 * @param b un char
	 */
	public Litteral(char a,char b) {
		this(a);
		l[1]=b;
	}
	
	/**
	 * Méthode permettant de vérifier si deux littéraux sont complémentaires.
	 * @param l2 un Litteral
	 * @return true si les deux littéraux sont complémentaires et false sinon
	 */
	public boolean compLit(Litteral l2) {
		boolean b=false;
		if (l[0]=='!') {
			if (l2.getL()[0]=='!') {
				b=false;
			}
			else {
				if(l[1]==l2.getL()[0])
					b=true;
				else
					b=false;
			}
		}
		else {
			if (l2.getL()[0]=='!') {
				if(l[0]==l2.getL()[1])
					b=true;
				else 
					b=false;
			}
			else
				b=false;
		}
		return b;
	}
	
	/**
	 * Getter
	 * @return le tableau l[] caractérisant le littéral.
	 */
	public char[] getL() {
		return l;
	}

	/**
	 * Setter
	 * @param l un tableau permettant de mettre à jour (modifier) le littéral.
	 */
	public void setL(char[] l) {
		this.l = l;
	}

	
	/**
	 * Méthode permettant de vérifier si le Litteral courant et celui passé en paramètre <br>
	 * sont égaux.
	 * @param l2 un littéral
	 * @return true si les deux littéraux sont égaux et false sinon.
	 */
	public boolean equal(Litteral l2) {
		if (this.l[0]==l2.getL()[0]) {
			if (this.l[1]==l2.getL()[1]) {
				return true;
			}
			else 
				return false;
		}
		else 
			return false;
	}
	
	/**
	 * Méthode de description d'un littéral.
	 * @return un description du littéral courant.
	 */
	@Override
	public String toString() {
		if (l[0]!='!') {
			return ""+l[0];
		}
		else {
			return l[0]+""+l[1];
		}
	}
}
