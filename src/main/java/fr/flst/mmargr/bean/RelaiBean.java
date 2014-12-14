package fr.flst.mmargr.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.flst.mmargr.dao.DAOFactory;
import fr.flst.mmargr.dao.RelaiDAO;
import fr.flst.mmargr.model.Adresse;
import fr.flst.mmargr.model.Filter;
import fr.flst.mmargr.model.Relai;

public class RelaiBean {
	private static final Logger log = LoggerFactory.getLogger(RelaiBean.class);
	
	private RelaiDAO relaiDAO = DAOFactory.getRelaiDAO();
	
	private Relai newRelai = new Relai();
	
	private Relai editRelai;
	
	private Filter[] filters = new Filter[3];
	
	private int selectedFilter;
	
	private DataModel<Relai> relaisModel;
	
	private void initNewRelai() {
		newRelai = new Relai();
		newRelai.setAdresse(new Adresse());
		newRelai.setNumeroRelai(RelaiBean.generateReference());
	}
	
	private void refreshData() {
		refreshData(relaiDAO.findAll());
	}
	
	private void refreshData(List<Relai> relais) {
		relaisModel.setWrappedData(relais);
	}
	
	@PostConstruct
	public void init() {
		log.debug("Init relai bean");
		
		initNewRelai();
		if(relaisModel == null) {
			relaisModel = new ListDataModel<>();
			refreshData();
		}
		
		filters[0] = new Filter("Numéro de relai", 0);
		filters[1] = new Filter("Nom du relai", 1);
		filters[2] = new Filter("Ville", 2);
    }

	public String createRelai() {
		relaiDAO.insert(newRelai);
		initNewRelai();
		refreshData();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Relai ajouté avec succès", "INFO MSG"));
		return "listRelai";
	}
	
	public String deleteRelai() {
		Relai relai = relaisModel.getRowData();
		relaiDAO.disactive(relai);
		refreshData();
		return null;
	}
	
	public String editRelai() {
		editRelai = relaisModel.getRowData();
		return "editRelai";
	}
	
	public String updateRelai() {
		relaiDAO.update(editRelai);
		refreshData();
		return "listRelai";
	}
	
	public Relai getNewRelai() {
		return newRelai;
	}

	public void setNewRelai(Relai newRelai) {
		this.newRelai = newRelai;
	}

	public Relai getEditRelai() {
		return editRelai;
	}

	public void setEditRelai(Relai editRelai) {
		this.editRelai = editRelai;
	}

	public Filter[] getFilters() {
		return filters;
	}

	public void setFilters(Filter[] filters) {
		this.filters = filters;
	}

	public int getSelectedFilter() {
		return selectedFilter;
	}

	public void setSelectedFilter(int selectedFilter) {
		this.selectedFilter = selectedFilter;
	}
	
	public DataModel<Relai> getRelaisModel() {
		return relaisModel;
	}
	
	public String sortRelais() {
		log.debug("Sort relai by " + selectedFilter);
		
		List<Relai> currentDisplayedRelais = new ArrayList<>();
		Iterator<Relai> iterator = relaisModel.iterator();
		while(iterator.hasNext()) {
			currentDisplayedRelais.add(iterator.next());
		}
		
		switch(selectedFilter) {
			case 0:
				Collections.sort(currentDisplayedRelais, new Comparator<Relai>() {
				    public int compare(Relai one, Relai other) {
			    		return Integer.compare(one.getNumeroRelai(), other.getNumeroRelai());
				    }
				});
				break;
			case 1:
				Collections.sort(currentDisplayedRelais, new Comparator<Relai>() {
				    public int compare(Relai one, Relai other) {
			    		return one.getLabel().compareTo(other.getLabel());
				    }
				});
				break;
			case 2:
				Collections.sort(currentDisplayedRelais, new Comparator<Relai>() {
				    public int compare(Relai one, Relai other) {
			    		return one.getAdresse().getVille().compareTo(other.getAdresse().getVille());
				    }
				});
				break;
		}
		
		refreshData(currentDisplayedRelais);
		return null;
	}
	public String generateReferenceNewRelai() {
		newRelai.setNumeroRelai(generateReference());
		return null;
	}
	
	public String generateReferenceEditRelai() {
		editRelai.setNumeroRelai(generateReference());
		return null;
	}
	
	public static int generateReference() {
		RelaiDAO relaiDAO = DAOFactory.getRelaiDAO();
		int reference;
		do {
			reference = (int)(Math.random() * 99999)+1000;
		}
		while(relaiDAO.findByReference(reference) != null);
		
		return reference;
	}
}
