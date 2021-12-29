package IdClasses;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.util.Objects;

public class EmployeeId implements Serializable {

    @GeneratedValue (strategy = GenerationType.IDENTITY)
    protected long employeeId;

    protected long ownerId;
    protected long companyId;

    public EmployeeId(){
    }

    public EmployeeId(long employeeId, long ownerId, long companyId){
        this.employeeId=employeeId;
        this.ownerId=ownerId;
        this.companyId=companyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeId that = (EmployeeId) o;
        return employeeId == that.employeeId && ownerId == that.ownerId && companyId == that.companyId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, ownerId, companyId);
    }
}