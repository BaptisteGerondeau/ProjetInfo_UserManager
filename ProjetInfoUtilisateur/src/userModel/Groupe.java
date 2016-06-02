package userModel;

public class Groupe {
	
	private int idGroupe;
	private int nbEtudiant;
	public String[] ensembleEtudiants = new String[nbEtudiant];
	
	public Groupe(int idGroupe, int nbEtudiant, String[] ensembleEtudiants) {
		this.idGroupe = idGroupe;
		this.nbEtudiant = nbEtudiant;
		this.ensembleEtudiants = ensembleEtudiants;
	}
	
	public Groupe(int idGroupe) {
		this.idGroupe = idGroupe;
		this.nbEtudiant = 0;
	}
	
	public int getId() {
		return idGroupe;
	}
	
	public int getNbEtudiants() {
		return nbEtudiant;
	}
	

}
