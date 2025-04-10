import java.util.*;

public class ChoixOptions {
	
	// associe le nom d'une option avec son objet Option correspondant
	private Map<String, Option> options;
	// ajouter ici les autres attributs
	private Map<Etudiant, List<Option>> preferences;
	
	//constructeur prenant un entier et une suite de string en param�tres
	//ces string repr�sentent les noms des diff�rentes options possibles
	public ChoixOptions(int nbEtudiantsParOption, String... nomsOption) {
		this.options = new HashMap<String, Option>();
		if (nomsOption.length < 3)
			throw new IllegalArgumentException();
		for (int i = 0; i < nomsOption.length; i++) {
			String nomOption = nomsOption[i];
			options.put(nomOption, new Option(nomOption, nbEtudiantsParOption));
		}
		// initialiser les nouveaux attributs

		this.preferences = new HashMap<Etudiant, List<Option>>();
	}

	// cette m�thode encode les pr�f�rences des �tudiants
	// il ne faut pas v�rifier que ces choix soient valides
	public void ajouterPreferences(Etudiant etu, String choix1, String choix2,
			String choix3) {
			List<Option> listeOptions = new LinkedList<Option>();
			listeOptions.add(options.get(choix1));
			listeOptions.add(options.get(choix2));
			listeOptions.add(options.get(choix3));
			preferences.put(etu, listeOptions);
		
	}

	// cette m�thode est appel�e apr�s que les �tudiants aient donn� leurs pr�f�rences
	// cette m�thode attribue les options aux �tudiants en favorisant les �tudiants 
	// ayant les meilleures moyennes si il n'y a plus de place disponible dans certaines 
	// options. Pour les �tudiants faibles, si les deux premi�res options sont pleines, 
	// il faut recourir au troisi�me choix.
	// Cette m�thode doit faire appel � la m�thode inscrireEtudiant de la classe Option.
	public void attribuerOptions() {
		List<Etudiant> etudiants = new ArrayList<>(preferences.keySet());
		etudiants.sort((e1, e2) -> Integer.compare(e2.getMoyenne(), e1.getMoyenne()));

		for (Etudiant etudiant : etudiants) {
			List<Option> listeOptions = preferences.get(etudiant);
			for (Option option : listeOptions) {
				if (option.inscrireEtudiant(etudiant)) {
					break;
				}
			}
		}
	}
	
	public String toString(){
		String s="";
		for (Option o:options.values()){
			s=s+o+"\n"+"-----------------"+"\n";
		}
		return s;
	}
}
