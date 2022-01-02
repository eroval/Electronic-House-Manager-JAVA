package dao;

import IdClasses.EmployeeBuildingId;
import entity.Employee;
import entity.EmployeeBuilding;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
            return session.createQuery("SELECT a FROM EmployeeBuilding a", entity.EmployeeBuilding.class).getResultList();
        }
    }

    public static EmployeeBuilding getEmployeeBuilding(long buildingId, long employeeId, long companyId, long ownerId) {
        EmployeeBuilding employeeBuilding;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employeeBuilding = session.get(EmployeeBuilding.class, new EmployeeBuildingId(buildingId, employeeId, companyId, ownerId));
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

    public static void deleteEmployeeBuilding(long buildingId, long employeeId, long companyId, long ownerId) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            EmployeeBuilding employeeBuilding = session.get(EmployeeBuilding.class, new EmployeeBuildingId(buildingId, employeeId, companyId, ownerId));
            session.delete(employeeBuilding);
            transaction.commit();
        }
    }

    public static long getNumberOfAssociatedBuildings(long employeeId){
        try(Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            long count = session.createQuery("SELECT COUNT(*) FROM EmployeeBuilding eb WHERE eb.employeeId=:employeeId")
                                            .setParameter("employeeId",employeeId).getFirstResult();
            return count;
        }
    }

//    //Not Ready
//    public static long getNumberOfAssociatedBuildings(List<Employee> employees){
//        try(Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
//            List<Long> employeeIds = new ArrayList<>();
//            for(Employee e : employees){
//                employeeIds.add(e.getEmployeeId());
//            }
//            Transaction transaction = session.beginTransaction();
//            System.out.println("COOOOOOOOOOOOOOOOOl");
//            System.out.println("COOOOOOOOOOOOOOOOOl");
//            System.out.println("COOOOOOOOOOOOOOOOOl");
//            List<Object> pair = (session.createQuery("SELECT COUNT(*), employeeId FROM EmployeeBuilding eb WHERE eb.employeeId in (:employeeIds) GROUP BY employeeId")
//                    .setParameterList("employeeIds", employeeIds).getResultList());
//            long max = (Long)pair.get(0);
//            for(long i=0; i<pair.size(); i+=2){
//                if((Long)pair.get(i)>)
//            }
//            System.out.println("COOOOOOOOOOOOOOOOOl");
//            System.out.println("COOOOOOOOOOOOOOOOOl");
//            System.out.println("COOOOOOOOOOOOOOOOOl");
//            return 0;
//        }
//    }
}
