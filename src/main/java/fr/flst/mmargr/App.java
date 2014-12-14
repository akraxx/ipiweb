package fr.flst.mmargr;

import java.util.Calendar;
import java.util.Date;

import fr.flst.mmargr.dao.ArticleDAO;
import fr.flst.mmargr.dao.CodePromoDAO;
import fr.flst.mmargr.dao.DAOFactory;
import fr.flst.mmargr.dao.RelaiDAO;
import fr.flst.mmargr.model.CodePromo;
import fr.flst.mmargr.model.Enseigne;
import fr.flst.mmargr.model.TypePromotion;

public class App {

	public static void main(String[] args) {
//		Test test = new Test();
//		User user = test.getCurrentUser();
//		if(user != null) {
//			System.out.println(user.getUsername() + " " + user.getPassword());
//		}
//		else {
//			System.out.println("User null");
//		}
		
//		User user = DAOFactory.getUserDAO().findById(7);
//		user.setLastname("Testqzdqzdqz");
//		DAOFactory.getUserDAO().update(user);
		//CreateSchema.initialize();
		ArticleDAO articleDAO = DAOFactory.getArticleDAO();
//		
//		Article article = new Article();
//		article.setDescriptionCourte("descriptionCourte");
//		article.setDescriptionLongue("descriptionLongue");
//		article.setEnseigne(Enseigne.IPIHardWare);
//		article.setImage("image");
//		article.setModele("modele");
//		article.setPrix(10.5);
//		article.setQuantite(500);
//		article.setReferenceArticle("ref");
//		article.setSuperClient(true);
		//articleDAO.insert(article);
		
//		article = articleDAO.findById(1);
//		
//		System.out.println(article.getEnseigne());
//		
//		article.setEnseigne(Enseigne.IPISoftWare);
//		
//		
		RelaiDAO relaiDAO = DAOFactory.getRelaiDAO();
//		
//		Relai relai = new Relai();
//		Adresse adresse = new Adresse();
//		
//		adresse.setCodePostal("59152");
//		adresse.setNumeroRue("nume");
//		adresse.setVille("Ville");
//		adresse.setComplement("Complément");
//		
//		relai.setNumeroRelai(155);
//		relai.setLabel("Relai");
//		relai.setAdresse(adresse);
		
		//relaiDAO.insert(relai);
		//articleDAO.update(article);
		
//		relai = relaiDAO.findById(3);
//		
//		relai.getAdresse().setCodePostal("59000");
//		
//		relaiDAO.update(relai);
		
		CodePromoDAO codePromoDAO = DAOFactory.getCodePromoDAO();
		
		CodePromo codePromo = new CodePromo();
		codePromo.setDateDebut(new Date());
		codePromo.setDateFin(new Date());
		codePromo.setEnseigne(Enseigne.IPISoftWare);
		codePromo.setTypePromotion(TypePromotion.REDCMD);
		codePromo.setId("A1");
		
		codePromo = codePromoDAO.findById("A1");
		
		Calendar cal = Calendar.getInstance();

	    cal.set(2015, 11, 15);
	    
	    Calendar cal2 = Calendar.getInstance();
	    
	    cal2.set(2014, 5, 30);

	    System.out.println(cal2.compareTo(cal));
	    codePromo.setDateFin(cal.getTime());
		
	    //codePromoDAO.update(codePromo);
	    
		System.out.println("SIZE CODE PROMO : " + codePromoDAO.findAll().size());
		System.out.println("SIZE RELAIS : " + relaiDAO.findAll().size());
		System.out.println("SIZE ARTICLES : " + articleDAO.findAll().size());
		System.out.println("SIZE USERS : " + DAOFactory.getUserDAO().findAllAdmins().size());
		
	}

}
