// default package
// Generated 13 mai 2014 00:11:51 by Hibernate Tools 3.4.0.CR1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class LigneCommande.
 * @see .LigneCommande
 * @author Hibernate Tools
 */
@Stateless
public class LigneCommandeHome {

	private static final Log log = LogFactory.getLog(LigneCommandeHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(LigneCommande transientInstance) {
		log.debug("persisting LigneCommande instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(LigneCommande persistentInstance) {
		log.debug("removing LigneCommande instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public LigneCommande merge(LigneCommande detachedInstance) {
		log.debug("merging LigneCommande instance");
		try {
			LigneCommande result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public LigneCommande findById(Integer id) {
		log.debug("getting LigneCommande instance with id: " + id);
		try {
			LigneCommande instance = entityManager.find(LigneCommande.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
