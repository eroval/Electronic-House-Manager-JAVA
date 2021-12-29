import dao.CompanyDAO;
import entity.Company;
import java.util.List;

public class main {
    public static void main(String args[]) {
        Company companyOne = new Company(1,"best", "company");
        CompanyDAO.saveOrUpdateCompany(companyOne);
    }

}
