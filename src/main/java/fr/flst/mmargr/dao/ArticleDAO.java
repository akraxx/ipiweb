package fr.flst.mmargr.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.flst.mmargr.model.Article;
import fr.flst.mmargr.util.HibernateUtil;

public class ArticleDAO {
	private static final Logger log = LoggerFactory.getLogger(ArticleDAO.class);
	
	private static ArticleDAO instance;
	
	private ArticleDAO() {
		
	}
	
	public static ArticleDAO getInstance() {
		if(instance == null) {
			instance = new ArticleDAO();
		}
		
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public List<Article> findAll() {
		log.debug("getting all Article instances with");
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from Article where actif = true order by enseigne desc");
			
			List<Article> list = query.list();
			session.close();
			return list;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Article> findLikeReference(String chain) {
		log.debug("getting all Article instances with chain like : " + chain);
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			
			Query query = session.createQuery("from Article where referenceArticle like :chain");
			query.setParameter("chain", "%" + chain + "%");
			
			List<Article> list = query.list();
			session.close();
			return list;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Article> findLikeDescriptionCourte(String chain) {
		log.debug("getting all Article instances with description courte like : " + chain);
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			
			Query query = session.createQuery("from Article where descriptionCourte like :chain");
			query.setParameter("chain", "%" + chain + "%");
			
			List<Article> list = query.list();
			session.close();
			return list;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Article> findLikePrix(Double chain) {
		log.debug("getting all Article instances with prixlike : " + chain);
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			
			Query query = session.createQuery("from Article where prix like :chain");
			query.setParameter("chain", chain);
			
			List<Article> list = query.list();
			session.close();
			return list;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public Article findById(Integer id) {
		log.debug("getting Article instance with id: " + id);
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			
			Article instance = (Article)session.get(Article.class, id);
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
	
	public Article findByReference(String reference) {
		log.debug("getting Article instance with reference: " + reference);
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			
			Query query = session.createQuery("from Article where referenceArticle = :reference");
			query.setParameter("reference", reference);
			
			Article instance = (Article)query.uniqueResult();
			
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
	
	public void insert(Article instance) {
		log.debug("persisting Article instance");
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
	
	public void disactive(Article instance) {
		log.debug("disactive Article instance " + instance.getId());
		try {
			instance.setActif(false);
			this.update(instance);
			log.debug("disactived successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
	
	public void delete(Article instance) {
		log.debug("deleting Article instance " + instance.getId());
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
	
	public Article update(Article instance) {
		log.debug("updating Article instance " + instance.getId());
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
