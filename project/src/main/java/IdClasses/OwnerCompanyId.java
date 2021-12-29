package IdClasses;

import java.io.Serializable;
import java.util.Objects;

public class OwnerCompanyId implements Serializable {
    protected long ownerId;
    protected long companyId;

    public OwnerCompanyId(){

    }

    public OwnerCompanyId(long ownerId, long companyId){
        this.ownerId=ownerId;
        this.companyId=companyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnerCompanyId that = (OwnerCompanyId) o;
        return ownerId == that.ownerId && companyId == that.companyId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerId, companyId);
    }
}
