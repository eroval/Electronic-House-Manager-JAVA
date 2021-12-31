package IdClasses;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.util.Objects;

public class EmployeeId implements Serializable {
    protected long employeeId;
    protected long companyId;
    protected long ownerId;

    public EmployeeId(){
    }

    public EmployeeId( long companyId, long ownerId){
        this.companyId=companyId;
        this.ownerId=ownerId;
    }

    public EmployeeId(long employeeId, long companyId, long ownerId){
        this.employeeId=employeeId;
        this.companyId=companyId;
        this.ownerId=ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeId that = (EmployeeId) o;
        return employeeId == that.employeeId && companyId == that.companyId && ownerId == that.ownerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, companyId, ownerId);
    }
}