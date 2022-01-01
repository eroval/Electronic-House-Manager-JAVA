package entity;

import configuration.ConfigNames;

import javax.persistence.*;

@Entity
@Table(name= ConfigNames.Family.Table)
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_generator_families")
    @Column(name=ConfigNames.Family.Id, nullable = false)
    private long familyId;

    @Column(name = ConfigNames.Family.Pet, nullable = false)
    private boolean pet;

    @OneToOne(mappedBy = ConfigNames.Family.Table, fetch = FetchType.LAZY)
    private PersonFamily person_family;

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
