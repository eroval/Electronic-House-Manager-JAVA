import CustomEvents.PaymentEvent;
import PreloadClasses.PreloadTables;
import dao.*;
import entity.*;

import java.util.List;

public class main {
    private static void checkTaxesPeriodically(){
        PaymentEvent p = new PaymentEvent();
        Thread t = new Thread(p);
        t.start();
    }

    public static void main(String args[]) {
        //checkTaxesPeriodically();
        //1-6
        PreloadTables.load();
        //System.out.println(BuildingDAO.getTotalNumberOfApartments(1));
        //System.out.println(BuildingDAO.getNumberOfAssociatedApartments(1));
        //System.out.println(BuildingDAO.checkAvailableApartments(1));
        //7
//        BuildingDAO.paySpecificTax(2,3);
        //8
//        System.out.println(OwnerCompanyDAO.getSortedCopmaniesByRevenueAsc());
//        System.out.println(OwnerCompanyDAO.getSortedCopmaniesByRevenueDsc());
//        System.out.println(OwnerCompanyDAO.getSortedEmployeesAsc(1));
//        System.out.println(OwnerCompanyDAO.getSortedEmployeesDsc(1));
//        System.out.println(BuildingDAO.peopleByNameAndAgeAsc());
//        System.out.println(BuildingDAO.peopleByNameAndAgeDsc());
        //9
//        System.out.println(EmployeeBuildingDAO.getEmployeeBuildingAssociation(1));
//        System.out.println(BuildingDAO.getAssociatedApartments(1));
//        List<Person> p = BuildingDAO.getAllPeopleInTheBuilding(1);
//        System.out.println(BuildingDAO.getAllUnpaidTaxes(3));
//        System.out.println(EmployeeBuildingDAO.getAllUnpaidTaxes(5));
//        System.out.println(OwnerCompanyDAO.getAllUnpaidTaxes(5));
//        System.out.println(BuildingDAO.getAllPaidTaxes(3));
//        System.out.println(EmployeeBuildingDAO.getAllPaidTaxes(5));
//        System.out.println(OwnerCompanyDAO.getAllPaidTaxes(5));
        //10 //actually works during each update and create of taxhistory
        System.out.println(BuildingDAO.getNumberOfAssociatedApartments(3));


    }

}
