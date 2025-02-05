package py.una.pol.webstock;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {

//    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static final SessionFactory sessionFactory;
    
	static {
		try {
			sessionFactory = new AnnotationConfiguration().configure()
					.buildSessionFactory();
		} catch (HibernateException he) {
			System.err.println("Ocurrió un error en la inicialización de la SessionFactory: " + he);
			throw new ExceptionInInitializerError(he);
		}
	}

//    private static SessionFactory buildSessionFactory() {
//        try {
//            // Create the SessionFactory from hibernate.cfg.xml
//            return new Configuration().configure().buildSessionFactory();
//        }
//        catch (Throwable ex) {
//            // Make sure you log the exception, as it might be swallowed
//            System.err.println("Initial SessionFactory creation failed.  " + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//    }

	public static SessionFactory getSessionFactory()
	{ 
	    return sessionFactory;
	} 
//    public static SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }

}