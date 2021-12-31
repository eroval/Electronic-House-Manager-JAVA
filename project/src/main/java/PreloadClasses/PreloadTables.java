package PreloadClasses;

import dao.*;
import entity.*;

import java.util.Arrays;
import java.util.List;

public class PreloadTables {

    private static void preloadOwners(){
        Owner owner = new Owner(1,"7602040567","Stefan","Landzhev");
        Owner owner2= new Owner(2,"7503040567","Angel","Krustev");
        Owner owner3= new Owner(3,"6523052367","Petar","Petkov");
        Owner owner4= new Owner(4,"8523124456","Stefan","Ivanov");
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
        OwnerCompany oc = new OwnerCompany(1,3);
        OwnerCompany oc2 = new OwnerCompany(1,4);
        OwnerCompany oc3 = new OwnerCompany(2,1);
        OwnerCompany oc4 = new OwnerCompany(1,5);
        OwnerCompany oc5 = new OwnerCompany(5,2);

        List<OwnerCompany> list = Arrays.asList(oc, oc2, oc3, oc4, oc5);
        OwnerCompanyDAO.saveOwnerCompanies(list);
    }

    private  static void preloadEmployees(){
        Employee e = new Employee(1,2,1,"Larry","Gasparov");
        Employee e2 = new Employee(2,2,1,"Garry", "Saddov");
        Employee e3 = new Employee(3,2,1,"Sad", "Lothar");
        Employee e4 = new Employee(4,2,1,"Sad", "Lothar");
        Employee e5 = new Employee(5,1,5,"Looper", "Dooper");

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
        EmployeeBuilding eb = new EmployeeBuilding(1,4,2,1);
        EmployeeBuilding eb2 = new EmployeeBuilding(2,2,2,1);
        EmployeeBuilding eb3 = new EmployeeBuilding(3,5,1,5);
        EmployeeBuilding eb4 = new EmployeeBuilding(5,3,2,1);
        EmployeeBuilding eb5 = new EmployeeBuilding(4,4,2,1);

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

        List<Apartment> list = Arrays.asList(a,a2,a3,a4,a5,a6 );
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
    }

    public static void customLoad(){

    }
}
