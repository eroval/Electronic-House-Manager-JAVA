package dao;

import IdClasses.PersonFamilyId;
import entity.PersonFamily;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PersonFamilyDAO {
    public static void savePersonFamily(PersonFamily personFamily) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(personFamily);
            transaction.commit();
        }
    }

    public static void saveOrUpdatePersonFamily(PersonFamily personFamily) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(personFamily);
            transaction.commit();
        }
    }

    public static void savePersonFamilys(List<PersonFamily> personFamilyList) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            personFamilyList.stream().forEach((com) -> session.save(com));
            transaction.commit();
        }
    }

    public static List<PersonFamily> readPersonFamilys() {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM PersonFamily a", PersonFamily.class).getResultList();
        }
    }

    public static PersonFamily getPersonFamily(String personId, Long familyId) {
        PersonFamily personFamily;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            personFamily = session.get(PersonFamily.class, new PersonFamilyId(personId, familyId));
            transaction.commit();
        }
        return personFamily;
    }

    public static void deletePersonFamily(PersonFamily personFamily) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(personFamily);
            transaction.commit();
        }
    }

    public static void deletePersonFamily(String personId, Long familyId) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            PersonFamily personFamily = session.get(PersonFamily.class, new PersonFamilyId(personId, familyId));
            session.delete(personFamily);
            transaction.commit();
        }
    }
}
