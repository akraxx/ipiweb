// default package
// Generated 13 mai 2014 00:11:51 by Hibernate Tools 3.4.0.CR1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class CodePromo.
 * @see .CodePromo
 * @author Hibernate Tools
 */
@Stateless
public class CodePromoHome {

	private static final Log log = LogFactory.getLog(CodePromoHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(CodePromo transientInstance) {
		log.debug("persisting CodePromo instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(CodePromo persistentInstance) {
		log.debug("removing CodePromo instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public CodePromo merge(CodePromo detachedInstance) {
		log.debug("merging CodePromo instance");
		try {
			CodePromo result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public CodePromo findById(String id) {
		log.debug("getting CodePromo instance with id: " + id);
		try {
			CodePromo instance = entityManager.find(CodePromo.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
