package fr.flst.mmargr.dao;

public class DAOFactory {

	public static UserDAO getUserDAO() {
		return UserDAO.getInstance();
	}
	
	public static ArticleDAO getArticleDAO() {
		return ArticleDAO.getInstance();
	}
	
	public static RelaiDAO getRelaiDAO() {
		return RelaiDAO.getInstance();
	}
	
	public static AdresseDAO getAdresseDAO() {
		return AdresseDAO.getInstance();
	}
	
	public static CodePromoDAO getCodePromoDAO() {
		return CodePromoDAO.getInstance();
	}
	
	public static ModeLivraisonDAO getModeLivraisonDAO() {
		return ModeLivraisonDAO.getInstance();
	}
	
	public static PaiementDAO getPaiementDAO() {
		return PaiementDAO.getInstance();
	}
	
	public static CommandeDAO getCommandeDAO() {
		return CommandeDAO.getInstance();
	}
}
