package fr.flst.mmargr.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import fr.flst.mmargr.model.Adresse;
import fr.flst.mmargr.model.Article;
import fr.flst.mmargr.model.CodePromo;
import fr.flst.mmargr.model.Commande;
import fr.flst.mmargr.model.LigneCommande;
import fr.flst.mmargr.model.ModeLivraison;
import fr.flst.mmargr.model.Paiement;
import fr.flst.mmargr.model.Relai;
import fr.flst.mmargr.model.User;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;

	static {
		try {
			Configuration configuration = new Configuration();
		    configuration.configure()
		    			.addPackage("fr.flst.mmargr.model") //the fully qualified package name
						.addAnnotatedClass(User.class)
						.addAnnotatedClass(Article.class)
						.addAnnotatedClass(ModeLivraison.class)
						.addAnnotatedClass(Adresse.class)
						.addAnnotatedClass(Relai.class)
						.addAnnotatedClass(CodePromo.class)
						.addAnnotatedClass(Paiement.class)
						.addAnnotatedClass(Commande.class)
						.addAnnotatedClass(LigneCommande.class);
		    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
		            configuration.getProperties()).build();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		    CreateSchema.initialize();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static String encrypt(String password)
    {
        byte[] uniqueKey = password.getBytes();
        byte[] hash      = null;

        try
        {
            hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new Error("No MD5 support in this VM.");
        }

        StringBuilder hashString = new StringBuilder();
        for (int i = 0; i < hash.length; i++)
        {
            String hex = Integer.toHexString(hash[i]);
            if (hex.length() == 1)
            {
                hashString.append('0');
                hashString.append(hex.charAt(hex.length() - 1));
            }
            else
                hashString.append(hex.substring(hex.length() - 2));
        }
        return hashString.toString();
    }
}
