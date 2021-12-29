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
        Owner owner = new Owner("7602040567","Stefan","Landzhev");
        Owner owner2= new Owner("7503040567","Angel","Krustev");
        Owner owner3= new Owner("6523052367","Petar","Petkov");
        Owner owner4= new Owner("8523124456","Stefan","Ivanov");
        Owner owner5= new Owner("7702054456","Isaac","Clarke");

        List<Owner> list= Arrays.asList(owner, owner2, owner3, owner4, owner5);

        try{
            OwnerDAO.deleteOwners(list);
            OwnerDAO.saveOwners(list);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static  void preloadCompanies(){
        Company company = new Company(1,"Cisco","Sofia");
        Company company2 = new Company(2,"DXC","Sofia");
        Company company3 = new Company(3,"Hyperscience","Sofia");
        Company company4 = new Company(4,"Uber","Sofia");
        Company company5 = new Company(5,"IBM","Sofia");

        List<Company> list = Arrays.asList(company, company2, company3, company4, company5);
        try {
            CompanyDAO.deleteCompanies(list);
            CompanyDAO.saveCompanies(list);
        }
        catch (Exception e){
            System.out.println(e.getStackTrace());
        }
    }

    public static  void preloadOwnerCompanies(){
        OwnerCompany oc = new OwnerCompany(1,7);
        OwnerCompany oc2 = new OwnerCompany(1,1);

        OwnerCompanyDAO.saveOrUpdateOwnerCompany(oc);
    }
}
