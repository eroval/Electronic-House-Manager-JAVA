package dao;

import entity.Company;
import entity.Owner;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class OwnerDAO {

    public static void saveOwner(entity.Owner owner) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(owner);
            transaction.commit();
        }
    }

    public static void saveOrUpdateOwner(Owner owner) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(owner);
            transaction.commit();
        }
    }

    public static void updateOwnerByEgn(String egn,Owner ownerUpdate){
        Owner owner;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Owner where egn like :egn");
            query.setParameter("egn",egn);
            owner = (Owner)query.getResultList().get(0);
            owner.loadFromObject(ownerUpdate);
            transaction.commit();
        }

    }

    public static void saveOwners(List<Owner> ownerList) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            ownerList.stream().forEach((com) -> session.save(com));
            transaction.commit();
        }
    }

    public static List<Owner> readOwners() {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM Owner a", entity.Owner.class).getResultList();
        }
    }

    public static Owner getOwner(long id) {
        Owner owner;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            owner = session.get(Owner.class, id);
            transaction.commit();
        }
        return owner;
    }

    public static Owner getOwner(String egn) {
        Owner owner;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            owner = session.get(Owner.class, egn);
            transaction.commit();
        }
        return owner;
    }

    public static void deleteOwner(Owner owner) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(owner);
            transaction.commit();
        }
    }

    public static void deleteOwners(List<Owner> list){
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            for (Owner owner : list) {
                session.delete(owner);
            }
            transaction.commit();
        }
    }

    public static void deleteOwner(long id) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Owner owner = session.get(Owner.class, id);
            session.delete(owner);
            transaction.commit();
        }
    }

    public static void deleteOwner(String egn) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Owner owner = session.get(Owner.class, egn);
            session.delete(owner);
            transaction.commit();
        }
    }
}
