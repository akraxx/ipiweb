package fr.flst.mmargr.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.flst.mmargr.model.CodePromo;
import fr.flst.mmargr.util.HibernateUtil;

public class CodePromoDAO {
	private static final Logger log = LoggerFactory.getLogger(CodePromoDAO.class);
	
	private static CodePromoDAO instance;
	
	private CodePromoDAO() {
		
	}
	
	public static CodePromoDAO getInstance() {
		if(instance == null) {
			instance = new CodePromoDAO();
		}
		
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public List<CodePromo> findAll() {
		log.debug("getting all CodePromo instances");
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from CodePromo");
			
			List<CodePromo> list = query.list();
			session.close();
			return list;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	
	public CodePromo findById(String id) {
		log.debug("getting CodePromo instance with id: " + id);
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			
			CodePromo instance = (CodePromo)session.get(CodePromo.class, id);
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

	
	public void insert(CodePromo instance) {
		log.debug("persisting CodePromo instance");
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
	
	public void delete(CodePromo instance) {
		log.debug("deleting CodePromo instance " + instance.getId());
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
	
	public CodePromo update(CodePromo instance) {
		log.debug("updating CodePromo instance " + instance.getId());
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
