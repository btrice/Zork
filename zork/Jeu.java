  
import java.util.ArrayList;
import java.util.List;
/**
 *  Classe principale du jeu "Zork". <p>
 *
 *  Zork est un jeu d'aventure très rudimentaire avec une interface en mode
 *  texte: les joueurs peuvent juste se déplacer parmi les différentes pieces.
 *  Ce jeu nécessite vraiment d'etre enrichi pour devenir intéressant!</p> <p>
 *
 *  Pour jouer a ce jeu, créer une instance de cette classe et appeler sa
 *  méthode "jouer". </p> <p>
 *
 *  Cette classe crée et initialise des instances de toutes les autres classes:
 *  elle crée toutes les pieces, crée l'analyseur syntaxique et démarre le jeu.
 *  Elle se charge aussi d'exécuter les commandes que lui renvoie l'analyseur
 *  syntaxique.</p>
 *
 * @author     Michael Kolling
 * @author     Marc Champesme (pour la traduction francaise)
 * @version    1.1
 * @since      March 2000
 */

public class Jeu {
	private AnalyseurSyntaxique analyseurSyntaxique;
	private Piece pieceCourante;
	private Mission m;
	private joueur joueur1;
	private Piece piecePrecedente;
	/**
	 *  Crée le jeu et initialise la carte du jeu (i.e. les pièces).
	 */
	public Jeu() {
		creerPieces();
		analyseurSyntaxique = new AnalyseurSyntaxique();
	}


	/**
	 *  Crée toutes les pieces et relie leurs sorties les unes aux autres.
	 */
	public void creerPieces() {
		Piece dehors;
		Piece salleTD;
		Piece taverne;
		Piece batimentC;
		Piece burreau;
		Piece salleTP;
		Piece gymnasse;
		
	
		// mettre toute les pi�ces du jeu dans une list 
		List<Piece> toutelespieces=new ArrayList<Piece>();

		// création des pieces
		dehors = new Piece("devant le batiment C");
		salleTD = new Piece("une salle de TD dans le batiment G");
		taverne = new Piece("la taverne");
		batimentC = new Piece("le batiment C");
		burreau = new Piece("le secrétariat");
		salleTP = new Piece("une salle de TP dans le batiment G");
		gymnasse = new Piece("Gymnasse universitaire");
		
		// initialise les sorties des pieces
		// nord, est, sud,  ouest
		dehors.setSorties(gymnasse, salleTD, batimentC, taverne);
		salleTD.setSorties(null, null, salleTP, dehors);
		taverne.setSorties(null, dehors, null, null);
		batimentC.setSorties(dehors, burreau, null, null);
		burreau.setSorties(null, null, null, batimentC);
		salleTP.setSorties(salleTD, null, null, batimentC);
		gymnasse.setSorties(burreau, null, dehors, batimentC);
        
		Objectpiece[] objet;
		objet =new Objectpiece[21];
		//salle de td
		objet[0]=new Objectpiece("tableau",50,false);
		objet[1]=new Objectpiece("table",50,true);
		objet[2]=new Objectpiece("bic",5,true);
		objet[3]=new Objectpiece("crayon",5,true);
		objet[4]=new Objectpiece("chaisse",25,true);
		salleTD.ajoudobjet(objet[0]);
		salleTD.ajoudobjet(objet[1]);
		salleTD.ajoudobjet(objet[2]);
		salleTD.ajoudobjet(objet[3]);
		salleTD.ajoudobjet(objet[4]);
		// bureau
		objet[5]=new Objectpiece("ordinateur",50,false);
		objet[6]=new Objectpiece("imprimante",50,false);
		objet[7]=new Objectpiece("livres",35,true);
		objet[8]=new Objectpiece("cahiers",35,true);
		objet[9]=new Objectpiece("bic",5,true);
		objet[10]=new Objectpiece("bulletin",17,true);
		objet[11]=new Objectpiece("attestation",12,true);
		burreau.ajoudobjet(objet[5]);
		burreau.ajoudobjet(objet[6]);
		burreau.ajoudobjet(objet[7]);
		burreau.ajoudobjet(objet[8]);
		burreau.ajoudobjet(objet[9]);
		burreau.ajoudobjet(objet[10]);
		burreau.ajoudobjet(objet[11]);
		//dehors
		objet[12]=new Objectpiece("poubelle",30,true);
		objet[13]=new Objectpiece("lampadaire",15,false);
		objet[14]=new Objectpiece("craie",9,true);
		dehors.ajoudobjet(objet[12]);
		dehors.ajoudobjet(objet[13]);
		dehors.ajoudobjet(objet[14]);
		//gymnasse
		objet[15]=new Objectpiece("ballon",30,true);
		objet[16]=new Objectpiece("chaussure",10,true);
		objet[17]=new Objectpiece("serviette",7,true);
	    gymnasse.ajoudobjet(objet[15]);
	    gymnasse.ajoudobjet(objet[16]);
	    gymnasse.ajoudobjet(objet[17]);
		//salle tp
		objet[18]=new Objectpiece("marqueur",30,true);
		objet[19]=new Objectpiece("ordibateur",50,false);
		objet[20]=new Objectpiece("feuille",10,true);
		salleTP.ajoudobjet(objet[18]);
		salleTP.ajoudobjet(objet[19]);
		salleTP.ajoudobjet(objet[20]);
		
		
		toutelespieces.add(dehors);
		toutelespieces.add(salleTD);
		toutelespieces.add(taverne);
		toutelespieces.add(batimentC);
		toutelespieces.add(burreau);
		toutelespieces.add(salleTP);
		toutelespieces.add(gymnasse);
        m=new Mission(toutelespieces);
		// le jeu commence dehorsob
		pieceCourante = dehors;
}
   
