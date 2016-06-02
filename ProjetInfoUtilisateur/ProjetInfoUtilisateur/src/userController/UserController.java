package userController;

import userModel.*;
import java.util.*;
/**
 * Cette classe est le contrôleur d'utilisateurs que vous devez implémenter. 
 * Elle contient un attribut correspondant à la base de données utilisateurs que vous allez créer.
 * Elle contient toutes les fonctions de l'interface IUserController que vous devez implémenter.
 * 
 * @author Jose Mennesson (Mettre à jour)
 * @version 04/2016 (Mettre à jour)
 * 
 */

//TODO Classe à modifier

public class UserController implements IUserController
{
	
	/**
	 * Contient une instance de base de données d'utilisateurs
	 * 
	 */
	private UserDB userDB=null;
	
	
	/**
	 * Constructeur de controleur d'utilisateurs créant la base de données d'utilisateurs
	 * 
	 * @param userfile
	 * 		Fichier XML contenant la base de données d'utilisateurs
	 */
	public UserController(String userfile){
		UserDB userDB=new UserDB(userfile);
		this.setUserDB(userDB);
	}

	@Override
	public String getUserName(String userLogin) {
		Iterator i = this.getUserDB().getAdministrator().entrySet().iterator();
		while (i.hasNext()) {
			Map.Entry elem = (Map.Entry)i.next();
			Administrateur admin = (Administrateur) elem.getValue();
			if (admin.getLogin().equals(userLogin)) {
				return admin.getPrenom()+" "+admin.getNom();
			}
		}
	
		Iterator i2 = this.getUserDB().getStudents().entrySet().iterator();
		while (i2.hasNext()) {
			Map.Entry elem = (Map.Entry)i2.next();
			Etudiant etu = (Etudiant) elem.getValue();
			if (etu.getLogin().equals(userLogin)) {
				return etu.getPrenom()+" "+etu.getNom();
			}
		}	
			
		Iterator i3 = this.getUserDB().getProfessors().entrySet().iterator();
		while (i3.hasNext()) {
			Map.Entry elem = (Map.Entry)i3.next();
			Professeur prof = (Professeur) elem.getValue();
			if (prof.getLogin().equals(userLogin)) {
				return prof.getPrenom()+" "+prof.getNom();
			}
		}
		System.out.println("Cet utilisateur n'existe pas");
		return null;
	}

	@Override
	public String getUserClass(String userLogin, String userPwd) {
		
		Iterator i = this.getUserDB().getAdministrator().entrySet().iterator();
		while (i.hasNext()) {
			Map.Entry elem = (Map.Entry)i.next();
			Administrateur admin = (Administrateur) elem.getValue();
			if (admin.getLogin().equals(userLogin) && admin.getMDP().equals(userPwd)) {
				return "Administrator";
			}
		}
	
		Iterator i2 = this.getUserDB().getStudents().entrySet().iterator();
		while (i2.hasNext()) {
			Map.Entry elem = (Map.Entry)i2.next();
			Etudiant etu = (Etudiant) elem.getValue();
			if (etu.getLogin().equals(userLogin) && etu.getMDP().equals(userPwd)) {
				return "Student";
			}
		}	
			
		Iterator i3 = this.getUserDB().getProfessors().entrySet().iterator();
		while (i3.hasNext()) {
			Map.Entry elem = (Map.Entry)i3.next();
			Professeur prof = (Professeur) elem.getValue();
			if (prof.getLogin().equals(userLogin) && prof.getMDP().equals(userPwd)) {
				return "Professor";
			}
		}
		System.out.println("Cet utilisateur n'existe pas");
		return "";
	}

	@Override
	public int getStudentGroup(String studentLogin) {
		
		Iterator i2 = this.getUserDB().getStudents().entrySet().iterator();
		while (i2.hasNext()) {
			Map.Entry elem = (Map.Entry)i2.next();
			Etudiant etu = (Etudiant) elem.getValue();
			if (etu.getLogin().equals(studentLogin)) {
				return etu.getIdGroupe();
			}
		}
		System.out.println("Cet utilisateur n'existe pas");
		return -1;
	}
	
