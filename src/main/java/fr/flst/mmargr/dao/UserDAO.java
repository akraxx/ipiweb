package fr.flst.mmargr.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.flst.mmargr.model.User;
import fr.flst.mmargr.util.HibernateUtil;

public class UserDAO {
	private static final Logger log = LoggerFactory.getLogger(UserDAO.class);
	
	private static UserDAO instance;
	
	private UserDAO() {
		
	}
	
	public static UserDAO getInstance() {
		if(instance == null) {
			instance = new UserDAO();
		}
		
		return instance;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		log.debug("getting all User instances with");
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			List<User> list = session.createCriteria(User.class).list();
			session.close();
			return list;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public User findById(Integer id) {
		log.debug("getting User instance with id: " + id);
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			
			User instance = (User)session.get(User.class, id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			session.close();
			
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public User findByUsernameAndPassword(String username, String password) {
		log.debug("getting User instance with username: " + username + " and password: " + password);
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			
			Criteria queryCriteria = session.createCriteria(User.class);
			queryCriteria.setMaxResults(1);
			
			Query query = session.createQuery("from User where username = :username and password = :password");
			query.setParameter("username", username);
			query.setParameter("password", password);
			User instance = (User)query.uniqueResult();
			
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			session.close();
			
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public User findByUsername(String username) {
		log.debug("getting User instance with username: " + username);
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			
			Criteria queryCriteria = session.createCriteria(User.class);
			queryCriteria.setMaxResults(1);
			
			Query query = session.createQuery("from User where username = :username");
			query.setParameter("username", username);
			User instance = (User)query.uniqueResult();
			
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			session.close();
			
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public void insert(User user) {
		log.debug("persisting User instance");
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
	
	public void delete(User user) {
		log.debug("deleting User instance " + user.getId());
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(user);
			session.getTransaction().commit();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
	
	public User update(User user) {
		log.debug("updating User instance " + user.getId());
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.merge(user);
			session.getTransaction().commit();
			log.debug("update successful");
			return user;
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findAllAdmins() {
		log.debug("getting all admin instances");
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			
			Criteria queryCriteria = session.createCriteria(User.class);
			queryCriteria.setMaxResults(1);
			
			Query query = session.createQuery("from User where admin = :admin order by superadmin desc");
			query.setParameter("admin", true);
			
			List<User> list = query.list();
			session.close();
			
			return list;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
