package entity;

import special_validators.egn_validator;

import javax.persistence.Column;
import javax.persistence.Id;

public class Owner {

    @Id
    private String ownerId;

    @Column(name="f_name", nullable = false)
    private String fName;

    @Column(name="l_name", nullable = false)
    private String lName;

    Owner(String ownerId, String fName, String lName){
        this.setOwnerId(ownerId);
        this.setFName(fName);
        this.setLName(lName);
    }


    private void setOwnerId(String ownerId){
        if(!egn_validator.checkId(ownerId)) throw new IllegalArgumentException("Owner id is incorrect!");
        this.ownerId=ownerId;
    }

    private void setFName(String fName){
        if(fName.isBlank()) throw new IllegalArgumentException("First name cannot be blank!");
        this.fName=fName;
    }

    private void setLName(String lName){
        if(lName.isBlank()) throw new IllegalArgumentException("Last name cannot be blank!");
        this.lName=lName;
    }

    public String getOwnerId(){
        return this.ownerId;
    }

    public String getFName(){
        return this.fName;
    }

    public String getLName(){
        return this.lName;
    }
}
