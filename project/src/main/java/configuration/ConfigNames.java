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

    public static class Building{
        public static final String Table = "building";
        public static final String Id = "building_id";
        public static final String Address = "address";
        public static final String NumApartments = "number_of_apartments";
        public static final String NumFloors = "number_of_floors";
        public static final String AreaTotal = "area_total";
        public static final String AreaCommon = "area_common";
    }

    public  static class Taxes{
        public static final String Table = "taxes";
        public static final String Id = "building_id";
        public static final String BaseTax = "base_tax";
        public static final String AppBaseTax = "app_base_tax";
        public static final String AppPetTax = "app_pet_tax";
        public static final String TaxDay = "tax_due_date";
    }

    public static class EmployeeBuilding{
        public static final String Table="employee_building";
        public static final String IdBuilding="building_id";
        public static final String IdEmployee="employee_id";
        public static final String IdCompany = "company_id";
        public static final String IdOwner = "owner_id";
    }

}
