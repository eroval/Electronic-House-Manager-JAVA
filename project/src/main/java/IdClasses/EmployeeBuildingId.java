package IdClasses;


import java.io.Serializable;
import java.util.Objects;

public class EmployeeBuildingId implements Serializable {
    protected long buildingId;
    protected long employeeId;

    public EmployeeBuildingId(){

    }

    public EmployeeBuildingId(long buildingId, long employeeId){
        this.buildingId=buildingId;
        this.employeeId=employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeBuildingId that = (EmployeeBuildingId) o;
        return buildingId == that.buildingId && employeeId == that.employeeId;
    }


    @Override
    public int hashCode() {
        return Objects.hash(buildingId, employeeId);
    }
}
