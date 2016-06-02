package userModel;

public class Administrateur extends User {
	
	protected int idAdmin;
	//public boolean creerEtudiant (String nomClasse, String login, String prenom, String nom, String motdepasse, int idEtudiant, int idGroupe);
	public boolean creerProf();
	public boolean creerAdmin();
	public boolean creerGroupe();
	public boolean associerEtudiantGroupe();
	public boolean supprimerUtilisateur();
	public boolean supprimerGroupe();
	
	public Administrateur(String login, String prenom, String nom, String motdepasse, int idAdmin) {
		super(login, prenom, nom, motdepasse);
		this.idAdmin = idAdmin;
	}
	
	public boolean creerEtudiant (String nomClasse, String login, String prenom, String nom, String motdepasse, int idEtudiant, int idGroupe){
		Etudiant etudiant = new Etudiant (login,prenom,nom,motdepasse,idEtudiant,idGroupe);
		return true;
	}
	
	public int getId() {
		return idAdmin;
	}
}
