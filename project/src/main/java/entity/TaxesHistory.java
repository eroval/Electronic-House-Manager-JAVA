package entity;


import IdClasses.TaxesHistoryId;
import configuration.ConfigNames;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@IdClass(TaxesHistoryId.class)
@Table(name= ConfigNames.TaxesHistory.Table)
public class TaxesHistory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_generator_taxhistories")
    @Column(name = ConfigNames.TaxesHistory.IDTax, nullable = false)
    private long taxId;

    @Id
    @Column(name = ConfigNames.TaxesHistory.IDApartment, nullable = false)
    private long apartmentId;

    @Id
    @Column(name=ConfigNames.TaxesHistory.IDBuilding, nullable = false)
    private long buildingId;

    @Column(name=ConfigNames.TaxesHistory.Amount, nullable = true)
    private double amount;

    @Column(name=ConfigNames.TaxesHistory.Paid, nullable = false)
    private boolean paid;

    @Column(name=ConfigNames.TaxesHistory.Date, nullable = false)
    private Timestamp date;

    @PrePersist
    protected void onCreate(){
        this.amount = calculatePrice();
        this.date = new Timestamp(new Date().getTime());
    }

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = ConfigNames.TaxesHistory.IDApartment, referencedColumnName = ConfigNames.Apartment.IdApp, updatable = false, insertable = false),
            @JoinColumn(name = ConfigNames.TaxesHistory.IDBuilding, referencedColumnName = ConfigNames.Apartment.IdBuilding, updatable = false, insertable = false)
    })
    private Apartment apartment;

    public TaxesHistory(){

    }

    public TaxesHistory(long taxId, long apartmentId, long buildingId, boolean paid){
        this.setTaxId(taxId);
        this.apartmentId=apartmentId;
        this.buildingId=buildingId;
        this.paid=paid;
    }

    public TaxesHistory(long apartmentId, long buildingId, boolean paid){
        this.apartmentId=apartmentId;
        this.buildingId=buildingId;
        this.paid=paid;
    }

    public void setTaxId(long taxId){
        if(taxId<=0) throw new IllegalArgumentException("Id for tax must be positive");
        this.taxId=taxId;
    }

    private double calculatePrice(){
        return 0;
    }

    public long getTaxId(){return this.taxId;}

    public long getApartmentId(){return this.apartmentId;}

    public long getBuildingId(){return this.buildingId;}

    public boolean getPaid(){return this.paid;}

    public Timestamp getDate(){return this.date;}
}