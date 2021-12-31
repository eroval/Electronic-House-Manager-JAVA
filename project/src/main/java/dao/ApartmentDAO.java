package dao;

import IdClasses.ApartmentId;
import entity.Apartment;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ApartmentDAO {
    public static void saveApartment(entity.Apartment apartment) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(apartment);
            transaction.commit();
        }
    }

    public static void saveOrUpdateApartment(Apartment apartment) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(apartment);
            transaction.commit();
        }
    }

    public static void saveApartments(List<Apartment> apartmentList) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            apartmentList.stream().forEach((com) -> session.save(com));
            transaction.commit();
        }
    }

    public static List<Apartment> readApartments() {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM Apartment a", entity.Apartment.class).getResultList();
        }
    }

    public static Apartment getApartment(long apartmentId, long buildingId) {
        Apartment apartment;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            apartment = session.get(Apartment.class, new ApartmentId(apartmentId, buildingId));
            transaction.commit();
        }
        return apartment;
    }

    public static void deleteApartment(Apartment apartment) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(apartment);
            transaction.commit();
        }
    }

    public static void deleteApartment(long apartmentId, long buildingId) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Apartment apartment = session.get(Apartment.class, new ApartmentId(apartmentId, buildingId));
            session.delete(apartment);
            transaction.commit();
        }
    }
}