	public boolean checkLogin(String login, String type) {
		
		switch(type) {
		case "Administrateur" : {
			Iterator i = this.getUserDB().getAdministrator().entrySet().iterator();
			while (i.hasNext()) {
				Map.Entry elem = (Map.Entry)i.next();
				Administrateur admin = (Administrateur) elem.getValue();
				if (admin.getLogin().equals(login)) {
					return true;
				}
			}
			
			System.out.println("\nLogin incorrect : "+login);
			return false;
		}
		
		case "Etudiant" : {
			Iterator i = this.getUserDB().getStudents().entrySet().iterator();
			while (i.hasNext()) {
				Map.Entry elem = (Map.Entry)i.next();
				Etudiant admin = (Etudiant) elem.getValue();
				if (admin.getLogin().equals(login)) {
					return true;
				}
			}
			
			System.out.println("\nLogin incorrect : "+login);
			return false;
		}
		
		case "Professeur" : {
			Iterator i = this.getUserDB().getProfessors().entrySet().iterator();
			while (i.hasNext()) {
				Map.Entry elem = (Map.Entry)i.next();
				Professeur admin = (Professeur) elem.getValue();
				if (admin.getLogin().equals(login)) {
					return true;
				}
			}
			
			System.out.println("\nLogin incorrect : "+login);
			return false;
		}
		
		default : {
			System.out.println("You stupid ?\n");
			return false;
		}
		
		}
	}

	@Override
	public boolean addAdmin(String adminLogin, String newAdminlogin, int adminID, String firstname, String surname,
			String pwd) {
		
		if(checkLogin(adminLogin, "Administrateur") && !checkLogin(newAdminlogin, "Administrateur")) {
			this.getUserDB().getAdministrator().put(adminID, new Administrateur(newAdminlogin, firstname, surname, pwd, adminID));
			return true;
		}

		else {
			return false;
		}	
		
	}

	@Override
	public boolean addTeacher(String adminLogin, String newteacherLogin, int teacherID, String firstname,
			String surname, String pwd) {
		
		if(checkLogin(adminLogin, "Administrateur") && !checkLogin(newteacherLogin, "Professeur")) {
			this.getUserDB().getProfessors().put(teacherID, new Professeur(newteacherLogin, firstname, surname, pwd, teacherID));
			return true;
		}

		else {
			return false;
		}
	}

	@Override
	public boolean addStudent(String adminLogin, String newStudentLogin, int studentID, String firstname,
			String surname, String pwd) {

		if(checkLogin(adminLogin, "Administrateur") && !checkLogin(newStudentLogin, "Etudiant")) {
			this.getUserDB().getStudents().put(studentID, new Etudiant(newStudentLogin, firstname, surname, pwd, studentID, 0));
			return true;
		}

		else {
			return false;
		}
	}

	@Override
	public boolean removeUser(String adminLogin, String userLogin) {
		
		if(checkLogin(adminLogin, "Administrateur") && checkLogin(userLogin, "Etudiant")) {
			
			Iterator i2 = this.getUserDB().getStudents().entrySet().iterator();
			while (i2.hasNext()) {
				Map.Entry elem = (Map.Entry)i2.next();
				Etudiant etu = (Etudiant) elem.getValue();
				if (etu.getLogin().equals(userLogin)) {
					
					if(etu.getId() != 0) {
						Iterator i = this.getUserDB().getGroups().entrySet().iterator();
						while (i.hasNext()) {
							Map.Entry e = (Map.Entry)i.next();
							Groupe grp = (Groupe) e.getValue();
							if (grp.getId() == etu.getId()) {
								grp.rmvEtudiant(etu);
							}
						}
					}
					
					this.getUserDB().getStudents().remove(etu);
					return true;
				}
			}
			
			
		}
		
		else if(checkLogin(adminLogin, "Administrateur") && checkLogin(userLogin, "Administrateur")) {
			Iterator i = this.getUserDB().getAdministrator().entrySet().iterator();
			while (i.hasNext()) {
				Map.Entry elem = (Map.Entry)i.next();
				Administrateur admin = (Administrateur) elem.getValue();
				if (admin.getLogin().equals(userLogin)) {
					this.getUserDB().getAdministrator().remove(admin);
					return true;
				}
			}
		}
		
		else if(checkLogin(adminLogin, "Administrateur") && checkLogin(userLogin, "Professeur")) {
			Iterator i = this.getUserDB().getProfessors().entrySet().iterator();
			while (i.hasNext()) {
				Map.Entry elem = (Map.Entry)i.next();
				Professeur prof = (Professeur) elem.getValue();
				if (prof.getLogin().equals(userLogin)) {
					this.getUserDB().getProfessors().remove(prof);
					return true;
				}
			}
		}
		else {
			return false;
		}
		
		
		return false;
	}

