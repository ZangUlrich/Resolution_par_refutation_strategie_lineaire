package sf_ia;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Une classe matérialisant la stucture de données adoptée pour notre TP.<br>
 * Il s'agit d'un graphe non orienté. On a décidé de le représenter grâce aux <br>
 * listes d'adjacence. Pour son implémentation en java, on a opté pour la structure<br>
 * <B>LinkedHashMap adjs</B> , car il permet une gestion d'ordre des clés, qui est<br> 
 * l'ordre de leur insertion. La clé est <B>Sommet</B> et la valeur un <B> ArrayList de Sommets</B>.
 *
 * @author HARA ZANG Ulrich
 * @author DJONGANG DJOMI Darylle
 * @see LinkedHashMap
 * @see Sommet
 */
public class Graphe {

	private LinkedHashMap<Sommet, ArrayList<Sommet>> adjs= new LinkedHashMap<>();

	/**
	 * Constructeur par défaut
	 */
	public Graphe() {
		adjs= new LinkedHashMap<Sommet, ArrayList<Sommet>>();
	}

	/**
	 * Un constructeur à un paramètre
	 * @param adjs un LinkdedHashMap
	 * @see LinkedHashMap
	 */
	public Graphe(LinkedHashMap<Sommet, ArrayList<Sommet>> adjs) {
		this.adjs = adjs;
	}

	/**
	 * Méthode d'ajout d'un sommet au graphe
	 * @param clause une Clause
	 * @see Clause
	 */
	public void addSommet(Clause clause) {
		adjs.putIfAbsent(new Sommet(clause), new ArrayList<Sommet>());
		System.out.println("Ajout du sommet :"+ clause.toString());
	}

	/**
	 * Méthode d'ajout d'un sommet au graphe
	 * @param s un Sommet
	 * @see Sommet
	 */
	public void addSommet(Sommet s) {
		adjs.putIfAbsent(s, new ArrayList<Sommet>());
		System.out.println("Ajout du sommet :"+ s.getCl().toString());
	}


	/**
	 * Ajout d'une arrête reliant les sommets s1 et s2
	 * @param s1 un Sommet
	 * @param s2 un Sommet
	 * @see Sommet
	 */
	public void addArc(Sommet s1,Sommet s2) {

		//si les deux sommets ne sont pas null
		if(s1!=null && s2!=null) {
			
			//si adjs ne contient pas déjà s1 on l'ajoute à l'ensemble des sommets
			if (!adjs.containsKey(s1)) { 
				addSommet(s1); 
			}

			//si adjs ne contient pas déjà s2 on l'ajoute à l'ensemble des sommets
			if (!adjs.containsKey(s2)) {
				addSommet(s2); 
			}
			
			adjs.get(s1).add(s2);//on ajoute s2 à l'arrayList des sommets de s1
			adjs.get(s2).add(s1);//on ajoute s1 à l'arrayList des sommets de s2
			
			System.out.println("ajout d'arc entre "+s1.getCl()+" et "+s2.getCl());
		}
		
		//sinon l'ajout d'arc est impossible
		else {
			System.out.println("ajout d'arc impossible");
		}
	}

	/**
	 * Méthode permettant de récupérer l'ensemble des sommets adjacents ,de adjs, <br>
	 * au Sommet s passé en paramètre (la clé)
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
	 * Méthode permettant de vérifier si la clause passé en paramètre fait déjà <br>
	 * parti des sommets du graphe.
	 * @param clause une Clause
	 * @return true si la clause est déjà existante dans le graphe et false sinon
	 * @see Clause
	 */
	public boolean verifDoublons(Clause clause) {
		boolean b=false;
		
		//copie des sommets (clés) du graphe
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
