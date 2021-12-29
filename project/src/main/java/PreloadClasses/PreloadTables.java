package PreloadClasses;

import configuration.ConfigNames;
import dao.CompanyDAO;
import dao.OwnerCompanyDAO;
import dao.OwnerDAO;
import entity.Company;
import entity.Owner;
import entity.OwnerCompany;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PreloadTables {

    public static void preloadOwners(){
        Owner owner = new Owner(1,"7602040567","Stefan","Landzhev");
        Owner owner2= new Owner(2,"7503040567","Angel","Krustev");
        Owner owner3= new Owner(3,"6523052367","Petar","Petkov");
        Owner owner4= new Owner(4,"8523124456","Stefan","Ivanov");
        Owner owner5= new Owner(5,"7702054456","Isaac","Clarke");

        List<Owner> list= Arrays.asList(owner, owner2, owner3, owner4, owner5);
        OwnerDAO.saveOwners(list);
    }

    public static  void preloadCompanies(){
        Company company = new Company("Cisco","Sofia");
        Company company2 = new Company("DXC","Sofia");
        Company company3 = new Company("Hyperscience","Sofia");
        Company company4 = new Company("Uber","Sofia");
        Company company5 = new Company("IBM","Sofia");

        List<Company> list = Arrays.asList(company, company2, company3, company4, company5);
        CompanyDAO.saveCompanies(list);
    }

    public static  void preloadOwnerCompanies(){
        OwnerCompany oc = new OwnerCompany(1,3);
        OwnerCompany oc2 = new OwnerCompany(1,4);
        OwnerCompany oc3 = new OwnerCompany(2,1);
        OwnerCompany oc4 = new OwnerCompany(1,5);
        OwnerCompany oc5 = new OwnerCompany(5,2);

        List<OwnerCompany> list = Arrays.asList(oc, oc2, oc3, oc4, oc5);
        OwnerCompanyDAO.saveOwnerCompanies(list);
    }

    public static void load(){
        preloadOwners();
        preloadCompanies();
        preloadOwnerCompanies();
    }

    public static void customLoad(){

    }
}
