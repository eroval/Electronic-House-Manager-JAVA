package IdClasses;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.util.Objects;

public class ApartmentId implements Serializable {
    protected long apartmentId;
    protected long buildingId;

    public ApartmentId(){

    }
    public ApartmentId(long buildingId){
        this.buildingId=buildingId;
    }

    public ApartmentId(long apartmentId, long buildingId){
        this.apartmentId=apartmentId;
        this.buildingId=buildingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApartmentId that = (ApartmentId) o;
        return apartmentId == that.apartmentId && buildingId == that.buildingId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(apartmentId, buildingId);
    }
}
