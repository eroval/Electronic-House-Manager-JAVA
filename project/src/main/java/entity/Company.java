package entity;

import javax.persistence.*;

@Entity
@Table(name="company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int company_id;

    @Column(name="name", nullable = false)
    private String name;


    @Column(name="address", nullable = false)
    private String address;

    public Company(){
        System.out.println("Wadup");
    }

    public Company(int id, String name, String address){
        this.setId(id);
        this.setCompanyName(name);
        this.setCompanyAddress(address);
    }

    private void setId(int id){
        if(id<=0) throw new IllegalArgumentException("Id for company must be positive");
        this.company_id=id;
    }

    private void setCompanyName(String name){
        if(name.isBlank()) throw new IllegalArgumentException("Name of company cannot be blank!");
        this.name=name;
    }

    private void setCompanyAddress(String address){
        if(address.isBlank()) throw new IllegalArgumentException("Address of company cannot be blank!");
        this.address=address;
    }

    public int  getCompanyId(){
        return this.company_id;
    }

    public String getCompanyName(){
        return this.name;
    }

    public String getCompanyAddress(){
        return this.address;
    }
}
