package dao;

import entity.Building;
import entity.Family;
import entity.Person;
import entity.Taxes;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
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

    public static Family getFamily(long familyId) {
        Family Family;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Family = session.get(Family.class, familyId);
            transaction.commit();
        }
        return Family;
    }

    public static void deleteFamily(Family family) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(family);
            transaction.commit();
        }
    }

    public static void deleteFamily(String familyId) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Family Family = session.get(Family.class, familyId);
            session.delete(Family);
            transaction.commit();
        }
    }

    public static List<Person> getAllFamilyMembers(long familyId){
        List<String> peopleIds = PersonFamilyDAO.getSpecificPeopleIds(familyId);
        return PersonDAO.getSpecificPeopleData(peopleIds);
    }

    public static boolean getPet(long familyId){
        Family f = FamilyDAO.getFamily(familyId);
        return f.getPet();
    }
}
