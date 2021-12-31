package entity;

import configuration.ConfigNames;
import special_validators.egn_validator;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name= ConfigNames.Person.Table)
public class Person {
    @Id
    @Column(name=ConfigNames.Person.Id, nullable = false)
    private String personId;

    @Column(name=ConfigNames.Person.FName, nullable = false)
    private String fName;

    @Column(name = ConfigNames.Person.LName, nullable = false)
    private String lName;

    @Column(name = ConfigNames.Person.Birthday, nullable = false)
    private Date birthday;

    @OneToOne (mappedBy = ConfigNames.Person.Table, fetch = FetchType.LAZY)
    private Family family;

//    @OneToOne (mappedBy = ConfigNames.Person.Table, fetch = FetchType.LAZY)
//    private Landlord landlord;

    public Person(){

    }

    public Person(String personId, String fName, String lName, Date birthday){
        this.setPersonId(personId);
        this.setFName(fName);
        this.setLName(lName);
        this.setBirthday(birthday);
    }

    public Person(String personId, String fName, String lName, String birthday){
        this.setPersonId(personId);
        this.setFName(fName);
        this.setLName(lName);
        this.setBirthday(birthday);
    }

    private void setPersonId(String personId){
        if(!egn_validator.checkId(personId)) throw new IllegalArgumentException("EGN is incorrect");
        this.personId=personId;
    }

    private void setFName(String fName){
        if(fName.isBlank()) throw new IllegalArgumentException("First name cannot be blank!");
        this.fName=fName;
    }

    private void setLName(String lName){
        if(lName.isBlank()) throw new IllegalArgumentException("Last name cannot be blank!");
        this.lName=lName;
    }

    private void setBirthday(Date birthday){
        this.birthday=birthday;
    }

    private void setBirthday(String birthday){
        try{
            //yyyy-MM-dd
            this.birthday = new SimpleDateFormat("dd/MM/yyyy").parse(birthday);
//            this.birthday = Date.valueOf(birthday);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            throw new IllegalArgumentException("Invalid date format.");
        }
    }

    public String getPersonId(){return this.personId;}

    public String getFName(){return this.fName;}

    public String getLName(){return this.lName;}

    public String getBirthday(){ return this.birthday.toString(); }

    @Override
    public String toString() {
        return "Person{" +
                "personId='" + personId + '\'' +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", birthday=" + birthday.toString() +
                '}';
    }
}
