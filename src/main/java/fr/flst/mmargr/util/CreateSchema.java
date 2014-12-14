package fr.flst.mmargr.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateSchema {
	private static final Logger log = LoggerFactory.getLogger(CreateSchema.class);
	
	public static void initialize() {
        try {
        	Class.forName("org.h2.Driver");
			Connection conn = DriverManager.getConnection("jdbc:h2:mem:java", "sa", "");
			Statement statement = conn.createStatement();
			
			statement.execute("DROP TABLE IF EXISTS adresse;");
			statement.execute(createAdresseTable());
			log.debug("adresse table created");
			statement.execute(fillAdresseTable());
			log.debug("adresse table filled");
			
			statement.execute("DROP TABLE IF EXISTS user;");
			statement.execute(createUserTable());
			log.debug("User table created");
			statement.execute(fillUserTable());
			log.debug("User table filled");
			
			statement.execute("DROP TABLE IF EXISTS article;");
			statement.execute(createArticleTable());
			log.debug("Article table created");
			statement.execute(fillArticleTable());
			log.debug("Article table filled");
			
			statement.execute("DROP TABLE IF EXISTS mode_livraison;");
			statement.execute(createModeLivraisonTable());
			log.debug("Mode_livraison table created");
			statement.execute(fillModeLivraisonTable());
			log.debug("Mode_livraison table filled");
			
			statement.execute("DROP TABLE IF EXISTS relai;");
			statement.execute(createRelaiTable());
			log.debug("relai table created");
			statement.execute(fillRelaiTable());
			log.debug("relai table filled");
			
			statement.execute("DROP TABLE IF EXISTS code_promo;");
			statement.execute(createCodePromoTable());
			log.debug("code_promo table created");
			statement.execute(fillCodePromoTable());
			log.debug("code_promo table filled");
			
			statement.execute("DROP TABLE IF EXISTS paiement;");
			statement.execute(createPaiementTable());
			log.debug("paiement table created");
			statement.execute(fillPaiementTable());
			log.debug("paiement table filled");
			
			statement.execute("DROP TABLE IF EXISTS commande;");
			statement.execute(createCommandeTable());
			log.debug("commande table created");
			statement.execute(fillCommandeTable());
			log.debug("commande table filled");
			
			statement.execute("DROP TABLE IF EXISTS ligne_commande;");
			statement.execute(createLigneCommandeTable());
			log.debug("ligne_commande table created");
			statement.execute(fillLigneCommandeTable());
			log.debug("ligne_commande table filled");
			
			statement.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static String createUserTable() {
		return "CREATE TABLE IF NOT EXISTS `user` ("
				+ "  `id` int(11) NOT NULL AUTO_INCREMENT,"
				+ "  `username` varchar(255) NOT NULL,"
				+ "  `password` varchar(255) NOT NULL,"
				+ "  `lastname` varchar(255) DEFAULT NULL,"
				+ "  `forname` varchar(255) DEFAULT NULL,"
				+ "  `id_adresse` int(11) DEFAULT NULL,"
				+ "  `admin` tinyint(1) NOT NULL DEFAULT '0',"
				+ "  `superadmin` tinyint(1) NOT NULL DEFAULT '0',"
				+ "  PRIMARY KEY (`id`),"
				+ "  UNIQUE KEY `username` (`username`),"
				+ "  FOREIGN KEY (id_adresse) REFERENCES adresse(id)"
				+ ");"
				+ "ALTER TABLE `user` ADD FOREIGN KEY (id_adresse) REFERENCES adresse (`id`);";
	}
	
	public static String fillUserTable() {
		return  "INSERT INTO `user` (`id`, `username`, `password`, `lastname`, `forname`, `admin`, `superadmin`) VALUES"
				+ "(1, 'superadmin', '5f4dcc3b5aa765d61d8327deb882cf99', 'MARIE', 'Maximilien', 1, 1),"
				+ "(2, 'j.michel', '5f4dcc3b5aa765d61d8327deb882cf99', 'MICHEL', 'Jean', 1, 0);";
	}
	
	public static String createArticleTable() {
		return "CREATE TABLE IF NOT EXISTS `article` ("
				+ "  `id` int(11) NOT NULL AUTO_INCREMENT,"
				+ "  `enseigne` varchar(50) NOT NULL,"
				+ "  `reference_article` varchar(10) NOT NULL,"
				+ "  `description_courte` varchar(255) NOT NULL,"
				+ "  `modele` varchar(50) NOT NULL,"
				+ "  `quantite` int(11) NOT NULL,"
				+ "  `description_longue` text NOT NULL,"
				+ "  `prix` double NOT NULL,"
				+ "  `image` varchar(255) NOT NULL,"
				+ "  `super_client` tinyint(1) NOT NULL,"
				+ "  `actif` tinyint(1) NOT NULL DEFAULT '1',"
				+ "  PRIMARY KEY (`id`)"
				+ ");";
	}
	
	public static String fillArticleTable() {
		return "INSERT INTO `article` (`id`, `enseigne`, `reference_article`, `description_courte`, `modele`, `quantite`, `description_longue`, `prix`, `image`, `super_client`, `actif`) VALUES"
				+ "(1, 'IPISoftWare', '84752584', 'Clavier', 'G15', 500, 'Clavier rétroéclairé', 79.99, 'image', 1, 1),"
				+ "(2, 'IPISoftWare', '84752583', 'Souris', 'MX518', 1500, 'Souris optique', 25.99, 'image', 0, 1);";
	}
	
	public static String createModeLivraisonTable() {
		return "CREATE TABLE IF NOT EXISTS `mode_livraison` ("
				+ "  `id` int(11) NOT NULL AUTO_INCREMENT,"
				+ "  `label` varchar(255) NOT NULL,"
				+ "  `cout_classique` double NOT NULL,"
				+ "  `cout_super` double NOT NULL,"
				+ "  PRIMARY KEY (`id`)"
				+ ");";
	}
	
	public static String fillModeLivraisonTable() {
		return "INSERT INTO `mode_livraison` (`id`, `label`, `cout_classique`, `cout_super`) VALUES"
				+ "(1, 'Express domicile', 6.9, 3.9),"
				+ "(2, 'Domicile', 4.9, 1.9),"
				+ "(3, 'Relai', 0, 0);";
	}
	
	public static String createAdresseTable() {
		return "CREATE TABLE IF NOT EXISTS `adresse` ("
				+ "  `id` int(11) NOT NULL AUTO_INCREMENT,"
				+ "  `numero_rue` varchar(255) NOT NULL,"
				+ "  `complement` varchar(255) NOT NULL,"
				+ "  `code_postal` varchar(10) NOT NULL,"
				+ "  `ville` varchar(25) NOT NULL,"
				+ "  PRIMARY KEY (`id`)"
				+ ");";
	}
	
	public static String fillAdresseTable() {
		return "INSERT INTO `adresse` (`id`, `numero_rue`, `complement`, `code_postal`, `ville`) VALUES"
				+ "(1, 'nume', 'Complément', '59000', 'Ville'),"
				+ "(2, 'Test', 'test', 'test', 'VilleTest'),"
				+ "(3, '1, rue solférione', 'Etage 2', '59000', 'Lille'),"
				+ "(4, '5 rue du port', '', '75000', 'Paris');";
	}
	
	public static String createRelaiTable() {
		return "CREATE TABLE IF NOT EXISTS `relai` ("
				+ "  `id` int(11) NOT NULL AUTO_INCREMENT,"
				+ "  `numero_relai` int(11) NOT NULL,"
				+ "  `label` varchar(50) NOT NULL,"
				+ "  `id_adresse` int(11) DEFAULT NULL,"
				+ "  `actif` tinyint(1) NOT NULL DEFAULT '1',"
				+ "  PRIMARY KEY (`id`),"
				+ "  FOREIGN KEY (id_adresse) REFERENCES adresse(id)"
				+ ");"
				+ "ALTER TABLE `relai` ADD FOREIGN KEY (id_adresse) REFERENCES adresse (id) ON DELETE SET NULL ON UPDATE NO ACTION;";
	}
	
	public static String fillRelaiTable() {
		return "INSERT INTO `relai` (`id`, `numero_relai`, `label`, `id_adresse`, `actif`) VALUES"
				+ "(3, 47464, 'Relai', 1, 1),"
				+ "(4, 91895, 'Test', 2, 0),"
				+ "(5, 97712, 'La petite ginguette', 3, 1),"
				+ "(6, 74859, 'Librairie biblio', 4, 1);";
	}
	
	public static String createCodePromoTable() {
		return "CREATE TABLE IF NOT EXISTS `code_promo` ("
				+ "  `id` varchar(5) NOT NULL,"
				+ "  `enseigne` varchar(50) NOT NULL,"
				+ "  `date_debut` date NOT NULL,"
				+ "  `date_fin` date NOT NULL,"
				+ "  `type_promotion` varchar(50) NOT NULL,"
				+ "  `pourcentage` int(11) DEFAULT NULL,"
				+ "  `reference` varchar(10) DEFAULT NULL,"
				+ "  PRIMARY KEY (`id`),"
				+ "  UNIQUE KEY `id` (`id`)"
				+ ");";
	}
	
	public static String fillCodePromoTable() {
		return "INSERT INTO `code_promo` (`id`, `enseigne`, `date_debut`, `date_fin`, `type_promotion`, `pourcentage`, `reference`) VALUES"
				+ "('A1', 'IPIHardWare', '2014-05-12', '2015-12-15', 'REDCMD', 1, ''),"
				+ "('TES', 'IPISoftWare', '2014-05-12', '2014-05-15', 'REDCMD', 10, '');";
	}
	
	public static String createPaiementTable() {
		return "CREATE TABLE IF NOT EXISTS `paiement` ("
				+ "  `id` int(11) NOT NULL AUTO_INCREMENT,"
				+ "  `label` varchar(50) NOT NULL,"
				+ "  `client_classique` tinyint(1) NOT NULL,"
				+ "  `super_client` tinyint(1) NOT NULL,"
				+ "  PRIMARY KEY (`id`)"
				+ ");";
	}
	
	public static String fillPaiementTable() {
		return "INSERT INTO `paiement` (`id`, `label`, `client_classique`, `super_client`) VALUES"
				+ "(1, 'CB', 1, 1),"
				+ "(2, 'Paypal', 1, 1),"
				+ "(3, 'Chèque', 1, 1),"
				+ "(4, 'Contre-remboursement', 0, 1);";
	}
	
	public static String createCommandeTable() {
		return "CREATE TABLE IF NOT EXISTS `commande` ("
				+ "  `numero_commande` varchar(10) NOT NULL,"
				+ "  `id_client` int(11) NOT NULL,"
				+ "  `id_mode_livraison` int(11) NOT NULL,"
				+ "  `id_relai` int(11) DEFAULT NULL,"
				+ "  `contre_remboursement` tinyint(1) NOT NULL DEFAULT '0',"
				+ "  `prix_total` double NOT NULL,"
				+ "  `envoyee` tinyint(1) NOT NULL DEFAULT '0',"
				+ "  PRIMARY KEY (`numero_commande`),"
				+ "  FOREIGN KEY (id_client) REFERENCES user(id),"
				+ "  FOREIGN KEY (id_mode_livraison) REFERENCES mode_livraison(id),"
				+ "  FOREIGN KEY (id_relai) REFERENCES relai(id)"
				+ ");"
				+ "ALTER TABLE `commande` ADD FOREIGN KEY (id_client) REFERENCES user (id);"
				+ "ALTER TABLE `commande` ADD FOREIGN KEY (id_mode_livraison) REFERENCES mode_livraison (id);"
				+ "ALTER TABLE `commande` ADD FOREIGN KEY (id_relai) REFERENCES relai (id);";
	}
	
	public static String fillCommandeTable() {
		return "INSERT INTO `commande` (`numero_commande`, `id_client`, `id_mode_livraison`, `id_relai`, `contre_remboursement`, `prix_total`, `envoyee`) VALUES"
				+ "('51RT4513', 1, 1, 6, 1, 562.5, 0);";
	}
	
	public static String createLigneCommandeTable() {
		return "CREATE TABLE IF NOT EXISTS `ligne_commande` ("
				+ "  `id` int(11) NOT NULL AUTO_INCREMENT,"
				+ "  `id_commande` varchar(10) NOT NULL,"
				+ "  `id_article` int(11) NOT NULL,"
				+ "  `quantite` int(11) NOT NULL,"
				+ "  PRIMARY KEY (`id`),"
				+ "  FOREIGN KEY (id_commande) REFERENCES commande(numero_commande),"
				+ "  FOREIGN KEY (id_article) REFERENCES article(id)"
				+ ");"
				+ "ALTER TABLE `ligne_commande` ADD FOREIGN KEY (id_commande) REFERENCES commande (numero_commande);"
				+ "ALTER TABLE `ligne_commande` ADD FOREIGN KEY (id_article) REFERENCES article (id);";
	}
	
	public static String fillLigneCommandeTable() {
		return "INSERT INTO `ligne_commande` (`id`, `id_commande`, `id_article`, `quantite`) VALUES"
				+ "(1, '51RT4513', 1, 50),"
				+ "(2, '51RT4513', 2, 1);";
	}
}
