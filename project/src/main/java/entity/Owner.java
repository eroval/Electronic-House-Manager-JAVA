package entity;

import configuration.ConfigNames;
import special_validators.egn_validator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name= ConfigNames.Owner.Table)
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name=ConfigNames.Owner.Id, nullable = false)
    private long id;

    @Column(name=ConfigNames.Owner.EGN, nullable = false)
    private String egn;

    @Column(name=ConfigNames.Owner.FName, nullable = false)
    private String fName;

    @Column(name=ConfigNames.Owner.LName, nullable = false)
    private String lName;

    @OneToMany (mappedBy = ConfigNames.Owner.Table)
    private List<OwnerCompany> owner_company;

    public Owner(){

    }

    public Owner(String egn, String fName, String lName){
        this.setEGN(egn);
        this.setFName(fName);
        this.setLName(lName);
    }

    public Owner(int ownerId, String egn, String fName, String lName){
        this.setId(ownerId);
        this.setEGN(egn);
        this.setFName(fName);
        this.setLName(lName);
    }

    public void loadNameFromObject(Owner owner){
        this.setFName(owner.getFName());
        this.setLName(owner.getLName());
    }

    public void loadFromObject(Owner owner){
        this.setEGN(owner.getEgn());
        this.setFName(owner.getFName());
        this.setLName(owner.getLName());
    }


    private void setId(long id){
        if(id<=0) throw new IllegalArgumentException("Id for company must be positive");
        this.id=id;
    }

    private void setEGN(String egn){
        if(!egn_validator.checkId(egn)) throw new IllegalArgumentException("Owner id is incorrect!");
        this.egn=egn;
    }

    private void setFName(String fName){
        if(fName.isBlank()) throw new IllegalArgumentException("First name cannot be blank!");
        this.fName=fName;
    }

    private void setLName(String lName){
        if(lName.isBlank()) throw new IllegalArgumentException("Last name cannot be blank!");
        this.lName=lName;
    }

    public long getId(){return this.id;}

    public String getEgn() {return this.egn;}

    public String getFName(){
        return this.fName;
    }

    public String getLName(){
        return this.lName;
    }
}
