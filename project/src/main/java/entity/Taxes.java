package entity;

import configuration.ConfigNames;

import javax.persistence.*;

@Entity
@Table(name= ConfigNames.Taxes.Table)
public class Taxes{

    @Id
    @Column(name= ConfigNames.Taxes.Id, nullable = false)
    private long buildingId;

    @Column(name=ConfigNames.Taxes.BaseTax, nullable = false)
    private double baseTax;

    @Column(name=ConfigNames.Taxes.AppBaseTax, nullable = false)
    private double appBaseTax;

    @Column(name=ConfigNames.Taxes.AppPetTax, nullable = false)
    private double appPetTax;

    @Column(name=ConfigNames.Taxes.TaxDay, nullable = false)
    private int taxDay;

    @OneToOne
    @JoinColumn (name=ConfigNames.Taxes.Id, updatable = false, insertable = false)
    private entity.Building building;

    public Taxes(){

    }

    public Taxes(long buildingId, double baseTax, double appBaseTax, double appPetTax, int taxDay){
        this.setBuildingId(buildingId);
        this.setBaseTax(baseTax);
        this.setAppBaseTax(baseTax);
        this.setAppPetTax(appPetTax);
        this.setTaxDay(taxDay);
    }


    private void setBuildingId(long id){
        if(id<=0) throw new IllegalArgumentException("Id for building must be positive");
        this.buildingId=id;
    }

    private void setBaseTax(double baseTax){
        if(baseTax<=0) throw new IllegalArgumentException("Base tax must be positive");
        this.baseTax=baseTax;
    }

    private void setAppBaseTax(double appBaseTax){
        if(appBaseTax<=0) throw new IllegalArgumentException("Base apartment tax must be positive");
        this.appBaseTax=appBaseTax;
    }

    private void setAppPetTax(double appPetTax){
        if(appPetTax<=0) throw new IllegalArgumentException("Pet tax must be positive");
        this.appPetTax=appPetTax;
    }

    private void setTaxDay(int taxDay){
        if(taxDay<=0||taxDay>27) throw new IllegalArgumentException("Tax day must be before 27th and at least on 1st");
        this.taxDay=taxDay;
    }

    public long getBuildingId(){return this.buildingId;}

    public double getBaseTax(){return this.baseTax;}

    public double getAppBaseTax(){return this.appBaseTax;}

    public double getAppPetTax(){return this.appPetTax;}

    public double getTaxDay(){return this.taxDay;}

}
