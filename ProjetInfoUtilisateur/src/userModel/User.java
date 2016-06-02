package userModel;

public class User {
	protected String login;
	protected String prenom;
	protected String nom;
	protected String motdepasse;
	
	
	public User(String login, String prenom, String nom, String motdepasse)
	{
		this.login = login;
		this.prenom = prenom;
		this.nom = nom;
		this.motdepasse = motdepasse;
	}
	
	public String getLogin() {
		return this.login;
	}
	
	public String getPrenom() {
		return this.prenom;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public String getMDP() {
		return this.motdepasse;
	}
}
