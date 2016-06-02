package userModel;

import java.io.*;
import java.util.*;
import java.util.logging.*;
import org.jdom2.*;
import org.jdom2.input.*;
import org.jdom2.output.*;

/**
 * 
 * Cette classe gére la base de données d'utilisateurs. Elle doit permettre de sauvegarder et charger les utilisateurs et les groupes à partir d'un fichier XML. 
 * La structure du fichier XML devra être la même que celle du fichier userDB.xml.
 * @see <a href="../../userDB.xml">userDB.xml</a> 
 * 
 * @author Jose Mennesson (Mettre à jour)
 * @version 04/2016 (Mettre à jour)
 * 
 */

//TODO Classe à modifier

public class UserDB {
	
	/**
	 * 
	 * Le fichier contenant la base de données.
	 * 
	 */
	private String file;
	private HashMap <Integer, Etudiant> student = new HashMap<Integer, Etudiant> ();
	protected HashMap <Integer, Professeur> professor = new HashMap<Integer, Professeur> ();
	protected HashMap <Integer, Administrateur> admin = new HashMap<Integer, Administrateur> ();
	private HashMap <Integer, Groupe> group = new HashMap<Integer, Groupe> ();
	
	/**
	 * 
	 * Constructeur de UserDB. 
	 * 
	 * !!!!!!!!!!!! PENSEZ À AJOUTER UN ADMINISTRATEUR (su par exemple) QUI VOUS PERMETTRA DE CHARGER LA BASE DE DONNÉES AU DEMARRAGE DE L'APPLICATION !!!!!!
	 * 
	 * @param file
	 * 		Le nom du fichier qui contient la base de données.
	 */
	public UserDB(String file){
		//TODO Fonction à modifier
		super();
		this.setFile(file);
	}
	
	protected org.jdom2.Element getXMLDocument() throws JDOMException, IOException
	{
		return new SAXBuilder().build(new File(this.getFile())).getRootElement();
	}
	
