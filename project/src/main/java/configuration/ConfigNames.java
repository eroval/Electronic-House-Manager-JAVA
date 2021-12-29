package configuration;

public class ConfigNames {

    public static class Owner{
        public static final String Table = "owner";
        public static final String Id = "owner_id";
        public static final String EGN = "egn";
        public static final String FName = "f_name";
        public static final String LName = "l_name";
    }

    public static class Company{
        public static final String Table = "company";
        public static final String Id = "company_id";
        public static final String Name = "name";
        public static final String Address = "address";
    }

    public static class OwnerCompany{
        public static final String Table = "owner_company";
        public static final String IdCompany = "company_id";
        public static final String IdOwner = "owner_id";
    }

    public static class Employee{
        public static final String Table = "employee";
        public static final String Id = "employee_id";
        public static final String FName = "f_name";
        public static final String LName = "l_name";
        public static final String IdCompany = "company_id";
        public static final String IdOwner = "owner_id";
    }

}
