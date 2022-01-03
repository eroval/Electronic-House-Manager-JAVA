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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((familyId == null) ? 0 : familyId.hashCode());
        result = prime * result + ((personId == null) ? 0 : personId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PersonFamily other = (PersonFamily) obj;
        if (familyId == null) {
            if (other.getFamilyIdLong() != null)
                return false;
        } else if (!familyId.equals(other.getFamilyIdLong()))
            return false;
        if (personId == null) {
            if (other.getPersonId() != null)
                return false;
        } else if (!personId.equals(other.getPersonId()))
            return false;
        return true;
    }
}
