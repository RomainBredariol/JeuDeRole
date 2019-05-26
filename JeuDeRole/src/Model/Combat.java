package Model;

public class Combat {
	
	private int tour;
	private Joueur joueur;
	private Monstre monstre;
	private boolean estTermine;
	
	// un combat est constitué d'un joueur et d'un ou plusieurs monstres
	public Combat(Joueur joueur, Monstre monstre) {
		this.tour = 1;
		this.joueur = joueur;
		this.monstre = monstre;
		this.estTermine = false;
	}
	
	public boolean estTermine() {
		return this.estTermine;
	}
	
	public int getTour() {
		return this.tour;
	}
	
	// continuer déclenche un tour de combat, le joueur inflige des dégats à un monstre
	// le monstres inflige des dégats au joueur. retourne le descriptif du combat.
	public String continuer() {
		String message = "";
		int degats;
		degats = this.joueur.attaquer(this.monstre);
		message += "tour "+this.tour+" : "+this.joueur.getNom()+ " attaque "+this.monstre.getNom()+" et inflige "+degats;
		if (this.monstre.estVivant()) {
			degats = this.monstre.attaquer(this.joueur);
			message += ", "+this.monstre.getNom()+" riposte et inflige "+degats+".";
		}
		if (!this.joueur.estVivant()) {
			this.estTermine = true;
			message += ", Vous tombez sous les coups de "+this.monstre.getNom()+", c'est la fin...";
		}
		if (!this.monstre.estVivant() ) {
			this.estTermine = true;
			message += ", Vous terassez "+this.monstre.getNom()+", Victoire !!";
		}
		this.tour++;
		return message;
	}
	
	// fuir permet de terminer un combat, les monstres sont soignés complétement
	public String fuir() {
		this.estTermine = true;
		return "Vous fuyez le combat contre "+this.monstre.getNom()+", lâche !";
	}
}
