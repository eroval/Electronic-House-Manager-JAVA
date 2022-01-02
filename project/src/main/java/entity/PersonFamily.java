package entity;

import IdClasses.VeryVerySpecialId;
import configuration.ConfigNames;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name= ConfigNames.PersonFamily.Table)
@IdClass(VeryVerySpecialId.class)
public class PersonFamily implements Serializable {

    @Id
    @Column(name=ConfigNames.PersonFamily.IDPerson, nullable = false)
    private String personId;

    @Id
    @Column(name=ConfigNames.PersonFamily.IDFamily, nullable = false)
    private Long familyId;

    @OneToOne
    @JoinColumn(name=ConfigNames.PersonFamily.IDPerson, referencedColumnName = ConfigNames.Person.Id, insertable = false, updatable = false)
    private Person person;

    @ManyToOne
    @JoinColumn(name = ConfigNames.PersonFamily.IDFamily, referencedColumnName = ConfigNames.Family.Id, insertable = false, updatable = false)
    private Family family;

    public PersonFamily(){
    }

    public PersonFamily(String personId, Long familyId){
        this.setPersonId(personId);
        this.setFamilyId(familyId);
    }

    public PersonFamily(String personId,long familyId){
        this.setPersonId(personId);
        this.setFamilyId(familyId);
    }

    private void setPersonId(String personId){this.personId=personId;}
    private void setFamilyId(long familyId){this.familyId=Long.valueOf(familyId);}
    private void setFamilyId(Long familyId){this.familyId=familyId;}

    public long getFamilyId(){return this.familyId.longValue();}
    public String getPersonId(){return this.personId;}

    @Override
    public String toString() {
        return "PersonFamily{" +
                "personId='" + personId + '\'' +
                ", familyId=" + familyId +
                '}';
    }
}
