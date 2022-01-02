package dao;

import entity.Building;
import entity.Taxes;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TaxesDAO {
    public static void saveTaxes(entity.Taxes taxes) {
    try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
        Transaction transaction = session.beginTransaction();
        session.save(taxes);
        transaction.commit();
    }
}

    public static void saveOrUpdateTaxes(Taxes taxes) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(taxes);
            transaction.commit();
        }
    }

    public static void saveTaxess(List<Taxes> taxesList) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            taxesList.stream().forEach((com) -> session.save(com));
            transaction.commit();
        }
    }

    public static List<Taxes> readTaxess() {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM Taxes a", entity.Taxes.class).getResultList();
        }
    }

    public static Taxes getTaxes(long buildingId) {
        Taxes taxes;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            taxes = session.get(Taxes.class, buildingId);
            transaction.commit();
        }
        return taxes;
    }

    public static void deleteTaxes(Taxes taxes) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(taxes);
            transaction.commit();
        }
    }

    public static void deleteTaxes(long buildingId) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Taxes taxes = session.get(Taxes.class, buildingId);
            session.delete(taxes);
            transaction.commit();
        }
    }

    public static List<Taxes> readSpecificBuildingTaxes(List<Building> buildings){
        List<Taxes> taxes;
        try(Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            List<Long> buildingIds = new ArrayList<>();
            for(Building b : buildings){
                buildingIds.add(b.getBuildingId());
            }
            taxes = session.createQuery("FROM Taxes t WHERE t.buildingId in (:buildingIds)")
                                        .setParameterList("buildingIds",buildingIds).getResultList();
            transaction.commit();
        }
        return taxes;
    }

}
