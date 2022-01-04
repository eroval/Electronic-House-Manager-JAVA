package dao;

import entity.Landlord;
import entity.OwnerCompany;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LandlordDAO {
    public static void saveLandlord(entity.Landlord Landlord) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(Landlord);
            transaction.commit();
        }
    }

    public static void saveOrUpdateLandlord(Landlord Landlord) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(Landlord);
            transaction.commit();
        }
    }

    public static void saveLandlords(List<Landlord> LandlordList) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            LandlordList.stream().forEach((com) -> session.save(com));
            transaction.commit();
        }
    }

    public static List<Landlord> readLandlords() {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM Landlord a", entity.Landlord.class).getResultList();
        }
    }

    public static Landlord getLandlord(String landlordId) {
        Landlord Landlord;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Landlord = session.get(Landlord.class, landlordId);
            transaction.commit();
        }
        return Landlord;
    }

    public static void deleteLandlord(Landlord Landlord) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(Landlord);
            transaction.commit();
        }
    }

    public static void deleteLandlord(String landlordId) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Landlord Landlord = session.get(Landlord.class, landlordId);
            session.delete(Landlord);
            transaction.commit();
        }
    }

    public static void deleteAll(){
        List<Landlord> landlords = LandlordDAO.readLandlords();
        for(Landlord landlord : landlords){
            LandlordDAO.deleteLandlord(landlord);
        }
    }
}
