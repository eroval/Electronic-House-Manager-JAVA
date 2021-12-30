package IdClasses;


import java.io.Serializable;
import java.util.Objects;

public class EmployeeBuildingId implements Serializable {
    protected long buildingId;
    protected long employeeId;
    protected long companyId;
    protected long ownerId;

    public EmployeeBuildingId(){

    }

    public EmployeeBuildingId(long buildingId, long employeeId, long companyId, long ownerId){
        this.buildingId=buildingId;
        this.employeeId=employeeId;
        this.companyId=companyId;
        this.ownerId=ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeBuildingId that = (EmployeeBuildingId) o;
        return buildingId == that.buildingId && employeeId == that.employeeId && companyId == that.companyId && ownerId == that.ownerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(buildingId, employeeId, companyId, ownerId);
    }
}
