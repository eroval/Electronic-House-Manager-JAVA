package entity;

import IdClasses.ApartmentId;
import configuration.ConfigNames;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@IdClass(ApartmentId.class)
@Table(name = ConfigNames.Apartment.Table)
public class Apartment implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "_generator_apartments")
    @Column(name=ConfigNames.Apartment.IdApp, nullable = false)
    private long apartmentId;

    @Id
    @Column(name=ConfigNames.Apartment.IdBuilding, nullable = false)
    private long buildingId;

    @Column(name=ConfigNames.Apartment.Area, nullable = false)
    private double area;

    @Column(name=ConfigNames.Apartment.OIdLandlord, nullable = true)
    private String landlordId;

    @Column(name=ConfigNames.Apartment.OIdFamily, nullable = true)
    private String familyName;

    @ManyToOne
    @JoinColumn(name=ConfigNames.Apartment.IdBuilding, referencedColumnName = ConfigNames.Building.Id, insertable = false, updatable = false)
    private Building building;

    @OneToMany (mappedBy = ConfigNames.Apartment.Table, fetch = FetchType.LAZY)
    private List<TaxesHistory> taxes_history;

    public Apartment(){

    }

    public Apartment(long apartmentId, long buildingId, double area, String landlordId, String familyName){
        this.setApartmentId(apartmentId);
        this.setBuildingId(buildingId);
        this.setArea(area);
        this.setLandlordId(landlordId);
        this.setFamilyName(familyName);
    }

    public Apartment(long buildingId, double area, String landlordId, String familyName){
        this.setBuildingId(buildingId);
        this.setArea(area);
        this.setLandlordId(landlordId);
        this.setFamilyName(familyName);
    }

    private void setApartmentId(long apartmentId){
        if(apartmentId<=0) throw new IllegalArgumentException("Id for apartment must be positive");
        this.apartmentId=apartmentId;
    }

    private void setBuildingId(long buildingId){
        this.buildingId=buildingId;
    }

    private void setArea(double area){
        if(area<=0) throw new IllegalArgumentException("Area must be positive");
        this.area=area;
    }

    private void setLandlordId(String landlordId){
        this.landlordId=landlordId;
    }

    private void setFamilyName(String familyName){
        this.familyName=familyName;
    }

    public long getApartmentId() { return this.apartmentId; }

    public long getBuildingId() { return this.buildingId; }

    public double getArea() {return this.area; }

    public String getLandlordId() { return this.landlordId; }

    public String getFamilyName() { return this.familyName; }

    @Override
    public String toString() {
        return "Apartment{" +
                "apartmentId=" + apartmentId +
                ", buildingId=" + buildingId +
                ", area=" + area +
                ", landlordId='" + landlordId + '\'' +
                ", familyName='" + familyName + '\'' +
                '}';
    }
}
