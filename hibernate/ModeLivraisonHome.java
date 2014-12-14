// default package
// Generated 13 mai 2014 00:11:51 by Hibernate Tools 3.4.0.CR1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class ModeLivraison.
 * @see .ModeLivraison
 * @author Hibernate Tools
 */
@Stateless
public class ModeLivraisonHome {

	private static final Log log = LogFactory.getLog(ModeLivraisonHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(ModeLivraison transientInstance) {
		log.debug("persisting ModeLivraison instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(ModeLivraison persistentInstance) {
		log.debug("removing ModeLivraison instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public ModeLivraison merge(ModeLivraison detachedInstance) {
		log.debug("merging ModeLivraison instance");
		try {
			ModeLivraison result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ModeLivraison findById(Integer id) {
		log.debug("getting ModeLivraison instance with id: " + id);
		try {
			ModeLivraison instance = entityManager.find(ModeLivraison.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