	protected List <org.jdom2.Element> getStudentsXML(org.jdom2.Element racine)
	{
		return racine.getChild("Students").getChildren();
	}
	protected List <org.jdom2.Element> getGroupsXML(org.jdom2.Element racine)
	{
		return racine.getChild("Groups").getChildren();
	}
	protected List <org.jdom2.Element> getTeachersXML(org.jdom2.Element racine)
	{
		return racine.getChild("Teachers").getChildren();
	}
	protected List <org.jdom2.Element> getAdminsXML(org.jdom2.Element racine)
	{
		return racine.getChild("Administrators").getChildren();
	}
	
	
	protected void loadGroups(org.jdom2.Element racine)
	{
		List <org.jdom2.Element> Groups = this.getGroupsXML(racine);
		Groupe GroupBuffer;
		for(org.jdom2.Element g : Groups)
		{
			GroupBuffer=new Groupe(Integer.parseInt(g.getChildText("groupId")));
			this.getGroups().put(GroupBuffer.getId(),GroupBuffer);
		}
	}
	protected void loadAdmins(org.jdom2.Element racine)
	{
		List <org.jdom2.Element> Admins = this.getAdminsXML(racine);
		Administrateur adminBuffer;
		for(org.jdom2.Element a : Admins)
		{
			adminBuffer=new Administrateur(a.getChildText("login"),
								 		   a.getChildText("firstname"),
								 		   a.getChildText("surname"),
								 		   a.getChildText("pwd"),
								 		   Integer.parseInt(a.getChildText("adminId")));
								 		   
			this.getAdministrator().put(adminBuffer.getId(),adminBuffer);
		}
	}
	protected void loadProfs  (org.jdom2.Element racine)
	{
		List <org.jdom2.Element> Profs = this.getTeachersXML(racine);
		Professeur profBuffer;
		for(org.jdom2.Element a : Profs)
		{
			profBuffer= new Professeur(a.getChildText("login"),
								 a.getChildText("firstname"),
								 a.getChildText("surname"),
								 a.getChildText("pwd"),
								 Integer.parseInt(a.getChildText("teacherId")));
								 
			this.getProfessors().put(profBuffer.getId(),profBuffer);
		}
	}
	protected void loadStuds  (org.jdom2.Element racine)
	{
		List <org.jdom2.Element> Studs = this.getStudentsXML(racine);
		Etudiant StudBuffer;
		for(org.jdom2.Element s : Studs)
		{
			StudBuffer= new Etudiant (s.getChildText("login"),
									 s.getChildText("firstname"),
									 s.getChildText("surname"),
									 s.getChildText("pwd"),
									 Integer.parseInt(s.getChildText("studentId")),
									 Integer.parseInt(s.getChildText("groupId")));
			this.getStudents().put(StudBuffer.getId(),StudBuffer);
		}
	}
	
	
	public void loadDB()
	{
		try {
			org.jdom2.Element doc = this.getXMLDocument();
			this.loadGroups(doc);
			this.loadProfs(doc);
			this.loadStuds(doc);
			this.loadAdmins(doc);
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
        
        
        public org.jdom2.Element groupToXML()
        {
            org.jdom2.Element ret = new org.jdom2.Element("Groups");
            Iterator i = this.getGroups().entrySet().iterator();
            while (i.hasNext()) {
                Map.Entry elem = (Map.Entry)i.next();
                Groupe g=(Groupe) elem.getValue();
                ret.addContent(new org.jdom2.Element("Group")
                                    .addContent(new org.jdom2.Element("groupId")
                                    .addContent(String.valueOf(g.getId()))));
            }
            return ret;
        }
        public org.jdom2.Element studentToXML()
        {
            org.jdom2.Element ret = new org.jdom2.Element("Students");
            Iterator i = this.getStudents().entrySet().iterator();
            while (i.hasNext()) {
                Map.Entry elem = (Map.Entry)i.next();
                org.jdom2.Element studElement=new org.jdom2.Element("Student");
                Etudiant etu=(Etudiant) elem.getValue();
                if(etu instanceof Etudiant)
                {
                    
                    studElement.addContent(new org.jdom2.Element("login")
                               .addContent(etu.getLogin()));
                    studElement.addContent(new org.jdom2.Element("firstname")
                               .addContent(etu.getPrenom()));
                    studElement.addContent(new org.jdom2.Element("surname")
                               .addContent(etu.getNom()));
                    studElement.addContent(new org.jdom2.Element("pwd")
                               .addContent(etu.getMDP()));
                    studElement.addContent(new org.jdom2.Element("studentId")
                               .addContent(String.valueOf(etu.getId())));
                    if(etu.getIdGroupe()!=-1)
                    {
                        studElement.addContent(new org.jdom2.Element("groupId")
                                   .addContent(String.valueOf(etu.getIdGroupe())));
                    }
                    else
                    {
                        studElement.addContent(new org.jdom2.Element("groupId")
                                   .addContent("-1")); 
                    }
                    ret.addContent(studElement);
                }
            }
            return ret;
        }
        public org.jdom2.Element teacherToXLM()
        {
            org.jdom2.Element ret = new org.jdom2.Element("Teachers");
            Iterator i = this.getProfessors().entrySet().iterator();
            while (i.hasNext()) {
                org.jdom2.Element profElement=new org.jdom2.Element("Teacher");
                Map.Entry elem = (Map.Entry)i.next();
                Professeur prof=(Professeur) elem.getValue();
                if(prof instanceof Professeur)
                {
                    profElement.addContent(new org.jdom2.Element("login")
                               .addContent(prof.getLogin()));
                    profElement.addContent(new org.jdom2.Element("firstname")
                               .addContent(prof.getPrenom()));
                    profElement.addContent(new org.jdom2.Element("surname")
                               .addContent(prof.getNom()));
                    profElement.addContent(new org.jdom2.Element("pwd")
                               .addContent(prof.getMDP()));
                    profElement.addContent(new org.jdom2.Element("teacherId")
                               .addContent(String.valueOf(prof.getId())));
                    ret.addContent(profElement);   
                }
            }
            return ret;
        }
        public org.jdom2.Element adminToXLM()
        {
            org.jdom2.Element ret = new org.jdom2.Element("Administrators");
            Iterator i = this.getAdministrator().entrySet().iterator();
            while (i.hasNext()) {
                Map.Entry elem = (Map.Entry)i.next();
                org.jdom2.Element adminElement=new org.jdom2.Element("Administrator");
                Administrateur admin =(Administrateur) elem.getValue();
                if(admin instanceof Administrateur)
                {
                    adminElement.addContent(new org.jdom2.Element("login")
                                .addContent(admin.getLogin()));
                    adminElement.addContent(new org.jdom2.Element("firstname")
                                .addContent(admin.getPrenom()));
                    adminElement.addContent(new org.jdom2.Element("surname")
                                .addContent(admin.getNom()));
                    adminElement.addContent(new org.jdom2.Element("pwd")
                                .addContent(admin.getMDP()));
                    adminElement.addContent(new org.jdom2.Element("adminId")
                                .addContent(String.valueOf(admin.getId())));
                    ret.addContent(adminElement);   
                }
            }
            return ret;
        }
        public void saveDB()
        {
            try {
                Document doc=new  Document();
                org.jdom2.Element root= new org.jdom2.Element("UsersDB");
                doc.setRootElement(root);
                root.addContent(this.groupToXML());
                root.addContent(this.studentToXML());
                root.addContent(this.teacherToXLM());
                root.addContent(this.adminToXLM());
                XMLOutputter outter=new XMLOutputter();
                outter.setFormat(Format.getPrettyFormat());
                outter.output(doc, new FileWriter(new File("test.xml")));
            } catch (IOException ex) {
                Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
	
	/**
	 * Getter de file
	 * 
	 * @return 
	 * 		Le nom du fichier qui contient la base de données.
	 */
	
	public String getFile() {
		return file;
	}
	
	public Map<Integer, Etudiant> getStudents() {
		return this.student;
	}
	
	public Map<Integer, Professeur> getProfessors() {
		return this.professor;
	}
	
	public Map<Integer, Administrateur> getAdministrator() {
		return this.admin;
	}
	
	public Map<Integer, Groupe> getGroups() {
		return this.group;
	}
	
	
	
	/**
	 * Setter de file
	 * 
	 * @param file
	 * 		Le nom du fichier qui contient la base de données.
	 */
	
	public void setFile(String file) {
		this.file = file;
	}
}
