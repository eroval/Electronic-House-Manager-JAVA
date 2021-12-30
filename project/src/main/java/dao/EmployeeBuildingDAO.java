package dao;

import IdClasses.EmployeeBuildingId;
import entity.EmployeeBuilding;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeBuildingDAO {
    public static void saveEmployeeBuilding(entity.EmployeeBuilding employeeBuilding) {
    try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
        Transaction transaction = session.beginTransaction();
        session.save(employeeBuilding);
        transaction.commit();
    }
}

    public static void saveOrUpdateEmployeeBuilding(EmployeeBuilding employeeBuilding) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(employeeBuilding);
            transaction.commit();
        }
    }

    public static void saveEmployeeBuildings(List<EmployeeBuilding> employeeBuildingList) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employeeBuildingList.stream().forEach((com) -> session.save(com));
            transaction.commit();
        }
    }

    public static List<EmployeeBuilding> readEmployeeBuildings() {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM entities.EmployeeBuilding a", entity.EmployeeBuilding.class).getResultList();
        }
    }

    public static EmployeeBuilding getEmployeeBuilding(long buildingId, long employeeId) {
        EmployeeBuilding employeeBuilding;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employeeBuilding = session.get(EmployeeBuilding.class, new EmployeeBuildingId(buildingId, employeeId));
            transaction.commit();
        }
        return employeeBuilding;
    }

    public static void deleteEmployeeBuilding(EmployeeBuilding employeeBuilding) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(employeeBuilding);
            transaction.commit();
        }
    }

    public static void deleteEmployeeBuilding(long buildingId, long employeeId) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            EmployeeBuilding employeeBuilding = session.get(EmployeeBuilding.class, new EmployeeBuildingId(buildingId, employeeId));
            session.delete(employeeBuilding);
            transaction.commit();
        }
    }
}
