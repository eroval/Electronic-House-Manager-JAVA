package entity;

import configuration.ConfigNames;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.ObjectInputFilter;

@Entity
@Table(name= ConfigNames.Company.Table)
public class Company {

    @Id
    @SequenceGenerator(name="gencomp", sequenceName = "generatorcompanies")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generatorcompanies")
    @Column(name = ConfigNames.Company.Id, nullable = false)
    private long companyId;

    @Column(name=ConfigNames.Company.Name, nullable = false)
    private String name;


    @Column(name=ConfigNames.Company.Address, nullable = false)
    private String address;

    @OneToOne (mappedBy = ConfigNames.Company.Table, fetch = FetchType.LAZY)
    private OwnerCompany owner_company;


    public Company(){

    }

    public Company(String name, String address){
        this.setCompanyName(name);
        this.setCompanyAddress(address);
    }

    public Company(long id, String name, String address){
        this.setId(id);
        this.setCompanyName(name);
        this.setCompanyAddress(address);
    }

    private void setId(long  id){
        if(id<=0) throw new IllegalArgumentException("Id for company must be positive");
        this.companyId=id;
    }

    private void setCompanyName(String name){
        if(name.isBlank()) throw new IllegalArgumentException("Name of company cannot be blank!");
        this.name=name;
    }

    private void setCompanyAddress(String address){
        if(address.isBlank()) throw new IllegalArgumentException("Address of company cannot be blank!");
        this.address=address;
    }

    public long getCompanyId(){
        return this.companyId;
    }

    public String getCompanyName(){
        return this.name;
    }

    public String getCompanyAddress(){
        return this.address;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
