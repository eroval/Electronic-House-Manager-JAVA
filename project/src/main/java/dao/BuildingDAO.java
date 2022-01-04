package dao;

import entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
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

    public static List<Apartment> getAssociatedApartments(Building building){
        return ApartmentDAO.getAllApartments(building.getBuildingId());
    }

    public static long getNumberOfAssociatedApartments(long buildingId){
        return ApartmentDAO.getNumberOfApartmentsByBuilding(buildingId);
    }

    public static long getTotalNumberOfApartments(long buildingId){
        Building b = BuildingDAO.getBuilding(buildingId);
        System.out.println("WHAAAAAAT");
        System.out.println(b.getNumberOfApartments());
        System.out.println("WHAAAAAAT");
        return b.getNumberOfApartments();
    }

    public static boolean checkAvailableApartments(long buildingId){
        return getNumberOfAssociatedApartments(buildingId)<getTotalNumberOfApartments(buildingId);
    }

    public static List<Apartment> getAssociatedApartments(long buildingId){
        return ApartmentDAO.getAllApartments(buildingId);
    }

    public static double calculatePrice(Apartment app, Taxes tax){
        double amount=0;
        try {
            if (app.getLandlordId() != null) {
                amount = tax.getBaseTax() * app.getArea();
            }
            if (app.getFamilyId() != null) {
                List<Person> people = FamilyDAO.getAllFamilyMembers(app.getFamilyId());
                for (Person p : people) {
                    if (TimeUnit.DAYS.convert(System.currentTimeMillis() - p.getBirthdayDate().getTime(), TimeUnit.MILLISECONDS) / 365 > 7) {
                        amount += tax.getAppBaseTax();
                    }
                }
                if (FamilyDAO.getPet(app.getFamilyId())) {
                    amount += tax.getAppPetTax();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return amount;
    }

    public static double calculatePrice(long apartmentId, long buildingId){
        double amount=0;
        try {
            Apartment app = ApartmentDAO.getApartment(apartmentId, buildingId);
            Taxes appTax = TaxesDAO.getTaxes(app.getBuildingId());
            if (app.getLandlordId() != null) {
                amount = appTax.getBaseTax() * app.getArea();
            }
            if (app.getFamilyId() != null) {
                List<Person> people = FamilyDAO.getAllFamilyMembers(app.getFamilyId());
                for (Person p : people) {
                    if (TimeUnit.DAYS.convert(System.currentTimeMillis() - p.getBirthdayDate().getTime(), TimeUnit.MILLISECONDS) / 365 > 7) {
                        amount += appTax.getAppBaseTax();
                    }
                }
                if (FamilyDAO.getPet(app.getFamilyId())) {
                    amount += appTax.getAppPetTax();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return amount;
    }

    public static double calculateBuildingPriceHistory(long buildingId){
        double amount = 0;
        try {
            List<TaxesHistory> taxesHistories = TaxesHistoryDAO.getAllBelongingToBuilding(buildingId);
            for (TaxesHistory th : taxesHistories) {
                if (th.getPaid()) {
                    amount += th.getAmount();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return amount;
    }

    public static void paySpecificTax(long apartmentId, long buildingId){
        try {
            Apartment app = ApartmentDAO.getApartment(apartmentId, buildingId);
            Taxes appTax = TaxesDAO.getTaxes(app.getBuildingId());
            double amount = BuildingDAO.calculatePrice(apartmentId, buildingId);
            long uid = LocalDate.now().getYear() * 100 + LocalDate.now().getMonthValue();
            TaxesHistory taxes = new TaxesHistory(uid, app.getApartmentId(), app.getBuildingId(), amount, true);
            if (TaxesHistoryDAO.getTaxesHistory(taxes.getTaxId(), taxes.getApartmentId(), taxes.getBuildingId()) == null) {
                TaxesHistoryDAO.saveTaxesHistory(taxes);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static List<Person> peopleByNameAndAgeAsc(){
        List<Person> people = new ArrayList<>();
        try {
            List<Building> buildings = BuildingDAO.readBuildings();
            for (Building building : buildings) {
                List<Apartment> apartments = BuildingDAO.getAssociatedApartments(building);
                for (Apartment app : apartments) {
                    if (app.getFamilyId() != null) {
                        people.addAll(FamilyDAO.getAllFamilyMembers(app.getFamilyId()));
                    }
                }
            }
            Collections.sort(people, new Comparator<Person>() {
                @Override
                public int compare(Person o1, Person o2) {
                    if (o1.getFName().toLowerCase().compareTo(o2.getFName().toLowerCase()) == 0) {
                        return -o1.getBirthdayDate().compareTo(o2.getBirthdayDate());
                    }
                    return o1.getFName().toLowerCase(Locale.ROOT).compareTo(o2.getFName().toLowerCase(Locale.ROOT));
                }
            });
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return people;
    }

    public static List<Person> peopleByNameAndAgeDsc(){
        List<Person> people = new ArrayList<>();
        try {
            List<Building> buildings = BuildingDAO.readBuildings();
            for (Building building : buildings) {
                List<Apartment> apartments = BuildingDAO.getAssociatedApartments(building);
                for (Apartment app : apartments) {
                    if (app.getFamilyId() != null) {
                        people.addAll(FamilyDAO.getAllFamilyMembers(app.getFamilyId()));
                    }
                }
            }
            Collections.sort(people, new Comparator<Person>() {
                @Override
                public int compare(Person o1, Person o2) {
                    if (o1.getFName().toLowerCase().compareTo(o2.getFName().toLowerCase()) == 0) {
                        return o1.getBirthdayDate().compareTo(o2.getBirthdayDate());
                    }
                    return -o1.getFName().toLowerCase(Locale.ROOT).compareTo(o2.getFName().toLowerCase(Locale.ROOT));
                }
            });
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return people;
    }

    public static List<Person> getAllPeopleInTheBuilding(long buildingId){
        List<Person> people = new ArrayList<>();
        try {
        List<Apartment> apartments = ApartmentDAO.getAllApartments(buildingId);
        for(Apartment app : apartments) {
            if (app.getFamilyId() != null) {
                    people.addAll(FamilyDAO.getAllFamilyMembers(app.getFamilyId()));
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return people;
    }

    public static List<TaxesHistory> getAllUnpaidTaxes(long buildingId){
        return TaxesHistoryDAO.getAllUnpaidTaxes(buildingId);
    }

    public static List<TaxesHistory> getAllPaidTaxes(long buildingId){
        return TaxesHistoryDAO.getAllPaidTaxes(buildingId);
    }

    public static void deleteAll(){
        List<Building> buildings = BuildingDAO.readBuildings();
        for(Building building : buildings){
            BuildingDAO.deleteBuilding(building);
        }
    }
}
