package fr.flst.mmargr.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.flst.mmargr.dao.CodePromoDAO;
import fr.flst.mmargr.dao.DAOFactory;
import fr.flst.mmargr.model.CodePromo;
import fr.flst.mmargr.model.Enseigne;
import fr.flst.mmargr.model.TypePromotion;

public class CodePromoBean {
private static final Logger log = LoggerFactory.getLogger(RelaiBean.class);
	
	private CodePromoDAO codePromoDAO = DAOFactory.getCodePromoDAO();
	
	private CodePromo newCodePromo = new CodePromo();
	
	private CodePromo editCodePromo;
	
	private String newDateFin;
	
	private String newDateDebut;
	
	private String editDateFin;
	
	private String editDateDebut;
	
	private DataModel<CodePromo> codesPromoModel;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	private void initNewCodepromo() {
		newCodePromo = new CodePromo();
		newDateFin = dateFormat.format(new Date());
		newDateDebut = dateFormat.format(new Date());
	}
	
	private void refreshData() {
		refreshData(codePromoDAO.findAll());
	}
	
	@PostConstruct
	public void init() {
		log.debug("Init code promo bean");
		
		initNewCodepromo();
		if(codesPromoModel == null) {
			codesPromoModel = new ListDataModel<>();
			refreshData();
		}
    }
	
	public String createCodePromo() {
		String message = null;
		if(codePromoDAO.findById(newCodePromo.getId()) != null) {
			message = "L'id " + newCodePromo.getId() + " existe déja.";
		}
		try {
			newCodePromo.setDateDebut(dateFormat.parse(newDateDebut));
			newCodePromo.setDateFin(dateFormat.parse(newDateFin));
			if(newCodePromo.getDateDebut().after(newCodePromo.getDateFin())) {
				message = "La date de début est après la date de fin.";
			}
		} catch (ParseException e) {
			message = "Veuillez respecter le format de date.";
		}
		
		if(message != null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
			return null;
		}
		else {
			codePromoDAO.insert(newCodePromo);
			initNewCodepromo();
			refreshData();
			return "listCodePromo";
		}
	}
	
	public String deleteCodePromo() {
		CodePromo codePromo = codesPromoModel.getRowData();
		codePromoDAO.delete(codePromo);
		refreshData();
		return null;
	}
	
	public String editCodePromo() {
		editCodePromo = codesPromoModel.getRowData();
		editDateDebut = dateFormat.format(editCodePromo.getDateDebut());
		editDateFin = dateFormat.format(editCodePromo.getDateFin());
		return "editCodePromo";
	}
	
	public String updateCodePromo() {
		String message = null;
		
		CodePromo codePromoInDb = codePromoDAO.findById(editCodePromo.getId());
		if(codePromoInDb != null && codePromoInDb.getId() != editCodePromo.getId()) {
			message = "L'id " + editCodePromo.getId() + " existe déja.";
		}
		
		try {
			editCodePromo.setDateDebut(dateFormat.parse(editDateDebut));
			editCodePromo.setDateFin(dateFormat.parse(editDateFin));
			if(editCodePromo.getDateDebut().after(editCodePromo.getDateFin())) {
				message = "La date de début est après la date de fin.";
			}
		} catch (ParseException e) {
			message = "Veuillez respecter le format de date.";
		}
		
		if(message != null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
			return null;
		}
		else {
			codePromoDAO.update(editCodePromo);
			refreshData();
			return "listCodePromo";
		}
	}
	
	private void refreshData(List<CodePromo> codesPromo) {
		codesPromoModel.setWrappedData(codesPromo);
	}

	public CodePromo getNewCodePromo() {
		return newCodePromo;
	}

	public CodePromo getEditCodePromo() {
		return editCodePromo;
	}

	public DataModel<CodePromo> getCodesPromoModel() {
		return codesPromoModel;
	}
	
	public TypePromotion[] getTypePromotions() {
		return TypePromotion.values();
	}
	
	public Enseigne[] getEnseignes() {
		return Enseigne.values();
	}

	public String getNewDateFin() {
		return newDateFin;
	}

	public void setNewDateFin(String newDateFin) {
		this.newDateFin = newDateFin;
	}

	public String getNewDateDebut() {
		return newDateDebut;
	}

	public void setNewDateDebut(String newDateDebut) {
		this.newDateDebut = newDateDebut;
	}
	
	public String getEditDateFin() {
		return editDateFin;
	}

	public void setEditDateFin(String editDateFin) {
		this.editDateFin = editDateFin;
	}

	public String getEditDateDebut() {
		return editDateDebut;
	}

	public void setEditDateDebut(String editDateDebut) {
		this.editDateDebut = editDateDebut;
	}

	public int[] getPourcentages() {
		int[] pourcentages = new int[70];
		for(int i = 1; i <= 70; i++) {
			pourcentages[i-1] = i;
		}
		return pourcentages;
	}
	
}
