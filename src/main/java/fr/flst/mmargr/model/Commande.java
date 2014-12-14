package fr.flst.mmargr.model;

// default package
// Generated 12 mai 2014 23:38:46 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Commande generated by hbm2java
 */
@Entity
@Table(name = "commande", catalog = "java")
public class Commande implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6421575126606391431L;
	private String numeroCommande;
	private Relai relai;
	private User user;
	private ModeLivraison modeLivraison;
	private boolean contreRemboursement;
	private double prixTotal;
	private boolean envoyee;
	private Set<LigneCommande> ligneCommandes = new HashSet<LigneCommande>(0);

	public Commande() {
	}

	public Commande(String numeroCommande, User user, ModeLivraison modeLivraison, boolean contreRemboursement, double prixTotal, boolean envoyee) {
		this.numeroCommande = numeroCommande;
		this.user = user;
		this.modeLivraison = modeLivraison;
		this.contreRemboursement = contreRemboursement;
		this.prixTotal = prixTotal;
		this.envoyee = envoyee;
	}

	public Commande(String numeroCommande, Relai relai, User user, ModeLivraison modeLivraison, boolean contreRemboursement, double prixTotal, boolean envoyee, Set<LigneCommande> ligneCommandes) {
		this.numeroCommande = numeroCommande;
		this.relai = relai;
		this.user = user;
		this.modeLivraison = modeLivraison;
		this.contreRemboursement = contreRemboursement;
		this.prixTotal = prixTotal;
		this.envoyee = envoyee;
		this.ligneCommandes = ligneCommandes;
	}

	@Id
	@Column(name = "numero_commande", unique = true, nullable = false, length = 10)
	public String getNumeroCommande() {
		return this.numeroCommande;
	}

	public void setNumeroCommande(String numeroCommande) {
		this.numeroCommande = numeroCommande;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_client", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_relai")
	public Relai getRelai() {
		return this.relai;
	}

	public void setRelai(Relai relai) {
		this.relai = relai;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_mode_livraison", nullable = false)
	public ModeLivraison getModeLivraison() {
		return this.modeLivraison;
	}

	public void setModeLivraison(ModeLivraison modeLivraison) {
		this.modeLivraison = modeLivraison;
	}

	@Column(name = "contre_remboursement", nullable = false)
	public boolean isContreRemboursement() {
		return this.contreRemboursement;
	}

	public void setContreRemboursement(boolean contreRemboursement) {
		this.contreRemboursement = contreRemboursement;
	}

	@Column(name = "prix_total", nullable = false, precision = 22, scale = 0)
	public double getPrixTotal() {
		return this.prixTotal;
	}

	public void setPrixTotal(double prixTotal) {
		this.prixTotal = prixTotal;
	}

	@Column(name = "envoyee", nullable = false)
	public boolean isEnvoyee() {
		return this.envoyee;
	}

	public void setEnvoyee(boolean envoyee) {
		this.envoyee = envoyee;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "commande")
	public Set<LigneCommande> getLigneCommandes() {
		return this.ligneCommandes;
	}

	public void setLigneCommandes(Set<LigneCommande> ligneCommandes) {
		this.ligneCommandes = ligneCommandes;
	}

}
