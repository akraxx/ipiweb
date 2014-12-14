package fr.flst.mmargr.bean;

import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.flst.mmargr.dao.DAOFactory;
import fr.flst.mmargr.dao.PaiementDAO;
import fr.flst.mmargr.model.Paiement;

public class PaiementBean {
	private PaiementDAO paiementDAO = DAOFactory.getPaiementDAO();
	
	private static final Logger log = LoggerFactory.getLogger(PaiementBean.class);
	
	private DataModel<Paiement> paiementsModel;
	
	private void refreshData() {
		paiementsModel.setWrappedData(paiementDAO.findAll());
	}
	
	@PostConstruct
	public void init() {
		log.debug("Init paiement bean bean");

		if(paiementsModel == null) {
			paiementsModel = new ListDataModel<>();
			refreshData();
		}
    }

	public DataModel<Paiement> getPaiementsModel() {
		return paiementsModel;
	}

	public String savePaiements() {
		Iterator<Paiement> iterator = paiementsModel.iterator();
		while(iterator.hasNext()) {
			Paiement paiement = iterator.next();
			paiementDAO.update(paiement);
			log.debug("Modify paiement " + paiement.getLabel() + " classique : " + paiement.isClientClassique() + " super : " +paiement.isSuperClient());
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Paiements sauvegardés", "INFO MSG"));
		return null;
	}
	
}
