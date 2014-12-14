// default package
// Generated 13 mai 2014 00:11:51 by Hibernate Tools 3.4.0.CR1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Relai.
 * @see .Relai
 * @author Hibernate Tools
 */
@Stateless
public class RelaiHome {

	private static final Log log = LogFactory.getLog(RelaiHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Relai transientInstance) {
		log.debug("persisting Relai instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Relai persistentInstance) {
		log.debug("removing Relai instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Relai merge(Relai detachedInstance) {
		log.debug("merging Relai instance");
		try {
			Relai result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Relai findById(Integer id) {
		log.debug("getting Relai instance with id: " + id);
		try {
			Relai instance = entityManager.find(Relai.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
