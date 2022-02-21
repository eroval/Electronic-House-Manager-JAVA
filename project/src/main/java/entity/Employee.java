package entity;

import IdClasses.EmployeeId;
import configuration.ConfigNames;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;

@Entity
@IdClass(EmployeeId.class)
@Table(name= ConfigNames.Employee.Table)
public class Employee  implements Serializable {

    @Id
    @SequenceGenerator(name="genemp", sequenceName = "_generatoremployees")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_generatoremployees")
    @Column(name= ConfigNames.Employee.Id, nullable = false)
    private long employeeId;

    @Id
    @Column(name = ConfigNames.Employee.IdCompany, nullable = false)
    private long companyId;

    @Id
    @Column(name = ConfigNames.Employee.IdOwner, nullable = false)
    private long ownerId;

    @Column(name=ConfigNames.Employee.FName, nullable = false)
    private String fName;

    @Column(name=ConfigNames.Employee.LName, nullable = false)
    private String lName;



    @ManyToOne
    @JoinColumns({
            @JoinColumn (name=ConfigNames.Employee.IdCompany,referencedColumnName = ConfigNames.OwnerCompany.IdCompany, insertable = false, updatable = false),
            @JoinColumn (name=ConfigNames.Employee.IdOwner,referencedColumnName = ConfigNames.OwnerCompany.IdOwner, insertable = false, updatable = false)

    })
    private entity.OwnerCompany owner_company;

    @OneToMany (mappedBy = ConfigNames.Employee.Table, fetch = FetchType.LAZY)
    private List<EmployeeBuilding> employee_building;

    public Employee(){

    }

    public Employee(long employeeId, long companyId, long ownerId, String fName, String lName){
        this.setEmployeeId(employeeId);
        this.setCompanyId(companyId);
        this.setOwnerId(ownerId);
        this.setFName(fName);
        this.setLName(lName);
    };

    public Employee(long companyId, long ownerId, String fName, String lName){
        this.setCompanyId(companyId);
        this.setOwnerId(ownerId);
        this.setFName(fName);
        this.setLName(lName);
    };

    private void setEmployeeId(long id){
        if(id<=0) throw new IllegalArgumentException("Id for employee must be positive");
        this.employeeId=id;
    }

    private void setCompanyId(long id){
        if(id<=0) throw new IllegalArgumentException("Id for company must be positive");
        this.companyId=id;
    }

    private void setOwnerId(long id){
        if(id<=0) throw new IllegalArgumentException("Id for owner must be positive");
        this.ownerId=id;
    }

    private void setFName(String fName){
        if(fName.isBlank()) throw new IllegalArgumentException("First name cannot be blank!");
        this.fName=fName;
    }

    private void setLName(String lName){
        if(lName.isBlank()) throw new IllegalArgumentException("Last name cannot be blank!");
        this.lName=lName;
    }

    public long getEmployeeId(){
        return this.employeeId;
    }

    public long getCompanyId(){
        return this.companyId;
    }

    public long getOwnerId(){
        return this.ownerId;
    }

    public String getFName(){
        return this.fName;
    }

    public String getLName(){
        return this.lName;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", companyId=" + companyId +
                ", ownerId=" + ownerId +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                '}';
    }

}