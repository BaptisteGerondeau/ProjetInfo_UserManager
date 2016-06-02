package userModel;

public class Administrateur extends User {
	
	protected int idAdmin;

	
	public Administrateur(String login, String prenom, String nom, String motdepasse, int idAdmin) {
		super(login, prenom, nom, motdepasse);
		this.idAdmin = idAdmin;
	}
	
	
	public int getId() {
		return idAdmin;
	}
	
	public String AdminToString() {
		return "\nid :"+this.getId()
					   +super.toString();
	}
}
