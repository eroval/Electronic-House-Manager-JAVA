package dao;

import entity.Building;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BuildingDAO {    
    public static void saveBuilding(entity.Building building) {
    try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
        Transaction transaction = session.beginTransaction();
        session.save(building);
        transaction.commit();
    }
}

    public static void saveOrUpdateBuilding(Building building) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(building);
            transaction.commit();
        }
    }

    public static void saveBuildings(List<Building> buildingList) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            buildingList.stream().forEach((com) -> session.save(com));
            transaction.commit();
        }
    }

    public static List<Building> readBuildings() {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM entities.Building a", entity.Building.class).getResultList();
        }
    }

    public static Building getBuilding(long buildingId) {
        Building building;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            building = session.get(Building.class, buildingId);
            transaction.commit();
        }
        return building;
    }

    public static void deleteBuilding(Building building) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(building);
            transaction.commit();
        }
    }

    public static void deleteBuilding(long buildingId) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Building building = session.get(Building.class, buildingId);
            session.delete(building);
            transaction.commit();
        }
    }
}