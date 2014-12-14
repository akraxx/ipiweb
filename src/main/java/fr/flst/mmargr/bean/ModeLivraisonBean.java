package fr.flst.mmargr.bean;

import javax.annotation.PostConstruct;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.flst.mmargr.dao.DAOFactory;
import fr.flst.mmargr.dao.ModeLivraisonDAO;
import fr.flst.mmargr.model.ModeLivraison;

public class ModeLivraisonBean {
	private ModeLivraisonDAO modeLivraisonDAO = DAOFactory.getModeLivraisonDAO();
	
	private static final Logger log = LoggerFactory.getLogger(ModeLivraisonBean.class);
	
	private DataModel<ModeLivraison> modesLivraisonModel;
	
	private void refreshData() {
		modesLivraisonModel.setWrappedData(modeLivraisonDAO.findAll());
	}
	
	@PostConstruct
	public void init() {
		log.debug("Init mode livraison bean");

		if(modesLivraisonModel == null) {
			modesLivraisonModel = new ListDataModel<>();
			refreshData();
		}
    }

	public DataModel<ModeLivraison> getModesLivraisonModel() {
		return modesLivraisonModel;
	}
	
	public String saveModeLivraison() {
		ModeLivraison modeLivraison = modesLivraisonModel.getRowData();
		modeLivraisonDAO.update(modeLivraison);
		log.debug("Modify mode livraison " + modeLivraison.getLabel() + " prices : " + modeLivraison.getCoutClassique() + " super : " +modeLivraison.getCoutSuper());
		return null;
	}
	
}
