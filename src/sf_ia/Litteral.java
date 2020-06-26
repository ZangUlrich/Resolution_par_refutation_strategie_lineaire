package sf_ia;

/**
 * Cette classe repr�sente un litteral, c'est � dire une proposition ou sa n�gation.<br>
 * Elle est caract�ris�e par un tableau de char <B> l[] </B> de taille 2.
 * @author HARA ZANG Ulrich
 * @author DJONGANG DJOMI Darylle
 * 
 *
 */
public class Litteral {

	private char l[] =new char[2];
	
	/**
	 * Constructeur � un param�tre, il s'agit ici d'une proposition.
	 * @param a un char
	 */
	public Litteral(char a) {
		l[0]=a;
	}
	
	/**
	 * Constructeur � deux param�tres, il s'agit ici de la n�gation d'une proposition.
	 * @param a un char
	 * @param b un char
	 */
	public Litteral(char a,char b) {
		this(a);
		l[1]=b;
	}
	
	/**
	 * M�thode permettant de v�rifier si deux litt�raux sont compl�mentaires.
	 * @param l2 un Litteral
	 * @return true si les deux litt�raux sont compl�mentaires et false sinon
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
	 * @return le tableau l[] caract�risant le litt�ral.
	 */
	public char[] getL() {
		return l;
	}

	/**
	 * Setter
	 * @param l un tableau permettant de mettre � jour (modifier) le litt�ral.
	 */
	public void setL(char[] l) {
		this.l = l;
	}

	
	/**
	 * M�thode permettant de v�rifier si le Litteral courant et celui pass� en param�tre <br>
	 * sont �gaux.
	 * @param l2 un litt�ral
	 * @return true si les deux litt�raux sont �gaux et false sinon.
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
	 * M�thode de description d'un litt�ral.
	 * @return un description du litt�ral courant.
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
