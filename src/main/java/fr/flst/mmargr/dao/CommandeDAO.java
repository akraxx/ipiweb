package fr.flst.mmargr.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.flst.mmargr.model.Commande;
import fr.flst.mmargr.util.HibernateUtil;

public class CommandeDAO {
	private static final Logger log = LoggerFactory.getLogger(CommandeDAO.class);
	
	private static CommandeDAO instance;
	
	private CommandeDAO() {
		
	}
	
	public static CommandeDAO getInstance() {
		if(instance == null) {
			instance = new CommandeDAO();
		}
		
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public List<Commande> findAll() {
		log.debug("getting all Commande instances");
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from Commande");
			
			List<Commande> list = query.list();
			session.close();
			return list;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public Commande findNotSendedById(String id) {
		log.debug("getting Commande instance with id: " + id);
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from Commande where envoyee = 0");
			Commande instance = (Commande)query.uniqueResult();
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
	
	public Commande findById(String id) {
		log.debug("getting Commande instance with id: " + id);
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			
			Commande instance = (Commande)session.get(Commande.class, id);
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

	
	public void insert(Commande instance) {
		log.debug("persisting Commande instance");
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(instance);
			session.getTransaction().commit();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
	
	public void delete(Commande instance) {
		log.debug("deleting Commande instance " + instance.getNumeroCommande());
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(instance);
			session.getTransaction().commit();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
	
	public Commande update(Commande instance) {
		log.debug("updating Commande instance " + instance.getNumeroCommande());
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.merge(instance);
			session.getTransaction().commit();
			log.debug("update successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
}
