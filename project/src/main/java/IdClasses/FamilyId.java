package IdClasses;

import entity.Family;

import java.io.Serializable;
import java.util.Objects;

public class FamilyId implements Serializable {
    protected Long familyId;
    protected String personId;

    public FamilyId(){

    }

    public FamilyId(Long familyId, String personId){
        this.setFamilyId(familyId);
        this.personId=personId;
    }

    public FamilyId(long familyId, String personId){
        this.setFamilyId(familyId);
        this.personId=personId;
    }

    private void setFamilyId(Long familyId) { this.familyId=familyId;}
    private void setFamilyId(long familyId) {this.familyId = Long.valueOf(familyId);}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FamilyId familyId1 = (FamilyId) o;
        return familyId.equals(familyId1.familyId) && personId.equals(familyId1.personId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(familyId, personId);
    }
}
