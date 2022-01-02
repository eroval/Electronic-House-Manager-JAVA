package IdClasses;

import java.io.Serializable;
import java.util.Objects;

public class VeryVerySpecialId implements Serializable {
    protected String personId;
    protected Long familyId;

    public VeryVerySpecialId(){

    }

    public VeryVerySpecialId(String personId, Long familyId){
        this.personId=personId;
        this.setFamilyId(familyId);
    }

    public VeryVerySpecialId(String personId, long familyId ){
        this.personId=personId;
        this.setFamilyId(familyId);
    }

    private void setFamilyId(Long familyId) { this.familyId=familyId;}
    private void setFamilyId(long familyId) { this.familyId = Long.valueOf(familyId);}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VeryVerySpecialId that = (VeryVerySpecialId) o;
        return Objects.equals(personId, that.personId) && Objects.equals(familyId, that.familyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, familyId);
    }
}
