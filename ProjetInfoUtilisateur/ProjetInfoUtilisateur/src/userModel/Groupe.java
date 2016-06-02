package userModel;

import java.util.ArrayList;
import java.util.List;

public class Groupe {
	
	protected int idGroupe;
	protected List<Etudiant> listeEtudiants = new ArrayList<>();
	
	public Groupe(int idGroupe, int nbEtudiant, List<Etudiant> ensembleEtudiants) {
		this.idGroupe = idGroupe;
		this.listeEtudiants = ensembleEtudiants;
	}
	
	public Groupe(int idGroupe) {
		this.idGroupe = idGroupe;
	}
	
	public int getId() {
		return idGroupe;
	}
	
	public int getNbEtudiants() {
		return listeEtudiants.size();
	}
	
	public List<Etudiant> getListeEtudiants() {
		return listeEtudiants;
	}
	
	public boolean addEtudiant(Etudiant etudiant) {
		return listeEtudiants.add(etudiant);
	}
	
	public boolean rmvEtudiant(Etudiant etudiant) {
		return listeEtudiants.remove(etudiant);
	}
	
	public String toString() {
		String GroupToString = new String();
		
		GroupToString += ("\nID : "+GroupToString.valueOf(this.getId())+"\nNb Students : "+GroupToString.valueOf(this.getNbEtudiants()));
		for (Etudiant etu : listeEtudiants) {
			GroupToString += "\n";
			GroupToString += etu.EtuToString();
		}
		
		return GroupToString;
	}

}
