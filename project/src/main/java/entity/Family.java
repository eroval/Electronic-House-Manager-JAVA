package entity;

import IdClasses.FamilyId;
import configuration.ConfigNames;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(FamilyId.class)
@Table(name= ConfigNames.Family.Table)
public class Family implements Serializable {
    @Id
    @Column(name=ConfigNames.Family.IDFamily, nullable = false)
    private Long familyId;

    @Id
    @Column(name=ConfigNames.Family.IDPerson, nullable = false)
    private String personId;

    @Column(name=ConfigNames.Family.Pet, nullable = false)
    private boolean pet;

    @OneToOne
    @JoinColumn(name=ConfigNames.Family.IDPerson, insertable = false, updatable = false)
    private Person person;

    public Family(){
    }

    public Family(Long familyId, String personId, boolean pet){
        this.setFamilyId(familyId);
        this.setPersonId(personId);
        this.setPet(pet);
    }

    public Family(long familyId, String personId, boolean pet){
        this.setFamilyId(familyId);
        this.setPersonId(personId);
        this.setPet(pet);
    }

    private void setFamilyId(long familyId){this.familyId=Long.valueOf(familyId);}
    private void setFamilyId(Long familyId){this.familyId=familyId;}
    private void setPersonId(String personId){this.personId=personId;}
    private void setPet(boolean pet){this.pet=pet;}

    public long getFamilyId(){return this.familyId.longValue();}
    public String getPersonId(){return this.personId;}
    public boolean getPet(){return this.pet;}

    @Override
    public String toString() {
        return "Family{" +
                "familyId=" + familyId +
                ", personId='" + personId + '\'' +
                ", pet=" + pet +
                '}';
    }
}
