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
    @SequenceGenerator(name="genapp", sequenceName = "_generatorapartments")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_generatorapartments")
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
    private Long familyId;

    @OneToMany (mappedBy = ConfigNames.Apartment.Table, fetch = FetchType.LAZY)
    private List<TaxesHistory> taxes_history;

    @ManyToOne
    @JoinColumn(name=ConfigNames.Apartment.IdBuilding, referencedColumnName = ConfigNames.Building.Id, insertable = false, updatable = false)
    private Building building;

    @OneToOne
    @JoinColumn(name=ConfigNames.Apartment.OIdLandlord, referencedColumnName = ConfigNames.Landlord.Id, insertable = false, updatable = false)
    private Landlord landlord;

    @OneToOne
    @JoinColumn(name=ConfigNames.Apartment.OIdFamily, referencedColumnName = ConfigNames.Family.Id, insertable = false, updatable = false)
    private Family family;

    public Apartment(){

    }

    public Apartment(long apartmentId, long buildingId, double area, String landlordId, Long familyId){
        this.setApartmentId(apartmentId);
        this.setBuildingId(buildingId);
        this.setArea(area);
        this.setLandlordId(landlordId);
        this.setFamilyId(familyId);
    }

    public Apartment(long apartmentId, long buildingId, double area, String landlordId, long familyId){
        this.setApartmentId(apartmentId);
        this.setBuildingId(buildingId);
        this.setArea(area);
        this.setLandlordId(landlordId);
        this.setFamilyId(familyId);
    }

    public Apartment(long buildingId, double area, String landlordId, Long familyId){
        this.setBuildingId(buildingId);
        this.setArea(area);
        this.setLandlordId(landlordId);
        this.setFamilyId(familyId);
    }

    public Apartment(long buildingId, double area, String landlordId, long familyId){
        this.setBuildingId(buildingId);
        this.setArea(area);
        this.setLandlordId(landlordId);
        this.setFamilyId(familyId);
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

    private void setFamilyId(Long familyId) { this.familyId=familyId; }

    private void setFamilyId(long familyId) { this.familyId=Long.valueOf(familyId); }



    public long getApartmentId() { return this.apartmentId; }

    public long getBuildingId() { return this.buildingId; }

    public double getArea() {return this.area; }

    public String getLandlordId() { return this.landlordId; }

    public Long getFamilyId() { return this.familyId; }



    @Override
    public String toString() {
        return "Apartment{" +
                "apartmentId=" + apartmentId +
                ", buildingId=" + buildingId +
                ", area=" + area +
                ", landlordId='" + landlordId + '\'' +
                ", familyId='" + familyId + '\'' +
                '}';
    }
}
