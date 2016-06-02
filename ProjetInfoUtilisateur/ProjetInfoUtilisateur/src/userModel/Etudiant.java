package userModel;

public class Etudiant extends User {
	
	public int idEtudiant;
	public int idGroupe;
	
	public Etudiant (String login, String prenom, String nom, String motdepasse, int idEtudiant, int idGroupe)
	{
		super(login, prenom, nom, motdepasse);
		this.idEtudiant = idEtudiant;
		this.idGroupe = idGroupe;
	}
	
	public int getId() {
		return idEtudiant;
	}
	
	public int getIdGroupe() {
		return idGroupe;
	}
	
	public void setGroupe(int idGroupe) {
		this.idGroupe = idGroupe;
	}
	
	public String EtuToString() {
		return "\nid :"+this.getId()
					   +super.toString()
					   +"\nGroupId :"+getIdGroupe();
	}
}