	@Override
	public boolean addGroup(String adminLogin, int groupId) {
		
		if(checkLogin(adminLogin, "Administrateur") && this.getUserDB().getGroups().get(groupId)==null) {
			this.getUserDB().getGroups().put(groupId, new Groupe(groupId));
			return true;
		}
		return false;
	}

	@Override
	public boolean removeGroup(String adminLogin, int groupId) {
		if(checkLogin(adminLogin, "Administrateur") && this.getUserDB().getGroups().get(groupId)==null){
			Groupe grp = this.getUserDB().getGroups().get(groupId);
			Iterator i = this.getUserDB().getGroups().get(groupId).getListeEtudiants().iterator();
			while(i.hasNext()) {
				Map.Entry elem = (Map.Entry)i.next();
				Etudiant etu = (Etudiant) elem.getValue();
				etu.setGroupe(0);
			}
			
			this.getUserDB().getGroups().remove(groupId);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean associateStudToGroup(String adminLogin, String studentLogin, int groupId) {
		if(checkLogin(adminLogin, "Administrateur") && this.getUserDB().getGroups().get(groupId)==null
													&& checkLogin(studentLogin, "Etudiant")) {
			Groupe grp = this.getUserDB().getGroups().get(groupId);
			Etudiant etu = this.getUserDB().getStudents().get(studentLogin);
			
			etu.setGroupe(groupId);
			return grp.addEtudiant(etu);
		}
		return false;
	}

	@Override
	public String[] usersToString() {
        
		LinkedList<Etudiant> buffer=new LinkedList<Etudiant>();
        Iterator i = this.getUserDB().getStudents().entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry elem = (Map.Entry)i.next();
            buffer.add((Etudiant) elem);
        }
        String[] infoEtu = new String[buffer.size()];
        int j=0;
        for(Etudiant e : buffer)
        {
            infoEtu[j]=e.EtuToString();
            j++;
        }
        
        LinkedList<Professeur> bufferProf=new LinkedList<Professeur>();
        i = this.getUserDB().getProfessors().entrySet().iterator();
        while(i.hasNext()) {
        	Map.Entry elem = (Map.Entry)i.next();
        	bufferProf.add((Professeur) elem); 
        }
        String[] infoProf = new String[bufferProf.size()];
        j = 0;
        for(Professeur p : bufferProf) {
        	infoProf[j]=p.ProfToString();
        	j++;
        }
        
        LinkedList<Administrateur> bufferAdmin=new LinkedList<Administrateur>();
        i = this.getUserDB().getAdministrator().entrySet().iterator();
        while(i.hasNext()) {
        	Map.Entry elem = (Map.Entry)i.next();
        	bufferAdmin.add((Administrateur) elem); 
        }
        String[] infoAdmin = new String[bufferAdmin.size()];
        j = 0;
        for(Administrateur a : bufferAdmin) {
        	infoAdmin[j]=a.AdminToString();
        	j++;
        }
        
        //Concatenation des tableaux de Strings obtenus (thx Stack Overflow)
        	   int aLen = infoEtu.length;
        	   int bLen = infoProf.length;
        	   int cLen = infoAdmin.length;
        	   String[] Infos= new String[aLen+bLen+cLen];
        	   System.arraycopy(infoEtu, 0, Infos, 0, aLen);
        	   System.arraycopy(infoProf, 0, Infos, aLen, bLen);
        	   System.arraycopy(infoAdmin, 0, Infos, aLen+bLen, cLen);
        
		return Infos;
	}

	@Override
	public String[] usersLoginToString() {
		
		LinkedList<Etudiant> buffer=new LinkedList<Etudiant>();
        Iterator i = this.getUserDB().getStudents().entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry elem = (Map.Entry)i.next();
            buffer.add((Etudiant) elem);
        }
        String[] infoEtu = new String[buffer.size()];
        int j=0;
        for(Etudiant e : buffer)
        {
            infoEtu[j]=e.getLogin();
            j++;
        }
        
        LinkedList<Professeur> bufferProf=new LinkedList<Professeur>();
        i = this.getUserDB().getProfessors().entrySet().iterator();
        while(i.hasNext()) {
        	Map.Entry elem = (Map.Entry)i.next();
        	bufferProf.add((Professeur) elem); 
        }
        String[] infoProf = new String[bufferProf.size()];
        j = 0;
        for(Professeur p : bufferProf) {
        	infoProf[j]=p.getLogin();
        	j++;
        }
        
        LinkedList<Administrateur> bufferAdmin=new LinkedList<Administrateur>();
        i = this.getUserDB().getAdministrator().entrySet().iterator();
        while(i.hasNext()) {
        	Map.Entry elem = (Map.Entry)i.next();
        	bufferAdmin.add((Administrateur) elem); 
        }
        String[] infoAdmin = new String[bufferAdmin.size()];
        j = 0;
        for(Administrateur a : bufferAdmin) {
        	infoAdmin[j]=a.getLogin();
        	j++;
        }
        
        //Concatenation des tableaux de Strings obtenus (thx Stack Overflow)
        	   int aLen = infoEtu.length;
        	   int bLen = infoProf.length;
        	   int cLen = infoAdmin.length;
        	   String[] Infos= new String[aLen+bLen+cLen];
        	   System.arraycopy(infoEtu, 0, Infos, 0, aLen);
        	   System.arraycopy(infoProf, 0, Infos, aLen, bLen);
        	   System.arraycopy(infoAdmin, 0, Infos, aLen+bLen, cLen);
        
		return Infos;
	}

