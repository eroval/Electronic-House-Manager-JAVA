package IdClasses;

import entity.PersonFamily;

import java.io.Serializable;
import java.util.Objects;

public class PersonFamilyId implements Serializable {
    protected String personId;
    protected Long familyId;

    public PersonFamilyId(){

    }

    public PersonFamilyId(String personId, Long familyId){
        this.personId=personId;
        this.setFamilyId(familyId);
    }

    public PersonFamilyId(String personId, long familyId ){
        this.personId=personId;
        this.setFamilyId(familyId);
    }

    private void setFamilyId(Long familyId) { this.familyId=familyId;}
    private void setFamilyId(long familyId) { this.familyId = Long.valueOf(familyId);}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonFamilyId that = (PersonFamilyId) o;
        return personId.equals(that.personId) && familyId.equals(that.familyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, familyId);
    }
}
