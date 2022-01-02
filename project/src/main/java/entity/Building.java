package entity;

import configuration.ConfigNames;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name= ConfigNames.Building.Table)
public class Building implements Serializable {

    @Id
    @SequenceGenerator(name="genbuild", sequenceName = "generatorbuildings")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generatorbuildings")
    @Column(name= ConfigNames.Building.Id, nullable = false)
    private long buildingId;

    @Column(name=ConfigNames.Building.Address, nullable = false)
    private String address;

    @Column(name=ConfigNames.Building.NumApartments, nullable = false)
    private int numberOfApartments;

    @Column(name=ConfigNames.Building.NumFloors, nullable = false)
    private int numberOfFloors;

    @Column(name=ConfigNames.Building.AreaTotal, nullable = false)
    private double areaTotal;

    @Column(name=ConfigNames.Building.AreaCommon, nullable = false)
    private  double areaCommon;


    @OneToOne (mappedBy = ConfigNames.Building.Table, fetch = FetchType.LAZY)
    private entity.Taxes taxes;

    @OneToOne (mappedBy = ConfigNames.Building.Table, fetch = FetchType.LAZY)
    private entity.EmployeeBuilding employee_building;

    @OneToMany (mappedBy = ConfigNames.Building.Table, fetch = FetchType.LAZY)
    private List<Apartment> apartment;

    public Building(){

    }

    public Building(long buildingId, String address, int numberOfApartments, int numberOfFloors, double areaTotal, double areaCommon){
        this.setBuildingId(buildingId);
        this.setAddress(address);
        this.setNumberOfApartments(numberOfApartments);
        this.setNumberOfFloors(numberOfFloors);
        this.setAreaTotal(areaTotal);
        this.setAreaCommon(areaCommon);
    }

    public Building(String address, int numberOfApartments, int numberOfFloors, double areaTotal, double areaCommon){

        this.setAddress(address);
        this.setNumberOfApartments(numberOfApartments);
        this.setNumberOfFloors(numberOfFloors);
        this.setAreaTotal(areaTotal);
        this.setAreaCommon(areaCommon);
    }


    private void setBuildingId(long id){
        if(id<=0) throw new IllegalArgumentException("Id for building must be positive");
        this.buildingId=id;
    }


    private void setAddress(String address){
        if(address.isBlank()) throw new IllegalArgumentException("Address cannot be blank!");
        this.address=address;
    }

    private void setNumberOfApartments(int numberOfApartments){
        if(numberOfApartments<=0) throw new IllegalArgumentException("There must be at least one apartment in the building!");
        this.numberOfApartments=numberOfApartments;
    }

    private void setNumberOfFloors(int numberOfFloors){
        if(numberOfFloors<=0) throw new IllegalArgumentException("There must be at least one floor in the building!");
        this.numberOfFloors=numberOfFloors;
    }

    private void setAreaTotal(double areaTotal){
        if(areaTotal<=0) throw new IllegalArgumentException("Total area must be positive");
        this.areaTotal=areaTotal;
    }

    private void setAreaCommon(double areaCommon){
        if(areaCommon<=0) throw new IllegalArgumentException("Common area must be positive");
        this.areaCommon=areaCommon;
    }

    public  long getBuildingId(){return this.buildingId;}

    public String getAddress(){return this.address;}

    public int getNumberOfApartments(){return this.numberOfApartments;}

    public int getNumberOfFloors(){return this.numberOfFloors;}

    public double getAreaTotal(){return this.areaTotal;}

    public double getAreaCommon(){return this.areaCommon;}

    @Override
    public String toString() {
        return "Building{" +
                "buildingId=" + buildingId +
                ", address='" + address + '\'' +
                ", numberOfApartments=" + numberOfApartments +
                ", numberOfFloors=" + numberOfFloors +
                ", areaTotal=" + areaTotal +
                ", areaCommon=" + areaCommon +
                '}';
    }
}
