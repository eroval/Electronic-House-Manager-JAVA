package PreloadClasses;

import dao.*;
import entity.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class PreloadTables {

    private static void preloadOwners(){
        Owner owner = new Owner(1,"7602040567","Stefan","Landzhev");
        Owner owner2= new Owner(2,"7503040567","Angel","Krustev");
        Owner owner3= new Owner(3,"6505232367","Petar","Petkov");
        Owner owner4= new Owner(4,"8512234456","Stefan","Ivanov");
        Owner owner5= new Owner(5,"7702054456","Isaac","Clarke");

        List<Owner> list= Arrays.asList(owner, owner2, owner3, owner4, owner5);
        OwnerDAO.saveOwners(list);
    }

    private  static  void preloadCompanies(){
        Company company = new Company(1,"Cisco","Sofia");
        Company company2 = new Company(2,"DXC","Sofia");
        Company company3 = new Company(3,"Hyperscience","Sofia");
        Company company4 = new Company(4,"Uber","Sofia");
        Company company5 = new Company(5,"IBM","Sofia");

        List<Company> list = Arrays.asList(company, company2, company3, company4, company5);
        CompanyDAO.saveCompanies(list);
    }

    private static  void preloadOwnerCompanies(){
        OwnerCompany oc = new OwnerCompany(3,1);
        OwnerCompany oc2 = new OwnerCompany(4,1);
        OwnerCompany oc3 = new OwnerCompany(1,2);
        OwnerCompany oc4 = new OwnerCompany(5,1);
        OwnerCompany oc5 = new OwnerCompany(2,5);

        List<OwnerCompany> list = Arrays.asList(oc, oc2, oc3, oc4, oc5);
        OwnerCompanyDAO.saveOwnerCompanies(list);
    }

    private  static void preloadEmployees(){
        Employee e = new Employee(1,1,2,"Larry","Gasparov");
        Employee e2 = new Employee(2,1,2,"Garry", "Saddov");
        Employee e3 = new Employee(3,1,2,"Sad", "Lothar");
        Employee e4 = new Employee(4,1,2,"Sad", "Lothar");
        Employee e5 = new Employee(5,5,1,"Looper", "Dooper");

        List<Employee> list = Arrays.asList(e,e2,e3,e4,e5);
        EmployeeDAO.saveEmployees(list);
    }

    private static void preloadBuildings(){
        Building b = new Building(1,"Sofia", 10, 2, 1000, 40);
        Building b2 = new Building(2,"Sofia", 12, 4, 1200, 10);
        Building b3 = new Building(3,"Sofia", 15, 15, 1536, 35);
        Building b4 = new Building(3,"Sofia", 3, 2, 290, 32);
        Building b5 = new Building(3,"Sofia", 10, 10, 2903, 47);

        List<Building> list = Arrays.asList(b,b2,b3,b4,b5);
        BuildingDAO.saveBuildings(list);
    }

    private static void preloadTaxes(){
        Taxes t = new Taxes(1,20,0.5,10,25);
        Taxes t2 = new Taxes(2,20,0.5,10,25);
        Taxes t3 = new Taxes(3,22,0.8,15,25);
        Taxes t4 = new Taxes(4,26,0.45,12,25);
        Taxes t5 = new Taxes(5,13,1,11,25);

        List<Taxes> list = Arrays.asList(t,t2,t3,t4,t5);
        TaxesDAO.saveTaxess(list);
    }

    private static void preloadEmployeeBuildings(){
        EmployeeBuilding eb = new EmployeeBuilding(1,4,1,2);
        EmployeeBuilding eb2 = new EmployeeBuilding(2,2,1,2);
        EmployeeBuilding eb3 = new EmployeeBuilding(3,5,5,1);
        EmployeeBuilding eb4 = new EmployeeBuilding(5,3,1,2);
        EmployeeBuilding eb5 = new EmployeeBuilding(4,4,1,2);

        List<EmployeeBuilding> list = Arrays.asList(eb,eb2,eb3,eb4,eb5);
        EmployeeBuildingDAO.saveEmployeeBuildings(list);
    }

    private static void preloadApartments(){
        Apartment a = new Apartment(1,30,null,null);
        Apartment a2 = new Apartment(1,53,null,null);
        Apartment a3 = new Apartment(1,55,null,null);
        Apartment a4 = new Apartment(2,65,null,null);
        Apartment a5 = new Apartment(2,87,null,null);
        Apartment a6 = new Apartment(2,103,null,null);

        List<Apartment> list = Arrays.asList(a,a2,a3,a4,a5,a6);
        ApartmentDAO.saveApartments(list);
    }

    private static void preloadTaxesHistory(){
        TaxesHistory t= new TaxesHistory(1,1,1,true);
        TaxesHistory t2= new TaxesHistory(2,2,1,true);
        TaxesHistory t3= new TaxesHistory(3,3,1,true);
        TaxesHistory t4= new TaxesHistory(4,4,2,true);
        TaxesHistory t5= new TaxesHistory(5,6,2,false);

        List<TaxesHistory> list = Arrays.asList(t,t2,t3,t4,t5);
        TaxesHistoryDAO.saveTaxesHistorys(list);
    }

    private static void preloadPersons(){
        Person p = new Person("5405243487","Lazar","Looner","24/05/1954");
        Person p2 = new Person("2607133566","Eugene","Darius","13/07/1926");
        Person p3 = new Person("6505063276","Sandra","Zaralik","06/05/1965");
        Person p4 = new Person("3511083276","Harry","Potter","08/11/1935");
        Person p5 = new Person("4403243276","Peter","Parker","24/03/1944");
        Person p6 = new Person("7001041162","Ivan","Looner","04/01/1970");
        Person p7 = new Person("7002020170","Petar","Darius","02/02/1970");
        Person p8 = new Person("6402206837","Vanya","Zaralik","20/02/1964");
        Person p9 = new Person("8605121040","Loren","Potter","12/05/1986");
        Person p10 = new Person("9506089805","Spider","Man","08/06/1995");

        List<Person> list = Arrays.asList(p, p2, p3, p4, p5, p6, p7, p8, p9, p10);
        PersonDAO.savePersons(list);
    }

    private static void preloadFamilies(){
        Family f = new Family(1,false);
        Family f2 = new Family(2, true);
        Family f3 = new Family(3, true);
        Family f4 = new Family(4, true);

        List<Family> list = Arrays.asList(f, f2, f3, f4);
        FamilyDAO.saveFamilys(list);
    }

    private static void preloadPersonFamilies(){
        PersonFamily f= new PersonFamily("5405243487",1);
        PersonFamily f1= new PersonFamily("2607133566",1);
        PersonFamily f2= new PersonFamily("6505063276",1);
        PersonFamily f3= new PersonFamily("6402206837",2);

        List<PersonFamily> list = Arrays.asList(f,f1,f2,f3);
        PersonFamilyDAO.savePersonFamilys(list);
    }

    private static void preloadLandlords(){
        Landlord l = new Landlord("3511083276");
        Landlord l2 = new Landlord("6402206837");

        List<Landlord> list = Arrays.asList(l, l2);
        LandlordDAO.saveLandlords(list);
    }

    private static void preloadApartmentsWithFamilies(){
        Apartment a = new Apartment(3,30,"3511083276",1);
        Apartment a2 = new Apartment(1,45,"6402206837",2);

        List<Apartment> list = Arrays.asList(a,a2);
        ApartmentDAO.saveApartments(list);
    }


    public static void load(){
        preloadOwners();
        preloadCompanies();
        preloadOwnerCompanies();
        preloadEmployees();
        preloadBuildings();
        preloadTaxes();
        preloadEmployeeBuildings();
        preloadApartments();
        preloadTaxesHistory();
        preloadPersons();
        preloadFamilies();
        preloadPersonFamilies();
        preloadLandlords();
        preloadApartmentsWithFamilies();
    }
}
