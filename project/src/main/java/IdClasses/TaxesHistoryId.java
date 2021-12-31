package IdClasses;

import java.io.Serializable;
import java.util.Objects;

public class TaxesHistoryId implements Serializable {
    protected long taxId;
    protected long apartmentId;
    protected long buildingId;

    public TaxesHistoryId(){

    }

    public TaxesHistoryId(long taxId, long apartmentId, long buildingId){
        this.taxId=taxId;
        this.apartmentId=apartmentId;
        this.buildingId=buildingId;
    }

    public TaxesHistoryId(long apartmentId, long buildingId){
        this.apartmentId=apartmentId;
        this.buildingId=buildingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxesHistoryId taxId1 = (TaxesHistoryId) o;
        return taxId == taxId1.taxId && apartmentId == taxId1.apartmentId && buildingId == taxId1.buildingId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(taxId, apartmentId, buildingId);
    }
}
