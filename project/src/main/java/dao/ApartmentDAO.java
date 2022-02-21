package dao;

import IdClasses.ApartmentId;
import entity.Apartment;
import entity.Building;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ApartmentDAO {
    public static void saveApartment(entity.Apartment apartment) {
        if(BuildingDAO.checkAvailableApartments(apartment.getBuildingId())){
            try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                session.save(apartment);
                transaction.commit();
            }
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
            for(Apartment app : apartmentList){
                if(BuildingDAO.checkAvailableApartments(app.getBuildingId())){
                    session.save(app);
                }
            }
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

    public static long getNumberOfApartmentsByBuilding(long buildingId){
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            long count = (Long) session.createQuery("SELECT COUNT(*) FROM Apartment a WHERE a.buildingId=:buildingId")
                    .setParameter("buildingId",buildingId).uniqueResult();
            transaction.commit();
            return count;
        }
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

    public static List<Apartment> getAllApartments(long buildingId){
        List<Apartment> apartments = new ArrayList<>();
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            apartments = session.createQuery("FROM Apartment app WHERE app.buildingId=:buildingId")
                                            .setParameter("buildingId",buildingId).getResultList();
            transaction.commit();
        }
        return apartments;
    }

    public static void deleteAll(){
        List<Apartment> apartments = ApartmentDAO.readApartments();
        for(Apartment apartment : apartments){
            ApartmentDAO.deleteApartment(apartment);
        }
    }
}
