import PreloadClasses.PreloadTables;
import dao.*;
import entity.*;

import java.util.List;

public class main {
    public static void main(String args[]) {
//        PreloadTables.load();
        Employee b = EmployeeDAO.getEmployee(2,2,1);
        //Employee b = new Employee(1,"Lary", "Gasparov",1,2);
        //EmployeeDAO.saveOrUpdateEmployee(b);
        Owner o = OwnerDAO.getOwner(1);
        Company c = CompanyDAO.getCompany(2);
        OwnerCompany a = OwnerCompanyDAO.getOwnerCompany(2,1);
        System.out.println("COOLCOOLCOOLCOOLCOOLCOOLCOOLCOOLCOOLCOOLCOOLCOOLCOOLCOOLCOOLCOOLCOOL\n");
        System.out.println(a);
        System.out.println(b);
        System.out.println(o);
        System.out.println(c);
        System.out.println("COOLCOOLCOOLCOOLCOOLCOOLCOOLCOOLCOOLCOOLCOOLCOOLCOOLCOOLCOOLCOOLCOOL\n");
    }

}
