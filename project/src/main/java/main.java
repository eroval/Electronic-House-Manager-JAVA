import PreloadClasses.PreloadTables;
import dao.*;
import entity.*;

import java.util.List;

public class main {
    public static void main(String args[]) {
        //PreloadTables.load();
        //System.out.println(EmployeeDAO.getEmployeesBelongingToOwnerCompany(1,2));
        //EmployeeBuildingDAO.readEmployeeBuildings();
//        List<OwnerCompany> companies = OwnerCompanyDAO.readOwnerCompanies();
//        Building b = new Building("New Addressho", 20, 30, 500, 10);
//        BuildingDAO.saveBuilding(b);
//        OwnerCompanyDAO.addBuilding(companies.get(0), b);
        System.out.println(PersonFamilyDAO.getPersonFamily("6505063276", Long.valueOf(1)));//FamilyDAO.getAllFamilyMembers(1));
    }

}
