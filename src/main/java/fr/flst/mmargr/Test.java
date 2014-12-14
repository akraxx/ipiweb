package fr.flst.mmargr;

import javax.inject.Inject;

import fr.flst.mmargr.annotation.LoggedIn;
import fr.flst.mmargr.model.User;

public class Test {
	@Inject @LoggedIn User currentUser;
	
	public Test() {
		// TODO Auto-generated constructor stub
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
}
