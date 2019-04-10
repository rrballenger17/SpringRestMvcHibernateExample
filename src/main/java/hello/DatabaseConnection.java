package hello;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class DatabaseConnection {
	
	private static  Configuration configuration = null;
	
	private static StandardServiceRegistryBuilder builder = null;
	
	private static SessionFactory factory = null;
	
	public static Session getSession() {
			
		if(configuration == null) {
			// configuration
	    	configuration = new Configuration().configure();    
	        // 
	        builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
	        // session factory
	        factory = configuration.buildSessionFactory(builder.build());
	        
	        ingestSampleData();
		}
    
        // session 
        return factory.openSession();
	}
	
	public static void closeSession(Session session) {
		session.close();
	}
	
	public static void closeFactory() {
		factory.close();
	}

	private static void ingestSampleData() {
		Session session = getSession();
		
        final Book book = new Book("93939398948 ", "Java 8", "Sebastian Daschner");
        
        final Book book2 = new Book("93939398949 ", "Angular", "Ryan Ballenger");

        
        // begin transaction
        session.beginTransaction();
        
        // save book to database
        session.save(book);
        
        session.save(book2);
        
        // commit transaction
        session.getTransaction().commit();
        
        closeSession(session);
	}
	
}
