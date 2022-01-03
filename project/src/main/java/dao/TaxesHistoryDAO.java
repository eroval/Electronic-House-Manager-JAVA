package dao;

import IdClasses.TaxesHistoryId;
import entity.TaxesHistory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;

import java.util.List;

public class TaxesHistoryDAO {
    public static void saveTaxesHistory(entity.TaxesHistory taxesHistory) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(taxesHistory);
            transaction.commit();
        }
    }

    public static void saveOrUpdateTaxesHistory(TaxesHistory taxesHistory) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(taxesHistory);
            transaction.commit();
        }
    }

    public static void saveTaxesHistorys(List<TaxesHistory> taxesHistoryList) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            taxesHistoryList.stream().forEach((com) -> session.save(com));
            transaction.commit();
        }
    }

    public static List<TaxesHistory> readTaxesHistorys() {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM TaxesHistory a", entity.TaxesHistory.class).getResultList();
        }
    }

    public static TaxesHistory getTaxesHistory(long taxId, long apartmentId, long buildingId) {
        TaxesHistory taxesHistory;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            taxesHistory = session.get(TaxesHistory.class, new TaxesHistoryId(taxId, apartmentId, buildingId));
            transaction.commit();
        }
        return taxesHistory;
    }

    public static void deleteTaxesHistory(TaxesHistory taxesHistory) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(taxesHistory);
            transaction.commit();
        }
    }

    public static void deleteTaxesHistory(long taxId, long apartmentId, long buildingId) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            TaxesHistory taxesHistory = session.get(TaxesHistory.class, new TaxesHistoryId(taxId, apartmentId, buildingId));
            session.delete(taxesHistory);
            transaction.commit();
        }
    }

    public static List<TaxesHistory> getAllBelongingToBuilding(long buildingId){
        List<TaxesHistory> taxesHistory = new ArrayList<>();;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            taxesHistory= session.createQuery("FROM TaxesHistory th WHERE th.buildingId=:buildingId")
                    .setParameter("buildingId",buildingId).getResultList();
            transaction.commit();
        }
        return  taxesHistory;
    }

    public static List<TaxesHistory> getAllUnpaidTaxes(long buildingId){
        List<TaxesHistory> taxes= new ArrayList<>();
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            taxes= session.createQuery("FROM TaxesHistory th WHERE th.buildingId=:buildingId AND paid=false")
                    .setParameter("buildingId",buildingId).getResultList();
            transaction.commit();
        }
        return taxes;
    }
}
