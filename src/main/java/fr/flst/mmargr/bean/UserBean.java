package fr.flst.mmargr.bean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.flst.mmargr.dao.DAOFactory;
import fr.flst.mmargr.dao.UserDAO;
import fr.flst.mmargr.model.User;
import fr.flst.mmargr.util.HibernateUtil;

public class UserBean {
	private static final int MAX_NUMBER_OF_ADMIN = 20;

	private static final Logger log = LoggerFactory.getLogger(UserBean.class);
	
	private UserDAO userDAO = DAOFactory.getUserDAO();

	private User newuser;
	
	private User editUser;
	
	private DataModel<User> usersModel;
	
	private void refreshData() {
		usersModel.setWrappedData(userDAO.findAllAdmins());
	}
	
	private boolean checkExistingUsername(String username) {
		if (userDAO.findByUsername(username) != null) {
			FacesMessage msg = new FacesMessage("Nom d'utilisateur déja existant : " + username, "ERROR MSG");
	        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        return true;
		}
		else {
			return false;
		}
	}
	
	public String createUser() {
		if(getCanAddNewAdmin()) {
			if(checkExistingUsername(newuser.getUsername())) return "addUser";
			newuser.setPassword(HibernateUtil.encrypt(newuser.getPassword()));
			userDAO.insert(newuser);
			newuser = new User();
			refreshData();
		}
		else {
			FacesMessage msg = new FacesMessage("La liste d'administrateur est pleine, nombre maximum atteint : " + MAX_NUMBER_OF_ADMIN, "ERROR MSG");
	        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        return "addUser";
		}
		return "listUser";
	}
	
	public String deleteUser() {
		User user = usersModel.getRowData();
		userDAO.delete(user);
		refreshData();
		return null;
	}
	
	public String editUser() {
		editUser = usersModel.getRowData();
		return "editUser";
	}
	
	public String updateUser() {
		editUser.setPassword(HibernateUtil.encrypt(editUser.getPassword()));
		userDAO.update(editUser);
		refreshData();
		return "listUser";
	}
	
	@PostConstruct
	public void init() {
		log.debug("Init user bean");
		
		newuser = new User();
		if(usersModel == null) {
			usersModel = new ListDataModel<>();
			refreshData();
		}
    }

	
	public DataModel<User> getUsersModel() {
		return usersModel;
	}

	public User getNewuser() {
		return newuser;
	}

	public void setNewuser(User newUser) {
		this.newuser = newUser;
	}
	
	
	public User getEditUser() {
		return editUser;
	}

	public String getRowClasses() {
	    StringBuilder rowClasses = new StringBuilder();

	    for (User user : userDAO.findAllAdmins()) {
	        if (rowClasses.length() > 0) {
	        	rowClasses.append(",");
	        }
        	rowClasses.append((user.isSuperadmin() ? "info" : "warn"));
	    }
	    log.debug("Row classes : " + rowClasses.toString());
	    return rowClasses.toString();
	}
	
	public boolean getCanAddNewAdmin() {
		return (userDAO.findAllAdmins().size() < MAX_NUMBER_OF_ADMIN);
	}
}
