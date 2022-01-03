package dao;

import entity.Building;
import entity.Person;
import entity.Taxes;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PersonDAO {
    public static void savePerson(entity.Person person) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(person);
            transaction.commit();
        }
    }

    public static void saveOrUpdatePerson(Person person) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(person);
            transaction.commit();
        }
    }

    public static void savePersons(List<Person> personList) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            personList.stream().forEach((com) -> session.save(com));
            transaction.commit();
        }
    }

    public static List<Person> readPersons() {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM Person a", entity.Person.class).getResultList();
        }
    }

    public static Person getPerson(String personId) {
        Person person;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            person = session.get(Person.class, personId);
            transaction.commit();
        }
        return person;
    }

    public static void deletePerson(Person person) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(person);
            transaction.commit();
        }
    }

    public static void deletePerson(String personId) {
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Person person = session.get(Person.class, personId);
            session.delete(person);
            transaction.commit();
        }
    }

    public static List<Person> getSpecificPeopleData(List<String> peopleIds){
        List<Person> people = new ArrayList<>();;
        try(Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            people = session.createQuery("FROM Person p WHERE p.personId in (:peopleIds)")
                    .setParameterList("peopleIds",peopleIds).getResultList();
            transaction.commit();
        }
        return people;
    }
}
