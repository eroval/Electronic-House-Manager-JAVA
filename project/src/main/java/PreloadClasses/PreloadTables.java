package PreloadClasses;

import dao.CompanyDAO;
import dao.EmployeeDAO;
import dao.OwnerCompanyDAO;
import dao.OwnerDAO;
import entity.Company;
import entity.Employee;
import entity.Owner;
import entity.OwnerCompany;

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
        Employee e = new Employee(1,"Larry","Gasparov",2,1);
        Employee e2 = new Employee(2,"Garry", "Saddov",2,1);
        Employee e3 = new Employee(3,"Sad", "Lothar",2,1);
        Employee e4 = new Employee(4,"Sad", "Lothar",2,1);
        Employee e5 = new Employee(5,"Looper", "Dooper",1,5);

        List<Employee> list = Arrays.asList(e,e2,e3,e4,e5);
        EmployeeDAO.saveEmployees(list);
    }

    public static void load(){
        preloadOwners();
        preloadCompanies();
        preloadOwnerCompanies();
        preloadEmployees();
    }

    public static void customLoad(){

    }
}
