package fr.flst.mmargr.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.flst.mmargr.annotation.LoggedIn;
import fr.flst.mmargr.dao.DAOFactory;
import fr.flst.mmargr.dao.UserDAO;
import fr.flst.mmargr.model.User;
import fr.flst.mmargr.util.HibernateUtil;

@SessionScoped
public class LoginBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7840053491556934413L;
	
	private String username;
    private String password;
	

	private User user;
	
	private static final Logger log = LoggerFactory.getLogger(UserDAO.class);
	
	public String login() {

		UserDAO userDAO = DAOFactory.getUserDAO();
		log.debug("User dao initialized");
		log.debug("Credentials " + username + " : " + password) ;
		FacesMessage facesMessage = null; 
		

		
		User result = userDAO.findByUsernameAndPassword(username, HibernateUtil.encrypt(password));
		if (result != null) {
			user = result;
		}
		else {
			facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mauvais nom d'utilisateur/mot de passe", "ERROR MSG");
		}
		
		if(facesMessage != null) {
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			return "/login.xhtml";
		}
		else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Login successfully", "INFO MSG"));
	        return "/secured/welcome.xhtml";
		}
	}

	public String logout() {
		FacesMessage msg = new FacesMessage("Logout successfully", "INFO MSG");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);
		user = null;
		return "/login.xhtml";
	}

	public boolean isLoggedIn() {
		return user != null;
	}

	@Produces
	@LoggedIn
	User getCurrentUser() {
		return user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
