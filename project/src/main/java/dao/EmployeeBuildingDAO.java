package dao;

import IdClasses.EmployeeBuildingId;
import entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
            Long count = (Long)session.createQuery("SELECT COUNT(*) FROM EmployeeBuilding eb WHERE eb.employeeId=:employeeId")
                                            .setParameter("employeeId",employeeId).uniqueResult();
            return count;
        }
    }

    public static List<Long> getBuildingsOfEmployees(List<Employee> employees){
        List<Long> buildingIds = new ArrayList<>();
        try(Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            List<Long> employeeIds = new ArrayList<>();
            for(Employee emp: employees){
                employeeIds.add(emp.getEmployeeId());
            }
            buildingIds = session.createQuery("SELECT eb.buildingId FROM EmployeeBuilding eb WHERE eb.employeeId in (:employeeIds)")
                    .setParameterList("employeeIds",employeeIds).getResultList();
        }
        return buildingIds;
    }

    public static List<Long> getAllBuildingIdsOfCompany(long companyId){
        List<Long> buildingIds = new ArrayList<>();;
        try(Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            buildingIds = session.createQuery("SELECT eb.buildingId FROM EmployeeBuilding eb WHERE eb.companyId = :companyId")
                    .setParameter("companyId",companyId).getResultList();
        }
        return buildingIds;
    }

    public static List<EmployeeBuilding> readAllByCompanyId(long companyId){
        List<EmployeeBuilding> employeeBuildings = new ArrayList<>();;
        try(Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            employeeBuildings = session.createQuery("SELECT a FROM EmployeeBuilding eb WHERE eb.companyId = :companyId")
                    .setParameter("companyId",companyId).getResultList();
        }
        return employeeBuildings;
    }

    public static List<Long> getAllUniqueEmployeeIdsByCompany(long companyId){
        List<Long> eids = new ArrayList<>();;
        try(Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            eids = session.createQuery("SELECT DISTINCT eb.employeeId FROM EmployeeBuilding eb WHERE eb.companyId = :companyId")
                    .setParameter("companyId",companyId).getResultList();
        }
        return eids;
    }

    public static List<String> getAllBuildingIdsAssociatedWithEmployee(long employeeId){
        List<String> buildingIds = new ArrayList<>();;
        try(Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            buildingIds = session.createQuery("SELECT eb.buildingId FROM EmployeeBuilding eb WHERE eb.employeeId = :employeeId")
                    .setParameter("employeeId",employeeId).getResultList();
        }
        return buildingIds;
    }

    public static List<Long> getAllBuildingIdsAssociatedWithEmployeeLong(long employeeId){
        List<Long> buildingIds = new ArrayList<>();;
        try(Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            buildingIds = session.createQuery("SELECT eb.buildingId FROM EmployeeBuilding eb WHERE eb.employeeId = :employeeId")
                    .setParameter("employeeId",employeeId).getResultList();
        }
        return buildingIds;
    }

    public static HashMap<Long,List<String>> getEmployeeBuildingAssociation(long companyId){
        HashMap<Long,List<String>> employeesInfo = new HashMap<>();
        List<Long> employeeIds = EmployeeBuildingDAO.getAllUniqueEmployeeIdsByCompany(companyId);
        for(Long eid: employeeIds){
            List<String> addon = new ArrayList<>();

            //everything from there on are the building ids
            addon.addAll(EmployeeBuildingDAO.getAllBuildingIdsAssociatedWithEmployee(eid));


            employeesInfo.put(eid,addon);
        }
        return employeesInfo;
    }


    public static List<TaxesHistory> getAllUnpaidTaxes(long employeeId){
        List<TaxesHistory> th = new ArrayList<>();
        try{
            List<Long> buildingIds= EmployeeBuildingDAO.getAllBuildingIdsAssociatedWithEmployeeLong(employeeId);
            for(Long id : buildingIds){
                th.addAll(BuildingDAO.getAllUnpaidTaxes(id));
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return th;
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
