package entity;


import IdClasses.TaxesHistoryId;
import configuration.ConfigNames;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.*;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@IdClass(TaxesHistoryId.class)
@Table(name= ConfigNames.TaxesHistory.Table)
public class TaxesHistory implements Serializable {
    @Id
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
        this.date = new Timestamp(new Date().getTime());
        saveToFile(this.date);
    }

    @PreUpdate
    protected  void onUpdate(){
        saveToFile(this.date);
    }

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = ConfigNames.TaxesHistory.IDApartment, referencedColumnName = ConfigNames.Apartment.IdApp, updatable = false, insertable = false),
            @JoinColumn(name = ConfigNames.TaxesHistory.IDBuilding, referencedColumnName = ConfigNames.Apartment.IdBuilding, updatable = false, insertable = false)
    })
    private Apartment apartment;

    public TaxesHistory(){

    }

    public TaxesHistory(long taxId, long apartmentId, long buildingId,double amount, boolean paid){
        this.setTaxId(taxId);
        this.apartmentId=apartmentId;
        this.buildingId=buildingId;
        this.amount=amount;
        this.paid=paid;
    }

    public void setTaxId(long taxId){
        if(taxId<=0) throw new IllegalArgumentException("Id for tax must be positive");
        this.taxId=taxId;
    }

    public long getTaxId(){return this.taxId;}

    public long getApartmentId(){return this.apartmentId;}

    public long getBuildingId(){return this.buildingId;}

    public double getAmount(){return this.amount;}

    public boolean getPaid(){return this.paid;}

    public Timestamp getDate(){return this.date;}

    @Override
    public String toString() {
        return "TaxesHistory{" +
                "taxId=" + taxId +
                ", apartmentId=" + apartmentId +
                ", buildingId=" + buildingId +
                ", amount=" + amount +
                ", paid=" + paid +
                '}';
    }

    private void saveToFile(Timestamp date){
        if(this.paid==true) {
            String directoryName = TaxesHistory.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "/";
            String fileName = String.valueOf(this.getBuildingId()) + String.valueOf(this.getApartmentId()) + date.getTime() + ".txt";

            File directory = new File(directoryName);
            if (!directory.exists()) {
                directory.mkdir();
            }

            File file = new File(directoryName + fileName);
            try {
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(this.toString());
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
