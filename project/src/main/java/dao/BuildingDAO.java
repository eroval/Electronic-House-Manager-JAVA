package dao;

import entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
            return session.createQuery("SELECT a FROM Building a", entity.Building.class).getResultList();
        }
    }

    public static Building getBuilding(long id) {
        Building building;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            building = session.get(Building.class, id);
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

    public static void paySpecificTax(long apartmentId, long buildingId){
        Apartment app = ApartmentDAO.getApartment(apartmentId, buildingId);
        Taxes appTax = TaxesDAO.getTaxes(app.getBuildingId());
        double amount=0;
        if(app.getFamilyId()!=null){
            amount=appTax.getBaseTax()*app.getArea();
        }
        if(app.getFamilyId()!=null){
            List<Person> people = FamilyDAO.getAllFamilyMembers(app.getFamilyId());
            for(Person p : people){
                if(TimeUnit.DAYS.convert(System.currentTimeMillis()-p.getBirthdayDate().getTime(),TimeUnit.MILLISECONDS)/365>7){
                    amount+= appTax.getAppBaseTax();
                }
            }
        }
        long uid = LocalDate.now().getYear()*100+LocalDate.now().getMonthValue();
        TaxesHistory taxes = new TaxesHistory(uid,app.getApartmentId(), app.getBuildingId(),amount,true);
        if(TaxesHistoryDAO.getTaxesHistory(taxes.getTaxId(),taxes.getApartmentId(),taxes.getBuildingId())==null){
            TaxesHistoryDAO.saveTaxesHistory(taxes);
        }
    }
}
