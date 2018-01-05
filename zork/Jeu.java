  
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
    private Mission mission;
    private Joueur joueur;
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

        List<Piece> toutelespieces = new ArrayList<Piece>();

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

        // ajout des ojets dans les pièces
        // salleTD
        salleTD.ajouter(new MonObjet("tableau",50,false));
        salleTD.ajouter(new MonObjet("table",50,true));
        salleTD.ajouter(new MonObjet("bic",5,true));
        salleTD.ajouter(new MonObjet("crayon",5,true));
        salleTD.ajouter(new MonObjet("chaisse",25,true));
        salleTD.ajouter(new Animer("Fille",60,false));
        salleTD.ajouter(new Animer("Prof",70,false));

        // burreau
        burreau.ajouter(new MonObjet("ordinateur",50,false));
        burreau.ajouter(new MonObjet("imprimante",50,false));
        burreau.ajouter(new MonObjet("livres",35,true));
        burreau.ajouter(new MonObjet("cahiers",35,true));
        burreau.ajouter(new MonObjet("bic",5,true));
        burreau.ajouter(new MonObjet("bulletin",17,true));
        burreau.ajouter(new MonObjet("attestation",12,true));
        burreau.ajouter(new Animer("directeur",80,false));

        // dehors
        dehors.ajouter(new MonObjet("poubelle",30,true));
        dehors.ajouter(new MonObjet("lampadaire",15,false));
        dehors.ajouter(new MonObjet("craie",9,true));

        // gymnasse
        gymnasse.ajouter(new MonObjet("ballon",30,true));
        gymnasse.ajouter(new MonObjet("chaussure",10,true));
        gymnasse.ajouter(new MonObjet("serviette",7,true));
        gymnasse.ajouter(new Animer("Monstre",80,false));

        // salleTP
        salleTP.ajouter(new MonObjet("marqueur",30,true));
        salleTP.ajouter(new MonObjet("ordibateur",50,false));
        salleTP.ajouter(new MonObjet("feuille",10,true));
        salleTP.ajouter(new Animer("mygale",20,false));

        toutelespieces.add(dehors);
        toutelespieces.add(salleTD);
        toutelespieces.add(taverne);
        toutelespieces.add(batimentC);
        toutelespieces.add(burreau);
        toutelespieces.add(salleTP);
        toutelespieces.add(gymnasse);
        mission  = new Mission(toutelespieces);
        // le jeu commence dehors
        pieceCourante = dehors;
    }

    /**
     *  Pour lancer le jeu. Boucle jusqu'a la fin du jeu.
     */
    public void jouer() {

        joueur = new Joueur("ZORK", 50);

        afficherMsgBienvennue();
        mission.creerMission();
        joueur.setPoidsMax(50 + mission.getPoidsDeVictoire()); // Poids Max = poids du joueur + poids des objets à transporter
        // Entrée dans la boucle principale du jeu. Cette boucle lit
        // et exécute les commandes entrées par l'utilisateur, jusqu'a
        // ce que la commande choisie soit la commande "quitter"
        boolean termine = false;
        while (!termine) {
            Commande commande = analyseurSyntaxique.getCommande();
			// deplacement aléatoire des être animer
            Animer animer = pieceCourante.getAnimer();
            if( animer != null ){
                deplacerEtreAnimer(animer);
            }
            termine = traiterCommande(commande);
            if(mission.gagner(joueur))
            {
                System.out.println("**************  Victoire !!!!!! BRAVO !!!***********");
                termine = true;
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
        pieceCourante.afficher();
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
        } else if (motCommande.equals("retour")) {
            if (commande.aSecondMot()) {
                System.out.println("retour ou ? taper juste retour");
            } else {
                deplacerVersAutrePiece(commande);
            }

        } else if (motCommande.equals("plan")) {
            if (commande.aSecondMot()) {
                System.out.println("Ecrivez plan");
            } else {
                commandejoueur(commande);
            }

        } else if (motCommande.equals("ma")) {
            if (!commande.aSecondMot()) {
                System.out.println("Ecrivez ma mission");
            } else {
                commandejoueur(commande);
            }

        } else if (motCommande.equals("déposer")) {
            if (!commande.aSecondMot()) {
                System.out.println("déposer quelle objets?");
            } else {
                commandejoueur(commande);
            }

        } else if (motCommande.equals("mes")) {
            if (!commande.aSecondMot()) {
                System.out.println("Mes informations ou mes objets?");
            } else {
                commandejoueur(commande);
            }


        } else if (motCommande.equals("transporter")) {
            if (!commande.aSecondMot()) {
                System.out.println("transporter qu'elle objets?");
            } else {
                commandejoueur(commande);
            }

        } else if (motCommande.equals("quitter")) {
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
                pieceCourante = piecePrecedente;
                System.out.println(pieceCourante.descriptionLongue());
                pieceCourante.afficher();
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
        //sauvegarde de la pièce courante dans la pièce précédente.
        piecePrecedente = pieceCourante;

        // Tentative d'aller dans la direction indiquée.
        Piece pieceSuivante = pieceCourante.pieceSuivante(direction);

        if (pieceSuivante == null) {
            System.out.println("Il n'y a pas de porte dans cette direction!");
        } else {
            pieceCourante = pieceSuivante;
            joueur.incrementPieceVisite();
            System.out.println(pieceCourante.descriptionLongue());
            //affichage des objets de la pièce courante
            pieceCourante.afficher();

        }
    }
    public void deplacerEtreAnimer(Animer animer){
        String direction = animer.deplacerAleatoire().getSecondMot();
        Piece pieceSuivante = pieceCourante.pieceSuivante(direction);
        if (pieceSuivante != null) {
            pieceCourante.supprimer(animer);
            pieceSuivante.ajouter(animer);
        }
    }
    /**
     * execute les commande ma, mes, transporter et déposer.
     * @param commande est une commande ࡥxecuter dont le second mot spꤩfie objets ou informations si la commande est mes
     */
    public void commandejoueur(Commande commande) {
        String motCommande = commande.getMotCommande();
        if (motCommande.equals("mes")) {
            if(commande.getSecondMot().equalsIgnoreCase("objets"))
                joueur.afficher();
            if(commande.getSecondMot().equalsIgnoreCase("informations"))
                joueur.informations();
        } else if(motCommande.equals("transporter")) {
            if (!pieceCourante.isTransportable(commande.getSecondMot())) {
                System.out.println("Cet objet n'est pas transportable");
            } else {
                MonObjet ob = pieceCourante.rechercher(commande.getSecondMot());
                if (ob != null) {
                    if (joueur.transporter(ob)) {
                        pieceCourante.supprimer(ob);
                        System.out.println("L'objet : " + ob.getNom() + " a été emporté avec succès ");
                        pieceCourante.afficher();
                    }
                } else {
                    System.out.println("Cet objet n'existe pas dans la pièce");
                }
            }

        } else if(motCommande.equals("déposer")) {
            String nomObjet = commande.getSecondMot();
            MonObjet ob = joueur.rechercher(nomObjet);
            if( ob != null) {
                joueur.deposer(ob);
                pieceCourante.ajouter(ob);
                System.out.println("vous avez déposé l'objet " + nomObjet + " dans la pièce" + pieceCourante.descriptionCourte());
                pieceCourante.afficher();
            } else {
                System.out.println("Vous ne possedez pas cet objet");
            }

        } else if(motCommande.equals("ma")) {
            mission.afficher();
        } else if (motCommande.equals("plan")) {
            mission.plan();

        }


    }


}
