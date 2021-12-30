package entity;

import IdClasses.EmployeeId;
import configuration.ConfigNames;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@IdClass(EmployeeId.class)
@Table(name= ConfigNames.Employee.Table)
public class Employee  implements Serializable {

    @Id
    @GeneratedValue
    @Column(name= ConfigNames.Employee.Id, nullable = false)
    private long employeeId;

    @Column(name=ConfigNames.Employee.FName, nullable = false)
    private String fName;

    @Column(name=ConfigNames.Employee.LName, nullable = false)
    private String lName;

    @Id
    @Column(name = ConfigNames.Employee.IdOwner, nullable = false)
    private long ownerId;

    @Id
    @Column(name = ConfigNames.Employee.IdCompany, nullable = false)
    private long companyId;


    @ManyToOne
    @JoinColumns({
            @JoinColumn (name=ConfigNames.Employee.IdOwner, insertable = false, updatable = false),
            @JoinColumn (name=ConfigNames.Employee.IdCompany, insertable = false, updatable = false)
    })
    private entity.OwnerCompany owner_company;

    @OneToMany (mappedBy = ConfigNames.Employee.Table)
    private List<EmployeeBuilding> employee_building;

    public Employee(){

    }

    public Employee(long employeeId, String fName, String lName,long ownerId, long companyId){
        this.setEmployeeId(employeeId);
        this.setFName(fName);
        this.setLName(lName);
        this.setOwnerId(ownerId);
        this.setCompanyId(companyId);
    }

    public Employee(String fName, String lName,long ownerId, long companyId){
        this.setEmployeeId(employeeId);
        this.setFName(fName);
        this.setLName(lName);
        this.setOwnerId(ownerId);
        this.setCompanyId(companyId);
    }

    private void setFName(String fName){
        if(fName.isBlank()) throw new IllegalArgumentException("First name cannot be blank!");
        this.fName=fName;
    }

    private void setLName(String lName){
        if(lName.isBlank()) throw new IllegalArgumentException("Last name cannot be blank!");
        this.lName=lName;
    }

    private void setEmployeeId(long id){
        if(id<=0) throw new IllegalArgumentException("Id for employee must be positive");
        this.employeeId=id;
    }

    private void setOwnerId(long id){
        if(id<=0) throw new IllegalArgumentException("Id for owner must be positive");
        this.ownerId=id;
    }

    private void setCompanyId(long id){
        if(id<=0) throw new IllegalArgumentException("Id for company must be positive");
        this.companyId=id;
    }

    public long getEmployeeId(){
        return this.employeeId;
    }

    public String getFName(){
        return this.fName;
    }

    public String getLName(){
        return this.lName;
    }

    public long getOwnerId(){
        return this.ownerId;
    }

    public long getCompanyId(){
        return this.companyId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", ownerId=" + ownerId +
                ", companyId=" + companyId +
                ", owner_company=" + owner_company +
                '}';
    }
}