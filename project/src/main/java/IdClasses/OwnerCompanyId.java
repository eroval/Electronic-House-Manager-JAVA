package IdClasses;

import java.io.Serializable;
import java.util.Objects;

public class OwnerCompanyId implements Serializable {
    protected long companyId;
    protected long ownerId;

    public OwnerCompanyId(){

    }

    public OwnerCompanyId(long companyId,long ownerId ){
        this.companyId=companyId;
        this.ownerId=ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnerCompanyId that = (OwnerCompanyId) o;
        return companyId == that.companyId && ownerId == that.ownerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, ownerId);
    }
}
