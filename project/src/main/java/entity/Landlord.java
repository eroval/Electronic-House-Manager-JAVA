package entity;

import configuration.ConfigNames;

import javax.persistence.*;

@Entity
@Table(name= ConfigNames.Landlord.Table)
public class Landlord {
    @Id
    @Column(name=ConfigNames.Landlord.Id, nullable = false)
    private String landlordId;

    @OneToOne
    @JoinColumn(name=ConfigNames.Landlord.Id, referencedColumnName = ConfigNames.Person.Id, insertable = false, updatable = false)
    private Person person;

    @OneToOne(mappedBy = ConfigNames.Landlord.Table, fetch = FetchType.LAZY)
    private Apartment apartment;

    public Landlord(){

    }

    public Landlord(String landlordId){
        this.landlordId=landlordId;
    }

    public String getPersonId(){return this.landlordId;}
    public String getLandlordId(){return this.landlordId;}

    @Override
    public String toString() {
        return "Landlord{" +
                "landlordId='" + landlordId + '\'' +
                '}';
    }
}
