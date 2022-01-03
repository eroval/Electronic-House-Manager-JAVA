package entity;

import configuration.ConfigNames;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name= ConfigNames.Family.Table)
public class Family {
    @Id
    @SequenceGenerator(name="genfam", sequenceName = "generatorfamilies")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generatorfamilies")
    @Column(name=ConfigNames.Family.Id, nullable = false)
    private long familyId;

    @Column(name = ConfigNames.Family.Pet, nullable = false)
    private boolean pet;

    @OneToMany(mappedBy = ConfigNames.Family.Table, fetch = FetchType.LAZY)
    private List<PersonFamily> person_family;

    @OneToOne(mappedBy = ConfigNames.Family.Table, fetch = FetchType.LAZY)
    private Apartment apartment;

    public Family(){

    }

    public Family(boolean pet){
        this.pet=pet;
    }

    public Family(long familyId, boolean pet){

    }

    private void setFamilyId(long familyId){
        if(familyId<=0) throw new IllegalArgumentException("Id for family must be above 0.");
        this.familyId=familyId;
    }

    public long getFamilyId(){ return this.familyId;}
    public boolean getPet(){return this.pet;}

    @Override
    public String toString() {
        return "Family{" +
                "familyId=" + familyId +
                ", pet=" + pet +
                '}';
    }
}
