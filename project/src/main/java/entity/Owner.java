package entity;

import special_validators.egn_validator;

import javax.persistence.*;

@Entity
@Table(name="owner")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="owner_id", nullable = false)
    private int id;

    @Column(name="egn", nullable = false)
    private String egn;

    @Column(name="f_name", nullable = false)
    private String fName;

    @Column(name="l_name", nullable = false)
    private String lName;

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


    private void setId(int id){
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

    public int getId(){return this.id;}

    public String getEgn() {return this.egn;}

    public String getFName(){
        return this.fName;
    }

    public String getLName(){
        return this.lName;
    }
}
