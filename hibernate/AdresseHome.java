// default package
// Generated 13 mai 2014 00:11:51 by Hibernate Tools 3.4.0.CR1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Adresse.
 * @see .Adresse
 * @author Hibernate Tools
 */
@Stateless
public class AdresseHome {

	private static final Log log = LogFactory.getLog(AdresseHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Adresse transientInstance) {
		log.debug("persisting Adresse instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Adresse persistentInstance) {
		log.debug("removing Adresse instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Adresse merge(Adresse detachedInstance) {
		log.debug("merging Adresse instance");
		try {
			Adresse result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Adresse findById(Integer id) {
		log.debug("getting Adresse instance with id: " + id);
		try {
			Adresse instance = entityManager.find(Adresse.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
