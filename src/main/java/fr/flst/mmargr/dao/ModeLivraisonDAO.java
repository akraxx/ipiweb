package fr.flst.mmargr.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.flst.mmargr.model.ModeLivraison;
import fr.flst.mmargr.util.HibernateUtil;

public class ModeLivraisonDAO {
	private static final Logger log = LoggerFactory.getLogger(ModeLivraisonDAO.class);
	
	private static ModeLivraisonDAO instance;
	
	private ModeLivraisonDAO() {
		
	}
	
	public static ModeLivraisonDAO getInstance() {
		if(instance == null) {
			instance = new ModeLivraisonDAO();
		}
		
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public List<ModeLivraison> findAll() {
		log.debug("getting all ModeLivraison instances with");
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from ModeLivraison");
			
			List<ModeLivraison> list = query.list();
			session.close();
			return list;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	
	public ModeLivraison findById(Integer id) {
		log.debug("getting ModeLivraison instance with id: " + id);
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			
			ModeLivraison instance = (ModeLivraison)session.get(ModeLivraison.class, id);
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

	
	public void insert(ModeLivraison instance) {
		log.debug("persisting ModeLivraison instance");
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
	
	public void delete(ModeLivraison instance) {
		log.debug("deleting ModeLivraison instance " + instance.getId());
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
	
	public ModeLivraison update(ModeLivraison instance) {
		log.debug("updating ModeLivraison instance " + instance.getId());
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