	/**
	 *  Pour lancer le jeu. Boucle jusqu'a la fin du jeu.
	 */
	public void jouer() {
	    
	    joueur1=new joueur("ZORK");
	    
		afficherMsgBienvennue();
         m.mission();
         joueur1.setpoidsmax(m.getpoidsdevictoire());
		// Entrée dans la boucle principale du jeu. Cette boucle lit
		// et exécute les commandes entrées par l'utilisateur, jusqu'a
		// ce que la commande choisie soit la commande "quitter"
		boolean termine = false;
		while (!termine) {
			Commande commande = analyseurSyntaxique.getCommande();
			/*
			 * ici qu'il faut appeler traitercommande pour les monstres, animaux
			 */
			termine = traiterCommande(commande);
			if(m.gagner(joueur1))
			{
			System.out.println("************** Mission r�usit Victoire !!!!!! BRAVO !!!***********");
			termine=true;
			}
		}
		System.out.println("Merci d'avoir jouer.  Au revoir.");
	}


	/**
	 *  Affiche le message d'accueil pour le joueur.
	 */
	public void afficherMsgBienvennue() {
		System.out.println();
		System.out.println("Bienvennue dans le monde de Zork !");
		System.out.println("Zork est un nouveau jeu d'aventure, terriblement enuyeux.");
		System.out.println("Tapez 'aide' si vous avez besoin d'aide.");
		System.out.println();
		System.out.println(pieceCourante.descriptionLongue());
		pieceCourante.affichemesobjet();
	}


	/**
	 *  Exécute la commande spécifiée. Si cette commande termine le jeu, la valeur
	 *  true est renvoyée, sinon false est renvoyée
	 *
	 * @param  commande  La commande a exécuter
	 * @return           true si cette commande termine le jeu ; false sinon.
	 */
	public boolean traiterCommande(Commande commande) {
		if (commande.estInconnue()) {
			System.out.println("Je ne comprends pas ce que vous voulez...");
			return false;
		}

		String motCommande = commande.getMotCommande();
		if (motCommande.equals("aide")) {
			afficherAide();
		} else if (motCommande.equals("aller")) {
			deplacerVersAutrePiece(commande);
		}//AIDARA modification
		else if (motCommande.equals("retour")) {
		    if (commande.aSecondMot()) {
				System.out.println("retour ou ? taper juste retour");
			} else {
				deplacerVersAutrePiece(commande);
			}
			
		}
		else if (motCommande.equals("plan")) {
		    if (commande.aSecondMot()) {
				System.out.println("ecriviez plan");
			} else {
				commandejoueur(commande);
			}
			
		}
		else if (motCommande.equals("ma")) {
		    if (!commande.aSecondMot()) {
				System.out.println("ecriviez ma mission");
			} else {
				commandejoueur(commande);
			}
			
		}
		else if (motCommande.equals("deposer")) {
		    if (!commande.aSecondMot()) {
				System.out.println("deposer quelle objets?");
			} else {
				commandejoueur(commande);
			}
			
		}
		else if (motCommande.equals("mes")) {
			if (!commande.aSecondMot()) {
				System.out.println("Mes informations ou mes objets?");
			} else {
			  commandejoueur(commande);
			}
			
			
		}
		else if (motCommande.equals("transporter")) {
			if (!commande.aSecondMot()) {
				System.out.println("transporter qu'elle objets?");
			} else {
			  commandejoueur(commande);
			}
			
		}
		else if (motCommande.equals("quitter")) {
			if (commande.aSecondMot()) {
				System.out.println("Quitter quoi ?");
			} else {
				return true;
			}
		}
		return false;
	}