	@Override
	public String[] studentsLoginToString() {
		
		LinkedList<Etudiant> buffer=new LinkedList<Etudiant>();
        Iterator i = this.getUserDB().getStudents().entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry elem = (Map.Entry)i.next();
            buffer.add((Etudiant) elem);
        }
        String[] infoEtu = new String[buffer.size()];
        int j=0;
        for(Etudiant e : buffer)
        {
            infoEtu[j]=e.getLogin();
            j++;
        }
        
		return infoEtu;
	}

	@Override
	public String[] groupsIdToString() {
		
		LinkedList<Groupe> buffer=new LinkedList<Groupe>();
        Iterator i = this.getUserDB().getGroups().entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry elem = (Map.Entry)i.next();
            buffer.add((Groupe) elem);
        }
        String[] groupsID = new String[buffer.size()];
        int j=0;
        for(Groupe grp : buffer)
        {
            groupsID[j].valueOf(grp.getId());
            j++;
        }
		return groupsID;
	}

	@Override
	public String[] groupsToString() {
		LinkedList<Groupe> buffer=new LinkedList<Groupe>();
        Iterator i = this.getUserDB().getGroups().entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry elem = (Map.Entry)i.next();
            buffer.add((Groupe) elem);
        }
        String[] groupsInfo = new String[buffer.size()];
        int j=0;
        for(Groupe grp : buffer)
        {
            groupsInfo[j].toString();
            j++;
        }
        
		return groupsInfo;
	}

	@Override
	public boolean loadDB() {
		this.getUserDB().loadDB();
		return true;
	}

	@Override
	public boolean saveDB() {
		this.getUserDB().saveDB();
		return true;
	}

	public UserDB getUserDB() {
		return userDB;
	}

	public void setUserDB(UserDB userDB) {
		this.userDB = userDB;
	}
	
	

}

