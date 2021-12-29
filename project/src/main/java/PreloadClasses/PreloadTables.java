package PreloadClasses;

import dao.CompanyDAO;
import dao.OwnerDAO;
import entity.Company;
import entity.Owner;

public class PreloadTables {
    public static void preloadOwners(){
        Owner owner = new Owner("7602040567","Stefe","Landzhev");
        Owner owner2= new Owner("7503040567","Angel","Krustev");
        Owner owner3= new Owner("6523052367","Petar","Petkov");
        Owner owner4= new Owner("8523124456","Stefan","Ivanov");
        Owner owner5= new Owner("7702054456","Isaac","Clarke");

//        OwnerDAO.saveOrUpdateOwner(owner);
//        OwnerDAO.saveOrUpdateOwner(owner2);
//        OwnerDAO.saveOrUpdateOwner(owner3);
//        OwnerDAO.saveOrUpdateOwner(owner4);
//        OwnerDAO.saveOrUpdateOwner(owner5);

        OwnerDAO.updateOwnerByEgn(owner.getEgn(),owner);
    }

    public static  void preloadCompanies(){
        Company company = new Company("Cisco","Sofia");
        Company company2 = new Company("DXC","Sofia");
        Company company3 = new Company("Hyperscience","Sofia");
        Company company4 = new Company("Uber","Sofia");
        Company company5 = new Company("IBM","Sofia");

        try {
            CompanyDAO.saveCompany(company);
            CompanyDAO.saveCompany(company2);
            CompanyDAO.saveCompany(company3);
            CompanyDAO.saveCompany(company4);
            CompanyDAO.saveCompany(company5);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
