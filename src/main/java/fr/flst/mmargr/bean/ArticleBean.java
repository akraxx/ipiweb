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

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.flst.mmargr.dao.ArticleDAO;
import fr.flst.mmargr.dao.DAOFactory;
import fr.flst.mmargr.model.Article;
import fr.flst.mmargr.model.Enseigne;
import fr.flst.mmargr.model.Filter;

public class ArticleBean {
	private static final Logger log = LoggerFactory.getLogger(ArticleBean.class);
	
	private ArticleDAO articleDAO = DAOFactory.getArticleDAO();

	private Article newArticle;
	
	private Article editArticle;
	
	private DataModel<Article> articlesModel;
	
	private Filter[] filters = new Filter[3];
	
	private int selectedFilter;
	
	private String searchText;
	
	private boolean ascDescription = false;
	
	private boolean ascPrix = false;
	
	private void initNewArticle() {
		newArticle = new Article();
		newArticle.setReferenceArticle(ArticleBean.generateReference());
	}
	
	private void refreshData() {
		refreshData(articleDAO.findAll());
	}
	
	private void refreshData(List<Article> articles) {
		articlesModel.setWrappedData(articles);
	}

	public Article getNewArticle() {
		return newArticle;
	}

	public Article getEditArticle() {
		return editArticle;
	}

	public DataModel<Article> getArticlesModel() {
		return articlesModel;
	}
	
	@PostConstruct
	public void init() {
		log.debug("Init user bean");
		
		initNewArticle();
		if(articlesModel == null) {
			articlesModel = new ListDataModel<>();
			refreshData();
		}
		
		filters[0] = new Filter("Référence article", 0);
		filters[1] = new Filter("Description courte", 1);
		filters[2] = new Filter("Prix (€)", 2);
    }
	
	public String createArticle() {
		articleDAO.insert(newArticle);
		initNewArticle();
		refreshData();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Article ajouté avec succès", "INFO MSG"));
		return "listArticle";
	}
	
	public String deleteArticle() {
		Article article = articlesModel.getRowData();
		articleDAO.disactive(article);
		refreshData();
		return null;
	}
	
	public String editArticle() {
		editArticle = articlesModel.getRowData();
		return "editArticle";
	}
	
	public String updateArticle() {
		articleDAO.update(editArticle);
		refreshData();
		return "listArticle";
	}
	
	public String searchArticle() {
		switch(selectedFilter) {
			case 0:
				refreshData(articleDAO.findLikeReference(searchText));
				break;
			case 1:
				refreshData(articleDAO.findLikeDescriptionCourte(searchText));
				break;
			case 2:
				if(NumberUtils.isNumber(searchText)) {
					refreshData(articleDAO.findLikePrix(Double.valueOf(searchText)));
				}
				break;
		}
		log.debug("Selected filter " + selectedFilter + " with text :" + searchText);
		return null;
	}
	
	public String sortByDescription() {
		List<Article> currentDisplayedArticles = new ArrayList<>();
		Iterator<Article> iterator = articlesModel.iterator();
		while(iterator.hasNext()) {
			currentDisplayedArticles.add(iterator.next());
		}
		
		Collections.sort(currentDisplayedArticles, new Comparator<Article>() {
		    public int compare(Article one, Article other) {
		    	if(!ascDescription) {
		    		return one.getDescriptionCourte().compareTo(other.getDescriptionCourte());
		    	}
		    	else {
		    		return other.getDescriptionCourte().compareTo(one.getDescriptionCourte());
		    	}
		    }
		}); 
		
		refreshData(currentDisplayedArticles);
		ascDescription = !ascDescription;
		
		return null;
	}
	
	public String sortByPrice() {
		List<Article> currentDisplayedArticles = new ArrayList<>();
		Iterator<Article> iterator = articlesModel.iterator();
		while(iterator.hasNext()) {
			currentDisplayedArticles.add(iterator.next());
		}
		
		Collections.sort(currentDisplayedArticles, new Comparator<Article>() {
		    public int compare(Article one, Article other) {
		    	if(!ascPrix) {
		    		return Double.compare(one.getPrix(), other.getPrix());
		    	}
		    	else {
		    		return Double.compare(other.getPrix(), one.getPrix());
		    	}
		    }
		}); 
		
		refreshData(currentDisplayedArticles);
		ascPrix = !ascPrix;
		
		return null;
	}
	
	public Enseigne[] getEnseignes() {
		return Enseigne.values();
	}

	public int getSelectedFilter() {
		return selectedFilter;
	}

	public void setSelectedFilter(int selectedFilter) {
		this.selectedFilter = selectedFilter;
	}

	public Filter[] getFilters() {
		return filters;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	
	public String generateReferenceNewArticle() {
		newArticle.setReferenceArticle(generateReference());
		return null;
	}
	
	public String generateReferenceEditArticle() {
		editArticle.setReferenceArticle(generateReference());
		return null;
	}
	
	public static String generateReference() {
		ArticleDAO articleDAO = DAOFactory.getArticleDAO();
		String reference;
		do {
			int x = (int)(Math.random() * 99999999)+10000000;
			reference = String.valueOf(x);
		}
		while(articleDAO.findByReference(reference) != null);
		
		return reference;
	}
}
