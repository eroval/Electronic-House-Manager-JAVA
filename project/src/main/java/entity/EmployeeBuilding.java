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

    @OneToOne
    @JoinColumn (name = ConfigNames.EmployeeBuilding.IdBuilding, insertable = false, updatable = false)
    private entity.Building building;

    @ManyToOne
    @JoinColumns ({
        @JoinColumn(name=ConfigNames.EmployeeBuilding.IdEmployee, insertable = false, updatable = false),
        @JoinColumn(name=ConfigNames.EmployeeBuilding.IdCompany, insertable = false, updatable = false),
        @JoinColumn(name=ConfigNames.EmployeeBuilding.IdOwner, insertable = false, updatable = false)
    })
    private entity.Employee employee;


    public EmployeeBuilding(){

    }

    public EmployeeBuilding(long buildingId, long employeeId){
        this.buildingId=buildingId;
        this.employeeId=employeeId;
    }

    public long getBuildingId(){return this.buildingId;}

    public long getEmployeeId(){return this.employeeId;}

}
