package fr.flst.mmargr.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.flst.mmargr.model.Relai;
import fr.flst.mmargr.util.HibernateUtil;

public class RelaiDAO {
	private static final Logger log = LoggerFactory.getLogger(RelaiDAO.class);
	
	private static RelaiDAO instance;
	
	private RelaiDAO() {
		
	}
	
	public static RelaiDAO getInstance() {
		if(instance == null) {
			instance = new RelaiDAO();
		}
		
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public List<Relai> findAll() {
		log.debug("getting all Relai instances");
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from Relai where actif = true order by numeroRelai asc");
			
			List<Relai> list = query.list();
			session.close();
			return list;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	
	public Relai findById(Integer id) {
		log.debug("getting Relai instance with id: " + id);
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			
			Relai instance = (Relai)session.get(Relai.class, id);
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

	public Relai findByReference(Integer reference) {
		log.debug("getting Relai instance with reference: " + reference);
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			
			Query query = session.createQuery("from Relai where numeroRelai = :reference");
			query.setParameter("reference", reference);
			
			Relai instance = (Relai)query.uniqueResult();
			
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
	
	public void insert(Relai instance) {
		log.debug("persisting Relai instance");
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
	
	public void disactive(Relai instance) {
		log.debug("disactive Relai instance " + instance.getId());
		try {
			instance.setActif(false);
			this.update(instance);
			log.debug("disactived successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
	
	public void delete(Relai instance) {
		log.debug("deleting Relai instance " + instance.getId());
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
	
	public Relai update(Relai instance) {
		log.debug("updating Relai instance " + instance.getId());
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
