// default package
// Generated 13 mai 2014 00:11:51 by Hibernate Tools 3.4.0.CR1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Paiement.
 * @see .Paiement
 * @author Hibernate Tools
 */
@Stateless
public class PaiementHome {

	private static final Log log = LogFactory.getLog(PaiementHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Paiement transientInstance) {
		log.debug("persisting Paiement instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Paiement persistentInstance) {
		log.debug("removing Paiement instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Paiement merge(Paiement detachedInstance) {
		log.debug("merging Paiement instance");
		try {
			Paiement result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Paiement findById(Integer id) {
		log.debug("getting Paiement instance with id: " + id);
		try {
			Paiement instance = entityManager.find(Paiement.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
