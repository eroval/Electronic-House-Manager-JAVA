package dao;

import IdClasses.FamilyId;
import entity.Family;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class FamilyDAO {
    public static void saveFamily(entity.Family family) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(family);
            transaction.commit();
        }
    }

    public static void saveOrUpdateFamily(Family family) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(family);
            transaction.commit();
        }
    }

    public static void saveFamilys(List<Family> familyList) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            familyList.stream().forEach((com) -> session.save(com));
            transaction.commit();
        }
    }

    public static List<Family> readFamilys() {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM Family a", entity.Family.class).getResultList();
        }
    }

    public static Family getFamily(long familyId, String personId) {
        Family family;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            family = session.get(Family.class, new FamilyId(familyId, personId));
            transaction.commit();
        }
        return family;
    }

    public static void deleteFamily(Family family) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(family);
            transaction.commit();
        }
    }

    public static void deleteFamily(long familyId, String personId) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Family family = session.get(Family.class, new FamilyId(familyId, personId));
            session.delete(family);
            transaction.commit();
        }
    }
}
