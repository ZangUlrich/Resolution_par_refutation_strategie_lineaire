package sf_ia;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Une classe mat�rialisant la stucture de donn�es adopt�e pour notre TP.<br>
 * Il s'agit d'un graphe non orient�. On a d�cid� de le repr�senter gr�ce aux <br>
 * listes d'adjacence. Pour son impl�mentation en java, on a opt� pour la structure<br>
 * <B>LinkedHashMap adjs</B> , car il permet une gestion d'ordre des cl�s, qui est<br> 
 * l'ordre de leur insertion. La cl� est <B>Sommet</B> et la valeur un <B> ArrayList de Sommets</B>.
 *
 * @author HARA ZANG Ulrich
 * @author DJONGANG DJOMI Darylle
 * @see LinkedHashMap
 * @see Sommet
 */
public class Graphe {

	private LinkedHashMap<Sommet, ArrayList<Sommet>> adjs= new LinkedHashMap<>();

	/**
	 * Constructeur par d�faut
	 */
	public Graphe() {
		adjs= new LinkedHashMap<Sommet, ArrayList<Sommet>>();
	}

	/**
	 * Un constructeur � un param�tre
	 * @param adjs un LinkdedHashMap
	 * @see LinkedHashMap
	 */
	public Graphe(LinkedHashMap<Sommet, ArrayList<Sommet>> adjs) {
		this.adjs = adjs;
	}

	/**
	 * M�thode d'ajout d'un sommet au graphe
	 * @param clause une Clause
	 * @see Clause
	 */
	public void addSommet(Clause clause) {
		adjs.putIfAbsent(new Sommet(clause), new ArrayList<Sommet>());
		System.out.println("Ajout du sommet :"+ clause.toString());
	}

	/**
	 * M�thode d'ajout d'un sommet au graphe
	 * @param s un Sommet
	 * @see Sommet
	 */
	public void addSommet(Sommet s) {
		adjs.putIfAbsent(s, new ArrayList<Sommet>());
		System.out.println("Ajout du sommet :"+ s.getCl().toString());
	}


	/**
	 * Ajout d'une arr�te reliant les sommets s1 et s2
	 * @param s1 un Sommet
	 * @param s2 un Sommet
	 * @see Sommet
	 */
	public void addArc(Sommet s1,Sommet s2) {

		//si les deux sommets ne sont pas null
		if(s1!=null && s2!=null) {
			
			//si adjs ne contient pas d�j� s1 on l'ajoute � l'ensemble des sommets
			if (!adjs.containsKey(s1)) { 
				addSommet(s1); 
			}

			//si adjs ne contient pas d�j� s2 on l'ajoute � l'ensemble des sommets
			if (!adjs.containsKey(s2)) {
				addSommet(s2); 
			}
			
			adjs.get(s1).add(s2);//on ajoute s2 � l'arrayList des sommets de s1
			adjs.get(s2).add(s1);//on ajoute s1 � l'arrayList des sommets de s2
			
			System.out.println("ajout d'arc entre "+s1.getCl()+" et "+s2.getCl());
		}
		
		//sinon l'ajout d'arc est impossible
		else {
			System.out.println("ajout d'arc impossible");
		}
	}

	/**
	 * M�thode permettant de r�cup�rer l'ensemble des sommets adjacents ,de adjs, <br>
	 * au Sommet s pass� en param�tre (la cl�)
	 * @param s un Sommet
	 * @return un arrayList de Sommet
	 * @see Sommet
	 */
	public ArrayList<Sommet> getAdjSommets(Sommet s){
		return adjs.get(s);
	}

	/**
	 * Getter
	 * @return un Map
	 * @see Map
	 */
	public Map<Sommet, ArrayList<Sommet>> getAdjs() {
		return adjs;
	}

	/**
	 * M�thode permettant de v�rifier si la clause pass� en param�tre fait d�j� <br>
	 * parti des sommets du graphe.
	 * @param clause une Clause
	 * @return true si la clause est d�j� existante dans le graphe et false sinon
	 * @see Clause
	 */
	public boolean verifDoublons(Clause clause) {
		boolean b=false;
		
		//copie des sommets (cl�s) du graphe
		ArrayList<Sommet> sommets=new ArrayList<Sommet>(this.adjs.keySet());

		for (Sommet sommet: sommets) {
			if (sommet.getCl().equal(clause)) {
				return true;
			}
		}

		b=false;
		return b;

	}


	/**
	 * Setter
	 * @param adjs un LinkedHashMap
	 */
	public void setAdjs(LinkedHashMap<Sommet, ArrayList<Sommet>> adjs) {
		this.adjs = adjs;
	}


}
