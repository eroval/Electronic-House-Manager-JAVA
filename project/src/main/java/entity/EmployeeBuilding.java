package entity;

import IdClasses.EmployeeBuildingId;
import configuration.ConfigNames;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(EmployeeBuildingId.class)
@Table(name= ConfigNames.EmployeeBuilding.Table)
public class EmployeeBuilding implements Serializable {
    @Id
    @Column(name = ConfigNames.EmployeeBuilding.IdBuilding, nullable = false)
    private long buildingId;

    @Id
    @Column(name = ConfigNames.EmployeeBuilding.IdEmployee, nullable = false)
    private long employeeId;

    @Id
    @Column(name = ConfigNames.EmployeeBuilding.IdCompany, nullable = false)
    private long companyId;

    @Id
    @Column(name = ConfigNames.EmployeeBuilding.IdOwner, nullable = false)
    private long ownerId;

    @OneToOne
    @JoinColumn (name = ConfigNames.EmployeeBuilding.IdBuilding, referencedColumnName = ConfigNames.Building.Id, insertable = false, updatable = false)
    private entity.Building building;

    @ManyToOne
    @JoinColumns ({
        @JoinColumn(name=ConfigNames.EmployeeBuilding.IdEmployee, referencedColumnName = ConfigNames.Employee.Id, insertable = false, updatable = false),
        @JoinColumn(name=ConfigNames.EmployeeBuilding.IdCompany, referencedColumnName = ConfigNames.Employee.IdCompany,insertable = false, updatable = false),
        @JoinColumn(name=ConfigNames.EmployeeBuilding.IdOwner, referencedColumnName = ConfigNames.Employee.IdOwner,insertable = false, updatable = false)
    })
    private entity.Employee employee;


    public EmployeeBuilding(){

    }

    public EmployeeBuilding(long buildingId, long employeeId, long companyId, long ownerId){
        this.buildingId=buildingId;
        this.employeeId=employeeId;
        this.companyId=companyId;
        this.ownerId=ownerId;
    }

    public long getBuildingId(){return this.buildingId;}

    public long getEmployeeId(){return this.employeeId;}

    public long getCompanyId(){return this.companyId;}

    public long getOwnerId(){return this.ownerId;}

}
