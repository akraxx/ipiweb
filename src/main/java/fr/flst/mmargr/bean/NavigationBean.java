package fr.flst.mmargr.bean;

public class NavigationBean {
	
	public String toListUser() {
		return "/secured/user/list.xhtml?faces-redirect=true";
	}
	
	public String toAddUser() {
		return "/secured/user/add.xhtml?faces-redirect=true";
	}
	
	public String toListArticle() {
		return "/secured/article/listArticle.xhtml?faces-redirect=true";
	}
	
	public String toAddArticle() {
		return "/secured/article/addArticle.xhtml?faces-redirect=true";
	}
	
	public String toListLivraison() {
		return "/secured/livraison/listLivraison.xhtml?faces-redirect=true";
	}
	
	public String toAddRelai() {
		return "/secured/relai/addRelai.xhtml?faces-redirect=true";
	}

	public String toListRelai() {
		return "/secured/relai/listRelai.xhtml?faces-redirect=true";
	}
	
	public String toAddCodePromo() {
		return "/secured/promo/addCodePromo.xhtml?faces-redirect=true";
	}

	public String toListCodePromo() {
		return "/secured/promo/listCodePromo.xhtml?faces-redirect=true";
	}
	
	public String toListPaiement() {
		return "/secured/paiement/listPaiement.xhtml?faces-redirect=true";
	}
	
	public String toListCommande() {
		return "/secured/commande/listCommande.xhtml?faces-redirect=true";
	}
	
	public String toListStatistique() {
		return "/secured/statistique/listStatistique.xhtml?faces-redirect=true";
	}
}
