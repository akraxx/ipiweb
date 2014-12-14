package fr.flst.mmargr.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.flst.mmargr.model.Adresse;
import fr.flst.mmargr.util.HibernateUtil;

public class AdresseDAO {
	private static final Logger log = LoggerFactory.getLogger(AdresseDAO.class);
	
	private static AdresseDAO instance;
	
	private AdresseDAO() {
		
	}
	
	public static AdresseDAO getInstance() {
		if(instance == null) {
			instance = new AdresseDAO();
		}
		
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public List<Adresse> findAll() {
		log.debug("getting all Adresse instances");
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from Adresse");
			
			List<Adresse> list = query.list();
			session.close();
			return list;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	
	public Adresse findById(Integer id) {
		log.debug("getting Adresse instance with id: " + id);
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			
			Adresse instance = (Adresse)session.get(Adresse.class, id);
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

	
	public void insert(Adresse instance) {
		log.debug("persisting Adresse instance");
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
	
	public void delete(Adresse instance) {
		log.debug("deleting Adresse instance " + instance.getId());
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
	
	public Adresse update(Adresse instance) {
		log.debug("updating Adresse instance " + instance.getId());
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
