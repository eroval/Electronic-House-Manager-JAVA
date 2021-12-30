import PreloadClasses.PreloadTables;
import dao.*;
import entity.*;

import java.util.List;

public class main {
    public static void main(String args[]) {
        Employee b = EmployeeDAO.getEmployee(1,1,2);
        Owner o = OwnerDAO.getOwner(1);
        Company c = CompanyDAO.getCompany(1);
        System.out.println("COOLCOOLCOOLCOOLCOOLCOOLCOOLCOOLCOOLCOOLCOOLCOOLCOOLCOOLCOOLCOOLCOOL\n");
        System.out.println(b);
        System.out.println(o);
        System.out.println(c);
        System.out.println("COOLCOOLCOOLCOOLCOOLCOOLCOOLCOOLCOOLCOOLCOOLCOOLCOOLCOOLCOOLCOOLCOOL\n");
    }

}
