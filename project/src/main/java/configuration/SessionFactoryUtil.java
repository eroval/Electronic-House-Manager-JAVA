package configuration;

import entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionFactoryUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Company.class);
            configuration.addAnnotatedClass(Owner.class);
            configuration.addAnnotatedClass(OwnerCompany.class);
            configuration.addAnnotatedClass(Employee.class);
            configuration.addAnnotatedClass(Building.class);
            configuration.addAnnotatedClass(EmployeeBuilding.class);
            configuration.addAnnotatedClass(Taxes.class);
            configuration.addAnnotatedClass(Apartment.class);
            configuration.addAnnotatedClass(TaxesHistory.class);
            configuration.addAnnotatedClass(Person.class);
            configuration.addAnnotatedClass(Family.class);
            configuration.addAnnotatedClass(PersonFamily.class);
            configuration.addAnnotatedClass(Landlord.class);
            ServiceRegistry serviceRegistry
                    = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }

        return sessionFactory;
    }
}