# Resolution_par_refutation_strategie_lineaire
Ce repos est une implémentation en java de la stratégie linéaire de la résolution par réfutation en système formel

Dans notre cadre on doit avoir une base de connaissance KB et une requête alpha, l'objectif est donc de montrer que KB a pour conséquence
logique alpha. On dispose pour cela de plusieurs types de résolution, mais ici l'on s'attarde sur la résolution par réfutation qui consiste 
à montrer que KB^(non)alpha est inconsistant. Et pour cela on utilise la méthode des résolvants; on écrit donc KB^(non)alpha sous CNF (forme normale 
conjonctive) ainsi on obtient un ensemble de clauses. Maintenant l'objectif est de construire un arbre de recherche pour trouver rapidement un arbre de 
réfutation. Et encore nous dans ce travail on se concentre uniquement sur la stratégie linéaire. 
Voilà sommairement ce que l'on cherche a résoudre comme problème dans ce code. La mannière d'entrer les données est :


* On demande à l’utilisateur d’entrer le nombre de clauses de la KB, il doit saisir un entier
strictement positif
* Ensuite on lui demande d’entrer les clauses de cette KB au fur et à mesure. Les indications pour
celles-ci sont :
  * Pour une clause les littéraux doivent être séparés par un espace exemple : P Q R (ce qui
    correspond à P˅Q˅R
  * La négation d’un littéral est : !
  * Pour valider la saisi d’une clause appuyer sur la touche « Enter »
  * Un exemple complet de saisi d’une clause est donc : !P Q !R S « Enter » (ce qui
    correspond à ˥P ˅ Q ˅ ˥R ˅ ˥S
* Puis l’on demande d’entrer le nombre de clauses de la négation de la requête ( !alpha), il doit
  une fois de plus saisir un entier strictement positif.
* Et enfin on lui demande d’entrer les clauses de la négation de la requête (voir les indications
données au point 2 pour la saisi des clauses).



Pour tester le programme il y a un .jar mis à votre disposition.
