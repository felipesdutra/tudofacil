package util;

import javax.persistence.EntityManagerFactory;

public class JpaUtil {
	private static final String PERSISTENCE_UNIT_NAME = "tudofacil";

    private static EntityManagerFactory factory;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (factory == null) {
            factory = javax.persistence.Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return factory;
    }
}