	// implementations des commandes utilisateur:

	/**
	 *  Affichage de l'aide. Affiche notament la liste des commandes utilisables.
	 */
	public void afficherAide() {
		System.out.println("Vous etes perdu. Vous etes seul. Vous errez");
		System.out.println("sur le campus de l'Université Paris 13.");
		System.out.println();
		System.out.println("Les commandes reconnues sont:");
		analyseurSyntaxique.afficherToutesLesCommandes();
	}


	/**
	 *  Tente d'aller dans la direction spécifiée par la commande. Si la piece
	 *  courante possède une sortie dans cette direction, la piece correspondant a
	 *  cette sortie devient la piece courante, dans les autres cas affiche un
	 *  message d'erreur.
	 *
	 * @param  commande  Commande dont le second mot spécifie la direction a suivre
	 */
	public void deplacerVersAutrePiece(Commande commande) {
		//AIDARA modification
		if(commande.getMotCommande().equals("retour"))
		{
			 if(pieceCourante.descriptionCourte().equals("devant le batiment C"))
			 {
				 System.out.println("Il n'y a pas de retour possible !");
				 return;
			 }
			 else
			 {
				 pieceCourante=piecePrecedente;
				 System.out.println(pieceCourante.descriptionLongue());
					pieceCourante.affichemesobjet();
					return;
			 }
		}else//AIDARA modification ajout du else
		    if (!commande.aSecondMot()) {
			// si la commande ne contient pas de second mot, nous ne
			// savons pas ou aller..
			System.out.println("Aller où ?");
			return;
		}
		String direction = commande.getSecondMot();
       //sauvegarde de la pi�ce courante dans la pi�ce precedente.
			piecePrecedente=pieceCourante;
			
		// Tentative d'aller dans la direction indiquée.
		Piece pieceSuivante = pieceCourante.pieceSuivante(direction);

		if (pieceSuivante == null) {
			System.out.println("Il n'y a pas de porte dans cette direction!");
		} else {
			pieceCourante = pieceSuivante;
			joueur1.setnbpiecevisite();
			System.out.println(pieceCourante.descriptionLongue());
			//affichage des objets de la pi�ce courante
			pieceCourante.affichemesobjet();
			
		}
	}
	/**
	 * execute les commande ma, mes, transporter et deposer.
	 * @param commande est une commande � executer dont le second mot sp�cifie objets ou informations si la commande est mes
	 */
	public void commandejoueur(Commande commande)
	{
	      	String motCommande = commande.getMotCommande();
			if (motCommande.equals("mes"))
			{
			  if(commande.getSecondMot().equalsIgnoreCase("objets"))
			   joueur1.affichagedesobjets();
			  if(commande.getSecondMot().equalsIgnoreCase("informations"))
			  joueur1.informations(joueur1);
			 }
			 else if(motCommande.equals("transporter"))
			      {
			          int n=0;
			          n=pieceCourante.rechercheobjet(commande.getSecondMot());
        				if(n != -1)
        				{    Objectpiece obj;
        				     obj=pieceCourante.renvoieobjet(n);
        					 if(joueur1.transporter(obj))		
        					   {	
        					       pieceCourante.supprimerobjet(n);
        					      System.out.println("L'OBJET : "+obj.getnomobjet() +" a �t� emporter avec succes ");   
        					   }	
        				}
        			         
			       }
			  else if(motCommande.equals("deposer"))
			      {
			           String nomobjet=commande.getSecondMot();
			        if(joueur1.esttransporte(nomobjet))
			        {
			          pieceCourante.ajoudobjet(joueur1.deposer(nomobjet));
			          System.out.println("vous aviez deposer l'objet "+commande.getSecondMot()+" dans la pi�ce"+pieceCourante.descriptionCourte() );
			        }
			        else
			        {
			           System.out.println("D�sol� vous ne transporter pas cet objet");
			         }
			       }
			       else if(motCommande.equals("ma"))
			      {
			          m.affichage();
			      }
			      else if (motCommande.equals("plan"))
			      {
			         m.plan();
			       
			      }
			
	 
	   }

	
}

