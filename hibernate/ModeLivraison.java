// default package
// Generated 13 mai 2014 00:11:51 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ModeLivraison generated by hbm2java
 */
@Entity
@Table(name = "mode_livraison", catalog = "java")
public class ModeLivraison implements java.io.Serializable {

	private Integer id;
	private String label;
	private double coutClassique;
	private double coutSuper;
	private Set<Commande> commandes = new HashSet<Commande>(0);

	public ModeLivraison() {
	}

	public ModeLivraison(String label, double coutClassique, double coutSuper) {
		this.label = label;
		this.coutClassique = coutClassique;
		this.coutSuper = coutSuper;
	}

	public ModeLivraison(String label, double coutClassique, double coutSuper, Set<Commande> commandes) {
		this.label = label;
		this.coutClassique = coutClassique;
		this.coutSuper = coutSuper;
		this.commandes = commandes;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "label", nullable = false)
	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Column(name = "cout_classique", nullable = false, precision = 22, scale = 0)
	public double getCoutClassique() {
		return this.coutClassique;
	}

	public void setCoutClassique(double coutClassique) {
		this.coutClassique = coutClassique;
	}

	@Column(name = "cout_super", nullable = false, precision = 22, scale = 0)
	public double getCoutSuper() {
		return this.coutSuper;
	}

	public void setCoutSuper(double coutSuper) {
		this.coutSuper = coutSuper;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "modeLivraison")
	public Set<Commande> getCommandes() {
		return this.commandes;
	}

	public void setCommandes(Set<Commande> commandes) {
		this.commandes = commandes;
	}

}
