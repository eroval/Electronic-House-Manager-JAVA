package entity;

import IdClasses.PersonFamilyId;
import configuration.ConfigNames;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass(PersonFamilyId.class)
@Table(name= ConfigNames.PersonFamily.Table)
public class PersonFamily implements Serializable {

    @Id
    @Column(name=ConfigNames.PersonFamily.IDPerson, nullable = false, unique = true)
    private String personId;

    @Id
    @Column(name=ConfigNames.PersonFamily.IDFamily, nullable = false, unique = false)
    private Long familyId;

    @OneToOne
    @JoinColumn(name=ConfigNames.PersonFamily.IDPerson, referencedColumnName = ConfigNames.Person.Id, insertable = false, updatable = false, unique = true)
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

    public String getPersonId(){return this.personId;}
    public long getFamilyId(){return this.familyId.longValue();}
    public Long getFamilyIdLong(){return this.familyId;}

    @Override
    public String toString() {
        return "PersonFamily{" +
                "personId='" + personId + '\'' +
                ", familyId=" + familyId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonFamily that = (PersonFamily) o;
        return personId.equals(that.personId) && familyId.equals(that.familyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, familyId);
    }
}