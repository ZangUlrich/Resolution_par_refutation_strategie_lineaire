package sf_ia;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Cette classe met en oeuvre la stratégie de résolution par réfutation linéaire.
 * @author HARA ZANG Ulrich
 * @author DJONGANG DJOMI Darylle
 *
 */
public class StrategieLineaire {

	Graphe graphe=new Graphe();
	static boolean b=false;//variable renseignant sur l'état de notre algorithme

	public StrategieLineaire(Graphe graphe) {
		this.graphe = graphe;
	}

	/**
	 * Enregistre les clauses de la KB et de !alpha en tant que clé de la hashMap
	 * @param clauses une ArrayList de Clause
	 * @see Clause
	 */
	public void enregistrerClauses(ArrayList<Clause> clauses) {
		for (Clause cl : clauses) {
			graphe.addSommet(cl);
		}
	}

	/**
	 * Méthode permettant de réaliser l'algorithme de la résolution par réfutation, <br>
	 *  avec stratégie de résolution linéaire. 
	 */
	public void resolutionLineaire() {

		//copie de l'ensemble des sommets (clés)
		ArrayList<Sommet> iterator=new ArrayList<Sommet>(graphe.getAdjs().keySet());
		
		//on prend comme clause centrale la dernière de l'arrayList
		Sommet c0= iterator.get(iterator.size()-1);
		System.out.println("\nLa clause centrale est c0: "+c0.getCl()+"\n");
		Clause resolvant=null;// on initialise la clause résolvante à null

		//on parcourt l'ensemble des sommets sauf le dernier
		for (int i=0;i<iterator.size()-1;i++) {
			int j=i+1;
			Sommet c1=iterator.get(i);
			System.out.println("c0: "+c0.getCl()+" , c"+ j +": "+c1.getCl());
			resolvant=c0.getCl().resolvant(c1.getCl());

			if(resolvant==null)
				System.out.println("paire non réductible");
			else 
				System.out.println("resolvant : "+resolvant+"\n");


			if (resolvant!=null) {

				if(resolvant.getLitterals().isEmpty()) {
					b=true;
					return ;
				}

				else {

					if (!this.graphe.verifDoublons(resolvant)) {
						Sommet r=new Sommet(resolvant);
						if(!graphe.getAdjs().containsKey(r)) {
							graphe.addArc(c0, r);
							graphe.addArc(c1, r);
							this.resolutionLineaire();
							if(b==true)
								break;
							else {
								System.out.println("\nLa clause n'ayant pas aboutit, continuons avec la clause précédente: "+c0.getCl()+"\n");
							}
						}	
					}
					
					else {
						System.out.println("Cette clause existe déjà");
						System.out.println("On continue avec la clause centrale c0: "+c0.getCl()+"\n");
					}

				}
			}
			else {
				if(c0.getCl().paireReductible(c1.getCl())) {
					b=true;
					return;
				}
				else {
					if(i==iterator.size()-1) {
						b=false;
						return;
					}
				}
			}
		}
		return ;
	}

	public static void main(String[] args) {
		
		Scanner scanner=new Scanner(System.in);

		System.out.print("Entrer le nombre de clauses dans KB: ");
		int nbClauses=scanner.nextInt();
		scanner.nextLine();
		ArrayList<String> strings=new ArrayList<String>();

		for (int i = 0; i < nbClauses; i++) {
			System.out.print("Entrer la clause c"+i+": ");
			strings.add(scanner.nextLine());
		}
		
		System.out.println("Entrer le nombre de clauses dans !alpha: ");
		nbClauses=scanner.nextInt();
		scanner.nextLine();
		
		for (int i = 0; i < nbClauses; i++) {
			System.out.print("Entrer la clause c"+i+": ");
			strings.add(scanner.nextLine());
		}
		
		
		scanner.close();
		ArrayList<Clause> clauses=new ArrayList<Clause>();
		ArrayList<Litteral> litterals=new ArrayList<Litteral>();
		Litteral litteral;

		for (String string : strings) {

			litterals.clear();
			char tab[]=string.toCharArray();
			int i=0;

			while (i<tab.length) {

				if(tab[i]=='!') {
					litteral=new Litteral('!',tab[i+1]);
					litterals.add(litteral);
					i+=2;
				}

				else {

					if(tab[i]!=' ') {
						litteral=new Litteral(tab[i]);
						litterals.add(litteral);
						i++;
					}

					else {
						i++;
					}

				}

			}
			clauses.add(new Clause(litterals));
		}

		StrategieLineaire strategieLineaire=new StrategieLineaire(new Graphe());
		System.out.println("\n");
		strategieLineaire.enregistrerClauses(clauses);
		strategieLineaire.resolutionLineaire();
		System.out.println(b);



	}
}
