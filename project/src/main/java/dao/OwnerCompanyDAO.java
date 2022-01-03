package dao;

import IdClasses.OwnerCompanyId;
import entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.*;

public class OwnerCompanyDAO {

    public static void saveOwnerCompany(entity.OwnerCompany ownerCompany) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(ownerCompany);
            transaction.commit();
        }
    }

    public static void saveOrUpdateOwnerCompany(OwnerCompany ownerCompany) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(ownerCompany);
            transaction.commit();
        }
    }

    public static void saveOwnerCompanies(List<OwnerCompany> ownerCompanyList) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            ownerCompanyList.stream().forEach((com) -> session.save(com));
            transaction.commit();
        }
    }

    public static List<OwnerCompany> readOwnerCompanies() {
        List<OwnerCompany> ownerCompanies = new ArrayList<>();;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            ownerCompanies = session.createQuery("SELECT a FROM OwnerCompany a", entity.OwnerCompany.class).getResultList();
        }
        return ownerCompanies;
    }

    public static OwnerCompany getOwnerCompany(long companyId,long ownerId ) {
        OwnerCompany ownerCompany;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            ownerCompany = session.get(OwnerCompany.class, new OwnerCompanyId(companyId, ownerId));
            transaction.commit();
        }
        return ownerCompany;
    }

    public static OwnerCompany getOwnerCompany(long companyId) {
        OwnerCompany ownerCompany;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            ownerCompany = (OwnerCompany) session.createQuery("FROM OwnerCompany ow WHERE ow.companyId=:companyId")
                    .setParameter("companyId",companyId).getResultList().get(0);
            transaction.commit();
        }
        return ownerCompany;
    }

    public static void deleteOwnerCompany(OwnerCompany ownerCompany) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(ownerCompany);
            transaction.commit();
        }
    }

    public static void deleteOwnerCompany(long companyId,long ownerId ) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            OwnerCompany ownerCompany = session.get(OwnerCompany.class, new OwnerCompanyId(companyId, ownerId));
            session.delete(ownerCompany);
            transaction.commit();
        }
    }

    public static Owner getOwner(OwnerCompany ownerCompany){
        Owner owner;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            owner = session.get(Owner.class, ownerCompany.getOwnerId());
            transaction.commit();
        }
        return owner;
    }

    public static Company getCompany(OwnerCompany ownerCompany){
        Company company;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            company = session.get(Company.class, ownerCompany.getCompanyId());
            transaction.commit();
        }
        return company;
    }

    public static void addBuilding(long companyId,long ownerId ,long buildingId){
        try {
            Building building = BuildingDAO.getBuilding(buildingId);
            OwnerCompany ownerCompany = OwnerCompanyDAO.getOwnerCompany(companyId, ownerId);
            if (building == null) throw new IllegalArgumentException("No such building");
            if (ownerCompany == null) throw new IllegalArgumentException("No such company associated with owner");
            List<Employee> employees = EmployeeDAO.getEmployeesBelongingToOwnerCompany(companyId, ownerId);


            long min = EmployeeBuildingDAO.getNumberOfAssociatedBuildings(employees.get(0).getEmployeeId());
            long id = employees.get(0).getEmployeeId();
            long tmp;
            for (Employee e : employees) {
                tmp = EmployeeBuildingDAO.getNumberOfAssociatedBuildings(e.getEmployeeId());
                if (tmp < min) {
                    min = tmp;
                    id = e.getEmployeeId();
                }
            }
            EmployeeBuilding eb = new EmployeeBuilding(buildingId, id, companyId, ownerId);
            EmployeeBuildingDAO.saveEmployeeBuilding(eb);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void addBuilding(OwnerCompany ownerCompany, Building building){
        OwnerCompanyDAO.addBuilding(ownerCompany.getCompanyId(), ownerCompany.getOwnerId(), building.getBuildingId());
    }

    public static double getRevenueOfCompany(OwnerCompany company){
        double totalRevenue=0;
        try {
            List<Long> buildingIds = EmployeeBuildingDAO.getAllBuildingIdsOfCompany(company.getCompanyId());
            System.out.println(buildingIds);
            for (Long id : buildingIds) {
                totalRevenue += BuildingDAO.calculateBuildingPriceHistory(id);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return totalRevenue;
    }

    public static List<Map.Entry<OwnerCompany, Double>> getSortedCopmaniesByRevenueAsc(){
        try {
            List<OwnerCompany> companies = OwnerCompanyDAO.readOwnerCompanies();
            Map<OwnerCompany, Double> pairs = new HashMap<>();
            for (OwnerCompany company : companies) {
                pairs.put(company, Double.valueOf(getRevenueOfCompany(company)));
            }
            List<Map.Entry<OwnerCompany, Double>> companiesByRevenue = new LinkedList<Map.Entry<OwnerCompany, Double>>(pairs.entrySet());
            Collections.sort(companiesByRevenue, new Comparator<Map.Entry<OwnerCompany, Double>>() {
                @Override
                public int compare(Map.Entry<OwnerCompany, Double> o1, Map.Entry<OwnerCompany, Double> o2) {
                    return (o1.getValue()).compareTo(o2.getValue());
                }
            });
            return companiesByRevenue;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new LinkedList<>();
        }
    }

    public static List<Map.Entry<OwnerCompany, Double>> getSortedCopmaniesByRevenueDsc(){
        try {
            List<OwnerCompany> companies = OwnerCompanyDAO.readOwnerCompanies();
            Map<OwnerCompany, Double> pairs = new HashMap<>();
            for (OwnerCompany company : companies) {
                pairs.put(company, Double.valueOf(getRevenueOfCompany(company)));
            }
            List<Map.Entry<OwnerCompany, Double>> companiesByRevenue = new LinkedList<Map.Entry<OwnerCompany, Double>>(pairs.entrySet());
            Collections.sort(companiesByRevenue, new Comparator<Map.Entry<OwnerCompany, Double>>() {
                @Override
                public int compare(Map.Entry<OwnerCompany, Double> o1, Map.Entry<OwnerCompany, Double> o2) {
                    return -(o1.getValue()).compareTo(o2.getValue());
                }
            });
            return companiesByRevenue;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new LinkedList<>();
        }
    }


    public static List<Map.Entry<Employee, Long>> getSortedEmployeesAsc(long companyId) {
        try{
            OwnerCompany ownerCompany = OwnerCompanyDAO.getOwnerCompany(companyId);
            List<Employee> employees = EmployeeDAO.getEmployeesBelongingToOwnerCompany(ownerCompany.getCompanyId(), ownerCompany.getOwnerId());
            Map<Employee, Long> pairs = new HashMap<>();
            for (Employee employee : employees) {
                pairs.put(employee, Long.valueOf(EmployeeBuildingDAO.getNumberOfAssociatedBuildings(employee.getEmployeeId())));
            }
            List<Map.Entry<Employee, Long>> employeesByBuildings = new LinkedList<Map.Entry<Employee, Long>>(pairs.entrySet());
            Collections.sort(employeesByBuildings
                    , new Comparator<Map.Entry<Employee, Long>>() {
                        @Override
                        public int compare(Map.Entry<Employee, Long> o1, Map.Entry<Employee, Long> o2) {
                            if (o1.getValue().compareTo(o2.getValue()) == 1) return 1;
                            if (o1.getValue().compareTo(o2.getValue()) == 0) {
                                return o1.getKey().getFName().toLowerCase(Locale.ROOT).compareTo(o2.getKey().getFName().toLowerCase(Locale.ROOT));
                            }
                            return -1;
                        }
                    }
            );
            return employeesByBuildings;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new LinkedList<>();
        }
    }

    public static List<Map.Entry<Employee, Long>> getSortedEmployeesDsc(long companyId){
        try {
            OwnerCompany ownerCompany = OwnerCompanyDAO.getOwnerCompany(companyId);
            List<Employee> employees = EmployeeDAO.getEmployeesBelongingToOwnerCompany(ownerCompany.getCompanyId(), ownerCompany.getOwnerId());
            Map<Employee, Long> pairs = new HashMap<>();
            for (Employee employee : employees) {
                pairs.put(employee, Long.valueOf(EmployeeBuildingDAO.getNumberOfAssociatedBuildings(employee.getEmployeeId())));
            }

            List<Map.Entry<Employee, Long>> employeesByBuildings = new LinkedList<Map.Entry<Employee, Long>>(pairs.entrySet());
            Collections.sort(employeesByBuildings, new Comparator<Map.Entry<Employee, Long>>() {
                        @Override
                        public int compare(Map.Entry<Employee, Long> o1, Map.Entry<Employee, Long> o2) {
                            if (o1.getValue().compareTo(o2.getValue()) == 1) return -1;
                            if (o1.getValue().compareTo(o2.getValue()) == 0) {
                                return -o1.getKey().getFName().toLowerCase(Locale.ROOT).compareTo(o2.getKey().getFName().toLowerCase(Locale.ROOT));
                            }
                            return 1;
                        }
                    }
            );
            return employeesByBuildings;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new LinkedList<>();
        }
    }
}
