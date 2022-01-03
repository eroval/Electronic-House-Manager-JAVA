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
//        PreloadTables.load();
//        BuildingDAO.paySpecificTax(7,3);
//        System.out.println(OwnerCompanyDAO.getSortedCopmaniesByRevenueAsc());
//        System.out.println(OwnerCompanyDAO.getSortedCopmaniesByRevenueDsc());
//        System.out.println(OwnerCompanyDAO.getSortedEmployeesAsc(1));
//        System.out.println(OwnerCompanyDAO.getSortedEmployeesDsc(1));
//        System.out.println(BuildingDAO.peopleByNameAndAgeAsc());
//        System.out.println(BuildingDAO.peopleByNameAndAgeDsc());
    }

}
