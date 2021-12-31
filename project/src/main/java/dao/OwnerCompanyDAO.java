package dao;

import IdClasses.OwnerCompanyId;
import entity.Company;
import entity.Owner;
import entity.OwnerCompany;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

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
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM OwnerCompany a", entity.OwnerCompany.class).getResultList();
        }
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
}
