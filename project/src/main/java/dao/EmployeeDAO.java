package dao;

import IdClasses.EmployeeId;
import entity.Company;
import entity.Employee;
import entity.Owner;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeDAO {
    public static void saveEmployee(entity.Employee employee) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        }
    }

    public static void saveOrUpdateEmployee(Employee employee) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(employee);
            transaction.commit();
        }
    }

    public static void saveEmployees(List<Employee> employeeList) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employeeList.stream().forEach((com) -> session.save(com));
            transaction.commit();
        }
    }

    public static List<Employee> readEmployees() {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM entities.Employee a", entity.Employee.class).getResultList();
        }
    }

    public static Employee getEmployee(long employeeId, long companyId, long ownerId) {
        Employee employee;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employee = session.get(Employee.class, new EmployeeId(employeeId, companyId, ownerId));
            transaction.commit();
        }
        return employee;
    }

    public static void deleteEmployee(Employee employee) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        }
    }

    public static void deleteEmployee(long employeeId, long companyId, long ownerId) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, new EmployeeId(employeeId, companyId, ownerId));
            session.delete(employee);
            transaction.commit();
        }
    }

    public static Owner getOwner(Employee employee){
        Owner owner;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            owner = session.get(Owner.class, employee.getOwnerId());
            transaction.commit();
        }
        return owner;
    }

    public static Company getCompany(Employee employee){
        Company company;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            company = session.get(Company.class, employee.getCompanyId());
            transaction.commit();
        }
        return company;
    }
}
