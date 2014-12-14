package fr.flst.mmargr.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.flst.mmargr.dao.CommandeDAO;
import fr.flst.mmargr.dao.DAOFactory;
import fr.flst.mmargr.model.Commande;
import fr.flst.mmargr.model.LigneCommande;

public class CommandeBean {
	private CommandeDAO commandeDAO = DAOFactory.getCommandeDAO();
	
	private static final Logger log = LoggerFactory.getLogger(CommandeBean.class);
	
	private Commande commande = null;
	
	private List<LigneCommande> lignesCommandes = null;
	
	private String searchText = "";
	
	public String searchCommande() {
		if(searchText.length() > 0) {
			commande = commandeDAO.findNotSendedById(searchText);
			if(commande != null) {
				lignesCommandes = new ArrayList<>();
				lignesCommandes.addAll(commande.getLigneCommandes());
			}
		}
		
		return null;
	}
	
	public String envoieCommande() {
		log.debug("Envoie de la commande");
		if(commande != null) {
			commande.setEnvoyee(true);
			commandeDAO.update(commande);
			commande = null;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Commande envoyée avec succès", "INFO MSG"));
		}
		return null;
	}
	
	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public List<LigneCommande> getLignesCommandes() {
		return lignesCommandes;
	}

	public void setLignesCommandes(List<LigneCommande> lignesCommandes) {
		this.lignesCommandes = lignesCommandes;
	}

}
