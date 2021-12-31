package dao;

import IdClasses.TaxesHistoryId;
import entity.TaxesHistory;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
}
