package IdClasses;

import java.io.Serializable;
import java.util.Objects;

public class CustomEmployeeId implements Serializable {
    protected long ownerId;
    protected long companyId;

    public CustomEmployeeId(){
    }

    public CustomEmployeeId(long ownerId, long companyId){
        this.ownerId=ownerId;
        this.companyId=companyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomEmployeeId that = (CustomEmployeeId) o;
        return ownerId == that.ownerId && companyId == that.companyId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerId, companyId);
    }
}
