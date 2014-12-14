package fr.flst.mmargr.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.flst.mmargr.model.Paiement;
import fr.flst.mmargr.util.HibernateUtil;

public class PaiementDAO {
	private static final Logger log = LoggerFactory.getLogger(PaiementDAO.class);
	
	private static PaiementDAO instance;
	
	private PaiementDAO() {
		
	}
	
	public static PaiementDAO getInstance() {
		if(instance == null) {
			instance = new PaiementDAO();
		}
		
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public List<Paiement> findAll() {
		log.debug("getting all Paiement instances with");
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from Paiement");
			
			List<Paiement> list = query.list();
			session.close();
			return list;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	
	public Paiement findById(Integer id) {
		log.debug("getting Paiement instance with id: " + id);
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			
			Paiement instance = (Paiement)session.get(Paiement.class, id);
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

	
	public void insert(Paiement instance) {
		log.debug("persisting Paiement instance");
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
	
	public void delete(Paiement instance) {
		log.debug("deleting Paiement instance " + instance.getId());
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
	
	public Paiement update(Paiement instance) {
		log.debug("updating Paiement instance " + instance.getId());
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
