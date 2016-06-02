package userModel;

public class Professeur extends User {
	
	protected int idProf;
	
	public Professeur(String login, String prenom, String nom, String motdepasse, int idProf) {
		super(login, prenom, nom, motdepasse);
		this.idProf = idProf;
	}
	
	public int getId() {
		return idProf;
	}
	
	public String ProfToString() {
		return "\nid :"+this.getId()
					   +super.toString();
	}

}
