import dao.CompanyDAO;
import entity.Company;
import java.util.List;

public class main {
    public static void main(String args[]) {
        Company company = new Company(1,"best company","Augusta Mayaza 24");
        CompanyDAO.saveCompany(company);
    }

}
