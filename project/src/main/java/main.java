import PreloadClasses.PreloadTables;
import dao.CompanyDAO;
import dao.OwnerDAO;
import entity.Company;
import entity.Owner;

import java.util.List;

public class main {
    public static void main(String args[]) {
        //PreloadTables.preloadCompanies();
        PreloadTables.preloadOwners();
    }

}
